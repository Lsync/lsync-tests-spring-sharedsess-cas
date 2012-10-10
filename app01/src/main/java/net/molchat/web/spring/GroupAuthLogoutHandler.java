/**
 * 
 */
package net.molchat.web.spring;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;


/**
 * Used to delete group authentication cookie from user browser.
 * 
 * @author Valentin Markovich
 * 
 */
public class GroupAuthLogoutHandler implements LogoutHandler {

//======================================================================================================== Static fields
private static String authCookieName = "AUTHENTICATED";
private static boolean processSubdomains = true;


//=============================================================================================================== logout
/**
 * Logout handler. Creates cookie with zero life long to delete it from user
 * browser.
 * 
 */
public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

	Cookie authCookie = new Cookie(authCookieName, null); // Empty cookie
	authCookie.setMaxAge(0);
	authCookie.setPath("/");
	authCookie.setDomain("." + request.getServerName());


	response.addCookie(authCookie);
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
 * GroupAuthFilter.authCookieName.
 * 
 * @param authCookieName
 *          Cookie name
 */
public void setAuthCookieName(String authCookieName) {

	GroupAuthLogoutHandler.authCookieName = authCookieName;
}


//================================================================================================== isProcessSubdomains
/**
 * Checks whether cookies cover subdomains of requested domain.
 * 
 * @return true if cookies will be set to .domain.com. Returns false, if cookies
 *         will be set to domain.com
 */
public static boolean isProcessSubdomains() {

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
public static void setProcessSubdomains(boolean processSubdomains) {

	GroupAuthLogoutHandler.processSubdomains = processSubdomains;
}

}
