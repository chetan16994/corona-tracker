package io.chetan.coronatracker.models;

public class LocationStats {

    private String county;
    private String state;
    private int totalLatestCases;
    private int lastWeekCases;

    public int getYesterdayCases() {
        return yesterdayCases;
    }

    public void setYesterdayCases(int yesterdayCases) {
        this.yesterdayCases = yesterdayCases;
    }

    private int yesterdayCases;

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalLatestCases() {
        return totalLatestCases;
    }

    public void setTotalLatestCases(int totalLatestCases) {
        this.totalLatestCases = totalLatestCases;
    }

    public int getLastWeekCases() {
        return lastWeekCases;
    }

    public void setLastWeekCases(int lastWeekCases) {
        this.lastWeekCases = lastWeekCases;
    }


    @Override
    public String toString() {
        return "LocationStats{" +
                "county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", totalLatestCases=" + totalLatestCases +
                ", lastWeekCases=" + lastWeekCases +
                ", yesterdayCases=" + yesterdayCases +
                '}';
    }
}
