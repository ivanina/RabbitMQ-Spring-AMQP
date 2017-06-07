package org.ivanina.examples.e3_publish_subscribe;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmitController {
    private Logger logger = Logger.getLogger(EmitController.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit")
    @ResponseBody
    String emit() {
        logger.info("Emit to exchange-example-3");
        template.setExchange("exchange-example-3");
        for(int i = 0; i < 3; i++)
            template.convertAndSend("This is Fanout message #"+(i+1));
        return "Emit to exchange-example-3";
    }
}
