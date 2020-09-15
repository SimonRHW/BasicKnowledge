package com.simon.java.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 *  * NIO 只负责对发生在 fd 描述符上的事件进行通知。
 *  * 事件的获取和通知部分是非阻塞的，但收到通知之后的操作，却是阻塞的；
 *  * 即使使用多线程去处理这些事件，它依然是阻塞的
 */
public class NIODemo {

    static boolean stop = false;

    public static void main(String[] args) throws Exception {
        int connectionNum = 0;
        int port = 8888;

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress("localhost", port));

        Selector selector = Selector.open();
        ssc.register(selector, ssc.validOps());

        while (!stop) {
            if (10 == connectionNum) {
                stop = true;
            }
            int num = selector.select();
            if (num == 0) {
                continue;
            }
            Iterator<SelectionKey> events = selector.selectedKeys().iterator();
            while (events.hasNext()) {
                SelectionKey event = events.next();
                if (event.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    connectionNum++;
                } else if (event.isReadable()) {
                    try {
                        SocketChannel sc = (SocketChannel) event.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int size = sc.read(buf);
                        if (-1 == size) {
                            sc.close();
                        }
                        String result = new String(buf.array()).trim();
                        ByteBuffer wrap = ByteBuffer.wrap(("PONG:" + result).getBytes());
                        sc.write(wrap);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                events.remove();
            }
        }
        ssc.close();
    }
}
