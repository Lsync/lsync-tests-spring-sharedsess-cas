package net.molchat.test.app.controller;


import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {


/**
 * Application name
 */
public static final String appName = "App01";


/**
 * @param locale
 * @param model
 * @param request
 * @return ---
 */
@RequestMapping(value = "/")
public String home(Locale locale, Model model, HttpServletRequest request) {

	return home(locale, model, request, "home");
}


/**
 * Simply selects the home view to render by returning its name.
 * 
 * @param locale
 * @param model
 * @param request
 * @param page
 * @return Homepage content as text
 */
@RequestMapping(value = "/{page}")
public String home(Locale locale, Model model, HttpServletRequest request, @PathVariable String page) {

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);

	model.addAttribute("serverTime", formattedDate);

	String userName; // Any default user  name
	Principal principal = request.getUserPrincipal();
	if (principal != null) {
		userName = principal.getName();
	} else {
		userName = "Not authenticated";
	}

	model.addAttribute("appName", appName);
	model.addAttribute("userLogin", userName);

	boolean fAdmin = request.isUserInRole("ROLE_ADMIN");
	model.addAttribute("isAdmin", fAdmin);

	boolean fUser = request.isUserInRole("ROLE_USER");
	model.addAttribute("isUser", fUser);
	model.addAttribute("page", page);

	return "home";
}

}
