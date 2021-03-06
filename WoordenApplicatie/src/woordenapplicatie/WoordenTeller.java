package woordenapplicatie;

import java.text.Normalizer;
import java.util.*;

/**
 * Created by guushamm on 22-2-16.
 */
public class WoordenTeller {
	private String input;

	private HashMap<String, Integer> occurences;

	public WoordenTeller() {
		occurences = new HashMap<>();
		input = "";
	}

	public void setInput(String rawInput){
		String normalizedInput = stringNormalizer(rawInput);
		if (this.input.equals(normalizedInput)){
			return;
		}
		this.input = normalizedInput;
		for (String word : input.split("[ ,. \\n]+")) {
			addOccurence(word.trim(),occurences);
		}
	}

	private String stringNormalizer(String input){
		String normalizedWord = input;

//		http://stackoverflow.com/a/8523728
		normalizedWord = Normalizer.normalize(normalizedWord, Normalizer.Form.NFD);
		normalizedWord = normalizedWord.replaceAll("[^\\p{ASCII}]", "");

		normalizedWord = normalizedWord.toLowerCase();
		return normalizedWord;
	}

	private int addOccurence(String word,Map<String,Integer> occurences){
		if (word.isEmpty()) {
			return 0;
		}

		if (occurences.containsKey(word)){
			occurences.put(word,occurences.get(word) + 1);
		}else{
			occurences.put(word,1);
		}

		return occurences.get(word);
	}

	public int getCountOccurences(){
		return occurences.values().stream().map((count) -> count).reduce((x,y)-> x + y).get();
	}

	public int getUniqueOccurences(){
		return occurences.keySet().size();
	}

	public Collection<String> sortReverse(){
		List<String> reversed = new ArrayList<>();

		reversed.addAll(occurences.keySet());

		// http://stackoverflow.com/a/13780089
		reversed.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

		return reversed;
	}

	public Map<String,Integer> getFrequency(){
		HashMap<String,Integer> frequency = new LinkedHashMap<>();
		List<Map.Entry<String, Integer>> sortedOccurences = new ArrayList<>(occurences.entrySet());
		Collections.sort(sortedOccurences, (entry1, entry2) -> entry1.getValue().compareTo( entry2.getValue() ));
		for (Map.Entry<String,Integer> entry : sortedOccurences){
			frequency.put(entry.getKey(),entry.getValue());
		}
		return frequency;
	}

	public Map<String, Set<Integer>> getConcordantie(){
		TreeMap<String,Set<Integer>> concordanatie = new TreeMap<>();
		int lineCounter = 0;
		for (String line : input.split("\n")){
			lineCounter ++;
			for (String word : line.split("[ ,. \\n]+")) {

				String normalizedString = stringNormalizer(word);

				if (word.isEmpty()){
					continue;
				}
				if (!concordanatie.containsKey(normalizedString)) {
					concordanatie.put(normalizedString, new HashSet<>());
				}

				Set<Integer> set = concordanatie.get(normalizedString);
				set.add(lineCounter);
			}
		}


		return concordanatie;
	}
}
