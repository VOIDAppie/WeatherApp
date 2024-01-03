package WeatherApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAppRunner {
    public static void main(String[] args) {
        //input city
        System.out.println("Enter the name of the city: ");
        String city = new java.util.Scanner(System.in).nextLine();

        getWeatherOfInputCity(city);
    }

    private static void getWeatherOfInputCity(String city) {
        try {
            String apiKey = "52545412be492f65bfffbd2633d79242";
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // Get the response code
            int responseStatusCode = connection.getResponseCode();

            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //readParameterLine stores each line read from the bufferedReader until it cant find anymore lines to read,
                // afterwards it sets to false and exits the while loop
                String readParameterLine;
                StringBuilder response = new StringBuilder();

                while ((readParameterLine = reader.readLine()) != null) {
                    response.append(readParameterLine).append("\n");
                }

                reader.close();
                connection.disconnect();

                System.out.println("Weather Information for " + city + ":");
                System.out.println(response);
            } else {
                System.out.println("Error: Unable to get weather information. Status Code: " + responseStatusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
