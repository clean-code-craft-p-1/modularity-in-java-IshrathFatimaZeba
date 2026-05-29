package temperature.analysis;

public class TemperatureSummary {
    private final int count;
    private final double min;
    private final double max;
    private final double average;

    public TemperatureSummary(int count, double min, double max, double average) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public int getCount() {
        return count;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }
}
