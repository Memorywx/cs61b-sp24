package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;


    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();

    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        int tmp = endYear;
        while (tmp <= endYear) {
            if (ts.containsKey(tmp)) {
                this.put(tmp, ts.get(tmp));
                tmp++;
            }
        }
    }
    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> returnYears = new ArrayList<>();
        for(Map.Entry<Integer, Double> entry : this.entrySet()) {
            Integer key = entry.getKey();
            returnYears.add(key);
        }
        return returnYears;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> returnData = new ArrayList<>();
        for (Map.Entry<Integer,Double> entry : this.entrySet()) {
            Double value = entry.getValue();
            returnData.add(value);
        }
        return returnData;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries sumSeries = new TimeSeries();
        sumSeries.putAll(ts);      // 把ts里的全部复制过来

        for(Map.Entry<Integer, Double> entry : this.entrySet()) {  // 再复制this的，已含有的年份就更新value
            Integer key = entry.getKey();
            Double value = entry.getValue();

            if (sumSeries.containsKey(key)) {
                value += sumSeries.get(key);
            }
            sumSeries.put(key, value);
        }
        return sumSeries;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries divideSeries = new TimeSeries();
        divideSeries.putAll(this);

        for (Map.Entry<Integer,Double> entry : ts.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();

            if (divideSeries.containsKey(key)) {
                if (!ts.containsKey(key)) {
                    throw new IllegalArgumentException();
                }
                value = divideSeries.get(key) / value;
                divideSeries.put(key, value);
            }
        }

        return divideSeries;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
