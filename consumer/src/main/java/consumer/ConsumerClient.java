package consumer;

import application.entity.Bid;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import endpoint.EndPoint;
import org.apache.commons.lang.SerializationUtils;
import service.MailService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ConsumerClient extends EndPoint implements Runnable, Consumer {

    public ConsumerClient(String endPoint) throws IOException {
        super(endPoint);
    }

    @Override
    public void handleConsumeOk(String s) {

    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {
        System.out.println("Consumer "+ s +" registered");
    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);

        Bid bid = (Bid) map.get("BID");
        String message = "The bid with ID: " + bid.getBidNumber() + " just started! The product is : " + bid.getItem().getProduct() + ". The starting price is : " + bid.getItem().getPrice();

        MailService mailService = new MailService("bookstoreapplication@gmail.com","Bookstore123");
        mailService.sendMail("nimigean.mihnea@gmail.com", "Bid started!", message);
    }

    @Override
    public void run() {
        try {
            channel.basicConsume(endPoint, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
