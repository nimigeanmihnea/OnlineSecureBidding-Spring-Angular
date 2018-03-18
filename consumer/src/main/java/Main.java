import consumer.ConsumerClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            ConsumerClient consumerClient = new ConsumerClient("queue");
            Thread consumerThread = new Thread(consumerClient);
            consumerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
