package methods;
import java.util.Objects;

public class Grid5x5 {

    static String[] PHRASE = "TKACHUK ALEKSEI TIRED".split("");
    static String[] text = new String[24];

    static int gridSize = 5;

    public static void main(String[] args) {
        String[][] encryptionGrid;
        String[][] temp2 = new String[gridSize][gridSize];
        int index = 0;
        int check = 0;
        String symbol = "*";

        encryptionGrid = new String[gridSize][gridSize];

        if (gridSize % 2 != 0) {
            encryptionGrid[gridSize / 2][gridSize / 2] = symbol;
        }

        for (int k = 0; k < text.length; k++)
        {
            if (check < PHRASE.length)
            {
                text[k] = PHRASE[k];
            }
            else text[k] = symbol;
            check++;
        }

        for (int j = 0; j < gridSize; j++) {
            if (index == text.length) break;
            encryptionGrid[0][0] = text[index];
            index++;
            if (index == text.length) break;
            encryptionGrid[4][2] = text[index];
            index++;
            if (index == text.length) break;
            encryptionGrid[3][2] = text[index];
            index++;
            if (index == text.length) break;
            encryptionGrid[1][1] = text[index];
            index++;
            if (index == text.length) break;
            encryptionGrid[1][4] = text[index];
            index++;
            if (index == text.length) break;
            encryptionGrid[3][4] = text[index];
            index++;

//            for (int p = 0; p < gridSize; p++) {
//                for (int o = 0; o < gridSize; o++) {
//                    System.out.print(encryptionGrid[p][o] + "\t");
//                }
//                System.out.println();
//            }
//            System.out.println();

            for (int k = 0; k < gridSize; k++) {
                for (int m = 0; m < gridSize; m++) {
                    temp2[m][gridSize - k - 1] = encryptionGrid[k][m];
                }
            }


            for (int k = 0; k < gridSize; k++) {
                System.arraycopy(temp2[k], 0, encryptionGrid[k], 0, gridSize);
            }
        }

        // вывод
        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                System.out.print(encryptionGrid[j][k] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        StringBuilder encryptedString = new StringBuilder();

        // Заносим из массива в строку
        for (String[] mas : encryptionGrid)
            for (int j = 0; j < gridSize; j++)
                encryptedString.append(mas[j]);

        System.out.println(encryptedString);

        String[] temp1 = encryptedString.toString().split("");

        // Декодирование
        index = 0;

        String[][] decryptionGrid;
        decryptionGrid = new String[gridSize][gridSize];


        // Заполнение зашифрованным текстом
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++) {
                decryptionGrid[i][j] = temp1[index];
                index++;
            }


        StringBuilder decryptedString = new StringBuilder();


        index = 0;


        for (int j = 0; j < gridSize; j++) {
            if (index == temp1.length) break;
            decryptedString.append(decryptionGrid[0][0]);
            index++;
            if (index == temp1.length) break;
            decryptedString.append(decryptionGrid[4][2]);
            index++;
            if (index == temp1.length) break;
            decryptedString.append(decryptionGrid[3][2]);
            index++;
            if (index == temp1.length) break;
            decryptedString.append(decryptionGrid[1][1]);
            index++;
            if (index == temp1.length) break;
            decryptedString.append(decryptionGrid[1][4]);
            index++;
            if (index == temp1.length) break;
            decryptedString.append(decryptionGrid[3][4]);
            index++;

//            for (int p = 0; p < gridSize; p++) {
//                for (int o = 0; o < gridSize; o++) {
//                    System.out.print(decryptionGrid[p][o] + "\t");
//                }
//                System.out.println();
//            }
//            System.out.println();

            for (int k = 0; k < gridSize; k++) {
                for (int m = 0; m < gridSize; m++) {
                    temp2[m][gridSize - k - 1] = decryptionGrid[k][m];
                }
            }
            for (int k = 0; k < gridSize; k++) {
                System.arraycopy(temp2[k], 0, decryptionGrid[k], 0, gridSize);
            }
        }


        //  StringBuilder k = decryptedString.replace('*', 'k');
        String[] result = decryptedString.toString().split("");
        result[result.length - 1] = "*";

        for (String i : result){
            if (Objects.equals(i, symbol)) continue;
            System.out.print(i);
        }

    }
}

