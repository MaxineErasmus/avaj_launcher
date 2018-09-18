package avaj.weather;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider     weatherProvider = new WeatherProvider();;
    private static String[]     weather = {
            "RAIN", "FOG", "SUN", "SNOW"
    };

    private                     WeatherProvider(){}

    public static WeatherProvider      getProvider(){
        return WeatherProvider.weatherProvider;
    }

    public String                      getCurrentWeather(Coordinates coordinates){
        long seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        Random r = new Random();

        r.setSeed(seed);
        int i = -1 * r.nextInt();

        return weatherProvider.weather[i % 4];
    }
}
