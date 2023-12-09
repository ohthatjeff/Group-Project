import java.io.*;

/**
* This class implements Decompression given a compressed input file. 
* @since 11-27-2023
* @author  Kushaan Naskar, Vinh Tran 
*/
public class DecompressFile {
	
	/**
	 * Decompresses the given input file using Huffman coding and writes the decompressed data 
	 * to the specified output file.
	 *
	 * @param in  Compressed file to be decompressed.
	 * @param out Output file to write the decompressed data to.
	 * @throws IOException Exception thrown if an error is detected during reading or writing of the file. 
	 * @throws ClassNotFoundException Exception thrown if HuffmanTree class isn't found.
	 */	
    public static void decompress(File in, File out) throws IOException, ClassNotFoundException {
        // file input stream from sourceFile
        FileInputStream inputStream = new FileInputStream(in);
        // object input stream from sourceFile
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        // huffman tree object from sourceFile
        HuffmanTree huffmanTree = (HuffmanTree) objInputStream.readObject();
        BitInputStream bitInputStream = new BitInputStream(inputStream);

        String decodeStr = huffmanTree.decode(bitInputStream.readBits());

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(out))) {
            writer.write(decodeStr);
        }

        objInputStream.close();
        bitInputStream.close();
    }

    
 
    public static void main(String[] args) {
        try {
            DecompressFile.decompress(new File(args[0]), new File(args[1]));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }        
    }
}