package org.example;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {
    // Copy your API-KEY here
    public final static String apiKey ="9505ab2287754e929da91358230203";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner cityname = new Scanner(System.in);
        String city = cityname.nextLine();
        String answer = getWeatherData(city);

        if (answer == null){

            System.out.println("invalid city");
        }

        else{

            System.out.println("humidity : " + getHumidity(answer));
            System.out.println("temperature : " + getTemperature(answer) + " c");
            System.out.println("wind direction : " + getWindDirection(answer));
            System.out.println("wind speed : " + getWindSpeed(answer)+ " mph");

        }


    }



    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        double temperature = 0.0;
        JSONObject weatherobj = new JSONObject(weatherJson);
        JSONObject currentTemp = weatherobj.getJSONObject("current");
        temperature = currentTemp.getDouble("temp_c");

        return temperature;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string

    public static int getHumidity(String weatherJson){
        int humidity = 0;
        JSONObject weatherobj = new JSONObject(weatherJson);
        JSONObject currentTemp = weatherobj.getJSONObject("current");
        humidity = currentTemp.getInt("humidity");
        return humidity;

    }
    public static String getWindDirection(String weatherJson){

        String direction ="";
        JSONObject weatherobj = new JSONObject(weatherJson);
        JSONObject currentTemp = weatherobj.getJSONObject("current");
        direction = currentTemp.getString("wind_dir");
        return direction;

    }
    public static double getWindSpeed(String weatherJson){

        double speed = 0.0;
        JSONObject weatherobj = new JSONObject(weatherJson);
        JSONObject currentTemp = weatherobj.getJSONObject("current");
        speed = currentTemp.getDouble("wind_mph");
        return speed;

    }

}
