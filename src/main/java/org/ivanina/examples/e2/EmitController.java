package org.ivanina.examples.e2;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmitController {

    Logger logger = Logger.getLogger(EmitController.class);

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/queue")
    @ResponseBody
    String queue1() {
        logger.info("Emit to queue");
        for(int i = 0;i<10;i++)
            template.convertAndSend("query-example-2","This is simple Message " + i);
        logger.info("Emitted");
        return "Emit to queue";
    }
}
