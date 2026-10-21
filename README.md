Milestone 1 of Mini Market Terminal: Stock Market Information. Chelsea Cashin and Samuel Vasquez-Maces

UML
class Main {
    + main(args: String[]): void
}

class MarketTerminal {
    - dataManager: MarketDataManager
    - analyzer: MarketAnalyzer
    - scanner: Scanner

    + MarketTerminal()
    + start(): void
    - searchStock(): void
    - viewHistoricalData(): void
    - analyzeStock(): void
    - displayAnalysis(
        ticker: String,
        startDate: LocalDate,
        endDate: LocalDate,
        result: MarketAnalysisResult
      ): void
}

class MarketDataManager {
    - stocks: Map<String, Stock>

    + MarketDataManager()
    + loadData(folderPath: String): void
    - loadStockFile(file: File): void
    + getStock(ticker: String): Stock
    + hasStock(ticker: String): boolean
    + getStocks(): Map<String, Stock>
}

class Stock {
    - ticker: String
    - records: List<PriceRecord>

    + Stock(ticker: String)
    + getTicker(): String
    + getRecords(): List<PriceRecord>
    + addRecord(record: PriceRecord): void
    + getRecordsBetween(
        startDate: LocalDate,
        endDate: LocalDate
      ): List<PriceRecord>
}

class PriceRecord {
    - date: LocalDate
    - open: double
    - high: double
    - low: double
    - close: double
    - volume: long

    + PriceRecord(
        date: LocalDate,
        open: double,
        high: double,
        low: double,
        close: double,
        volume: long
      )

    + getDate(): LocalDate
    + getOpen(): double
    + getHigh(): double
    + getLow(): double
    + getClose(): double
    + getVolume(): long
    + toString(): String
}

class MarketAnalyzer {
    + analyze(records: List<PriceRecord>): MarketAnalysisResult
}

class MarketAnalysisResult {
    - highestClosingPrice: double
    - lowestClosingPrice: double
    - averageClosingPrice: double
    - percentagePriceChange: double
    - averageTradingVolume: double
    - bestTradingDay: LocalDate
    - bestTradingDayChange: double
    - worstTradingDay: LocalDate
    - worstTradingDayChange: double

    + MarketAnalysisResult(
        highestClosingPrice: double,
        lowestClosingPrice: double,
        averageClosingPrice: double,
        percentagePriceChange: double,
        averageTradingVolume: double,
        bestTradingDay: LocalDate,
        bestTradingDayChange: double,
        worstTradingDay: LocalDate,
        worstTradingDayChange: double
      )

    + getHighestClosingPrice(): double
    + getLowestClosingPrice(): double
    + getAverageClosingPrice(): double
    + getPercentagePriceChange(): double
    + getAverageTradingVolume(): double
    + getBestTradingDay(): LocalDate
    + getBestTradingDayChange(): double
    + getWorstTradingDay(): LocalDate
    + getWorstTradingDayChange(): double
}

Main --> MarketTerminal

MarketTerminal --> MarketDataManager

    MarketDataManager "1" --> "0..*" Stock

        Stock "1" o-- "0..*" PriceRecord

MarketTerminal --> MarketAnalyzer

    MarketAnalyzer ..> PriceRecord : uses

    MarketAnalyzer --> MarketAnalysisResult

MarketTerminal --> MarketAnalysisResult



Explain:
This Java application loads historical stock market data from CSV files and allows users to search for stocks, view historical data, and analyze stock performance over a selected date range.
How to Run:
1. Open the project 
2. Make sure the data folder is located in the main project folder AND named "data".
3. Run Main.java.
4. Select an option from the menu.
Menu Options:
1. Search Stock
2. View Historical Data
3. Analyze Stock
0. Exit
Available Stock Tickers:
AAPL
AMZN
AVGO
BRK
GOOGL
LLY
META
MSFT
NVDA
TSLA
Stock Analysis:
The Analyze Stock option calculates:
- Highest closing price
- Lowest closing price
- Average closing price
- Percentage price change
- Average trading volume
- Best trading day
- Worst trading day
Date Format: Dates must be entered using: YYYY-MM-DD
Program Architecture:
Main: Starts the Mini Market Terminal application.
PriceRecord: Stores the market data for one trading day, including date, opening price, closing price, high price, low price, and trading volume.
Stock: Represents one stock and stores its historical PriceRecord objects.
MarketDataManager: Loads historical stock data from CSV files and manages the available stocks.
MarketAnalyzer: Performs calculations on stock data.
MarketAnalysisResult: Stores the results produced by MarketAnalyzer.
MarketTerminal: Provides the text-based user interface and manages interaction with the user.


Code running program, loading files, and searching a stock:

<img width="377" height="712" alt="MarketTerminal_Running1" src="https://github.com/user-attachments/assets/298e1b97-dbae-46c2-9f67-b59be398e322" />

Code Running Stock Anaylsis:

<img width="482" height="707" alt="MarketTerminal_Running2" src="https://github.com/user-attachments/assets/77d0c6fb-5e3d-447b-b7be-bdd3273e409d" />

Code Running view Historical Data:

<img width="987" height="692" alt="MarketTerminal_Running3" src="https://github.com/user-attachments/assets/625cc597-5989-4007-a754-50f92aca752b" />

