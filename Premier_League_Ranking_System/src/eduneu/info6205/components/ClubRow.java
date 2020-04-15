package eduneu.info6205.components;

public class ClubRow {

	private String name;
	private int played;
	private int won;
	private int drew;
	private int lost;
	private int goalsFired;
	private int goalsAcquired;
	private int goalDifference;
	private int points;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlayed() {
		return played;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getDrew() {
		return drew;
	}
	public void setDrew(int drew) {
		this.drew = drew;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public int getGoalsFired() {
		return goalsFired;
	}
	public void setGoalsFired(int goalsFired) {
		this.goalsFired = goalsFired;
	}
	public int getGoalsAcquired() {
		return goalsAcquired;
	}
	public void setGoalsAcquired(int goalsAcquired) {
		this.goalsAcquired = goalsAcquired;
	}
	
	public int getGoalDifference() {
		return goalDifference;
	}
	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return name + ", played=" + played + ", won=" + won + ", drew=" + drew + ", lost=" + lost
				+ ", goalsFired=" + goalsFired + ", goalsAcquired=" + goalsAcquired + ", goalDifference="
				+ goalDifference + ", points=" + points;
	}
	
	
	
}
