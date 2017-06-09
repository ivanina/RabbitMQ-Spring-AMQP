package org.ivanina.tutorial.e4_routing;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BlackMagician extends Magician {
    private static final String[] ROUTING_KEYS = {"BLACK","COMMON"};

    public BlackMagician() throws IOException, TimeoutException {
        runner( ROUTING_KEYS );
    }

    @Override
    String getMagicianType() {
        return "Black";
    }
}
