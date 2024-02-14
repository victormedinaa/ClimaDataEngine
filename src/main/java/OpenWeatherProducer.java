import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.google.gson.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class OpenWeatherProducer {
    private final HttpClient httpClient;
    private final HttpRequest request;
    private final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private final String TOPIC = "sensor.Weather";
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=madrid&appid=571acc31657a43c79187ce032c7cbcbe";

    private Connection connection;
    private MessageProducer producer;
    private Session session;

    public OpenWeatherProducer() {
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(API_URL))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(TOPIC);
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private String getWeatherData() {
        System.out.println("\n *** PetitionOpenWeatherMap service");
        Instant instant = Instant.now();
        String ts = DateTimeFormatter.ISO_INSTANT.format(instant);
        System.out.println("*** Fecha y hora : " + ts + " ***\n");

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("*** ResponseOpenWeatherMap ***");
            System.out.println(response.body() + "\n");

            JsonObject js = new Gson().fromJson(response.body(), JsonObject.class);
            JsonObject main = js.get("main").getAsJsonObject();
            JsonObject wind = js.get("wind").getAsJsonObject();

            Weather weather = new Weather(
                    ts,
                    js.get("coord").getAsJsonObject().get("lat").getAsDouble(),
                    js.get("coord").getAsJsonObject().get("lon").getAsDouble(),
                    js.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString(),
                    wind.get("speed").getAsDouble(),
                    wind.get("deg").getAsInt(),
                    main.get("temp").getAsDouble(),
                    main.get("pressure").getAsInt(),
                    main.get("humidity").getAsInt()
            );

            return new Gson().toJson(weather);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void feedAndSend() throws JMSException {
        TextMessage message = session.createTextMessage(getWeatherData());
        producer.send(message);
    }
}
