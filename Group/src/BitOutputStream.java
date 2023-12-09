import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Creates or uses an existing file, clears it, and then writes bits to the file
 * @since 11-27-2023
 * @author Terry Ton, Esteban Madrigal , Manvir Hansra, Kushaan Naskar, Vinh Tran 
 */
public class BitOutputStream {
    private OutputStream output; // file to output to
    private int bitCount = 0;
    private byte currentByte = 0;

    public BitOutputStream(File file, boolean append) throws FileNotFoundException {
        this.output = new FileOutputStream(file, append);
    }

    /**
     * Writes a singular bit to the output stream
     * @param bit the bit to be written
     * @throws IOException
     */
    public void writeBit(char bit) throws IOException {
        currentByte <<= 1; // shift byte one bit left
        currentByte |= (bit - '0'); // subtracting 0 (48) returns 0 or 1 integer

        bitCount++; // increment bit count

        if (bitCount == 8) {
            // full byte has been written
            bitCount = 0; // reset bitcount
            output.write(currentByte); // write the current byte
        }
    }

    /**
     * Writes multiple bits to the output stream
     * @param bitString bits to be written
     * @throws IOException
     */
    public void writeBits(String bitString) throws IOException{
        for (char c : bitString.toCharArray()) {
            writeBit(c);
        }
    }

    /**
     * Closes the file and fills in 0s for the remaining bits in the current byte if needed
     * @throws IOException
     */
    public void close() throws IOException {
        if (bitCount != 8 && bitCount != 0) {
            // there is 1 or 7 bits needed to fill
            while (bitCount < 8) {
                // shift current byte 1 left until byte is 8 bits
                currentByte <<= 1; 
                bitCount++;
            }
            // byte has been filled 
            output.write(currentByte);
        }
        output.close();
    }
}
