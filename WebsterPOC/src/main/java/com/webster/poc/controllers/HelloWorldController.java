package com.webster.poc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(@RequestParam String name) {
		return "welcome "+ name;
	}
}