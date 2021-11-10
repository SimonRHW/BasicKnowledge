package com.simon.java.io;


import java.io.*;

public class FileManager {

    public void download(String src, String dest) {
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
