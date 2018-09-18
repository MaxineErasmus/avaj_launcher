package avaj.aircrafts;

import avaj.weather.Coordinates;

public class Aircraft {
    protected long          id;
    protected String        name;
    protected Coordinates   coordinates;
    private static long     idCounter;

    protected               Aircraft(String name, Coordinates coordinates){
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private static long      nextId(){
        return ++idCounter;
    }
}
