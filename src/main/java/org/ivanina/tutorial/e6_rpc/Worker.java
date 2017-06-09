package org.ivanina.tutorial.e6_rpc;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker {
    public static final String QUEUE_NAME = "rpc_queue_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(
                QUEUE_NAME,
                false,
                false,
                false,
                null
        );

        System.out.println(" [x] Awaiting RPC requests");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();

                String message = new String(body,"UTF-8");
                int n = Integer.parseInt(message);

                System.out.println(" [.] fib(" + message + ")");
                String response = "";

                // all do here
                response += fib(n);

                System.out.println(" [x] Done. Result '"+response+"' sent");

                // publishing for response result
                channel.basicPublish(
                        "",
                        properties.getReplyTo(),
                        replyProps,
                        response.getBytes("UTF-8")
                );

                channel.basicAck(
                        envelope.getDeliveryTag(),
                        false
                );
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);

    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
