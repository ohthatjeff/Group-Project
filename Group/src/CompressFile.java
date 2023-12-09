import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public final class CompressFile {

    public static void compress(File inFile, File outFile) throws IOException {
        writeToFile(new HuffmanTree(inFile), new BufferedReader(new FileReader(inFile)), outFile);
    }

    private static void writeToFile(HuffmanTree tree, BufferedReader input, File output) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(output));
        BitOutputStream bitOut = new BitOutputStream(output, true);
        Map<Character, String> codes = tree.getHuffmanCodes();

        objOut.writeObject(tree);

        while (input.ready()) {
            char c = (char) input.read();
            bitOut.writeBits(codes.get(c));
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
