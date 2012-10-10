package net.molchat.web.spring;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Used to group authentication - when user is authenticated in one of web
 * applications, this filter starts authentication process on every other
 * application on the same server.
 * 
 * It uses a cookie, that shows when an authentication process should start.
 * 
 * @author Valentin Markovich
 * 
 */
public class GroupAuthFilter implements Filter, InitializingBean {

//======================================================================================================== Static fields
private static String authCookieName = "AUTHENTICATED";
private static String authCookieValue = "true";
private static boolean processSubdomains = true;


//============================================================================================================= doFilter
/**
 * Checks what to do and goes according to conditions:
 * 
 * If user is NOT authenticated and NO group authentication cookie present -
 * does no actions.<br/>
 * If user is NOT authenticated and group authentication cookie present - throws
 * exception to trigger authentication process.<br/>
 * If user IS authenticated and NO group authentication cookie present - sets
 * group authentication cookie.<br/>
 * If user IS authenticated and group authentication cookie present - does no
 * actions.<br/>
 * 
 */
public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException,
		ServletException {

	// Check if user is authenticated now
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) { // Authenticated

		// Check if cookie present
		if (!hasAuthCookie(request)) {
			// Set cookie
			Cookie authCookie = new Cookie(authCookieName, authCookieValue);
			authCookie.setMaxAge(-1);
			authCookie.setPath("/");
			if (processSubdomains) {
				authCookie.setDomain("." + request.getServerName());
			} else {
				authCookie.setDomain(request.getServerName());
			}
			((HttpServletResponse) response).addCookie(authCookie);
		}
	} else { // Not authenticated

		// Check if cookie present
		if (hasAuthCookie(request)) {
			// Throw an exception to trigger an authentication process
			throw new AccessDeniedException("Please authenticate yourself"); // Needs authorization
		}
	}

	next.doFilter(request, response);
}


//======================================================================================================== hasAuthCookie
/**
 * Checks if group authentication cookie present in a request.
 * 
 * @param request
 *          Current HttpServletRequest
 * @return true if group authentication cookie with any value present.
 */
private boolean hasAuthCookie(ServletRequest request) {

	Cookie[] cookies = ((HttpServletRequest) request).getCookies();

	if ((cookies == null) || (cookies.length == 0)) {
		return false;
	}

	for (Cookie cookie : cookies) {
		if (authCookieName.equals(cookie.getName())) {
			return true;
		}
	}

	return false;
}


//======================================================================================================= Stuff not used
public void afterPropertiesSet() throws Exception {

	// TODO Auto-generated method stub
}


public void init(FilterConfig filterConfig) throws ServletException {

	// Not used in Spring context
}


public void destroy() {

	// Not used in Spring context
}


//==================================================================================================== getAuthCookieName
/**
 * Returns cookie name used as flag "authentication needed".
 * 
 * @return cookie name or null.
 */
public String getAuthCookieName() {

	return authCookieName;
}


//==================================================================================================== setAuthCookieName
/**
 * Sets cookie name used as flag "authentication needed". Should be identical to
 * GroupAuthLogoutHandler.authCookieName.
 * 
 * @param authCookieName
 *          Cookie name
 */
public void setAuthCookieName(String authCookieName) {

	GroupAuthFilter.authCookieName = authCookieName;
}


//================================================================================================== isProcessSubdomains
/**
 * Checks whether cookies cover subdomains of requested domain.
 * 
 * @return true if cookies will be set to .domain.com. Returns false, if cookies
 *         will be set to domain.com
 */
public boolean isProcessSubdomains() {

	return processSubdomains;
}


//================================================================================================= setProcessSubdomains
/**
 * Sets whether cookies cover subdomains of requested domain. Beware! Use false
 * for access by IP (not by domain name).
 * 
 * Default is true.
 * 
 * @param processSubdomains
 *          true - set cookies to .domain.com, false - set them to domain.com
 */
public void setProcessSubdomains(boolean processSubdomains) {

	GroupAuthFilter.processSubdomains = processSubdomains;
}

}
