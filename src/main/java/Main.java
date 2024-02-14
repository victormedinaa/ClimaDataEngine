import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        OpenWeatherProducer owp = new OpenWeatherProducer();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    owp.feedAndSend();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Problemas al realizar la petición");
                }
            }
        };

        timer.schedule(task, 10, 3000 /*1000 * 60 * 15*/);
    }
}
