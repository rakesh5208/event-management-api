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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.learningwithrakesh.EventManagement.dto.ErrorResponse;
import com.learningwithrakesh.EventManagement.dto.ErrorType;
import com.learningwithrakesh.EventManagement.entity.User;
import com.learningwithrakesh.EventManagement.service.UserService;
import com.learningwithrakesh.EventManagement.util.ExcludePathUtil;

@Component
@Order(2)
public class UserFirstTimeLoginFilter implements Filter {
	private static final List<String> excludePaths = Arrays
			.asList(new String[] { "/auth/login", "/users/{id}/change-password" });

	@Autowired
	UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletReq = (HttpServletRequest) request;
		HttpServletResponse httpServletRes = (HttpServletResponse) response;
		if (!ExcludePathUtil.isToExcluede(excludePaths,httpServletReq.getRequestURI())) {
			String accessToken = httpServletReq.getHeader("access-token");
			DecodedJWT decodedJWT = JWT.decode(accessToken);
			String username = decodedJWT.getClaim("username").asString();
			User user = this.userService.getUserbyUsername(username);

			if (user.isHasToResetPassword()) {
				ErrorResponse er = ErrorResponse.GetInstance().withStatus(HttpServletResponse.SC_UNAUTHORIZED)
						.withMessage("User has to changed the current password").withType(ErrorType.FORCE_CHANGED_PASSWORD);
				JSONObject json = new JSONObject(er);
				httpServletRes.setContentType("application/json");
				httpServletRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				PrintWriter writer = httpServletRes.getWriter();
				writer.print(json);
				writer.flush();
				return;
			}
		}
		chain.doFilter(request, response);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
