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

import pe.isil.model.Notice;
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
public class AvisoController {
	
	
	@Autowired
    RestTemplate restTemplate;
	
	Logger log = LoggerFactory.getLogger(AvisoController.class);
	
	private static final String LIST_AVISO_URL = "http://localhost:8081/jobs/api/notices/all";
	private static final String DELETE_AVISO_URL = "http://localhost:8081/jobs/api/notices/delete/";
	private static final String ADD_AVISO_URL = "http://localhost:8081/jobs/api/notices/save";
	
    private HttpHeaders getHeaders(String token) {
		String accessToken = token;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+accessToken);
		return headers;
	}  
	
	
	@GetMapping("/listarAviso/{token}")
	public String listarAviso(Model model,@PathVariable(value="token") String token) {
		
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		
		List<?> miLista = null;
		
		ResponseEntity<List> userListRE = restTemplate.exchange(LIST_AVISO_URL,HttpMethod.GET ,jwtEntity,List.class);
	     if(userListRE.getStatusCode().equals(HttpStatus.OK)){
	        	 miLista = userListRE.getBody();	
	    }
		 
	     model.addAttribute("listAvisos",miLista);
		 model.addAttribute("token",token);
	     return "listarAviso";
	}
	
	@RequestMapping("eliminarAviso/{id}/{token}")
	public String eliminarAviso(@PathVariable(value="id") Long id, @PathVariable(value="token") String token) {
		HttpHeaders headers = getHeaders(token);
		HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
		ResponseEntity<List> eliminarListRE =restTemplate.exchange(DELETE_AVISO_URL + Long.toString(id),HttpMethod.GET , jwtEntity,List.class);
		return "redirect:/listarAviso/"+token;
	}
	
	
	@RequestMapping(value = "/formAviso/{token}")
	public String crear(Map<String, Object> model, @PathVariable(value="token") String token) {

		Notice aviso = new Notice();
		model.put("aviso", aviso);
		model.put("token", token);
		model.put("titulo", "Formulario de aviso");
		return "formAviso";
	}
	
	
	@RequestMapping(value = "/formAviso/{token}", method = RequestMethod.POST)
	public String guardar(@Validated Notice aviso, 
	                       BindingResult result, 
						   Model model, 
						   SessionStatus status,
						   @PathVariable(value="token") String token) {

	    log.info("send message:  "+aviso.getContenidoaviso());		

        HttpHeaders headers = getHeaders(token);
		HttpEntity<Notice> request = new HttpEntity<>(aviso, headers);
		
	
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de aviso");
			return "formAviso";
		}
        
		Notice noticeResponse = restTemplate.postForObject(ADD_AVISO_URL, request, Notice.class);
		
		status.setComplete();
		return "redirect:/listarAviso/"+token;
	}  
	

}
