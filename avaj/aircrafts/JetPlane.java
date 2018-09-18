package avaj.aircrafts;

import java.io.*;
import avaj.WeatherTower;
import avaj.weather.Coordinates;

class JetPlane extends Aircraft implements Flyable{
    private WeatherTower    weatherTower;

    JetPlane(String name, Coordinates coordinates){
        super(name,coordinates);
    }

    public void             updateConditions(){
        try {
            FileOutputStream out = new FileOutputStream("Simulation.txt", true);

            out.write(("JetPlane#" + this.name + "(" + this.id + "): ").getBytes());
            if (weatherTower.getWeather(this.coordinates) ==  "RAIN"){
                out.write(("Rain! Rain! Rain on mah plaaaane. Rain on my jetplane...\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 5,
                        this.coordinates.getHeight()
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "FOG"){
                out.write(("Fog! Jetplane no can see! Sheeeeet...\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 1,
                        this.coordinates.getHeight()
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "SUN"){
                out.write(("*Jetplane does summersaults and draws LIGMA in smoke trails in the clear sky*\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 10,
                        this.coordinates.getHeight() + 2
                );
            }else if (weatherTower.getWeather(this.coordinates) ==  "SNOW"){
                out.write(("Let it snoooow. Let it snoooow. Jetplane letttt ittt goooooo...\n").getBytes());
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 7
                );
            }

            if (this.coordinates.getHeight() == 0){
                out.write(("Tower says: JetPlane#" + name + "(" + id + ")" + " landing.\n").getBytes());
                out.write(("Tower says: JetPlane#" + name + "(" + id + ")" + " My coordinates are "
                        + this.coordinates.getLongitude() + " "
                        + this.coordinates.getLatitude() + " "
                        + this.coordinates.getHeight()
                        + ".\n").getBytes());
                this.weatherTower.unregister(this);
                out.write(("\033[0;36mTower says: JetPlane#" + name + "(" + id + ")" + " unregistered from weather tower.\033[0m\n").getBytes());
            }
        }catch (IOException e) {System.out.println("\033[0;31mFailed to write to file.\033[0m"); }
    }
    public void             registerTower(WeatherTower weatherTower){
        try {
            FileOutputStream out = new FileOutputStream("Simulation.txt", true);

            this.weatherTower = weatherTower;
            this.weatherTower.register(this);
            out.write(("\033[0;32mTower says: JetPlane#" + name + "(" + id + ")" + " registered to weather tower.\033[0m\n").getBytes());
        }catch (IOException e) {System.out.println("\033[0;31mFailed to write to file.\033[0m"); }
    }
}
