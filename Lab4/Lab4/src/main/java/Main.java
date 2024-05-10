import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int FNV1AHash(String input)
    {
 int FNV_prime = 0x1000193;
 int FNV_offset_basic = 0x811C9DC5;
        int hash = FNV_offset_basic;
        for (char item: input.toCharArray())
        {
            char byte_of_data = item;
            hash ^= byte_of_data;
            hash *= FNV_prime;
        }
        return hash;
    }
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\FCP\\SEM6\\KIOKI\\Lab4\\data.txt"));
        List<String> arr = new ArrayList<String>();
        List<String> arr2 = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null)
        {
            arr.add(line);
        }
        reader.close();
        for(Object s: arr)
        {
            arr2.add(Integer.toHexString(FNV1AHash((String) s)));
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\FCP\\SEM6\\KIOKI\\Lab4\\data2.txt"));
        for(Object s: arr2)
        {
            writer.write("0x" + ((String) s).toUpperCase() + "\n");
        }
        writer.close();
    }
}
