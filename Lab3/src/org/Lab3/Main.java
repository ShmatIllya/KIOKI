package org.Lab3;

import java.io.*;
import java.util.*;

public class Main {
   public static char[] alphabet = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', ' ', ',', '?'};
   static int x_f;
    static int y_f;
   public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Message:");
        String message = scanner.nextLine();
        int p, q;
        System.out.println("Input 2 keys:");
        p = scanner.nextInt();
        q = scanner.nextInt();
        int n = p * q;
        List<Integer> message_i = Encrypt(p,q,n, message);
        System.out.println("Encrypted message: ");
        for(int i: message_i)
        {
            System.out.print(i + " ");
        }
        System.out.println("");
        List<String> res = Decrypt(p, q, n, message_i);
        System.out.println("Decrypted message: ");
        String s = "";
        for(int i = 0; i < res.size(); i++)
        {
            s += res.get(i);
            System.out.print(res.get(i));
        }
        System.out.println("");
       FileReader reader = new FileReader(new File("D:\\FCP\\SEM6\\KIOKI\\Lab3\\src\\org\\Lab3\\words.utf-8.txt"));
       BufferedReader read = new BufferedReader(reader);
        Set<String> setter = new HashSet<>();
        String line;
        while((line = read.readLine()) != null)
        {
            setter.add(line);

        }
        SpellChecker spellChecker = new SpellChecker(setter);
        spellChecker.check(s);
    }
    public static List<Integer> Encrypt(int p, int q, int n, String message)
    {
        String[] message_c = message.split("");
        List<Integer> message_i = new ArrayList<>();
        for(int i = 0; i < message_c.length; i++)
        {
            for(int j = 0; j < alphabet.length; j++)
            {
                if(message_c[i].equals(String.valueOf(alphabet[j])))
                {
                    message_i.add(j + 1);
                }
            }
        }
        for(int i = 0; i < message_i.size(); i++)
        {
            int val = message_i.get(i).intValue();
            System.out.println(val);
            message_i.set(i, (val * val) % n);
        }
        return message_i;
    }

    public static List<String> Decrypt(int p, int q, int n, List<Integer> message_i)
    {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < message_i.size(); i++)
        {
            int m_p = (int) (Math.pow(message_i.get(i), ((p + 1) / 4)) % p);
            int m_q = (int) (Math.pow(message_i.get(i), ((q + 1) / 4)) % q);
            //long[] res_l = ex_gcd(p,q);
            //x_f = (int) res_l[1];
            //y_f = (int) res_l[2];
            Gcd(p,q);
            System.out.println(m_p + " " + m_q);
            System.out.println(x_f + " " + y_f);
            List<Integer> result = new ArrayList<>();
            result.add((x_f * p * m_q + y_f * q * m_p) % n);
            result.add(n - result.get(0));
            result.add((x_f * p * m_q - y_f * q * m_p) % n);
            result.add(n - result.get(2));
            for(int j: result)
            {
                System.out.println(j);
                j = Math.abs(j);
                if(j < alphabet.length + 1 && j > 0)
                {
                    res.add(String.valueOf(alphabet[j - 1]));
                    if(alphabet[j - 1] == 'o')
                    {
                        res.remove(res.size() - 2);
                    }
                }
            }
        }
        return res;
    }
   /* public static long[] ex_gcd(long a, long b, long[] x, long[] y){

        long gcd;
        long[] result = new long[3];

        if(b==0){
            result[0] = a;
            result[1] = x[0];
            result[2] = y[0];
            return result;
        }
        long q=a/b;
        long tx1 = x[0]-q*x[1];
        long ty1 = y[0]-q*y[1];
        long[] tx = {x[1],tx1};
        long[] ty = {y[1],ty1};
        return ex_gcd(b,a%b,tx,ty);
    }

    public static long[] ex_gcd(long a, long b){
        long[] x = {1,0};
        long[] y = {0,1};
        return ex_gcd(a,b,x,y);
    }*/
   static int Gcd(int a, int b)
   {
       if (b < a)
       {
           var t = a;
           a = b;
           b = t;
       }

       if (a == 0)
       {
           x_f = 0;
           y_f = 1;
           return b;
       }

       int gcd = Gcd(b % a, a);

       int newY = x_f;
       int newX = y_f - (b / a) * x_f;

       x_f = newX;
       y_f = newY;
       return gcd;
   }
}
