import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * Fill this file.
 */

public class HuffmanEncoder implements Serializable {
    private Node root;
    private final File file;
    Map<Character, Integer> charFrequencies = new HashMap<>();
    private final Map<Character, String> huffmanCodes;

    public HuffmanEncoder(File inputFile) throws IOException{
        this.file = inputFile;
        populateMap();
        huffmanCodes = new HashMap<>();
    }

    /**
     * Keep counts of characters and their frequencies in the text
     */
    private void populateMap() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Character c;

        while (reader.ready()) {
            c = (char) reader.read();
            if (charFrequencies.containsKey(c)) {
                charFrequencies.put(c, charFrequencies.get(c) + 1);
            } else {
                charFrequencies.put(c, 1);
            }
        }

        reader.close();
    }

    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();
        charFrequencies.forEach((character, frequency) -> queue.add(new Leaf(character, frequency)));
        while (queue.size() > 1) {
            queue.add(new  Node(queue.poll(), queue.poll()));
        }
        generateHuffmanCodes(root = queue.poll(), "");

        return null;
    }

    /**
     * 
     * @param node
     * @param code
     */
    private void generateHuffmanCodes(Node node, String code) {
        if (node instanceof Leaf) {
            huffmanCodes.put(((Leaf)node).getCharacter() , code);
            return;
        }
        generateHuffmanCodes(node.getLeft(), code.concat("0"));
        generateHuffmanCodes(node.getRight(), code.concat("1"));
    }
    
    /**
     * goes through stream of characters using the generated huffman tree to write 
     * bit representations in new file
     */
    public void writeToFile() {
        
    }
}