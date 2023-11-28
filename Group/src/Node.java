/**
 * frequency is the occurence of a character in list
 * parent nodes store the sum of frequency of children
 * 
 * @since 11-27-2023
 * @author Terry Ton, Esteban Madrigal , Manvir Hansra, Kushaan Naskar, Vinh Tran 
 * 
 */
public class Node implements Comparable<Node>{
    private Node left;
    private Node right;
    private int frequency;
    
    /**
     * updates frequency to be sum between left and right nodes
     * @param leftNode
     * @param rightNode
     */
    public Node(Node leftNode, Node rightNode) {
        this.left = leftNode;
        this.right = rightNode;
        this.frequency = leftNode.frequency + rightNode.frequency;
    }

    public Node(int frequency) {
        this.frequency = frequency;
    }
    
    /**
     * @param node
     * @return whether this.frequency is larger than node.frequency
     */
    @Override
    public int compareTo(Node node) {
        return Integer.compare(frequency, node.frequency);
    }

    /**
     * @return node to the left
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * @return node to the right
     */
    public Node getRight() {
        return this.right;
    }
}
