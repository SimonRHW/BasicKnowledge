package com.simon.java.example;

public class Demo1 {
//    public static void main(String args[]) {
//    程序错误: 不兼容的类型: 从long转换到int可能会有损失
//    数据类型转换必须满足如下规则：
//            • 1. 不能对boolean类型进行类型转换。
//            • 2. 不能把对象类型转换成不相关类的对象。
//            • 3. 在把容量大的类型转换为容量小的类型时必须使用强制类型转换。
//            • 4. 转换过程中可能导致溢出或损失精度
//        int num = 2147483647 ;//        long temp = num + 2L ;
//        System.out.println(num) ;
//    }

    public static void main(String args[]) {
    //分析：每个case是一个tag，当匹配到tag时会忽略其他tag然后顺序执行代码体，直到遭遇break或者}
        char c = 'A' ;
        int num = 10 ;
        switch(c) {
            case 'B' :
                num ++ ;
            case 'A' :
                num ++ ;
            case 'Y' :
                num ++ ;
                break ;
            default :
                num -- ;
        }
        System.out.println(num) ;
    }
}
