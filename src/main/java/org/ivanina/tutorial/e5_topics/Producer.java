package org.ivanina.tutorial.e5_topics;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static final String EXCHANGE_NAME = "animals_rice";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Queue<Pair<String, String>> animals = new ArrayDeque<Pair<String, String>>() {{
            add(new Pair<>("quick.orange.rabbit", "Quick orange rabbit   [ 1 ]"));
            add(new Pair<>("lazy.brown.fox", "Lazy brown Foz   [ 2 ]"));
            add(new Pair<>("quick.orange.fox", "Quick orange Fox   [ 3 ]"));
            add(new Pair<>("lazy.orange.elephant", "Lazy orange Elephant   [ 4 ]"));
            add(new Pair<>("lazy.green.rabbit","Lazy green Rabbits   [ 5 ]"));
            add(new Pair<>("very.quick.brown.rabbit", "Very quick brown Rabbits   [ 6 ]"));
            add(new Pair<>("fox", "FOX   [ 7 ]"));
        }};


        // -1- as usual
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // -2- declare exchanger
        channel.exchangeDeclare(
                EXCHANGE_NAME,  // <-- set exchange name
                "topic"    // <-- set type of exchange - TOPIC
        );

        // -3-
        while (animals.size() > 0) {
            Pair<String,String> task = animals.remove();

            channel.basicPublish(
                    EXCHANGE_NAME,      // <-- push to specific exchanger
                    task.getKey(),      // <-- set specific routing key
                    null,
                    task.getValue().getBytes()
            );

            System.out.println("[x] Sent: '" + task.getValue() + "'");
            Thread.sleep(500);
        }

        // -4-
        channel.close();
        connection.close();

    }
}
