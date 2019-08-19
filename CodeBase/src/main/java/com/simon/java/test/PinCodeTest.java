package com.simon.java.test;

public class PinCodeTest {

    public static void main(String args[]) {
        String pinCode = "【纳智捷组队口令】复制这条信息 #806678# 打开纳智捷app就能加入队伍，也可在纳 智捷app车队功能中输入组队口令完成加入！";
        String[] strings = pinCode.split("#");
        for (int i =0 ; i< strings.length ;i++){
            System.out.println(strings[i]);
        }
    }
}
