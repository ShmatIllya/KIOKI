package methods;

import java.util.Arrays;
import java.util.Objects;

public class Phrase {

    static String[] KEY = "СПРАВЕДЛИВОСЬ".split("");
    static String[] LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя".split("");
    static String[] PHRASE = "ТКАЧУК АЛЕКСЕЙ УСТАЛ".split("");

    public static void main(String[] args) {
        int[] mas = new int[KEY.length];
        String[][] cipherGrid;
        String[] temp;
        int index = 0;

        System.out.println("Фраза, которую нужно зашифровать: ");
        for (String i : PHRASE) System.out.print(i);
        System.out.println("\n");


        for (String i : LETTERS) // Расставляем приоритет букв в ключевом слове, по которому в дальнейшем будем шифровать
            for (int j = 0; j < KEY.length; j++)
                if (Objects.equals(i, KEY[j])) {
                    mas[j] = index;
                    index++;
                }

        // mas[j] - массив, где каждый mas[j] хранит в себе номер, под которым должна стоять буква

        for (int i : mas) System.out.print(i + " ");
        System.out.println("\n");



        if (PHRASE.length % KEY.length == 0) cipherGrid = new String[PHRASE.length / KEY.length][KEY.length];
        else cipherGrid = new String[1 + (PHRASE.length / KEY.length)][KEY.length]; // добавляем строку


        // Заполняем таблицу
        index = 0;

        for (int i = 0; i < cipherGrid.length; i++)
            for (int j = 0; j < KEY.length; j++) {
                if (PHRASE.length == index) cipherGrid[i][j] = " "; //
                else {
                    cipherGrid[i][j] = PHRASE[index];
                    index++;
                }
            }




        // Делаем блок
        temp = new String[KEY.length];

        for (String[] i : cipherGrid)
        {
            for (int j = 0; j < KEY.length; j++)
                temp[mas[j]] = i[j];

            System.arraycopy(temp, 0, i, 0, KEY.length);
            // Из temp в i, которое содержит ссылку на выбранную строку cipherGrid
        }

        // Вывод в строку
        for (String[] i : cipherGrid)
            for (String j : i)
                System.out.print(j);
        System.out.println();
        System.out.println(Arrays.deepToString(cipherGrid));


       // Декодирование
        for (String[] phrase : cipherGrid) {
            for (int j = 0; j < KEY.length; j++)
                temp[j] = phrase[mas[j]];
            System.arraycopy(temp, 0, phrase, 0, KEY.length);
        }

        for (String[] i : cipherGrid)
            for (String j : i)
                System.out.print(j);
        System.out.println();
    }
}
