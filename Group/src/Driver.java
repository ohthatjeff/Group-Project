import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        CompressFile.compress(new File("input.txt"), new File("output.txt"));
    }
}
