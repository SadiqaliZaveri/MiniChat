package com.minichat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MiniChatController {

	@RequestMapping(value={"/","Home","home"})
	public String HomePage()
	{
		
			return "Home";
	}
	@RequestMapping("/403")
	public String AccessDeniedPage()
	{
		return "403";
	}
	
	@RequestMapping("/FAQ")
	public String FAQPage()
	{
		return "FAQ";
	}
	
	@RequestMapping("/contactus")
	public String ContactUsPage()
	{
		return "ContactUs";
	}
	@RequestMapping("/checkoutsuccess")
	public String checkout()
	{
		return "Home";
	}
	
	@RequestMapping("/404")
	public String ResourceNotFoundPage()
	{
		
		return "404";
	}
	
	@RequestMapping("/aboutus")
	public String AboutUs()
	{
		
		return "AboutUs";
	}

}
