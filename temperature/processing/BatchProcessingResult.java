package temperature.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import temperature.model.TemperatureRecord;

public class BatchProcessingResult {
    private final List<TemperatureRecord> records;
    private final int errors;

    public BatchProcessingResult(List<TemperatureRecord> records, int errors) {
        this.records = Collections.unmodifiableList(new ArrayList<>(records));
        this.errors = errors;
    }

    public List<TemperatureRecord> getRecords() {
        return records;
    }

    public int getErrors() {
        return errors;
    }

    public int getValidCount() {
        return records.size();
    }
}
