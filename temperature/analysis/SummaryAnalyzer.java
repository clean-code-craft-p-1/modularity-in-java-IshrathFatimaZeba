package temperature.analysis;

import java.util.List;
import temperature.model.TemperatureRecord;

public class SummaryAnalyzer implements TemperatureAnalyzer<TemperatureSummary> {
    @Override
    public TemperatureSummary analyze(List<TemperatureRecord> records) {
        if (records.isEmpty()) {
            return new TemperatureSummary(0, 0.0, 0.0, 0.0);
        }

        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0.0;

        for (TemperatureRecord record : records) {
            double value = record.getValue();
            min = Math.min(min, value);
            max = Math.max(max, value);
            sum += value;
        }

        return new TemperatureSummary(records.size(), min, max, sum / records.size());
    }
}
