rust jni doc:https://docs.rs/jni/latest/jni/index.html

在该目录下使用javac生成对应的头文件,头文件主要为编写Rust Jni对应方法时直接拷贝方法名

javac -h . HelloWorld.java 

当前lib目录下的为 Apple M1 Pro 编译产物

在rust项目下执行
cargo build  不带target参数默认当前运行环境的架构
编译后的生成的目标库在target/debug目录下下

如果编译其他架构二进制库，交叉编译是内置的，只需使用rustup安装目标支持：
rustup target list
rustup target install x86_64-apple-darwin
rustup target install x86_64-unknown-none
rustup target install armv7-linux-androideabi
构建目标架构
cargo build --target x86_64-apple-darwin
M1多亏了Rosetta，你甚至可以这样运行它
cargo run --target x86_64-apple-darwin
像这样构建你的板条箱

具体参考： https://github.com/japaric/rust-cross
