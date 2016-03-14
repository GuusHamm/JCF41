/**
 * Created by GuusHamm on 7-3-2016.
 */
public class HuffmanTree implements Comparable<HuffmanTree>{
	private int frequency;

	public HuffmanTree(int frequency) {
		this.frequency = frequency;
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public int compareTo(HuffmanTree o) {
		return frequency - o.frequency;
	}
}
