import com.google.gson.Gson;

import java.sql.*;

public class WeatherSQLiteStorage implements Storage {

    private String DB_URL;
    private Connection connection;


    private void createTableIfNotExist() {
        String query = "CREATE TABLE IF NOT EXISTS weather (\n"
                + "	ts long,\n"
                + "	lat double,\n"
                + " lon double,\n"
                + "	temperature double,\n"
                + "	pressure double,\n"
                + "	humidity double\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WeatherSQLiteStorage(String dbFileName) {
        DB_URL = "jdbc:sqlite:" + dbFileName ;

        try {
            connection = DriverManager.getConnection(DB_URL);
            if (connection != null) {
                createTableIfNotExist();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void store(String jsonData) {
        Weather weather = new Gson().fromJson(jsonData, Weather.class);

        String query = String.format("INSERT INTO weather " +
                "(ts, lat, lon, temperature, pressure, humidity) " +
                "VALUES (\"%s\", %s, %s, %s, %s, %s);",
                    weather.getTs(),
                    weather.getLat() + "",
                    weather.getLon() + "",
                    weather.getTemp() + "",
                    weather.getPressure() + "",
                    weather.getHumidity() + ""
                );

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
