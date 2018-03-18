package application.producer;

import endpoint.EndPoint;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class Producer extends EndPoint {

    public Producer(String endPoint) throws IOException {
        super(endPoint);
    }

    public void sendMessage(Serializable object) throws IOException{
        channel.basicPublish("", endPoint, null, SerializationUtils.serialize(object));
    }
}
