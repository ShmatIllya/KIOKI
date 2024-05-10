import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JOAATHash {
    public static int calculateHash(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        String input = sb.toString();
        byte[] bytes = input.getBytes();
        int length = bytes.length;
        int index = 0;
        int hash = 0;

        while (length-- > 0) {
            hash += (bytes[index++] & 0xff); // & 0xff sets result to the (unsigned) value
            hash += (hash << 10);
            hash ^= (hash >>> 6); // >>> because (unsigned) value
        }

        hash += (hash << 3);
        hash ^= (hash >>> 11);
        hash += (hash << 15);

        return hash;
    }
}
