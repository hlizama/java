package isil.proyecto.intapli.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import isil.proyecto.intapli.domain.Purchase;


@Service
public class ClientEventService {

    Logger log = LoggerFactory.getLogger(ClientEventService.class);

    private final JmsTemplate jmsTemplate;
    private final String clientQueueName;

    public ClientEventService(JmsTemplate jmsTemplate, @Value("${client.queue.name}") String clientQueueName) {
        this.jmsTemplate = jmsTemplate;
        this.clientQueueName = clientQueueName;
    }

    public void sendEvent(Purchase client) {
        try {
			
			 ObjectMapper om = new ObjectMapper();
             om.registerModule(new JavaTimeModule());
             om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
            String clientString = om.writeValueAsString(client);
            log.info("send message < "+clientString+" > to "+clientQueueName);
            jmsTemplate.convertAndSend(clientQueueName, clientString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
