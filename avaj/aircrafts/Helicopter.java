package avaj.aircrafts;

import java.io.*;
import avaj.WeatherTower;
import avaj.weather.Coordinates;

class Helicopter extends Aircraft implements Flyable{
    private WeatherTower    weatherTower;

    Helicopter(String name, Coordinates coordinates){
        super(name,coordinates);
    }

    public void             updateConditions(){
        try {
            FileOutputStream out = new FileOutputStream("Simulation.txt", true);

            out.write(("Helicopter#" + this.name + "(" + this.id + "): ").getBytes());
            if (weatherTower.getWeather(this.coordinates) ==  "RAIN"){
                out.write(("Rain! Rain! Wash my helicopter blades cleeeeean...\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 5,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight()
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "FOG"){
                out.write(("Helicopter karate CHOP! Chop. Chop. Chop up the fog.\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 1,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight()
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "SUN"){
                out.write(("*Passengers sky dive out the helicopter*\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 10,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 2
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "SNOW"){
                out.write(("*Helicopter blades shivering* WINTER IS COMING JOHN SNOW!\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 12
                );
            }

            if (this.coordinates.getHeight() == 0){
                out.write(("Tower says: Helicopter#" + name + "(" + id + ")" + " landing.\n").getBytes());
                out.write(("Tower says: Helicopter#" + name + "(" + id + ")" + " My coordinates are "
                        + this.coordinates.getLongitude() + " "
                        + this.coordinates.getLatitude() + " "
                        + this.coordinates.getHeight()
                        + ".\n").getBytes());
                this.weatherTower.unregister(this);
                out.write(("\033[0;36mTower says: Helicopter#" + name + "(" + id + ")" + " unregistered from weather tower.\033[0m\n").getBytes());
            }
        }catch (IOException e) {System.out.println("\033[0;31mFailed to write to file.\033[0m"); }
    }
    public void             registerTower(WeatherTower weatherTower){
        try {
            FileOutputStream out = new FileOutputStream("Simulation.txt", true);

            this.weatherTower = weatherTower;
            this.weatherTower.register(this);
            out.write(("\033[0;32mTower says: Helicopter#" + name + "(" + id + ")" + " registered to weather tower.\033[0m\n").getBytes());
        }catch (IOException e) {System.out.println("\033[0;31mFailed to write to file.\033[0m"); }
    }
}
