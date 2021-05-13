package pe.isil.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import pe.isil.model.Client;

@Service
public class ClientEventService {

    Logger log = LoggerFactory.getLogger(ClientEventService.class);

    private final static String clientQueueNames = "client.queue";

    private final ObjectMapper objectMapper;

    public ClientEventService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = clientQueueNames)
    public void receiveEvent(String clientString){
//        log.info("receive clientString < "+clientString+" > to "+clientQueueNames);
        try {
            Client client = objectMapper.readValue(clientString, Client.class);
            log.info("receive client < "+client+">");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
