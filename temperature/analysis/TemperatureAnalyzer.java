package temperature.analysis;

import java.util.List;
import temperature.model.TemperatureRecord;

public interface TemperatureAnalyzer<T> {
    T analyze(List<TemperatureRecord> records);
}
