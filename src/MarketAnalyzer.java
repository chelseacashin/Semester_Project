import java.util.List;

public class MarketAnalyzer {

    public MarketAnalysisResult analyze(List<PriceRecord> records) {

        if (records == null || records.isEmpty()) {
            return null;
        }

        double highestClosingPrice = records.get(0).getClose();
        double lowestClosingPrice = records.get(0).getClose();

        double totalClosingPrice = 0;
        double totalVolume = 0;

        double bestTradingDayChange = Double.NEGATIVE_INFINITY;
        double worstTradingDayChange = Double.POSITIVE_INFINITY;

        PriceRecord bestTradingDayRecord = null;
        PriceRecord worstTradingDayRecord = null;

        for (int i = 0; i < records.size(); i++) {

            PriceRecord currentRecord = records.get(i);

            double close = currentRecord.getClose();

            if (close > highestClosingPrice) {
                highestClosingPrice = close;
            }

            if (close < lowestClosingPrice) {
                lowestClosingPrice = close;
            }

            totalClosingPrice += close;
            totalVolume += currentRecord.getVolume();

            if (i > 0) {

                PriceRecord previousRecord = records.get(i - 1);

                double previousClose = previousRecord.getClose();

                double dailyPercentageChange =
                        ((close - previousClose) / previousClose) * 100;

                if (dailyPercentageChange > bestTradingDayChange) {
                    bestTradingDayChange = dailyPercentageChange;
                    bestTradingDayRecord = currentRecord;
                }

                if (dailyPercentageChange < worstTradingDayChange) {
                    worstTradingDayChange = dailyPercentageChange;
                    worstTradingDayRecord = currentRecord;
                }
            }
        }

        double averageClosingPrice =
                totalClosingPrice / records.size();

        double averageTradingVolume =
                totalVolume / records.size();

        double firstClosingPrice =
                records.get(0).getClose();

        double lastClosingPrice =
                records.get(records.size() - 1).getClose();

        double percentagePriceChange =
                ((lastClosingPrice - firstClosingPrice)
                        / firstClosingPrice) * 100;

        if (bestTradingDayRecord == null) {
            bestTradingDayRecord = records.get(0);
            bestTradingDayChange = 0;
        }

        if (worstTradingDayRecord == null) {
            worstTradingDayRecord = records.get(0);
            worstTradingDayChange = 0;
        }

        return new MarketAnalysisResult(
                highestClosingPrice,
                lowestClosingPrice,
                averageClosingPrice,
                percentagePriceChange,
                averageTradingVolume,
                bestTradingDayRecord.getDate(),
                bestTradingDayChange,
                worstTradingDayRecord.getDate(),
                worstTradingDayChange
        );
    }
}