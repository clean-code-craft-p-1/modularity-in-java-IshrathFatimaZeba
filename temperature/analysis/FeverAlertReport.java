package temperature.analysis;

import java.time.Duration;

public class FeverAlertReport {
    private final boolean alert;
    private final Duration longestSustainedFever;

    public FeverAlertReport(boolean alert, Duration longestSustainedFever) {
        this.alert = alert;
        this.longestSustainedFever = longestSustainedFever;
    }

    public boolean isAlert() {
        return alert;
    }

    public Duration getLongestSustainedFever() {
        return longestSustainedFever;
    }
}
