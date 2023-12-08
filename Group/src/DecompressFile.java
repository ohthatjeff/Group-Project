import java.io.*;

/**
* This class implements Decompression given a compressed input file. 
* @since 11-27-2023
* @author Terry Ton, Esteban Madrigal , Manvir Hansra, Kushaan Naskar, Vinh Tran 
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
        FileInputStream inputStream = new FileInputStream(in);
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        HuffmanTree huffmanTree = (HuffmanTree) objInputStream.readObject();
        BitInputStream bitInputStream = new BitInputStream(inputStream);

        String decodeStr = huffmanTree.decode(bitInputStream.readBits());

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(out))) {
            writer.write(decodeStr);
        }

        objInputStream.close();
        bitInputStream.close();
    }
}