package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	private Graph <Team, DefaultWeightedEdge> grafo;
	private PremierLeagueDAO dao;
	private Map<Integer, Team> map;
	private List<Match> match;
	List<PuntiS> classifica= new LinkedList<>();
	private List<Arco> archi=new ArrayList<>();
	public Model() {
		dao=new PremierLeagueDAO();
		map=new HashMap<Integer,Team>();
		dao.listAllTeams(map);
		this.match=dao.listAllMatches();
	}
	
	public void creaGrafo() {
		grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, map.values());
		
		caricasquadre();
		caricaClassifica();
		creaArchi(this.archi);
		for(Arco a : archi) {
			Graphs.addEdge(grafo, a.getT1(), a.getT2(), a.getDelta());
		}
		
	}
	
	private void caricasquadre() {
		for(Team t : map.values()) {
			PuntiS s = new PuntiS(t,0);
			this.classifica.add(s);
		}
	}
	private void caricaClassifica() {
		for(Match m: this.match) {
			if(m.getResultOfTeamHome()==1) {
				for(PuntiS p: this.classifica) {
					if(p.getTeam().teamID==m.teamHomeID) {
						p.setPunti(p.getPunti()+3);
					}
				}
			}
			else if(m.getResultOfTeamHome()==0) {
				for(PuntiS p: this.classifica) {
					if(p.getTeam().teamID==m.teamHomeID) {
						p.setPunti(p.getPunti()+1);
					}if(p.getTeam().teamID==m.teamAwayID) {
						p.setPunti(p.getPunti()+1);
					}
				}
			}else if(m.getResultOfTeamHome()==-1) {
				for(PuntiS p: this.classifica) {
					if(p.getTeam().teamID==m.teamAwayID) {
						p.setPunti(p.getPunti()+3);
					}
				}
			}
		}
	}
	private void creaArchi(List<Arco> a) {
		
		for(PuntiS p1:classifica) {
			for(PuntiS p2: classifica) {
				if(!p1.equals(p2)) {
					if(p1.getPunti()>p2.getPunti()) {
						
						Arco ar= new Arco(p1.getTeam(),p2.getTeam(),(p1.getPunti()-p2.getPunti()));
						a.add(ar);
						
					}
				}
			}
		}
		
	}
	public List<PuntiS> teamMigliori(Team t){
		List<PuntiS> res=new ArrayList<PuntiS>();
		int punti=0;
		
		for(PuntiS p: this.classifica) {
			if(p.getTeam().equals(t)) {
				punti=p.getPunti();
		}
	}
		for(PuntiS p: this.classifica) {
		if(!p.getTeam().equals(t) && p.getPunti()>punti) {
			PuntiS pu= new PuntiS(p.getTeam(),punti-p.getPunti());
			res.add(pu);
		}
		
		}
		return res;
	}
	
	public List<PuntiS> teamPeggiori(Team t){
		List<PuntiS> res=new ArrayList<PuntiS>();
		int punti=0;
		
		for(PuntiS p: this.classifica) {
			if(p.getTeam().equals(t)) {
				punti=p.getPunti();
		}
	}
		for(PuntiS p: this.classifica) {
		if(!p.getTeam().equals(t) && p.getPunti()<punti) {
			PuntiS pu= new PuntiS(p.getTeam(),p.getPunti()-punti);
			res.add(pu);
		}
		
		}
		return res;
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	public List<PuntiS> getclassifica(){
		return this.classifica;
	}
	public Collection<Team> teams(){
		return this.map.values();
	}
}
