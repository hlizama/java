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

import pe.isil.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@Controller
public class ProductoController {
	
	
	@Autowired
    RestTemplate restTemplate;
	
	private static final String LIST_PRODUCT_URL = "http://localhost:8081/jobs/api/products/all";
	private static final String DELETE_PRODUCT_URL = "http://localhost:8081/jobs/api/products/delete/";
	private static final String ADD_PRODUCT_URL = "http://localhost:8081/jobs/api/products/save";
	
    private HttpHeaders getHeaders(String token) {
		String accessToken = token;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+accessToken);
		return headers;
	}  
	
	
	@GetMapping("/listarProducto/{token}")
	public String listarProducto(Model model, @PathVariable(value="token") String token) {
		
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		List<?> miLista = null;
		ResponseEntity<List> userListRE = restTemplate.exchange(LIST_PRODUCT_URL,HttpMethod.GET ,jwtEntity,List.class);
	     
		if(userListRE.getStatusCode().equals(HttpStatus.OK)){
	        	 miLista = userListRE.getBody();	
	    } 
	    model.addAttribute("listProducto",miLista);
		model.addAttribute("token",token);
	    return "listarProducto";
	}
	
	@RequestMapping("eliminarProducto/{id}/{token}")
	public String eliminarProducto(@PathVariable(value="id") Long id, @PathVariable(value="token") String token) {
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		ResponseEntity<List> eliminarListRE =restTemplate.exchange(DELETE_PRODUCT_URL + Long.toString(id),HttpMethod.GET , jwtEntity,List.class);
		return "redirect:/listarProducto/"+token;
	}
	
	
	@RequestMapping(value = "/formProducto/{token}")
	public String crear(Map<String, Object> model, @PathVariable(value="token") String token) {
		Product producto = new Product();
		model.put("producto", producto);
		model.put("token",token);
		model.put("titulo", "Formulario de Producto");
		return "formProducto";
	}
	
	
	@RequestMapping(value = "/formProducto/{token}", method = RequestMethod.POST)
	public String guardar(@Validated Product producto, BindingResult result, Model model, SessionStatus status, @PathVariable(value="token") String token) {

        HttpHeaders headers = getHeaders(token);
		HttpEntity<Product> request = new HttpEntity<>(producto, headers);
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Producto");
			return "formProducto";
		}
        
		Product productResponse = restTemplate.postForObject(ADD_PRODUCT_URL, request, Product.class);
		
		status.setComplete();
		return "redirect:/listarProducto/"+token;
	}
	
	
	

}
