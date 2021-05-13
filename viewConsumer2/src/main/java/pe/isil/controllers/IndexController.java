package pe.isil.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pe.isil.model.AuthenticationRequest;
import pe.isil.model.AuthenticationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller	
public class IndexController {
	
	
	@Autowired
    RestTemplate restTemplate;
	
	Logger log = LoggerFactory.getLogger(IndexController.class);
	
	private static final String ADD_AUT_URL = "http://localhost:8081/jobs/auth/authenticate";
	
    private HttpHeaders getHeaders() {
		//String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc2lsIiwiaWF0IjoxNjA2MzE3MTAxLCJleHAiOjE2MDYzMzUxMDF9.31i80CVBEnMKahGEsRSi15lFShxTO_yyqCxQaxJLZYU";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", "Bearer "+accessToken);
		return headers;
	}

	/* @GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("login", login);
		return "login";
	} */
	
	@RequestMapping(value = "/")
	public String index(Map<String, Object> model) {

		AuthenticationRequest login = new AuthenticationRequest();
		model.put("login", login);
		model.put("titulo", "Formulario de login");
		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated AuthenticationRequest requestAut, BindingResult result, Model model, SessionStatus status) {

       
        log.info("send message:  "+requestAut.getUsername()+"--"+requestAut.getPassword());	   


        HttpHeaders headers = getHeaders();
		HttpEntity<AuthenticationRequest> request = new HttpEntity<>(requestAut, headers);
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Login");
			return "/";
		}
		
		AuthenticationResponse authenticationResponse = restTemplate.postForObject(ADD_AUT_URL, request, AuthenticationResponse.class);
		 //Client clientResponse = restTemplate.postForObject(ADD_CLIENT_URL, request, Client.class);
		 
		//ResponseEntity<AuthenticationResponse> authenticationResponse = restTemplate.exchange(ADD_AUT_URL, HttpMethod.POST, request, AuthenticationResponse.class);				
		
		log.info("send message:  "+authenticationResponse.getJwt());	   
		
		//System.out.println(authenticationResponse.getStatusCode().equals(HttpStatus.OK));
		
		/* AuthenticationResponse result2 = new AuthenticationResponse();
		
		result2.setJwt(authenticationResponse.getJwt()); */
		
		status.setComplete();
		return "redirect:listarAviso/"+authenticationResponse.getJwt();
	}
	
}
