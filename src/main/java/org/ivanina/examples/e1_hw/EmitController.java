package org.ivanina.examples.e1_hw;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmitController {
    private Logger logger = Logger.getLogger(EmitController.class);

    private AmqpTemplate template;

    @Autowired
    public EmitController(AmqpTemplate template){
        this.template = template;
    }

    @RequestMapping("/emit")
    @ResponseBody
    String queue1() {
        logger.info("<<-- Emit to queue1");
        template.convertAndSend("queue1","Message to queue #1");
        logger.info("<<-- Emit to queue2");
        template.convertAndSend("queue2","Message to queue #2");
        return "Emit to queue";
    }
}
