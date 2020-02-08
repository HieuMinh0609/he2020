package com.hieuminh.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.utils.DisplayTagUtils;
import com.hieuminh.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.utils.DisplayTagUtils;
import com.hieuminh.utils.SecurityUtils;

@Component
public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private static final Logger log = Logger.getLogger(MySuccessHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			log.error(CustomMessages.ERR_NOT_REDIRECT);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public String determineTargetUrl(Authentication authentication) {
		String url = "";
		List<String> roles = SecurityUtils.getAuthorities();
		if (isUser(roles)) {
			url = SystemConstant.HOME_PAGE;
		} else if (isAdmin(roles)) {			
			url = SystemConstant.ADMIN_HOME_PAGE;
		}else{
			url = SystemConstant.HOME_PAGE;
		}
		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains(SystemConstant.ROLE_MANAGER)) {
			return true;
		}
		return false;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains(SystemConstant.ROLE_USER)) {
			return true;
		}
		return false;
	}
}
