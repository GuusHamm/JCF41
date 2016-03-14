import java.util.*;

/**
 * Created by GuusHamm on 10-3-2016.
 */
public class Huffman {
	private HuffmanTree buildTree(LinkedHashMap<Character,Integer> occurences){
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
		occurences.entrySet().stream().filter(entry -> entry.getValue() > 0).forEach(entry -> {
			trees.offer(new HuffmanLeaf(entry.getValue(), entry.getKey()));
		});


		while (trees.size() > 1){
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();

			trees.offer(new HuffmanKnot(a,b));
		}
		return trees.poll();
	}

	public HashMap<Character,BitSet> generateReferenceTable(HuffmanTree tree){
		return generateReferenceTable(tree,new StringBuilder(),new HashMap<>());
	}

	private HashMap<Character,BitSet> generateReferenceTable(HuffmanTree tree, StringBuilder prefix, HashMap<Character,BitSet> refTable) {
		assert tree != null;

		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf)tree;

			System.out.println(String.format("%s\t%d\t%s",leaf.getValue(), leaf.getFrequency(), prefix.toString()));

			BitSet bitSet = new BitSet();
			for (int i = 0; i < prefix.length(); i++){
				if (prefix.charAt(i) == '1'){
					bitSet.set(i,true);
				}
			}

			refTable.put(leaf.getValue(),bitSet);

		} else if (tree instanceof HuffmanKnot) {
			HuffmanKnot knot = (HuffmanKnot)tree;

			// traverse left
			prefix.append('0');
			generateReferenceTable(knot.getLeftTree(), prefix,refTable);
			prefix.deleteCharAt(prefix.length()-1);

			// traverse right
			prefix.append('1');
			generateReferenceTable(knot.getRightTree(), prefix, refTable);
			prefix.deleteCharAt(prefix.length()-1);
		}
		return refTable;
	}

	public HuffmanTree generateTree(String input){
		LinkedHashMap<Character,Integer> occurences = new LinkedHashMap<>();

		// count the number of times a character appears in the input
		for (char character : input.toCharArray()){
			if(! occurences.containsKey(character)) occurences.put(character,0);

			occurences.put(character,occurences.get(character) + 1);
		}

		// sort the list based on the number of times a character is present
		List<Map.Entry<Character, Integer>> sortedOccurencesList = new ArrayList<>(occurences.entrySet());
		occurences.clear();
		Collections.sort(sortedOccurencesList, (entry1, entry2) -> entry1.getValue().compareTo( entry2.getValue() ));

		for (Map.Entry<Character,Integer> entry : sortedOccurencesList){
			occurences.put(entry.getKey(),entry.getValue());
		}

		// create one tree for all the characters and return it.
		return buildTree(occurences);
	}

	public List<BitSet> encodeString(String input, HashMap<Character,BitSet> table){
		LinkedList<BitSet> bitSets = new LinkedList<>();

		for (Character character : input.toCharArray()){
			assert table.containsKey(character);
			bitSets.add(table.get(character));
		}

		return bitSets;
	}

	public String decodeString(List<BitSet> bitSets,HashMap<BitSet, Character> table){
		StringBuilder result = new StringBuilder();

		for (BitSet bitSet : bitSets){
			assert table.containsKey(bitSet);
			result.append(table.get(bitSet));
		}

		return result.toString();
	}
}
