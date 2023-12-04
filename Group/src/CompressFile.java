import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public final class CompressFile {

    private CompressFile() {}

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

        objOut.close();
        bitOut.close();
        input.close();
    }
}
