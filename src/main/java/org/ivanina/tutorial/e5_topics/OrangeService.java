package org.ivanina.tutorial.e5_topics;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class OrangeService extends AbstractService {
    private String animalType = "Orange Service";

    public OrangeService() throws IOException, TimeoutException {
        runner(new String[]{"*.orange.*"});
    }

    @Override
    String getAnimalType() {
        return animalType;
    }
}
