import java.io.*;
import java.util.BitSet;
import java.util.HashMap;

/**
 * Created by GuusHamm on 14-3-2016.
 */
public class Programm {
	public static void main(String[] args) {
		Huffman huffman = new Huffman();
		File filePath = new File(String.format("%s/huffman.bat",System.getProperty("user.home")));
		if (args[0].toLowerCase().equals("write")){
			String input = args[1];

			// using the input generate a huffman tree
			HuffmanTree tree = huffman.generateTree(input);

			// print out the tree
			System.out.println("SYMBOL\tFREQ\tHUFFMAN CODE");

			// generate the reference table
			HashMap<Character,BitSet> table = huffman.generateReferenceTable(tree);

			HuffmanSerializable huffmanSerializable = new HuffmanSerializable(huffman.encodeString(input,table),table);

			ObjectOutputStream objectOutputStream;

			try {
				objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
				objectOutputStream.writeObject(huffmanSerializable);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


		}
		if (args[0].toLowerCase().equals("read")){
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
				HuffmanSerializable huffmanSerializable = (HuffmanSerializable) objectInputStream.readObject();

				System.out.println(huffman.decodeString(huffmanSerializable.getBitSets(),(HashMap) huffmanSerializable.getReverseTable()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

//
		}



	}
}
