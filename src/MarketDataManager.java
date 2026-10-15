import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MarketDataManager {
    private Map<String, Stock> stocks;

    public MarketDataManager() {
        stocks = new HashMap<>();
    }

    public void loadData(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Data folder not found: " + folderPath);
            return;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No files found in the data folder.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".csv")) {
                loadStockFile(file);
            }
        }
    }

    private void loadStockFile(File file) {
        String fileName = file.getName();

        String ticker = fileName.split("_")[0].toUpperCase();

        Stock stock = new Stock(ticker);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            // Skip header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length >= 6) {
                    LocalDate date = LocalDate.parse(parts[0]);

                    double open = Double.parseDouble(parts[1]);
                    double high = Double.parseDouble(parts[2]);
                    double low = Double.parseDouble(parts[3]);
                    double close = Double.parseDouble(parts[4]);
                    long volume = Long.parseLong(parts[5]);

                    PriceRecord record = new PriceRecord(
                            date,
                            open,
                            high,
                            low,
                            close,
                            volume
                    );

                    stock.addRecord(record);
                }
            }

            stocks.put(ticker, stock);

            System.out.println("Loaded " + ticker +
                    " with " + stock.getRecords().size() + " records.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        } catch (Exception e) {
            System.out.println("Error processing file: " + file.getName());
        }
    }

    public Stock getStock(String ticker) {
        if (ticker == null) {
            return null;
        }

        return stocks.get(ticker.toUpperCase());
    }

    public boolean hasStock(String ticker) {
        if (ticker == null) {
            return false;
        }

        return stocks.containsKey(ticker.toUpperCase());
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }
}
