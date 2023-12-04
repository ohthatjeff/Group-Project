import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Takes a file as input and reads the bits from it
 * @author Manvir Hansra
 */
public class BitInputStream {
    private InputStream input;
    private byte currentByte;
    private int totalBits;
    private int bitsRead;
    private int bitToRead = 0;
    private boolean firstRead = true;

    public BitInputStream(InputStream input) throws IOException {
        this.input = input;
        this.totalBits = input.available()*8;
    }

    /**
     * Returns the total amount of bits in the file
     * @return total number of bits in the file 
     * @throws IOException
     */    
    public int available() throws IOException {
        return totalBits-bitsRead;
    }

    /**
     * Returns the next bit to be read in the file as a character
     * @return the next bit in the file as a char
     * @throws IOException
     */
    public char readBit() throws IOException {
        if (available() == 0) {
            throw new EOFException();
        } else if (firstRead) {
            currentByte = (byte) input.read();
            firstRead = false;
        }
        char c = ((currentByte << bitToRead) & 0x80) == 0 ? '0' : '1';

        bitToRead++;
        bitsRead++;

        if (bitToRead == 8) {
            currentByte = (byte) input.read();
            bitToRead = 0;
        }
        return c;
    }

    /**
     * Returns all remaining bits in the file as a string
     * @return the remaining bits in the file as a string
     * @throws IOException
     */
    public String readBits() throws IOException {
        StringBuilder bitString = new StringBuilder();

        while (available() > 0) {
            bitString.append(readBit());
        }

        return bitString.toString();
    }

    /**
     * Closes all files
     * @throws IOException
     */
    public void close() throws IOException {
        input.close();
    }

}
