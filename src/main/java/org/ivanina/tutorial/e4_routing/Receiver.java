package org.ivanina.tutorial.e4_routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    public static void main(String[] args) throws IOException, TimeoutException {
        // start 2 workers: wight and black magicians
        new BlackMagician();
        new WightMagician();
    }
}
