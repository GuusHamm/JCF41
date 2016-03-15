import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by GuusHamm on 14-3-2016.
 */
public class HuffmanSerializable implements Serializable {
	private transient List<BitSet> bitSets;
	private transient Map<BitSet,Character> reverseTable;

	public HuffmanSerializable(List<BitSet> bitSets, Map<Character,BitSet> table) {
		this.bitSets = bitSets;

		reverseTable = new HashMap<>();
		for (Map.Entry<Character,BitSet> entry: table.entrySet()){
			reverseTable.put(entry.getValue(),entry.getKey());
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {

		byte[][] message = new byte[bitSets.size()][];

		for (int i=0; i < bitSets.size(); i++){
			message[i]= bitSets.get(i).toByteArray();
		}
		out.writeObject(message);

		Map<byte[],Character> byteTable = new HashMap<>();
		for (Map.Entry<BitSet,Character> entry : reverseTable.entrySet()){
			byteTable.put(entry.getKey().toByteArray(),entry.getValue());
		}
		out.writeObject(byteTable);
	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.bitSets = new LinkedList<>();

		byte[][] message = (byte[][]) in.readObject();

		for (byte[] bytes : message){
			this.bitSets.add(BitSet.valueOf(bytes));
		}

		this.reverseTable = new HashMap<>();


		Map<byte[],Character> byteTable = (HashMap<byte[],Character>) in.readObject();

		for (Map.Entry<byte[],Character> entry : byteTable.entrySet()){
			this.reverseTable.put(BitSet.valueOf(entry.getKey()),entry.getValue());
		}

	}

	public List<BitSet> getBitSets() {
		return bitSets;
	}

	public Map<BitSet, Character> getReverseTable() {
		return reverseTable;
	}
}
