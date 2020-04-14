package eduneu.info6205.components;

public class Club {
	private String name;
//	public double AttackHomeForm=0;
//	public double AttackAwayForm=0;
//	public double DefenceHomeForm=0;
//	public double DefenceAwayForm=0;
	public double homeMatchesPlayed=0;
	public double awayMatchesPlayed=0;
	public double homeGoals=0;
	public double awayGoals=0;
	public double homeConceded=0;
	public double awayConceded=0;
	public double homeShotsOnTarget=0;
	public double awayShotsOnTarget=0;
	public double homeRedCards=0;
	public double awayRedCards=0;
	
	public Club(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Club [name=" + name + ", homeMatchesPlayed=" + homeMatchesPlayed + ", awayMatchesPlayed="
				+ awayMatchesPlayed + ", homeGoals=" + homeGoals + ", awayGoals=" + awayGoals + ", homeConceded="
				+ homeConceded + ", awayConceded=" + awayConceded + ", homeShotsOnTarget=" + homeShotsOnTarget
				+ ", awayShotsOnTarget=" + awayShotsOnTarget + ", homeRedCards=" + homeRedCards + ", awayRedCards="
				+ awayRedCards + "]";
	}
	
	
	
}
