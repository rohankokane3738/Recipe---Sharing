package com.jerry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping
	public String homeController() {
		
		return "Welcome back the Demon King Emperor";
	}
}
