Milestone 1 of Mini Market Terminal: Stock Market Information. Chelsea Cashin and Samuel Vasquez-Maces


Main
  |
  v
MarketTerminal
  |
  ----> MarketDataManager
  |             |
  |             v
  |           Stock
  |             |
  |             v
  |        PriceRecord
  |
  ----> MarketAnalyzer
              |
              v
     MarketAnalysisResult


Explain:
This Java application loads historical stock market data from CSV files and allows users to search for stocks, view historical data, and analyze stock performance over a selected date range.
How to Run:
1. Open the project 
2. Make sure the data folder is located in the main project folder.
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
