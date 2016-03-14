import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GuusHamm on 14-3-2016.
 */
public class HuffmanSerializable implements Serializable {
	private List<BitSet> bitSets;
	private Map<BitSet,Character> reverseTable;

	public HuffmanSerializable(List<BitSet> bitSets, Map<Character,BitSet> table) {
		this.bitSets = bitSets;

		reverseTable = new HashMap<>();
		for (Map.Entry<Character,BitSet> entry: table.entrySet()){
			reverseTable.put(entry.getValue(),entry.getKey());
		}
	}

	public List<BitSet> getBitSets() {
		return bitSets;
	}

	public Map<BitSet, Character> getReverseTable() {
		return reverseTable;
	}
}
