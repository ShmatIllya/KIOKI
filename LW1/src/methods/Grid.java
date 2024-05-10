package methods;

import java.util.Objects;

public class Grid {

    static String[] PHRASE = "Ткачук Алексей".split("");
    static String[] text = new String[16];

    static int gridSize = 4;

    public static void main(String[] args) {
        String[][] encryptedGrid;
        String[][] temp = new String[gridSize][gridSize];
        int index = 0;
        int check = 0;

        encryptedGrid = new String[gridSize][gridSize];

        for (int k = 0; k < text.length; k++)
        {
            if (check < PHRASE.length)
            {
                text[k] = PHRASE[k];
            }
            else text[k] = "*";
            check++;
        }

        for (int j = 0; j < gridSize; j++) {
            if (index == text.length) break;
            encryptedGrid[0][0] = text[index];
            index++;
            if (index == text.length) break;
            encryptedGrid[1][3] = text[index];
            index++;
            if (index == text.length) break;
            encryptedGrid[3][1] = text[index];
            index++;
            if (index == text.length) break;
            encryptedGrid[1][1] = text[index];
            index++;



            // Строка столбец
            // k = 0 --> m = 0/1/2/3 --> temp[0/1/2/3][3] = mas [0][0/1/2/3]
            // k = 1 --> m = 0/1/2/3 --> temp[0/1/2/3][2] = mas [1][0/1/2/3]
            // 1 в 4
            for (int k = 0; k < gridSize; k++) {
                for (int m = 0; m < gridSize; m++) {
                    temp[m][gridSize - k - 1] = encryptedGrid[k][m];
                }
            }

            for (int k = 0; k < gridSize; k++) {
                System.arraycopy(temp[k], 0, encryptedGrid[k], 0, gridSize);
            }
        }

        // вывод
        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                System.out.print(encryptedGrid[j][k] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        StringBuilder encryptedString = new StringBuilder();

        // Заносим из массива в строку
        for (String[] mas : encryptedGrid)
            for (int j = 0; j < gridSize; j++)
                encryptedString.append(mas[j]);

        System.out.println(encryptedString);

        String[] d = encryptedString.toString().split("");

        // Декодирование
        index = 0;

        String[][] mas1;
        mas1 = new String[gridSize][gridSize];


        // Заполнение зашифрованным текстом
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++) {
                mas1[i][j] = d[index];
                index++;
            }


        StringBuilder resultEncoding = new StringBuilder();


        index = 0;


        for (int j = 0; j < gridSize; j++) {
            if (index == d.length) break;
            resultEncoding.append(mas1[0][0]);

            index++;
            if (index == d.length) break;
            resultEncoding.append(mas1[1][3]);

            index++;
            if (index == d.length) break;
            resultEncoding.append(mas1[3][1]);

            index++;
            if (index == d.length) break;
            resultEncoding.append(mas1[1][1]);

            index++;

            for (int k = 0; k < gridSize; k++) {
                for (int m = 0; m < gridSize; m++) {
                    temp[m][gridSize - k - 1] = mas1[k][m];
                }
            }
            for (int k = 0; k < gridSize; k++) {
                System.arraycopy(temp[k], 0, mas1[k], 0, gridSize);
            }
        }


        //  StringBuilder k = resultEncoding.replace('*', 'k');
        String[] res = resultEncoding.toString().split("");

        for (String i : res){
            if (Objects.equals(i, "*")) continue;
            System.out.print(i);
        }

    }
}
