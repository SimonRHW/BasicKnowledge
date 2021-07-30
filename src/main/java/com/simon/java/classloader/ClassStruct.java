package com.simon.java.classloader;

import java.io.Serializable;

/**
 * @author Simon
 * @date 2021/2/28 13:47
 * Desc ：class 类文件结构
 * <p>
 * 魔数|版本号|常量池|访问标志|类、父类、接口|字段描述集合|方法描述集合|属性描述集合
 * <p>
 * 当 JVM加载某个class文件时,JVM就是根据上面的结构去解析class文件，加载class文件到内存中并在内存中分配相应的空间。
 * 这些结构按照预先规定好的顺序紧密的从前向后排列，相邻的项之间没有任何间隙
 * 具体某一种结构需要占用大多空间，如下所示
 * <p>
 * 字段               | 名称       |  数据类型      | 数量
 * magic number         魔数            u4             1
 * major version        主版本号         u2             1
 * minor version        副版本号         u2             1
 * constant_pool_count  常量池大小       u2             1
 * constant_pool        常量池          cp_info 常量池表 （重要）      constant_pool_count-1
 * 等...
 *
 * java中 String 字符串的长度有限制吗？
 *
 * 而我们在java代码中声明的String字符串最终在class文件中的存储格式就 CONSTANT_utf8_info。
 * 因此一个字符串最大长度也就是u2所能代表的最大值65536个，
 * 但是需要使用2个字节来保存 null 值，因此一个字符串的最大长度为 65536 - 2 = 65534
 */
public class ClassStruct implements Serializable, Cloneable {

    private int num = 1;

    public int add(int i) {
        int j = 10;
        num = num + i;
        return num;
    }
}
