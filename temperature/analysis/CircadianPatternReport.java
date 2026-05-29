package temperature.analysis;

public class CircadianPatternReport {
    private final double dayAverage;
    private final double nightAverage;

    public CircadianPatternReport(double dayAverage, double nightAverage) {
        this.dayAverage = dayAverage;
        this.nightAverage = nightAverage;
    }

    public double getDayAverage() {
        return dayAverage;
    }

    public double getNightAverage() {
        return nightAverage;
    }
}
