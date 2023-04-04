package it.polito.tdp.lab04.model;

import java.util.*;
import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();   }

	public List<Corso> getCorsi() {
	List<Corso> listaCorsi = corsoDAO.getTuttiICorsi();
	return listaCorsi;  }
	
	public List<Studente> getStudenti() {
		List<Studente> listaStudenti = studenteDAO.getTuttiGliStudenti();
		return listaStudenti;  }
	
	public List<Studente> getStudentiPerCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);  }
	
}
