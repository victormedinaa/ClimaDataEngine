import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.time.ZoneId;

public class DataLakeFileStorage implements Storage {
    private String SENSOR_WEATHER_PATH;

    public DataLakeFileStorage(String dataLakeRootDir) {
        SENSOR_WEATHER_PATH = dataLakeRootDir + "/events/sensor.Weather";
        File weatherDir = new File(dataLakeRootDir + "/events/sensor.Weather");
        if (!weatherDir.exists()){
            weatherDir.mkdirs();
        }
    }

    @Override
    public void store(String jsonData) {
        Weather weather = new Gson().fromJson(jsonData, Weather.class);

        Instant time = Instant.parse(weather.getTs());
        String year = String.valueOf(time.atZone(ZoneId.systemDefault()).getYear());
        String month = String.valueOf (time.atZone (ZoneId.systemDefault()).getMonthValue());
        String day = String.valueOf(time.atZone (ZoneId.systemDefault()).getDayOfMonth());
        String hour = String.valueOf (time.atZone(ZoneId.systemDefault()).getHour());

        String filePath = SENSOR_WEATHER_PATH + "/" + year + month + day + hour + ".json";

        File file = new File(filePath);

        writeJsonData(file, jsonData);
    }

    private void writeJsonData(File f, String jsonData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            bw.append(jsonData + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
