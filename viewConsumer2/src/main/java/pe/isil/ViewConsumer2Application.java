package pe.isil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ViewConsumer2Application {

	public static void main(String[] args) {
		SpringApplication.run(ViewConsumer2Application.class, args);
	}
	
	@Bean
    public RestTemplate createRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
