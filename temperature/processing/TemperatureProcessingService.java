package temperature.processing;

import java.io.IOException;
import java.nio.file.Path;
import temperature.analysis.CircadianPatternAnalyzer;
import temperature.analysis.CircadianPatternReport;
import temperature.analysis.FeverAlertAnalyzer;
import temperature.analysis.FeverAlertReport;
import temperature.analysis.SummaryAnalyzer;
import temperature.analysis.TemperatureSummary;
import temperature.io.TemperatureBatchReader;

public class TemperatureProcessingService {
    private final TemperatureBatchReader batchReader;
    private final SummaryAnalyzer summaryAnalyzer;
    private final FeverAlertAnalyzer feverAlertAnalyzer;
    private final CircadianPatternAnalyzer circadianPatternAnalyzer;

    public TemperatureProcessingService(
            TemperatureBatchReader batchReader,
            SummaryAnalyzer summaryAnalyzer,
            FeverAlertAnalyzer feverAlertAnalyzer,
            CircadianPatternAnalyzer circadianPatternAnalyzer) {
        this.batchReader = batchReader;
        this.summaryAnalyzer = summaryAnalyzer;
        this.feverAlertAnalyzer = feverAlertAnalyzer;
        this.circadianPatternAnalyzer = circadianPatternAnalyzer;
    }

    public TemperatureProcessingReport process(Path filePath) throws IOException {
        BatchProcessingResult batchResult = batchReader.read(filePath);
        TemperatureSummary summary = summaryAnalyzer.analyze(batchResult.getRecords());
        FeverAlertReport feverAlert = feverAlertAnalyzer.analyze(batchResult.getRecords());
        CircadianPatternReport circadian = circadianPatternAnalyzer.analyze(batchResult.getRecords());

        return new TemperatureProcessingReport(batchResult, summary, feverAlert, circadian);
    }
}
