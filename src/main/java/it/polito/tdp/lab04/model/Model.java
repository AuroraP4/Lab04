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
		return corsoDAO.getTuttiICorsi();  }
	
	public List<Studente> getStudenti() {
		return studenteDAO.getTuttiGliStudenti();  }
	
	public List<Studente> getStudentiPerCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);  }
	
	public List<Corso> getCorsiDegliStudenti(int matricola) {
		return studenteDAO.getCorsiFromStudente(matricola);  }
	
	public boolean isStudenteIscrittoACorso(int matricola, Corso corso) {
		return studenteDAO.isStudenteIscrittoACorso(matricola,corso);
	}
	
	public boolean inscriviStudenteACorso(int matricola, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(matricola, corso);
	}
}
