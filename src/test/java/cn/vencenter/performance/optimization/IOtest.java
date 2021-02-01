package cn.vencenter.performance.optimization;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Classname IOtest
 * @description
 * @Author chuanfang
 * @Date 2021/1/18 10:41
 * @Version 1.0
 */
@Slf4j
public class IOtest {

    @SneakyThrows
    public static void main(String[] args) {

        //同步并阻塞
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)
                , new ThreadFactoryBuilder().setNamePrefix("--bio--").build());
        ServerSocket serverSocket = new ServerSocket(6666);
        log.info("tomcat服务器启动...");
        while (true) {
            Socket accept = serverSocket.accept();
            log.info("连接到一个客户端! ip:{} port:{}",accept.getInetAddress(),accept.getPort());
            executor.execute(() -> {
                log.info("处理请求:"+Thread.currentThread().getName());
                handle(accept);
            });
        }
    }

    private static void handle(Socket accept) {
        try (InputStream is = accept.getInputStream()) {
            byte[] bucket = new byte[1024];
            int flag;
            if ((flag = is.read(bucket)) != -1) {
                System.out.println(new String(bucket,0,flag));
            }

        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                log.info("关闭和client的连接...:"+Thread.currentThread().getName());
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
