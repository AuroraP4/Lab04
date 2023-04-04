package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<Corso> corsi;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea areaDiTesto;

    @FXML
    private Button bCercaCorsi;

    @FXML
    private Button bCercaIscrittiCorso;

    @FXML
    private Button bErgo;

    @FXML
    private Button bIscrivi;

    @FXML
    private Button bReset;

    @FXML
    private ComboBox<Corso> cbCorsi;

    @FXML
    private TextField tfCognomeStudente;

    @FXML
    private TextField tfMatricolaStudente;

    @FXML
    private TextField tfNomeStudente;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	this.areaDiTesto.clear(); 
    	boolean flag = false;
    	String mat = tfMatricolaStudente.getText();
    	
    	if(mat.equals("")) {
    		this.areaDiTesto.setText("Inserire una matricola!"); 
    		return; }
    	if(!mat.matches("\\d*")) {
    		this.areaDiTesto.setText("La matricola è composta di soli numeri!"); 
    		return; }
    	
    	int matricola = Integer. parseInt(mat);
    	for(Studente s: model.getStudenti()) {
    		if(s.getMatricola()==matricola) {
    			flag = true;}  }
    	
    	if(flag == false) {
    		this.areaDiTesto.setText("ERRORE! Questa matricola non è registrata nel database!");   }
   
    	List<Corso> corsi = model.getCorsiDegliStudenti(matricola);
    	
    	for(Corso c: corsi) {
    		this.areaDiTesto.appendText(c.toStringCorsiDelloStudente() + "\n");   }
    }
    	
    

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	this.areaDiTesto.clear(); 
    	
    	Corso corsoDaTendina = this.cbCorsi.getValue();
    	
    	if(corsoDaTendina == null) {
    		this.areaDiTesto.setText("Scegliere un corso al menù a tendina!"); 
    		return; }
    	
    	List <Studente> studenti = model.getStudentiPerCorso(corsoDaTendina);
   
    	for(Studente st: studenti) {
    		this.areaDiTesto.appendText(st.toString() + "\n");   }
    }

    @FXML
    void doErgo(ActionEvent event) {
    	this.areaDiTesto.clear(); 
    	boolean flag = false;
    	
    	String mat = tfMatricolaStudente.getText();
    	
    	if(mat.equals("")) {
    		this.areaDiTesto.setText("Inserire una matricola!"); 
    		return; }
    	if(!mat.matches("\\d*")) {
    		this.areaDiTesto.setText("La matricola è composta di soli numeri!"); 
    		return; }
    	
    	int matricola = Integer. parseInt(mat);
    	this.tfNomeStudente.clear();
		this.tfCognomeStudente.clear();
    	
    	for(Studente s: model.getStudenti()) {
    		if(s.getMatricola()==matricola) {
    			this.tfNomeStudente.setText(s.getNome());
    			this.tfCognomeStudente.setText(s.getCognome());
    			flag = true;}  }
    	
    	if(flag == false) {
    		this.areaDiTesto.setText("ERRORE! Questa matricola non è registrata nel database!");   }
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	this.areaDiTesto.clear();

    	boolean flag = false;
    	String mat = tfMatricolaStudente.getText();
    	
    	if(mat.equals("")) {
    		this.areaDiTesto.setText("Inserire una matricola!"); 
    		return; }
    	if(!mat.matches("\\d*")) {
    		this.areaDiTesto.setText("La matricola è composta di soli numeri!"); 
    		return; }
    	
    	int matricola = Integer. parseInt(mat);
    	for(Studente s: model.getStudenti()) {
    		if(s.getMatricola()==matricola) {
    			flag = true;}  }
    	
    	Corso corsoDaTendina = this.cbCorsi.getValue();
    	
    	if(corsoDaTendina == null) {
    		this.areaDiTesto.setText("Scegliere un corso al menù a tendina!"); 
    		return; }
    	
    	if(flag == false) {
    		this.areaDiTesto.setText("ERRORE! Questa matricola non è registrata nel database!");   }
   
    	if (model.isStudenteIscrittoACorso(matricola, corsoDaTendina)) {
			this.areaDiTesto.appendText("Studente già iscritto a questo corso!");
			return;	}

    	if (!model.inscriviStudenteACorso(matricola, corsoDaTendina)) {
			this.areaDiTesto.appendText("Errore durante l'iscrizione al corso");
			return;  } 
    	else {
			this.areaDiTesto.appendText("Studente iscritto al corso!"); }
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.areaDiTesto.clear();
    	this.tfCognomeStudente.clear();
    	this.tfMatricolaStudente.clear();
    	this.tfNomeStudente.clear(); 
    	this.cbCorsi.getSelectionModel().clearSelection();}
    
    public void setModel(Model model) {
    	this.model = model;
    	corsi = model.getCorsi();
    	cbCorsi.getItems().addAll(corsi);
    	cbCorsi.getItems().add(null); }

    @FXML
    void initialize() {
        assert areaDiTesto != null : "fx:id=\"areaDiTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bCercaCorsi != null : "fx:id=\"bCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bCercaIscrittiCorso != null : "fx:id=\"bCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bErgo != null : "fx:id=\"bErgo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bIscrivi != null : "fx:id=\"bIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bReset != null : "fx:id=\"bReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbCorsi != null : "fx:id=\"cbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tfCognomeStudente != null : "fx:id=\"tfCognomeStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tfMatricolaStudente != null : "fx:id=\"tfMatricolaStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tfNomeStudente != null : "fx:id=\"tfNomeStudente\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}

