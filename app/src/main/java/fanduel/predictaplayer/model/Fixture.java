
package fanduel.predictaplayer.model;


import com.google.gson.annotations.SerializedName;

public class Fixture {

    @SerializedName("away_team")
    private AwayTeam awayTeam;
    @SerializedName("home_team")
    private HomeTeam homeTeam;
    private String id;
    private String sport;
    @SerializedName("start_date")
    private String startDate;
    private Object status;

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

}
