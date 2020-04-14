package eduneu.info6205.components;

public class Club {
	private String name;
	public double AttackHomeForm;
	public double AttackAwayForm;
	public double DefenceHomeForm;
	public double DefenceAwayForm;
	public int homeGoals;
	public int awayGoals;
	public int homeConceded;
	public int awayConceded;
	
	
	public Club(String name, double attackHomeForm, double attackAwayForm, double defenceHomeForm,double defenceAwayForm) {
		super();
		this.name = name;
		AttackHomeForm = attackHomeForm;
		AttackAwayForm = attackAwayForm;
		DefenceHomeForm = defenceHomeForm;
		DefenceAwayForm = defenceAwayForm;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAttackHomeForm() {
		return AttackHomeForm;
	}
	public void setAttackHomeForm(double attackHomeForm) {
		AttackHomeForm = attackHomeForm;
	}
	public double getAttackAwayForm() {
		return AttackAwayForm;
	}
	public void setAttackAwayForm(double attackAwayForm) {
		AttackAwayForm = attackAwayForm;
	}
	public double getDefenceHomeForm() {
		return DefenceHomeForm;
	}
	public void setDefenceHomeForm(double defenceHomeForm) {
		DefenceHomeForm = defenceHomeForm;
	}
	public double getDefenceAwayForm() {
		return DefenceAwayForm;
	}
	public void setDefenceAwayForm(double defenceAwayForm) {
		DefenceAwayForm = defenceAwayForm;
	}
	
	
}
