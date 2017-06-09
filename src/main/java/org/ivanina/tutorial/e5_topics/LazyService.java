package org.ivanina.tutorial.e5_topics;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LazyService extends AbstractService{
    private String animalType = "Lazy Service";

    public LazyService() throws IOException, TimeoutException {
        runner(new String[]{"lazy.*.*"});
    }
    @Override
    String getAnimalType() {
        return animalType;
    }
}
