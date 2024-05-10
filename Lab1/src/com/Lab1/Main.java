package com.Lab1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);
    public static String F2(int choice, String[] PHRASE, int key)
    {
        int K = key;
        String[] DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
        int DICTIONARY_LENGTH = DICTIONARY.length;
        StringBuilder encodedString = new StringBuilder();
        int index = 0;
        String result = "";
        if(choice == 1) {
            for (String s : PHRASE) {
                if (Objects.equals(s, " ")) {
                    encodedString.append(" ");
                    continue;
                }

                for (int j = 0; j < DICTIONARY_LENGTH; j++)
                    if (Objects.equals(s, DICTIONARY[j])) {
                        ;
                        index = (j + K) % DICTIONARY_LENGTH;
                        break;
                    }

                encodedString.append(DICTIONARY[index]);
            }

            result = String.valueOf(encodedString);
        }

        else {
            String[] b = PHRASE;
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

            result = String.valueOf(decodedString);
        }
        return result;
    }
    public static String F1(int choice, String[] PHRASE, int key)
    {
         int KeyCoding = key;
         int KeyEncoding;
         String[] DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
         int DICTIONARY_LENGTH = DICTIONARY.length;

        StringBuilder resultCoding = new StringBuilder();
        int index = 0;
        String result = "";
        KeyEncoding = 0;
        if(choice == 1) {
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
                result = String.valueOf(resultCoding);
            }

        }
        else {
            while((KeyCoding * KeyEncoding) % DICTIONARY_LENGTH != 1)
            {
                KeyEncoding++;
            }
            String[] temp = PHRASE;
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
                result = String.valueOf(resultEncoding);
            }


        }
        return result;
    }
    //////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    public static String Reshetka(int choice, String text)
    {
        if(text.length() % 16 != 0)
        {
            while(text.length() % 16 != 0)
            {
                text += String.valueOf('#');
            }
        }
        int num_text = text.length() / 16;
        System.out.println(num_text);
        char[] text_c = text.toCharArray();
        ArrayList<int[][]> numbers_arr = new ArrayList<int[][]>();
        int num_count = 0;
        for(int i = 0; i < num_text; i++)
        {
            int[][] numbers = {{num_count,num_count + 5,num_count + 10,num_count + 12},{num_count + 14,num_count + 11,num_count + 7,num_count + 1},{num_count + 9,num_count + 15,num_count + 3,num_count + 6},{num_count + 4,num_count + 2,num_count + 13,num_count + 8}};
            numbers_arr.add(numbers);
            num_count += 16;
        }
        int counter = 0;
        String result = "";
        if(choice == 1)
        {
            int index = 0;
            while(counter < text_c.length) {
                for (int i = 0; i < 4; i++) {
                    for(int j = 0; j < 4; j++)
                    {
                        result += text_c[numbers_arr.get(index)[i][j]];
                        counter++;
                        if(counter % 16 == 0)
                        {
                            index++;
                        }
                    }
                }
            }
        }
        else
        {
            ArrayList<char[][]> symbols_arr = new ArrayList<char[][]>();
            counter = 0;
            for(int i = 0; i < num_text; i++)
            {
                char[][] temp = new char[4][4];
                for(int j = 0; j < 4; j++)
                {
                    for(int k = 0; k < 4; k++)
                    {
                        temp[j][k] = text_c[counter];
                        counter++;
                    }
                }
                symbols_arr.add(temp);
            }
            int index = 0;
            counter = 0;
            char[] res = new char[text_c.length];
            while(index < num_text) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        res[numbers_arr.get(index)[i][j]] = symbols_arr.get(index)[i][j];
                        counter++;
                        if (counter % 16 == 0) {
                            index++;
                        }
                    }
                }
            }
                for(int i = 0; i < res.length; i++) {
                    if(res[i] != '#')
                    result += String.valueOf(res[i]);
                }
        }
        return result;
    }
    public static void Relsi(int choice, String text, int key)
    {
        char[] text_c = text.toCharArray();
        int[] array_n = new int[text_c.length];
        int k = 0;
        boolean b = false;
        for(int i = 0; i < text_c.length; i ++)
        {
            if(k >= key)
            {
                k-=2;
                b = true;
            }
            array_n[i] = k;
            if(b == false) {
                k++;
            }
            else if (b == true)
            {
                k--;
            }
            if(k == 0)
            {
                b = false;
            }
        }
        if(choice == 1)
        {
            String result = "";
            int k2 = 0;
            while(k2 < key)
            {
                for (int i = 0; i < text_c.length; i++)
                {
                    if(array_n[i] == k2)
                    {
                        if(text_c[i] != '#')
                        result += String.valueOf(text_c[i]);
                    }
                }
                k2++;
            }
            System.out.println(result);
        }
        else
        {
            char[] result = new char[text_c.length];
            int k2 = 0;
            int jk = 0;
            while(k2 < key)
            {
                for (int i = 0; i < text_c.length; i++)
                {
                    if(array_n[i] == k2)
                    {
                        if(text_c[jk] != '#')
                        result[i] = text_c[jk];
                        jk++;
                    }
                }
                k2++;
            }

                System.out.println(result);
        }
    }

    public static String KeyWord(int choice, String text, String key)
    {
        String alphabet = "ABCEFGHIJKLMNOPQRSTUVWXYZ";
        char[] text_c = text.toCharArray();
        char[] alphabet_c = alphabet.toCharArray();
        char[] key_c = key.toCharArray();
        int[] numbers = new int[key_c.length];
        int current_num = 1;
        for(int i = 0; i < alphabet_c.length; i ++)
        {
            for(int j = 0; j < key_c.length; j ++)
            {
                if(Character.compare(alphabet_c[i], key_c[j]) == 0)
                {
                    numbers[j] = current_num;
                    current_num++;
                }
            }
        }
        for(int l: numbers)
        {
            System.out.println(l);
        }
        ///////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////
        if(choice == 1) {
            int num = text_c.length / key_c.length + 1;
            char[][] text_dc = new char[num][key_c.length];
            int counter = 0;
            boolean break_b = false;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < key_c.length; j++) {
                    text_dc[i][j] = text_c[counter];
                    counter++;
                    if (counter >= text_c.length) {
                        while (j + 1 < key_c.length) {
                            text_dc[i][j + 1] = '#';
                            j++;
                        }
                        break_b = true;
                        break;
                    }
                }
                if (break_b == true) {
                    break;
                }
            }
            counter = 0;
            current_num = 1;
            String result = "";
            for (int i = 0; i < num; i++) {
                while (current_num < key_c.length + 1) {
                    for (int k = 0; k < key_c.length; k++) {
                        if (numbers[k] == current_num) {
                            if (text_dc[i][k] == '#') {
                                current_num++;
                                continue;
                            }
                            result += text_dc[i][k];
                            current_num++;
                            counter++;
                            if (counter == text_c.length) {
                                return result;
                            }
                            break;
                        }
                    }
                }
                current_num = 1;
            }
            return result;
        }
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        else
        {
            int num = text_c.length / key_c.length + 1;
            if(text.length() % key_c.length != 0)
            {
                while(text.length() % key_c.length != 0)
                {
                    text += String.valueOf('#');
                }

            }
            char[] text_c2 = text.toCharArray();
            char[][] text_dc = new char[num][key_c.length];
            int counter = 0;
            boolean end_check = false;
                for(int i = 0; i < num; i++)
                {
                    current_num = 1;
                    for(int j = 0; j < key_c.length; j++)
                    {
                        text_dc[i][j] = text_c2[numbers[current_num - 1] + (i * key_c.length) - 1];
                        current_num++;
                        counter++;
                        System.out.println(text_dc[i][j]);
                        if(counter >= text_c2.length)
                        {
                           System.out.println(counter);
                           System.out.println(text_c2.length);
                           end_check = true;
                           break;
                        }
                    }
                    if(end_check)
                    {
                        break;
                    }
                }
                counter = 0;
                String result = "";
                for(int i = 0; i < num; i++)
                {
                    for (int j = 0; j< key_c.length; j++)
                    {
                        if(text_dc[i][j] != '#')
                        result += String.valueOf(text_dc[i][j]);
                        counter++;
                        if(counter >= text_c2.length)
                        {
                            return result;
                        }
                    }
                }

        }
        return "!!!";
    }
    public static int Action()
    {
        while(true) {
            System.out.println("Выберите действие:\n1.Шифрование\n2.Дешифрование\n");
            String choice = in.nextLine();
            switch(choice)
            {
                case "1":
                {
                    return 1;
                }
                case "2":
                {
                    return 2;
                }
                default:
                {
                    System.out.println("Неверный ввод");
                    continue;
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Добро пожаловать!");
	// write your code here
        while(true)
        {
            System.out.println("============================\n============================\nВыберите тип кодировки:\n1.Рельсы\n2.Кодовое слово\n3.Решетка\n4.Формула1\n5.Формула2\n6.Формула3");
            String choice1 = in.nextLine();
            switch(choice1)
            {
                case "1":
                {
                    int choice2 = Action();
                    System.out.println("Введите текст:");
                    String text = in.nextLine();
                    System.out.println("Введите ключ:");
                    int key = in.nextInt();
                    Relsi(choice2, text, key);
                    break;
                }
                case "2":
                {
                    int choice2 = Action();
                    System.out.println("Введите текст:");
                    String text = in.nextLine();
                    System.out.println("Введите ключ:");
                    String key = in.nextLine();
                    String result = KeyWord(choice2, text, key);
                    System.out.println(result);
                    break;
                }
                case "3":
                {
                    int choice2 = Action();
                    System.out.println("Введите текст:");
                    String text = in.nextLine();
                    String result = Reshetka(choice2, text);
                    System.out.println(result);
                    break;
                }
                case "4":
                {
                    int choice2 = Action();
                    System.out.println("Введите текст:");
                    String text = in.nextLine();
                    System.out.println("Введите ключ:");
                    int key = in.nextInt();
                    String result = F1(choice2, text.split(""), key);
                    System.out.println(result);
                    break;
                }
                case "5":
                {
                    int choice2 = Action();
                    System.out.println("Введите текст:");
                    String text = in.nextLine();
                    System.out.println("Введите ключ:");
                    int key = in.nextInt();
                    String result = F2(choice2, text.split(""), key);
                    System.out.println(result);
                    break;
                }
                default:
                {
                    System.out.println("Невернй ввод");
                    continue;
                }
            }
        }
    }
}
