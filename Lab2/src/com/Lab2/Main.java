package com.Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    ArrayList<String> filler_count = new ArrayList<>();
    static int[] IP1 = {1, 5, 2, 0, 3, 7, 4, 6};
    static int[] IP2 = {3, 0, 2, 4, 6, 1, 7, 5};
    public static String BinaryToString(String binaryCode)
    {
        String[] code = binaryCode.split(" ");
        String word="";
        for(int i=0;i<code.length;i++)
        {
            word+= (char)Integer.parseInt(code[i],2);
        }
        System.out.println(word);
        return word;
    }
    public static int Binary_Simple(int symbol1, int symbol2)
    {
        int result = 0;
        switch (symbol1)
        {
            case 0:
            {
                if(symbol2 == 0)
                {
                    result = 0;
                }
                else
                {
                    result = 1;
                }
                break;
            }
            case 1:
            {
                if(symbol2 == 0)
                {
                    result = 2;
                }
                else
                {
                    result = 3;
                }
                break;
            }
        }
        return result;
    }
    public static int[] Round(int[] text, int[] key)
    {
        int[][] ASS_Block1 = {{1,0,3,2}, {3,2,1,0}, {0,2,1,3}, {3,1,3,2}};
        int[][] ASS_Block2 = {{0,1,2,3}, {2,0,1,3}, {3,0,1,0}, {2,1,0,3}};
        int[] text_L = new int[] {text[0],text[1],text[2],text[3]};
        int[] text_R = new int[] {text[7], text[4], text[5], text[6], text[5], text[6], text[7], text[4]};
        int[] text_R2 = new int[text_R.length];
        for(int i = 0; i < text_R.length; i++)
        {
            if(text_R[i] == key[i])
            {
                text_R2[i] = 0;
            }
            else
            {
                text_R2[i] = 1;
            }
        }
        int[] text_R3 = new int[text_R2.length / 2];
        boolean ASS_Check = true;
        for(int i = 0 , counter = 0; i < text_R2.length; i+=4, counter += 2)
        {
            int i1 = Binary_Simple(text_R2[i], text_R2[i + 3]), i2 = Binary_Simple(text_R2[i + 1], text_R2[i + 2]);
            int i3 = 0;
            if(ASS_Check == true)
            {
                i3 = ASS_Block1[i1][i2];
                ASS_Check = false;
            }
            else
            {
                i3 = ASS_Block2[i1][i2];
                ASS_Check = true;
            }
            switch (i3)
            {
                case 0:
                {
                    text_R3[counter] = 0;
                    text_R3[counter + 1] = 0;
                    break;
                }
                case 1:
                {
                    text_R3[counter] = 0;
                    text_R3[counter + 1] = 1;
                    break;
                }
                case 2:
                {
                    text_R3[counter] = 1;
                    text_R3[counter + 1] = 0;
                    break;
                }
                case 3:
                {
                    text_R3[counter] = 1;
                    text_R3[counter + 1] = 1;
                    break;
                }
            }
        }
        int[] text_R4 = new int[text_R3.length];
        int[] IP4 = {1,3,2,0};
        for(int i = 0; i < IP4.length; i++)
        {
            text_R4[i] = text_R3[IP4[i]];
        }
        for(int i = 0; i < text_R4.length; i++)
        {
            if(text_R4[i] == text_L[i])
            {
                text_R3[i] = 0;
            }
            else
            {
                text_R3[i] = 1;
            }
        }
        int[] text_Res = {text_R3[0], text_R3[1], text_R3[2], text_R3[3], text[4], text[5], text[6], text[7]};
        return text_Res;
    }
    public static  int[] Sypher (int[] key1, int[] key2, int[] text)
    {

        int[] text_i2 = new int[text.length];
        for(int i = 0; i < IP1.length; i++)
        {
            text_i2[i] = text[IP1[i]];
        }
        text = Round(text_i2, key1);
        int[] text2 = {text[4],text[5],text[6],text[7],text[0],text[1],text[2],text[3]};
        text = Round(text2, key2);
        for(int i = 0; i < IP2.length; i++)
        {
            text2[i] = text[IP2[i]];
        }
        return text2;
    }
    public static int[] Desypher(int[] key1, int[] key2, int[] text)
    {
        int[] text2 = new int[text.length];
        for(int i = 0; i < IP1.length; i++)
        {
            text2[i] = text[IP1[i]];
        }
        text = Round(text2, key2);
        text2 = new int[] {text[4], text[5], text[6], text[7], text[0], text[1], text[2], text[3]};
        text = Round(text2, key1);
        for(int i = 0; i < IP2.length; i++)
        {
            text2[i] = text[IP2[i]];
        }
        return text2;
    }


    public static String stringToBinary(String s) {

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            answer.append(Integer.toBinaryString(c)).append(' ');
        }

        return answer.toString();
    }

    public static void main(String[] args) throws IOException {
        List<String> zero_amount = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Input key (10 bits)");
        String key_s = in.nextLine();
        System.out.println("Select action:\n1.Cipher\n2.Decipher");
        String global_choice = in.nextLine();

        char[] key_c = key_s.toCharArray();
        int[] key_i = new int[key_c.length];
        for(int i = 0; i < key_c.length; i++)
        {
            key_i[i] = Integer.parseInt(String.valueOf(key_c[i]));
        }
        int[] P10 = {2, 4, 1, 6, 3, 9, 0, 8, 7, 5};
        int[] key_i2 = new int[key_c.length];
        for(int i = 0; i < P10.length; i++)
        {
            key_i2[i] = key_i[P10[i]];
        }
       int[][] key_di = {{key_i2[0],key_i2[1],key_i2[2],key_i2[3],key_i2[4]}, {key_i2[5],key_i2[6],key_i2[7],key_i2[8],key_i2[9]}};
       int count = 0;
        for(int i = 0; i < 2; i++)
        {
            int temp = key_di[i][0];
            for(int j = 0; j < 5; j++)
            {
                if(j + 1 != 5)
                {
                    key_di[i][j] = key_di[i][j + 1];
                }
                else
                {
                    key_di[i][j] = temp;
                }
                key_i[count] = key_di[i][j];
                count++;
            }
        }
        int[] P8 = {5, 2, 6, 3, 7, 4, 9, 8};
        int[] key1 = new int[8];
        for(int i = 0; i < P8.length; i++)
        {
            key1[i] = key_i[P8[i]];
        }
        key_di = new int[][] {{key_i[0],key_i[1],key_i[2],key_i[3],key_i[4]}, {key_i[5],key_i[6],key_i[7],key_i[8],key_i[9]}};

        int k = 0;
        while(k < 2) {
            count = 0;
            for (int i = 0; i < 2; i++) {
                int temp = key_di[i][0];
                for (int j = 0; j < 5; j++) {
                    if (j + 1 != 5) {
                        key_di[i][j] = key_di[i][j + 1];
                    } else {
                        key_di[i][j] = temp;
                    }
                    key_i[count] = key_di[i][j];
                    count++;
                }
            }
            k++;
        }
        int[] key2 = new int[8];
        for(int i = 0; i < P8.length; i++)
        {
            key2[i] = key_i[P8[i]];
        }
        //////////////////////////////////
        BufferedReader abc = new BufferedReader(new FileReader("D:\\FCP\\SEM6\\KIOKI\\Lab2\\ForLab.txt"));
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = abc.readLine()) != null) {
            lines.add(line);
        }
        abc.close();
        if(global_choice.equals("2"))
        {
            Decipher(key1, key2, lines);
            return;
        }
        for(int i = 0; i < lines.size(); i++)
        {
            lines.set(i, stringToBinary(lines.get(i)));
        }
        abc.close();
        List<String> lines2 = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++)
        {
            String[] temp = lines.get(i).split(" ");
            for(int j = 0; j < temp.length; j++)
            {
                lines2.add(temp[j]);
            }
            lines2.add("*");
        }
        for(int i = 0; i < lines2.size(); i++)
        {
            if(lines2.get(i).length() < 8 && !lines2.get(i).equals("*"))
            {
                int zero_amount_int = 0;
                String some_temp = lines2.get(i);
                while(some_temp.length() != 8)
                {
                    some_temp += "0";
                    zero_amount_int++;
                }
                zero_amount.add(String.valueOf(zero_amount_int));
                lines2.set(i, some_temp);
                System.out.println(lines2.get(i));
            }
        }
        System.out.println("==================================================");
        /////////////////////////////////////
       BufferedWriter abc_w = new BufferedWriter(new FileWriter("D:\\FCP\\SEM6\\KIOKI\\Lab2\\ForLab.txt"));
       int j3 = 0;
        for(int j = 0; j < lines2.size(); j++) {
            int j2 = 0;
            System.out.println(lines2.get(j));
            if(lines2.get(j).equals("*"))
            {
                abc_w.write("\n");
                continue;
            }
                int[] res1 = Sypher(key1, key2, new int[]{Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 1))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 2))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 3))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 4))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 5))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 6))), Integer.valueOf(String.valueOf(lines2.get(j).charAt(j2 + 7)))});
            String s = "";
            for(int kl = 0; kl < res1.length ; kl++)
                s += String.valueOf(res1[kl]);
            abc_w.write(s + " " + zero_amount.get(j3) + "\n");
            j3++;
        }
        abc_w.close();
	// write your code here
    }
    public static void Decipher(int[] key1, int[] key2, List<String> values) throws IOException {
        BufferedWriter abc = new BufferedWriter(new FileWriter("D:\\FCP\\SEM6\\KIOKI\\Lab2\\ForLab.txt"));
        for(int j = 0; j < values.size(); j++) {
            String values_s[] = values.get(j).split(" ");
            int j2 = 0;
            System.out.println(values.get(j));
            if(values.get(j).equals(""))
            {
                abc.write("\n");
                continue;
            }
            int[] res1 = Desypher(key1, key2, new int[]{Integer.valueOf(String.valueOf(values_s[0].charAt(j2))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 1))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 2))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 3))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 4))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 5))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 6))), Integer.valueOf(String.valueOf(values_s[0].charAt(j2 + 7)))});
            String s = "";
            for(int kl = 0; kl < res1.length - Integer.parseInt(values_s[1]); kl++)
                s += String.valueOf(res1[kl]);
            s = BinaryToString(s);
            abc.write(s);
            }
        abc.close();
    }
}
