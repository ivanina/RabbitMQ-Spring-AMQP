package org.ivanina.tutorial.e2_queues;

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
    public final static String Q_NAME = "queue-2-durable";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        boolean durable = true;

        Queue<String> tasks = new ArrayDeque<String>(){{
            addAll(Arrays.asList(
                    "This is task #1 ...",
                    "New  task #2 ....",
                    "Important task #3 ........",
                    "Simple task #4 ....",
                    "Small bug - task #5 .........",
                    "Low priority task #6 ..",
                    "Final task #7 ." )
            );
        }};

        // -1-
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        // -2-
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        // with Message acknowledgment
        channel.queueDeclare(
                Q_NAME,
                durable, // <-- !!!
                false,
                false,
                null
        );

        // -3-
        while (tasks.size() > 0){
            String msg = tasks.remove();
            channel.basicPublish(
                    "",
                    Q_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN, // <-- !!!
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
