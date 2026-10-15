 import java.time.LocalDate;

public class PriceRecord {
    private LocalDate date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;

    public PriceRecord(LocalDate date, double open, double high,
                       double low, double close, long volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return date +
                " | Open: $" + String.format("%.2f", open) +
                " | High: $" + String.format("%.2f", high) +
                " | Low: $" + String.format("%.2f", low) +
                " | Close: $" + String.format("%.2f", close) +
                " | Volume: " + String.format("%,d", volume);
    }
}
