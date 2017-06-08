package org.ivanina.tutorial.e2_queues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker {
    public final static String Q_NAME = "queue-2-durable";
    public static void main(String[] args) throws IOException, TimeoutException {

        boolean durable = true;

        // -1-
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        // -2-
        final Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        // with Message acknowledgment
        channel.queueDeclare(
                Q_NAME,
                durable, // <-- !!!
                false,
                false,
                null
        );

        System.out.println(" [*] Waiting for messages.");

        // -3-
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);  // <-- +++ !!!
                }
            }
        };
        boolean autoAck = false;
        channel.basicConsume(Q_NAME, autoAck, consumer);  // IMPORTANT ! autoAck set to 'false'. For simple queue it's 'true'
    }

    public static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}
