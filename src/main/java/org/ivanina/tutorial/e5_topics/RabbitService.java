package org.ivanina.tutorial.e5_topics;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitService extends AbstractService {
    private String animalType = "Rabbit Service";

    public  RabbitService() throws IOException, TimeoutException {
        runner(new String[]{"*.*.rabbit"});
    }
    @Override
    String getAnimalType() {
        return animalType;
    }
}
