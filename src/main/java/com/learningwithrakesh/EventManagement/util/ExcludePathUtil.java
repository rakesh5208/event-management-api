package com.learningwithrakesh.EventManagement.util;

import java.util.List;

public class ExcludePathUtil {
	public static boolean isToExcluede(List<String> excludeList, String requestedPath){
		for(int i=0;i<excludeList.size();i++){
			if(excludeList.get(i).equals(excludeList) || isTemplatePathEqual(excludeList.get(i), requestedPath)){
				return true;
			}
		}
		return false;
	}
	
	private static boolean isTemplatePathEqual(String excludePath, String requestedPath){
		String[] excludePathSplitted = excludePath.split("/");
		String[] requestedPathSplitted = requestedPath.split("/");
		if(excludePathSplitted.length != requestedPathSplitted.length)
			return false;
		for(int i=0;i<excludePathSplitted.length;i++){
			if(!excludePathSplitted[i].startsWith("{") && !excludePathSplitted[i].equals(requestedPathSplitted[i])){
				return false;
			}
		}
		return true;
	}
}
