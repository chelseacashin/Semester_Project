import java.time.LocalDate;

public class MarketAnalysisResult {
    private double highestClosingPrice;
    private double lowestClosingPrice;
    private double averageClosingPrice;
    private double percentagePriceChange;
    private double averageTradingVolume;

    private LocalDate bestTradingDay;
    private double bestTradingDayChange;

    private LocalDate worstTradingDay;
    private double worstTradingDayChange;

    public MarketAnalysisResult(
            double highestClosingPrice,
            double lowestClosingPrice,
            double averageClosingPrice,
            double percentagePriceChange,
            double averageTradingVolume,
            LocalDate bestTradingDay,
            double bestTradingDayChange,
            LocalDate worstTradingDay,
            double worstTradingDayChange) {

        this.highestClosingPrice = highestClosingPrice;
        this.lowestClosingPrice = lowestClosingPrice;
        this.averageClosingPrice = averageClosingPrice;
        this.percentagePriceChange = percentagePriceChange;
        this.averageTradingVolume = averageTradingVolume;
        this.bestTradingDay = bestTradingDay;
        this.bestTradingDayChange = bestTradingDayChange;
        this.worstTradingDay = worstTradingDay;
        this.worstTradingDayChange = worstTradingDayChange;
    }

    public double getHighestClosingPrice() {
        return highestClosingPrice;
    }

    public double getLowestClosingPrice() {
        return lowestClosingPrice;
    }

    public double getAverageClosingPrice() {
        return averageClosingPrice;
    }

    public double getPercentagePriceChange() {
        return percentagePriceChange;
    }

    public double getAverageTradingVolume() {
        return averageTradingVolume;
    }

    public LocalDate getBestTradingDay() {
        return bestTradingDay;
    }

    public double getBestTradingDayChange() {
        return bestTradingDayChange;
    }

    public LocalDate getWorstTradingDay() {
        return worstTradingDay;
    }

    public double getWorstTradingDayChange() {
        return worstTradingDayChange;
    }
}
