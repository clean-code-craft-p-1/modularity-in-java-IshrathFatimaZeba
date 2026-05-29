package temperature.analysis;

import java.time.LocalTime;
import java.util.List;
import temperature.model.TemperatureRecord;

public class CircadianPatternAnalyzer implements TemperatureAnalyzer<CircadianPatternReport> {
    private static final LocalTime DAY_START = LocalTime.of(6, 0);
    private static final LocalTime DAY_END = LocalTime.of(22, 0);

    @Override
    public CircadianPatternReport analyze(List<TemperatureRecord> records) {
        double daySum = 0.0;
        int dayCount = 0;
        double nightSum = 0.0;
        int nightCount = 0;

        for (TemperatureRecord record : records) {
            LocalTime time = record.getTime();
            if (!time.isBefore(DAY_START) && time.isBefore(DAY_END)) {
                daySum += record.getValue();
                dayCount++;
            } else {
                nightSum += record.getValue();
                nightCount++;
            }
        }

        double dayAverage = dayCount == 0 ? 0.0 : daySum / dayCount;
        double nightAverage = nightCount == 0 ? 0.0 : nightSum / nightCount;
        return new CircadianPatternReport(dayAverage, nightAverage);
    }
}
