package org.ivanina.tutorial.e3_publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.TimeoutException;


public class Producer {
    public final static String EX_NAME = "exchange-3";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Queue<String> tasks = new ArrayDeque<String>(){{
            addAll(Arrays.asList(
                    "Task #1 (3) ...",
                    "Task #2 (4) ....",
                    "Task #3 (8) ........",
                    "Task #4 (1) .",
                    "Task #5 (9) .........",
                    "Task #6 (2) ..",
                    "Task #7 (5) .....")
            );
        }};

        // -1-
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        // -2-
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EX_NAME, "fanout");

        // -3-
        while (tasks.size() > 0){
            String msg = tasks.remove();
            channel.basicPublish(
                    EX_NAME,
                    "",
                    null,
                    msg.getBytes()
            );
            System.out.println("[x] Sent: '"+msg+"'");
            Thread.sleep(2000);
        }


        // -4-
        channel.close();
        connection.close();
    }
}
