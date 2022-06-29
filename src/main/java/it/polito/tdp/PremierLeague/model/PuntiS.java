package it.polito.tdp.PremierLeague.model;

import java.util.Objects;

public class PuntiS {
   private Team team;
   private int punti;
public PuntiS(Team team, int punti) {
	super();
	this.team = team;
	this.punti = punti;
}
public Team getTeam() {
	return team;
}
public void setTeam(Team team) {
	this.team = team;
}
public int getPunti() {
	return punti;
}
public void setPunti(int punti) {
	this.punti = punti;
}
@Override
public String toString() {
	return team.getName() + "  "+punti ;
}
@Override
public int hashCode() {
	return Objects.hash(punti, team);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	PuntiS other = (PuntiS) obj;
	return punti == other.punti && Objects.equals(team, other.team);
}
   
   
}
