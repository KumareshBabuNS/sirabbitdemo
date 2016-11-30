package io.pivotal.vgpoccode;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransmitterController {
    private static final Logger LOGGER = Logger.getLogger(TransmitterController.class);

    public static final String SENDER_QUEUE = "vgdemo.inputqueue";

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
