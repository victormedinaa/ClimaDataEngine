public class Weather {
    private final double lat;
    private final double lon;
    private final String ts;
    private final String weather;
    private final int windDirection;
    private final int humidity;
    private final int pressure;
    private final double wind;
    private final double temp;


    public Weather(String ts, double lat, double lon, String weather, double wind, int windDirection, double temp, int humidity, int pressure) {
        this.ts = ts;
        this.lat = lat;
        this.lon = lon;
        this.weather = weather;
        this.wind = wind;
        this.windDirection = windDirection;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTs() {
        return ts;
    }

    public String getWeather() {
        return weather;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public double getWind() {
        return wind;
    }

    public double getTemp() {
        return temp;
    }
}
