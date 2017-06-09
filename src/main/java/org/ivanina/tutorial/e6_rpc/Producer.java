package org.ivanina.tutorial.e6_rpc;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static final String QUEUE_NAME = "rpc_queue_1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Integer calculateFor = 40;  // input

        String corrId = UUID.randomUUID().toString(); // identification correlation Id

        // -1- as standard
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // -2- get channel name for basicConsume and for BasicProperties
        String  replyQueueName = channel.queueDeclare().getQueue();


        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName) // <-- Set where to send the response
                .build();

        channel.basicPublish(
                "",
                QUEUE_NAME,
                props,          // prepared BasicProperties
                calculateFor.toString().getBytes("UTF-8")  // message
        );

        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });

        System.out.println("RESULT: "+response.take());

        channel.close();
        connection.close();
    }
}
