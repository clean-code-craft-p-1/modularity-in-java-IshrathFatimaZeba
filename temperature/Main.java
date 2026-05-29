package temperature;

import java.io.*;
import java.time.Duration;
import java.nio.file.Paths;
import temperature.analysis.CircadianPatternAnalyzer;
import temperature.analysis.FeverAlertAnalyzer;
import temperature.analysis.SummaryAnalyzer;
import temperature.io.TemperatureBatchReader;
import temperature.io.TemperatureLineParser;
import temperature.processing.TemperatureProcessingReport;
import temperature.processing.TemperatureProcessingService;

public class Main {

    private static final TemperatureProcessingService PROCESSING_SERVICE =
        new TemperatureProcessingService(
            new TemperatureBatchReader(new TemperatureLineParser()),
            new SummaryAnalyzer(),
            new FeverAlertAnalyzer(38.0, Duration.ofMinutes(30)),
            new CircadianPatternAnalyzer());
    
    public static void processBatch(String filename) throws IOException {
        TemperatureProcessingReport report = PROCESSING_SERVICE.process(Paths.get(filename));

        if (report.getBatchResult().getValidCount() == 0) {
            System.out.println("No valid temperature data found.");
            return;
        }

        System.out.println("Total: " + report.getSummary().getCount() + " | Errors: " + report.getBatchResult().getErrors());
        System.out.printf("Min: %.2f | Max: %.2f | Avg: %.2f%n", 
            report.getSummary().getMin(), report.getSummary().getMax(), report.getSummary().getAverage());
    }
    
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java temperature.Main <filename>");
            return;
        }
        processBatch(args[0]);
    }
}