import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Analytics extends WeatherConsumer {

    public static void main (String[] args) throws JMSException {
        String dbPath = "./db-analytics.sqlite";

        if (args.length > 0) {
            dbPath = args[0];
        }

        Analytics analytics = new Analytics(
            new WeatherSQLiteStorage(dbPath)
        );

        while (true) {
            analytics.feed();
        }
    }

    public Analytics(Storage storage) {
        super(storage);
    }


}
