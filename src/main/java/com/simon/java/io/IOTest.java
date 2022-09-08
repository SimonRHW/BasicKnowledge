package com.simon.java.io;


import java.io.FileInputStream;

/**
 * @author Simon
 * @date 2021/11/10 21:43
 * Desc ：
 */
public class IOTest {


    public static void main(String[] args) {

        try {
             new FileManager().download("src/main/resources/sample.txt","src/main/resources/test.log" );
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/test.log");
            int read = fileInputStream.read();
            System.out.println("read"+read);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
