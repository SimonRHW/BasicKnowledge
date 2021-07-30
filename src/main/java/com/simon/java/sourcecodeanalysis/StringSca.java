package com.simon.java.sourcecodeanalysis;

public class StringSca {

    public static void main(String[] args) {
        var str1 = "HaHa";
        var str2 = new String("HaHa");
        char[] ch = {'H', 'a', 'H', 'a'};
        var str3 = new String(ch);

        System.out.println(str1.equals(str2)); //true
        System.out.println(str1.equals(str3)); //true
        System.out.println(str2.equals(str3)); //true

        System.out.println(str1 == str2); //false
        System.out.println(str1 == str3); //false
        System.out.println(str2 == str3); //false
        System.out.println("-------------");

        /*
        String 常见的创建方式有两种，new String() 的方式和直接赋值的方式，直接赋值的方式会先去字符串常量池中查找是否已经有此值，
        如果有则把引用地址直接指向此值，否则会先在常量池中创建，然后再把引用指向此值；而 new String() 的方式一定会先在堆上创建一个字符串对象，
        然后再去常量池中查询此字符串的值是否已经存在，如果不存在会先在常量池中创建此字符串，然后把引用的值指向此字符串
         */
        var s1 = new String("Java");
        //如果常量池中存在当前字符串, 就会直接返回当前字符串. 如果常量池中没有此字符串, 会将此字符串放入常量池中后, 再返回
        var s2 = s1.intern();
        var s3 = "Java";
        System.out.println(s1 == s2); //false
        System.out.println(s2 == s3); //true
        System.out.println(s1.equals(s2));  //true
        System.out.println(s2.equals(s3));  //true
    }

    /*
    一、String 被final修饰的原因：
    Java 语言之父 James Gosling 的回答是，他会更倾向于使用 final，因为它能够缓存结果，
    当你在传参时不需要考虑谁会修改它的值；
    如果是可变类的话，则有可能需要重新拷贝出来一个新值进行传参，这样在性能上就会有一定的损失。
    James Gosling 还说迫使 String 类设计成不可变的另一个原因是安全，当你在调用其他方法时，
    比如调用一些系统级操作指令之前，可能会有一系列校验，
    如果是可变类的话，可能在你校验过后，它的内部的值又被改变了，这样有可能会引起严重的系统崩溃问题，
    这是迫使 String 类设计成不可变类的一个重要原因。
    总结来说，使用final修饰的第一个好处是安全；第二个好处是高效。
    只有字符串是不可变时，我们才能实现字符串常量池，字符串常量池可以为我们缓存字符串，提高程序的运行效率

    二、String 变更  两大改善，存储由char[]变为byte[]数组，存储变得更为紧凑可以更小的内存空间、操作性能更高性

    JDK8实现


    JDK9以后 引入了StringLatin1 和 StringUTF16 进行处理
    if (anObject instanceof String) {
            String aString = (String)anObject;
            if (coder() == aString.coder()) {
                return isLatin1() ? StringLatin1.equals(value, aString.value)
                                  : StringUTF16.equals(value, aString.value);
            }
    }
        return false;
    }
    */

}
