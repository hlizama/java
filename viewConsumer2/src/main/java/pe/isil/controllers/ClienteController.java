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

import pe.isil.model.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@Controller
public class ClienteController {
	
	
	@Autowired
    RestTemplate restTemplate;
	
	private static final String LIST_CLIENT_URL = "http://localhost:8081/jobs/api/users/all";
	private static final String DELETE_CLIENT_URL = "http://localhost:8081/jobs/api/users/delete/";
	private static final String ADD_CLIENT_URL = "http://localhost:8081/jobs/api/users/save";
	
    private HttpHeaders getHeaders(String token) {
		String accessToken = token;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+accessToken);
		return headers;
	}  
	
	
	@GetMapping("/listarCliente/{token}")
	public String listarCliente(Model model,@PathVariable(value="token") String token) {
		
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		List<?> miLista = null;
		ResponseEntity<List> userListRE = restTemplate.exchange(LIST_CLIENT_URL,HttpMethod.GET ,jwtEntity,List.class);
	     
		if(userListRE.getStatusCode().equals(HttpStatus.OK)){
	        	 miLista = userListRE.getBody();	
	    } 
	    model.addAttribute("listCliente",miLista);
		model.addAttribute("token",token);
	    return "listarCliente";
	}
	
	@RequestMapping("eliminarCliente/{id}/{token}")
	public String eliminarCliente(@PathVariable(value="id") Long id, @PathVariable(value="token") String token) {
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		ResponseEntity<List> eliminarListRE =restTemplate.exchange(DELETE_CLIENT_URL + Long.toString(id),HttpMethod.GET , jwtEntity,List.class);
		return "redirect:/listarCliente/"+token;
	}
	
	
	@RequestMapping(value = "/formCliente/{token}")
	public String crear(Map<String, Object> model , @PathVariable(value="token") String token) {

		Client cliente = new Client();
		model.put("cliente", cliente);
		model.put("token",token);
		model.put("titulo", "Formulario de cliente");
		return "formCliente";
	}
	
	
	@RequestMapping(value = "/formCliente/{token}", method = RequestMethod.POST)
	public String guardar(@Validated Client cliente, BindingResult result, Model model, SessionStatus status , @PathVariable(value="token") String token) {

        HttpHeaders headers = getHeaders(token);
		HttpEntity<Client> request = new HttpEntity<>(cliente, headers);
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "formCliente";
		}
        
		Client clientResponse = restTemplate.postForObject(ADD_CLIENT_URL, request, Client.class);
		
		status.setComplete();
		return "redirect:/listarCliente/"+token;
	}
	
	
	

}
