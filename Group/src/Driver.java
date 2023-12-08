import java.io.File;
import java.io.IOException;

/**
* Driver to test Compression and Decompression using Huffman Coding. 
* @since 11-27-2023
* @author Terry Ton, Esteban Madrigal , Manvir Hansra, Kushaan Naskar, Vinh Tran 
*/
public class Driver {
    public static void main(String[] args) throws IOException {
        CompressFile.compress(new File("sourceFile.txt"), new File("compressedFile.txt"));        
        try {
            DecompressFile.decompress(new File("compressedFile.txt"), new File("decompressedOutput.txt"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }        
    }
}
