/**
 * Created by GuusHamm on 7-3-2016.
 */
public class HuffmanKnot extends HuffmanTree{
	private HuffmanTree leftTree;
	private HuffmanTree rightTree;

	public HuffmanKnot(HuffmanTree leftTree, HuffmanTree rightTree) {
		super(leftTree.getFrequency() + rightTree.getFrequency());
		this.leftTree = leftTree;
		this.rightTree = rightTree;
	}

	public HuffmanTree getLeftTree() {
		return leftTree;
	}

	public HuffmanTree getRightTree() {
		return rightTree;
	}
}
