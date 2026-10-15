import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String ticker;
    private List<PriceRecord> records;

    public Stock(String ticker) {
        this.ticker = ticker.toUpperCase();
        this.records = new ArrayList<>();
    }

    public String getTicker() {
        return ticker;
    }

    public List<PriceRecord> getRecords() {
        return records;
    }

    public void addRecord(PriceRecord record) {
        records.add(record);
    }

    public List<PriceRecord> getRecordsBetween(LocalDate startDate, LocalDate endDate) {
        List<PriceRecord> filteredRecords = new ArrayList<>();

        for (PriceRecord record : records) {
            if (!record.getDate().isBefore(startDate)
                    && !record.getDate().isAfter(endDate)) {
                filteredRecords.add(record);
            }
        }

        return filteredRecords;
    }
}