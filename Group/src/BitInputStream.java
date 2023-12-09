import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * reads bits from passed file
 * @since 11-27-2023
 * @author Terry Ton, Esteban Madrigal , Manvir Hansra,
 */
public class BitInputStream {
    private InputStream input; // file to read from
    private byte currentByte;
    private int totalBits;
    private int bitsRead;
    private int bitToRead = 0;
    private boolean firstRead = true;

    public BitInputStream(InputStream input) throws IOException {
        this.input = input;
        this.totalBits = input.available() * 8;
    }

    /**
     * returns the amount of bits left to be read
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
            // number of bits read is total amount
            throw new EOFException();
        } else if (firstRead) {
            // bit is first to be read
            currentByte = (byte) input.read();
            firstRead = false;
        }
        // assign 0 or 1 to c
        char c = ((currentByte << bitToRead) & 0x80) == 0 ? '0' : '1';

        bitToRead++; // increment bits to read
        bitsRead++; // increment amount of bits read

        if (bitToRead == 8) {
            // full byte has been read
            currentByte = (byte) input.read(); // read a new byte
            bitToRead = 0; // set bits to read at 0
        }

        // return the bit
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
