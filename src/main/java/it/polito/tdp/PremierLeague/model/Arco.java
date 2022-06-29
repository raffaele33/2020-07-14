package it.polito.tdp.PremierLeague.model;

public class Arco {
    private Team t1;
    private Team t2;
    private int delta;
	public Arco(Team t1, Team t2, int delta) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.delta = delta;
	}
	public Team getT1() {
		return t1;
	}
	public void setT1(Team t1) {
		this.t1 = t1;
	}
	public Team getT2() {
		return t2;
	}
	public void setT2(Team t2) {
		this.t2 = t2;
	}
	public int getDelta() {
		return delta;
	}
	public void setDelta(int delta) {
		this.delta = delta;
	}
    
    
    
}
