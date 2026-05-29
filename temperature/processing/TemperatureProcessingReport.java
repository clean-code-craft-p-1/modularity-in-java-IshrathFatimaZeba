package temperature.processing;

import temperature.analysis.CircadianPatternReport;
import temperature.analysis.FeverAlertReport;
import temperature.analysis.TemperatureSummary;

public class TemperatureProcessingReport {
    private final BatchProcessingResult batchResult;
    private final TemperatureSummary summary;
    private final FeverAlertReport feverAlert;
    private final CircadianPatternReport circadianPattern;

    public TemperatureProcessingReport(
            BatchProcessingResult batchResult,
            TemperatureSummary summary,
            FeverAlertReport feverAlert,
            CircadianPatternReport circadianPattern) {
        this.batchResult = batchResult;
        this.summary = summary;
        this.feverAlert = feverAlert;
        this.circadianPattern = circadianPattern;
    }

    public BatchProcessingResult getBatchResult() {
        return batchResult;
    }

    public TemperatureSummary getSummary() {
        return summary;
    }

    public FeverAlertReport getFeverAlert() {
        return feverAlert;
    }

    public CircadianPatternReport getCircadianPattern() {
        return circadianPattern;
    }
}
