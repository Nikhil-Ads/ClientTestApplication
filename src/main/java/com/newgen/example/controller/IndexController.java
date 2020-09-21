package com.newgen.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@GetMapping
	@RequestMapping(path = {"/","/index*","/login*"})
	public String getIndexPage() {
		return "/index";
	}
		

}
