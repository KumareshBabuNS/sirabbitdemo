package io.pivotal.vgpoccode;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WebtransmitterApplication {
    private static final String SENDER_QUEUE = "vgdemo.inputqueue";

	public static void main(String[] args) {
		SpringApplication.run(WebtransmitterApplication.class, args);
	}

	@RestController
	public static class TransmitterController {
        private static final Logger LOGGER = Logger.getLogger(TransmitterController.class);

        @Autowired
        public TransmitterController(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        private RabbitTemplate rabbitTemplate;

        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String usage() {
            return "Usage: curl http://appurl/<dataid>";
        }

        @RequestMapping(value = "/{key}", method = RequestMethod.GET)
        public String sendMessage(@PathVariable("key") String key) {
            LOGGER.info("Sending message " + key);
            rabbitTemplate.convertAndSend(SENDER_QUEUE, key);
            return "Success! " + key;
        }
    }
}
