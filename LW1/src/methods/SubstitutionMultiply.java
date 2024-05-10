package methods;

import java.util.Objects;
import java.util.Random;

public class SubstitutionMultiply {

    static String[] PHRASE = "TKACHUK ALEKSEI IVANOVICH".split("");
    static int KeyCoding;
    static int KeyEncoding;
    static String[] DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
    static int DICTIONARY_LENGTH = DICTIONARY.length;

    public static void main(String[] args) {
        StringBuilder resultCoding = new StringBuilder();
        int index = 0;


        System.out.print("Original phrase: ");
        for (String i : PHRASE)
            System.out.print(i);
        System.out.println();


        KeyCoding = 49;
        KeyEncoding = 17;

        for (String i : PHRASE) {
            if (Objects.equals(i, " ")) {
                resultCoding.append(" ");
                continue;
            }

            for (int j = 0; j < DICTIONARY_LENGTH; j++)
                if (Objects.equals(i, DICTIONARY[j])) {
                    index = (j * KeyCoding) % DICTIONARY_LENGTH;
                    break;
                }

            resultCoding.append(DICTIONARY[index]);
        }

        System.out.println("Encoded string: " + resultCoding);


        String[] temp = resultCoding.toString().split("");
        StringBuilder resultEncoding = new StringBuilder();

        for (String i : temp) {
            if (Objects.equals(i, " ")) {
                resultEncoding.append(" ");
                continue;
            }

            for (int j = 0; j < DICTIONARY_LENGTH; j++)
                if (Objects.equals(i, DICTIONARY[j])) {
                    index = (j * KeyEncoding) % DICTIONARY_LENGTH;
                    break;
                }

            resultEncoding.append(DICTIONARY[index]);
        }

        System.out.println("Decoded string: " + resultEncoding);
    }
}
