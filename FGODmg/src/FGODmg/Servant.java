package FGODmg;

//Class file for servant stats

public class Servant {
	
	protected String sName;
	protected int Atk;
	protected String cardType;
	protected int npPerc;
	
	public Servant(String sName, int Atk, String cardType, int npPerc) {
		this.sName = sName;
		this.Atk = Atk;
		this.cardType = cardType;
		this.npPerc = npPerc;
	}
	
	public String getName() {
		return sName;
	}
	
	public int getAtk() {
		return Atk;
	}
	
	public String getCardType() {
		return cardType;
	}
	
	public int getNPPerc() {
		return npPerc;
	}
	
}
