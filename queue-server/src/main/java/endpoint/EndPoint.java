package endpoint;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public abstract class EndPoint {

    protected Channel channel;
    protected Connection connection;
    protected String endPoint;

    public EndPoint(String endPoint) throws IOException{
        this.endPoint = endPoint;

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");

        connection = connectionFactory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(this.endPoint, false, false, false, null);
    }

    public void close() throws IOException{
        this.channel.close();
        this.connection.close();
    }
}
