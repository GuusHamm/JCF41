/**
 * Created by GuusHamm on 10-3-2016.
 */
public class HuffmanLeaf extends HuffmanTree{
	private char value;


	public HuffmanLeaf(int frequency, char value) {
		super(frequency);
		this.value = value;
	}

	public char getValue() {
		return value;
	}
}
