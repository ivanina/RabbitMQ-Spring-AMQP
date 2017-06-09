package org.ivanina.tutorial.e4_routing;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WightMagician extends Magician {
    private static final String[] ROUTING_KEYS = {"WIGHT","COMMON"};

    public WightMagician() throws IOException, TimeoutException {
        runner( ROUTING_KEYS );
    }

    @Override
    String getMagicianType() {
        return "Wight";
    }
}
