// вариант №2 -по схеме DSA

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        DSA dsa = new DSA(32);
        BigInteger[] rs = dsa.signMessage("D:\\FCP\\SEM6\\KIOKI\\Lab5\\LW5\\input.txt");
        System.out.println(dsa.verifySignature("D:\\FCP\\SEM6\\KIOKI\\Lab5\\LW5\\input.txt", rs[0], rs[1]));

        try {
            FileWriter writer = new FileWriter("D:\\FCP\\SEM6\\KIOKI\\Lab5\\LW5\\output.txt");
            writer.write("r: " + rs[0].toString());
            writer.write("\n");
            writer.write("s: " + rs[1].toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}