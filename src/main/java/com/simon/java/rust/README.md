在该目录下使用javac生成对应的头文件,头文件主要为编写Rust Jni对应方法时直接拷贝方法名

javac -h . HelloWorld.java 

编译.so库 Mac上二进制后缀为xxx.dylib

在rust项目下执行
cargo build
编译后的.so库，在target/debug下

如果编译其他架构二进制库：

1.安装交叉工具链：       rustup target add armv7-unknown-linux-gnueabihf

2.指定CPU架构编译rust：  cargo build --target x86_64-unknown-linux-musl

具体参考： https://github.com/japaric/rust-cross
