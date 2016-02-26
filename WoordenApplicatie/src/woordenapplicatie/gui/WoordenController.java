package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import woordenapplicatie.WoordenTeller;

import java.net.URL;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

	private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n" +
			"Hoedje van, hoedje van\n" +
			"Een, twee, drie, vier\n" +
			"Hoedje van papier\n" +
			"\n" +
			"Heb je dan geen hoedje meer\n" +
			"Maak er één van bordpapier\n" +
			"Eén, twee, drie, vier\n" +
			"Hoedje van papier\n" +
			"\n" +
			"Een, twee, drie, vier\n" +
			"Hoedje van, hoedje van\n" +
			"Een, twee, drie, vier\n" +
			"Hoedje van papier\n" +
			"\n" +
			"En als het hoedje dan niet past\n" +
			"Zetten we 't in de glazenkas\n" +
			"Een, twee, drie, vier\n" +
			"Hoedje van papier";

	private WoordenTeller woordenTeller;

	@FXML
	private Button btAantal;
	@FXML
	private TextArea taInput;
	@FXML
	private Button btSorteer;
	@FXML
	private Button btFrequentie;
	@FXML
	private Button btConcordantie;
	@FXML
	private TextArea taOutput;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		taInput.setText(DEFAULT_TEXT);
		woordenTeller = new WoordenTeller();
	}


	@FXML
	private void aantalAction(ActionEvent event) {
		woordenTeller.setInput(taInput.getText());
		int totalWords = woordenTeller.getCountOccurences();
		int uniqueWords = woordenTeller.getUniqueOccurences();
		taOutput.setText(String.format("Total Words: %d\nUnique Words: %d", totalWords, uniqueWords));
	}

	@FXML
	private void sorteerAction(ActionEvent event) {
		woordenTeller.setInput(taInput.getText());
		StringBuilder reversedWords = new StringBuilder();
		for (String word : woordenTeller.sortReverse()) {
			reversedWords.append(word + "\n");
		}
		taOutput.setText(reversedWords.toString());
	}

	@FXML
	private void frequentieAction(ActionEvent event) {
		woordenTeller.setInput(taInput.getText());
		StringBuilder result = new StringBuilder();
		HashMap<String, Integer> frequency = (LinkedHashMap<String, Integer>) woordenTeller.getFrequency();
		for (String key : frequency.keySet()) {
			result.append(String.format("%s : %d\n", key, frequency.get(key)));
		}
		taOutput.setText(result.toString());
	}

	@FXML
	private void concordatieAction(ActionEvent event) {
		woordenTeller.setInput(taInput.getText());
		StringBuilder concordantieString = new StringBuilder();

		TreeMap<String, Set<Integer>> concordantie = (TreeMap<String, Set<Integer>>) woordenTeller.getConcordantie();
		for (String key : concordantie.keySet()) {
			concordantieString.append(String.format("%s : %s\n", key, concordantie.get(key)));
		}
		taOutput.setText(concordantieString.toString());
	}

}
