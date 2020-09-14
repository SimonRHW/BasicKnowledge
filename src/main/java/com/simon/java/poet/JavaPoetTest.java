package com.simon.java.poet;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;


public class JavaPoetTest {

    public static void main(String[] args) {
        // `JavaFile` 代表 Java 文件
        JavaFile javaFile = JavaFile.builder("com.simon.java",
                // TypeSpec 代表一个类
                TypeSpec.classBuilder("JavaPoetDemo")
                        // 给类添加一个属性
                        .addField(FieldSpec.builder(int.class,
                                "mField",
                                Modifier.PRIVATE)
                                .build())
                        // 给类添加一个方法
                        .addMethod(MethodSpec.methodBuilder("method")
                                .addModifiers(Modifier.PUBLIC)
                                .returns(void.class)
                                .addStatement("System.out.println(mField)")
                                .build())
                        .build())
                .build();

        System.out.println(javaFile.toString());
    }
}
