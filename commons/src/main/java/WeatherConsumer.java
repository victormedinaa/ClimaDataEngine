import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class WeatherConsumer {
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final String TOPIC = "sensor.Weather";

    private Session session;
    private Connection connection;
    private MessageConsumer consumer;
    private Storage storage;

    public WeatherConsumer(Storage storage) {
        this.storage = storage;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(WeatherConsumer.BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic(TOPIC);
            consumer = session.createConsumer(destination);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected void feed() throws JMSException {
        Message message = getConsumer().receive();

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            getStorage().store(textMessage.getText());
        }
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }

    public Storage getStorage() {
        return storage;
    }
}
