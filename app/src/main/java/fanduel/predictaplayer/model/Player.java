
package fanduel.predictaplayer.model;


import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("first_name")
    private String firstName;
    @SerializedName("fixture")
    private Fixture fixture ;
    @SerializedName("fppg")
    private Float fppg;
    @SerializedName("id")
    private String id;
    @SerializedName("images")
    private Images images;
    @SerializedName("injured")
    private Boolean injured;
    @SerializedName("injury_details")
    private Object injuryDetails;
    @SerializedName("injury_status")
    private Object injuryStatus;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("news")
    private News news;
    @SerializedName("played")
    private Integer played;
    @SerializedName("player_card_url")
    private String playerCardUrl;
    @SerializedName("position")
    private String position;
    @SerializedName("removed")
    private Boolean removed;
    @SerializedName("salary")
    private Integer salary;
    @SerializedName("starting_order")
    private Object startingOrder;
    @SerializedName("team")
    private Team team;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Float getFppg() {
        return fppg;
    }

    public void setFppg(Float fppg) {
        this.fppg = fppg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Boolean getInjured() {
        return injured;
    }

    public void setInjured(Boolean injured) {
        this.injured = injured;
    }

    public Object getInjuryDetails() {
        return injuryDetails;
    }

    public void setInjuryDetails(Object injuryDetails) {
        this.injuryDetails = injuryDetails;
    }

    public Object getInjuryStatus() {
        return injuryStatus;
    }

    public void setInjuryStatus(Object injuryStatus) {
        this.injuryStatus = injuryStatus;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Integer getPlayed() {
        return played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }

    public String getPlayerCardUrl() {
        return playerCardUrl;
    }

    public void setPlayerCardUrl(String playerCardUrl) {
        this.playerCardUrl = playerCardUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Object getStartingOrder() {
        return startingOrder;
    }

    public void setStartingOrder(Object startingOrder) {
        this.startingOrder = startingOrder;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
