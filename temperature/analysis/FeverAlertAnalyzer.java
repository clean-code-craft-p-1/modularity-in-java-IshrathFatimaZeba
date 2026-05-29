package temperature.analysis;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import temperature.model.TemperatureRecord;

public class FeverAlertAnalyzer implements TemperatureAnalyzer<FeverAlertReport> {
    private final double feverThreshold;
    private final Duration sustainedDuration;

    public FeverAlertAnalyzer(double feverThreshold, Duration sustainedDuration) {
        this.feverThreshold = feverThreshold;
        this.sustainedDuration = sustainedDuration;
    }

    @Override
    public FeverAlertReport analyze(List<TemperatureRecord> records) {
        if (records.isEmpty()) {
            return new FeverAlertReport(false, Duration.ZERO);
        }

        List<TemperatureRecord> sorted = new ArrayList<>(records);
        sorted.sort(Comparator.comparing(TemperatureRecord::getTime));

        LocalTime windowStart = null;
        LocalTime previousTime = null;
        Duration longest = Duration.ZERO;

        for (TemperatureRecord record : sorted) {
            if (record.getValue() >= feverThreshold) {
                if (windowStart == null) {
                    windowStart = record.getTime();
                }
                previousTime = record.getTime();
                Duration current = Duration.between(windowStart, previousTime);
                if (current.compareTo(longest) > 0) {
                    longest = current;
                }
            } else {
                windowStart = null;
                previousTime = null;
            }
        }

        return new FeverAlertReport(longest.compareTo(sustainedDuration) >= 0, longest);
    }
}
