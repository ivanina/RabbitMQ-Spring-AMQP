package org.ivanina.tutorial.e1_hw;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public final static String Q_NAME = "queue-1";
    public static void main(String[] args) throws IOException, TimeoutException {

        String message = "This is test message from Sender";

        // -1-
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        // -2-
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Q_NAME,false,false,false,null);

        // -3-
        channel.basicPublish("",Q_NAME,null, message.getBytes());

        System.out.println("[x] Sent: '"+message+"'");

        // -4-
        channel.close();
        connection.close();
    }
}
