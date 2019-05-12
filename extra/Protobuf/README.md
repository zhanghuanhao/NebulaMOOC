# Protobuf使用说明

编译环境：Ubuntu 18.04
Protoc可执行文件下载地址：https://github.com/protocolbuffers/protobuf/releases
建议在Linux环境下编译protoc和protoc-gen-grpc-java，windows环境下问题可能较多。
（一定要在翻墙的情况下进行编译！！）

步骤：

1. 运行下面的命令
```sh
sudo apt-get install autoconf automake libtool curl make g++ unzip
git clone https://github.com/google/protobuf.git
cd protobuf
git submodule update --init --recursive
./autogen.sh
./configure
make
make check
sudo make install
sudo ldconfig
```
测试是否成功：
```sh
protoc --version
```

2. 运行下面的命令
```sh
git clone https://github.com/grpc/grpc-java.git
cd compiler
../gradlew java_pluginExecutable
```
如果成功，可以在目录下看到build/exe/java_plugin/protoc-gen-grpc-java
复制到根目录使用。

3. 
- 使用 proto 工具生成 java 代码
```sh
protoc --java_out=. hello.proto
```
- 使用 grpc-java 工具生成 java 代码
```sh
protoc --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java --grpc-java_out=. --proto_path=. hello.proto
```

附：
- ChatMessage.proto：聊天协议
- protoc-gen-grpc-java：版本1.20.0