package com.simon.java.io;


import java.io.*;

public class FileManager {

    public static void main(String[] args) {
        try {
            new FileManager().copyFile("src/main/resources/sample.txt", "src/main/resources/test.log");
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/test.log");
            int read = fileInputStream.read();
            System.out.println("read" + read);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String src, String dest) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;
        try {
            fileIn = new FileInputStream(src);
            BufferedInputStream bis = new BufferedInputStream(fileIn);
            fileOut = new FileOutputStream(dest);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);
            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
