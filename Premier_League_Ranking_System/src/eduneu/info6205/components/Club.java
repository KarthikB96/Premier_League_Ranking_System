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
		return name + "," + homeMatchesPlayed + ","
				+ awayMatchesPlayed + "," + homeGoals + "," + awayGoals + ","
				+ homeConceded + "," + awayConceded ;
	}
	
	
	
}
