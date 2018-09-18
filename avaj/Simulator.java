package avaj;

import java.io.*;

import avaj.*;
import avaj.aircrafts.*;
import avaj.weather.*;
import avaj.aircrafts.AircraftFactory;
import avaj.aircrafts.Flyable;

public class Simulator {
    private static Flyable[]   airplanes;

    public static void main(String[] args) {
        if (args.length == 1) {
            Lexer lex = new Lexer(args[0]);
            WeatherTower wt = new WeatherTower();
            AircraftFactory af = new AircraftFactory();

            //Clear Simulation.txt file or create if not exists
            try {
                FileOutputStream out = new FileOutputStream("Simulation.txt");
                out.write("".getBytes());
            } catch (IOException e) {
            }

            //Lex Scenario.txt file input
            if (lex.isValid()) {
                for (int i = 0; i < lex.numOfAirplanes; i++) {
                    af.newAircraft(
                            lex.airplanes[i].group(1),
                            lex.airplanes[i].group(2),
                            Integer.parseInt(lex.airplanes[i].group(3)),
                            Integer.parseInt(lex.airplanes[i].group(4)),
                            Integer.parseInt(lex.airplanes[i].group(5))
                    ).registerTower(wt);
                }

                for (int i = 0; i < lex.getRunTime(); i++) {
                    wt.changeWeather();
                }
            }
        }else{
            System.out.println("\033[0;31mIncorrect Number of Arguments");
        }
    }
}