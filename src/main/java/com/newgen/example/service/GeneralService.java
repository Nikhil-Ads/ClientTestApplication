package com.newgen.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.newgen.example.controller.ExceptionThrower;

@Service
public abstract class GeneralService extends ExceptionThrower{
	
	@Autowired
	protected RestTemplate restTemplate;
	
}
