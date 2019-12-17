package com.simon.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 这些是Java NIO中最重要的通道的实现：
 * <p>
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 * <p>
 * FileChannel 从文件中读写数据。
 * <p>
 * DatagramChannel 能通过UDP读写网络中的数据。
 * <p>
 * SocketChannel 能通过TCP读写网络中的数据。
 * <p>
 * ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 */
public class ChannelTemp {

    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("F:\\INtellijProject\\BasicKnowledge\\data\\data.txt", "rw");
            FileChannel channel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int read = channel.read(byteBuffer);
            while (read != -1) {
                System.out.println("Read " + read);
                //注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
                byteBuffer.clear();
                read = channel.read(byteBuffer);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
