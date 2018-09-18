package avaj.aircrafts;

import java.io.*;
import avaj.WeatherTower;
import avaj.weather.Coordinates;

class Balloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates){
        super(name,coordinates);
    }

    public void             updateConditions(){
        try {
            FileOutputStream out = new FileOutputStream("Simulation.txt", true);

            out.write(("Balloon#" + this.name + "(" + this.id + "): ").getBytes());
            if (weatherTower.getWeather(this.coordinates) ==  "RAIN"){
                out.write(("drip drop on my balloon top. Don't drip too hard or we go POP!\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 5
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "FOG"){
                out.write(("*Passengers can't watch the show! Gosh darn fog view restrictions*\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 3
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "SUN"){
                out.write(("*Balloon passengers enjoy a hot dinner for two, lit by the glow of forest fires below*\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 2,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 10
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "SNOW"){
                out.write(("Balloon is cold. Balloon no want fly more. Balloon go down\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 15
                );
            }

            if (this.coordinates.getHeight() == 0){
                out.write(("Tower says: Baloon#" + name + "(" + id + ")" + " landing.\n").getBytes());
                out.write(("Tower says: Baloon#" + name + "(" + id + ")" + " My coordinates are "
                        + this.coordinates.getLongitude() + " "
                        + this.coordinates.getLatitude() + " "
                        + this.coordinates.getHeight()
                        + ".\n").getBytes());
                this.weatherTower.unregister(this);
                out.write(("\033[0;36mTower says: Baloon#" + name + "(" + id + ")" + " unregistered from weather tower.\033[0m\n").getBytes());
            }
        }catch (IOException e) {System.out.println("\033[0;31mFailed to write to file.\033[0m"); }
    }
    public void             registerTower(WeatherTower weatherTower){
        try {
            FileOutputStream out = new FileOutputStream("Simulation.txt", true);

            this.weatherTower = weatherTower;
            this.weatherTower.register(this);
            out.write(("\033[0;32mTower says: Baloon#" + name + "(" + id + ")" + " registered to weather tower.\033[0m\n").getBytes());
        }catch (IOException e) {System.out.println("\033[0;31mFailed to write to file.\033[0m"); }
    }
}
