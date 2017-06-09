package org.ivanina.tutorial.e5_topics;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AnyAnimalsService extends AbstractService {
    private String animalType = "Service for all animals";

    public AnyAnimalsService() throws IOException, TimeoutException {
        runner(new String[]{"#"});
    }

    @Override
    String getAnimalType() {
        return animalType;
    }
}
