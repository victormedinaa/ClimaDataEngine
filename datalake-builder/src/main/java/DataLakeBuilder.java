import javax.jms.*;

public class DataLakeBuilder extends WeatherConsumer {
    public static void main (String[] args) throws JMSException {
        String rootDir = "./data-lake";

        if (args.length > 0) {
            rootDir = args[0];
        }

        DataLakeBuilder analytics = new DataLakeBuilder(
                new DataLakeFileStorage(rootDir)
        );

        while (true) { analytics.feed(); }
    }

    public DataLakeBuilder(Storage storage) {
        super(storage);
    }
}
