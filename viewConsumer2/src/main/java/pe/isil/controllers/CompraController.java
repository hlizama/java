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

import pe.isil.model.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@Controller
public class CompraController {
	
	
	@Autowired
    RestTemplate restTemplate;
	
	private static final String LIST_PURCHASE_URL = "http://localhost:8081/jobs/api/purchases/all";
	private static final String DELETE_PURCHASE_URL = "http://localhost:8081/jobs/api/purchases/delete/";
	private static final String ADD_PURCHASE_URL = "http://localhost:8081/jobs/api/purchases/save";
	
    private HttpHeaders getHeaders(String token) {
		String accessToken = token;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+accessToken);
		return headers;
	}  
	
	
	@GetMapping("/listarCompra/{token}")
	public String listarCompra(Model model, @PathVariable(value="token") String token) {
		
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		List<?> miLista = null;
		ResponseEntity<List> userListRE = restTemplate.exchange(LIST_PURCHASE_URL,HttpMethod.GET ,jwtEntity,List.class);
	     
		if(userListRE.getStatusCode().equals(HttpStatus.OK)){
	        	 miLista = userListRE.getBody();	
	    } 
	    model.addAttribute("listCompra",miLista);
		model.addAttribute("token",token);
	    return "listarCompra";
	}
	
	@RequestMapping("eliminarCompra/{id}/{token}")
	public String eliminarCompra(@PathVariable(value="id") Long id, @PathVariable(value="token") String token) {
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		ResponseEntity<List> eliminarListRE =restTemplate.exchange(DELETE_PURCHASE_URL + Long.toString(id),HttpMethod.GET , jwtEntity,List.class);
		return "redirect:/listarCompra/"+token;
	}
	
	
	@RequestMapping(value = "/formCompra/{token}")
	public String crear(Map<String, Object> model,@PathVariable(value="token") String token) {

		Purchase compra = new Purchase();
		model.put("compra", compra);
		model.put("token",token);
		model.put("titulo", "Formulario de Compra");
		return "formCompra";
	}
	
	
	@RequestMapping(value = "/formCompra/{token}", method = RequestMethod.POST)
	public String guardar(@Validated Purchase compra, BindingResult result, Model model, SessionStatus status,@PathVariable(value="token") String token) {

        HttpHeaders headers = getHeaders(token);
		HttpEntity<Purchase> request = new HttpEntity<>(compra, headers);
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Compra");
			return "formCompra";
		}
        
		Purchase purchaseResponse = restTemplate.postForObject(ADD_PURCHASE_URL, request, Purchase.class);
		
		status.setComplete();
		return "redirect:/listarCompra/"+token;
	}
	
	
	

}
