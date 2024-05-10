import java.io.*;
import java.util.*;
public class phone{
    public String phone_name(String phone){
        String name= new String();
        String line;
        try{
            FileReader fr = new FileReader("D:/FCP/SEM6/Rice/Lab6/phones.txt");
            BufferedReader in = new BufferedReader(fr);
            while ((line=in.readLine())!=null){
                String[] str = line.split(" ");
                if (str[0].equals(phone)){ name=str[1]; break;}
                else 	name="NO SUCH PAYMENT";
            }
        }
        catch(IOException e){e.printStackTrace();}
        return name;
    }

    public String name_phone(String name){
        String phone= new String();
        String line;
        try{
            FileReader fr = new FileReader("D:/FCP/SEM6/Rice/Lab6/phones.txt");
            BufferedReader in = new BufferedReader(fr);
            while ((line=in.readLine())!=null){
                String[] str = line.split(" ");
                if (str[1].equals(name)){ phone=str[0]; break;}else
                    phone="NO SUCH MONTH";
            }
        }catch(IOException e){e.printStackTrace();}
        return phone;
    }
}
