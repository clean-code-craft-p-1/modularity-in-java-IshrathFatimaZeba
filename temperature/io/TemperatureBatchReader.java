package temperature.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import temperature.model.TemperatureRecord;
import temperature.processing.BatchProcessingResult;

public class TemperatureBatchReader {
    private final TemperatureLineParser parser;

    public TemperatureBatchReader(TemperatureLineParser parser) {
        this.parser = parser;
    }

    public BatchProcessingResult read(Path filePath) throws IOException {
        List<TemperatureRecord> records = new ArrayList<>();
        int errors = 0;

        for (String line : Files.readAllLines(filePath)) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            Optional<TemperatureRecord> parsed = parser.parse(line);
            if (parsed.isPresent()) {
                records.add(parsed.get());
            } else {
                errors++;
            }
        }

        return new BatchProcessingResult(records, errors);
    }
}
