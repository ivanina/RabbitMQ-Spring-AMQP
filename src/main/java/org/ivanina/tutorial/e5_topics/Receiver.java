package org.ivanina.tutorial.e5_topics;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    public static void main(String[] args) throws IOException, TimeoutException {
        new OrangeService();
        new RabbitService();
        new LazyService();
        new AnyAnimalsService();
    }
}
