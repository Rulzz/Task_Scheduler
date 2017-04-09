package com.chatbot.aws;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		/*for(int j=1;j<=5;j++)
		{
			System.out.println("hELoo");
	    p= Math.random();
		if(p<=0.3)
		{}
		else
			
		{}
		}*/
		
		return "home1";
	}
	
	   @RequestMapping(value = "/table", method = RequestMethod.POST)
	    public String getTime() {
		   /*System.out.println("in.........");
	        Random rand = new Random();
	        float r = rand.nextFloat() * 100;
	        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
	        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());*/
	        return "home";
	    }
	
}
