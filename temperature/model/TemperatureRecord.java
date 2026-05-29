package temperature.model;

import java.time.LocalTime;

public class TemperatureRecord {
    private final LocalTime time;
    private final double value;

    public TemperatureRecord(LocalTime time, double value) {
        this.time = time;
        this.value = value;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }
}
