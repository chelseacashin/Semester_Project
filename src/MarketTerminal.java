import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MarketTerminal {

    private MarketDataManager dataManager;
    private MarketAnalyzer analyzer;
    private Scanner scanner;

    public MarketTerminal() {
        dataManager = new MarketDataManager();
        analyzer = new MarketAnalyzer();
        scanner = new Scanner(System.in);
    }

    public void start() {

        dataManager.loadData("data");

        System.out.println("================================");
        System.out.println("      MINI MARKET TERMINAL");
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("1. Search Stock");
            System.out.println("2. View Historical Data");
            System.out.println("3. Analyze Stock");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    searchStock();
                    break;

                case "2":
                    viewHistoricalData();
                    break;

                case "3":
                    analyzeStock();
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void searchStock() {

        System.out.print("Enter ticker symbol: ");
        String ticker = scanner.nextLine().trim().toUpperCase();

        if (dataManager.hasStock(ticker)) {
            Stock stock = dataManager.getStock(ticker);

            System.out.println("Stock found: " + ticker);
            System.out.println(
                    "Historical records available: "
                            + stock.getRecords().size()
            );
        } else {
            System.out.println("Invalid ticker symbol.");
        }
    }

    private void viewHistoricalData() {

        System.out.print("Enter ticker symbol: ");
        String ticker = scanner.nextLine().trim().toUpperCase();

        Stock stock = dataManager.getStock(ticker);

        if (stock == null) {
            System.out.println("Invalid ticker symbol.");
            return;
        }

        System.out.println();
        System.out.println("Historical Data for " + ticker);
        System.out.println("--------------------------------");

        for (PriceRecord record : stock.getRecords()) {
            System.out.println(record);
        }
    }

    private void analyzeStock() {

        System.out.print("Enter ticker symbol: ");
        String ticker = scanner.nextLine().trim().toUpperCase();

        Stock stock = dataManager.getStock(ticker);

        if (stock == null) {
            System.out.println("Invalid ticker symbol.");
            return;
        }

        try {

            System.out.print("Enter start date (YYYY-MM-DD): ");
            LocalDate startDate =
                    LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Enter end date (YYYY-MM-DD): ");
            LocalDate endDate =
                    LocalDate.parse(scanner.nextLine().trim());

            if (startDate.isAfter(endDate)) {
                System.out.println(
                        "Invalid date range. Start date must be before end date."
                );
                return;
            }

            List<PriceRecord> records =
                    stock.getRecordsBetween(startDate, endDate);

            if (records.isEmpty()) {
                System.out.println(
                        "No historical records found for that date range."
                );
                return;
            }

            MarketAnalysisResult result =
                    analyzer.analyze(records);

            displayAnalysis(
                    ticker,
                    startDate,
                    endDate,
                    result
            );

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Invalid date format. Please use YYYY-MM-DD."
            );
        }
    }

    private void displayAnalysis(
            String ticker,
            LocalDate startDate,
            LocalDate endDate,
            MarketAnalysisResult result) {

        System.out.println();
        System.out.println("================================");
        System.out.println("          STOCK ANALYSIS");
        System.out.println("================================");

        System.out.println("Ticker: " + ticker);
        System.out.println(
                "Period: " + startDate + " - " + endDate
        );

        System.out.println();

        System.out.printf(
                "Highest Closing Price:   $%.2f%n",
                result.getHighestClosingPrice()
        );

        System.out.printf(
                "Lowest Closing Price:    $%.2f%n",
                result.getLowestClosingPrice()
        );

        System.out.printf(
                "Average Closing Price:   $%.2f%n",
                result.getAverageClosingPrice()
        );

        System.out.printf(
                "Price Change:             %+.2f%%%n",
                result.getPercentagePriceChange()
        );

        System.out.printf(
                "Average Trading Volume:  %,.0f%n",
                result.getAverageTradingVolume()
        );

        System.out.printf(
                "Best Trading Day:         %s (%+.2f%%)%n",
                result.getBestTradingDay(),
                result.getBestTradingDayChange()
        );

        System.out.printf(
                "Worst Trading Day:        %s (%+.2f%%)%n",
                result.getWorstTradingDay(),
                result.getWorstTradingDayChange()
        );
    }
}