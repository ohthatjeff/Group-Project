public class Leaf extends Node {
    
    private final char CHARACTER;

    public Leaf(char character, int frequency) {
        super(frequency);
        this.CHARACTER = character;
    }

    public char getCharacter() {
        return CHARACTER;
    }
}
