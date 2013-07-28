package com.daydayup.petstore;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.druid.util.DruidWebUtils;
import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import com.daydayup.petstore.service.AccessLogService;

public class AccessLogWebFilter implements Filter {

	public static final String PARAM_NAME_EXCLUSIONS = "exclusions";

	private WebApplicationContext appContext;
	private String contextPath;
	private AccessLogService accessLogService;

	private Set<String> excludesPattern;
	protected PatternMatcher pathMatcher = new ServletPathMatcher();

	@Override
	public void init(FilterConfig config) throws ServletException {
		{
			String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
			if (exclusions != null && exclusions.trim().length() != 0) {
				excludesPattern = new HashSet<String>(Arrays.asList(exclusions
						.split("\\s*,\\s*")));
			}
		}

		ServletContext context = config.getServletContext();
		this.contextPath = context.getContextPath();
		this.appContext = WebApplicationContextUtils
				.getWebApplicationContext(context);

		this.accessLogService = appContext.getBean(AccessLogService.class);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String sessionId = null;
		try {
			chain.doFilter(request, response);
		} finally {
			HttpSession session = httpRequest.getSession(false);
			if (session != null) {
				sessionId = session.getId();
			}
			String page = httpRequest.getServletPath();
			String ua = httpRequest.getHeader("user-agent");
			String ip = DruidWebUtils.getRemoteAddr(httpRequest);
			accessLogService.add(page, sessionId, ip, ua);
		}
	}

	@Override
	public void destroy() {

	}

	public boolean isExclusion(String requestURI) {
		if (excludesPattern == null) {
			return false;
		}

		if (contextPath != null && requestURI.startsWith(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
			if (!requestURI.startsWith("/")) {
				requestURI = "/" + requestURI;
			}
		}

		for (String pattern : excludesPattern) {
			if (pathMatcher.matches(pattern, requestURI)) {
				return true;
			}
		}

		return false;
	}
}
