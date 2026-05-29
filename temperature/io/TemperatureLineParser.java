package temperature.io;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import temperature.model.TemperatureRecord;

public class TemperatureLineParser {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final double MIN_TEMP = -100.0;
    private static final double MAX_TEMP = 200.0;

    public Optional<TemperatureRecord> parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return Optional.empty();
        }

        String[] parts = line.trim().split(",");
        if (parts.length != 2) {
            return Optional.empty();
        }

        try {
            LocalTime time = LocalTime.parse(parts[0].trim(), TIME_FORMAT);
            double temp = Double.parseDouble(parts[1].trim());
            if (temp < MIN_TEMP || temp > MAX_TEMP) {
                return Optional.empty();
            }
            return Optional.of(new TemperatureRecord(time, temp));
        } catch (DateTimeParseException | NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
