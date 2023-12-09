import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
/**
 * reads from inFile and writes a compressed equal to outFile
 * @since 11-27-2023
 * @author Terry Ton, Esteban Madrigal , Manvir Hansra, Kushaan Naskar, Vinh Tran 
 */
public final class CompressFile {

    public static void compress(File inFile, File outFile) throws IOException {
        // write to outFile huffman tree object and compressed bit representations
        writeToFile(new HuffmanTree(inFile), new BufferedReader(new FileReader(inFile)), outFile);
    }

    /**
     * writes a compressed version of input to output file
     * @param tree huffman tree of input
     * @param input input file
     * @param output file to output compression results
     * @throws IOException
     */
    private static void writeToFile(HuffmanTree tree, BufferedReader input, File output) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(output));
        BitOutputStream bitOut = new BitOutputStream(output, true);
        Map<Character, String> codes = tree.getHuffmanCodes();

        // write the huffman tree object to output
        objOut.writeObject(tree);

        while (input.ready()) {
            // input is okay to read from
            char c = (char) input.read(); // c is character from input
            bitOut.writeBits(codes.get(c)); // write the bit representation of current character
        }
        bitOut.writeBits(codes.get(HuffmanTree.EOF));    //add the EOF token to the compressed file.

        objOut.close();
        bitOut.close();
        input.close();
    }

    public static void main(String[] args) throws IOException {
        compress(new File(args[0]), new File(args[1]));
    }
}
