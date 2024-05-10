package com.Test;

import org.apache.axis.*;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ServiceException;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
class Test {
    public static void main(String[] args) throws ServiceException, MalformedURLException {
        String endpoint = "http://localhost:8080/axis/phone.jws";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(endpoint));
        System.out.println("1 - enter payment month");
        System.out.println("2 - enter payment amount");
        System.out.println("3 - exit");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            line = in.readLine();
            String response = "111";
            while(!line.equals("3")){
                if (line.equals("3")) break;
                if (line.equals("1")) {
                    String phone = in.readLine();
                    Object[] param1 = new Object[]{phone};
                    response = (String) call.invoke("phone_name", param1);
                    System.out.println("Month=" + phone + "\n" + "Payment=" + response);
                }
                else if (line.equals("2")){
                        String name = in.readLine();
                        Object[] param2 = new Object[]{name};
                        response = (String)call.invoke("name_phone", 	param2);
                        System.out.println("Payment="+name+ "\n"+"Month="+response);}
                    System.out.println("1 - enter payment month");
                    System.out.println("2 - enter payment amount");
                    System.out.println("3 - exit");
                    line = in.readLine();
            }} catch (IOException e) {e.printStackTrace();} }}


