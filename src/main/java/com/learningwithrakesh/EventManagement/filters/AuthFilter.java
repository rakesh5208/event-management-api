package com.learningwithrakesh.EventManagement.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.learningwithrakesh.EventManagement.dto.ErrorResponse;
import com.learningwithrakesh.EventManagement.util.ExcludePathUtil;
import com.learningwithrakesh.EventManagement.util.JwtHelper;

@Component
@Order(1)
public class AuthFilter implements Filter {
	private static final List<String> excludePaths = Arrays.asList(new String[]{"/auth/login"});

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletReq = (HttpServletRequest) req;
		HttpServletResponse httpServletRes = (HttpServletResponse) res;
		String requestURI = httpServletReq.getRequestURI();
		ErrorResponse.GetInstance().withPath(requestURI).withType(null).withMessage(null).withStatus(-1);
		if (!ExcludePathUtil.isToExcluede(excludePaths,requestURI)) {
			String accessToken = httpServletReq.getHeader("access-token");
			try {
				if (accessToken == null) {
					this.setUnauthBody(httpServletRes, "access-token header is missing");
					return;
				} else {
					try {
						JwtHelper.verfyToken(accessToken);
					} catch (JWTDecodeException | AlgorithmMismatchException | InvalidClaimException
							| SignatureVerificationException ex) {
						ex.printStackTrace();
						this.setUnauthBody(httpServletRes, "access-token is invalid or tempered");
						return;
					} catch (TokenExpiredException ex) {
						this.setUnauthBody(httpServletRes, "access-token has been expired");
						return;
					}
				}
			} catch (JSONException ex) {
				throw new IllegalArgumentException(ex);
			}
		}
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	private void setUnauthBody(HttpServletResponse httpServletRes, String message) throws IOException, JSONException {
		ErrorResponse er = ErrorResponse.GetInstance().withStatus(HttpServletResponse.SC_UNAUTHORIZED)
				.withMessage(message);
		JSONObject json = new JSONObject(er);
		httpServletRes.setContentType("application/json");
		httpServletRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = httpServletRes.getWriter();
		writer.print(json);
		writer.flush();
	}
}
