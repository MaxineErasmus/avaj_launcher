package avaj.aircrafts;

import avaj.WeatherTower;

public interface Flyable {
    public void     updateConditions();
    public void     registerTower(WeatherTower weatherTower);
}
