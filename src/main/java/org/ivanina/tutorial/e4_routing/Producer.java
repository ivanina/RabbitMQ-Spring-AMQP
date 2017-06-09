package org.ivanina.tutorial.e4_routing;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String EXCHANGE_NAME = "direct_tasks";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Queue<Pair<String, String>> tasks = new ArrayDeque<Pair<String, String>>() {{
            add(new Pair<>("BLACK", "Task for BLACK magician #1"));
            add(new Pair<>("WIGHT", "Task for WIGHT magician #2"));
            add(new Pair<>("COMMON", "COMMON task #3"));
            add(new Pair<>("BLACK", "Task for BLACK magician #4"));
            add(new Pair<>("COMMON", "COMMON task #5"));
            add(new Pair<>("WIGHT", "Task for WIGHT magician #6"));
            add(new Pair<>("BLACK", "Task for BLACK magician #7"));
        }};


        // -1- as usual
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // -2- declare exchanger
        channel.exchangeDeclare(
                EXCHANGE_NAME,  // <-- set exchange name
                "direct"    // <-- set type of exchange
        );

        // -3-
        while (tasks.size() > 0) {
            Pair<String,String> task = tasks.remove();

            // We do not know about queue or recipients
            // We send task with marker: routing key
            channel.basicPublish(
                    EXCHANGE_NAME,      // <-- push to specific exchanger
                    task.getKey(),      // <-- set specific routing key
                    null,
                    task.getValue().getBytes()
            );

            System.out.println("[x] Sent: '" + task.getValue() + "'");
            Thread.sleep(1000);
        }

        // -4-
        channel.close();
        connection.close();

    }

}
