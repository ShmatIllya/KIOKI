package methods;

import java.util.Objects;

public class SubstitutionPlus {

    static String[] PHRASE = "TKACHUK ALEKSEI IVANOVICH".split("");
    static int K = 10;
    static String[] DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
    static int DICTIONARY_LENGTH = DICTIONARY.length;

    public static void main(String[] args) {
        StringBuilder encodedString = new StringBuilder();
        int index = 0;

        System.out.print("Original phrase: ");
        for (String i : PHRASE)
            System.out.print(i);
        System.out.println();

        for (String s : PHRASE)
        {
            if (Objects.equals(s, " "))
            {
                encodedString.append(" ");
                continue;
            }

            for (int j = 0; j < DICTIONARY_LENGTH; j++)
                if (Objects.equals(s, DICTIONARY[j]))
                {;
                    index = (j + K) % DICTIONARY_LENGTH;
                    break;
                }

            encodedString.append(DICTIONARY[index]);
        }

        System.out.println("Encoded string: " + encodedString);



        String[] b = encodedString.toString().split("");
        StringBuilder decodedString = new StringBuilder();

        for (String s : b) {
            if (Objects.equals(s, " ")) {
                decodedString.append(" ");
                continue;
            }

            for (int j = 0; j < DICTIONARY_LENGTH; j++)
                if (Objects.equals(s, DICTIONARY[j])) {
                    index = (j + DICTIONARY_LENGTH - K) % DICTIONARY_LENGTH;
                    break;
                }

            decodedString.append(DICTIONARY[index]);
        }

        System.out.println("Decoded string: " + decodedString);
    }
}
