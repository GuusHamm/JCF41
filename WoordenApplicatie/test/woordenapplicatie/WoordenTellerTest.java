package woordenapplicatie;

import org.junit.Before;

/**
 * Created by guushamm on 22-2-16.
 */
public class WoordenTellerTest {

	private static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
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

	WoordenTeller woordenTeller;

	@Before
	public void setUp() throws Exception {
		woordenTeller = new WoordenTeller(DEFAULT_TEXT);
	}
}