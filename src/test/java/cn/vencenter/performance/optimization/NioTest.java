package cn.vencenter.performance.optimization;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Classname NioTest
 * @description
 * @Author chuanfang
 * @Date 2021/1/22 9:31
 * @Version 1.0
 */
@Slf4j
public class NioTest {

    @Test
    void serverTest() throws IOException {
        //通道(对socket的封装  ) 缓冲区 选择器   socket对tcp抽象出来的调用接口


    }

    @Test
    void fileChannelTest() throws IOException {

        byte[] data = "test1234".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        String basePath = this.getClass().getClassLoader().getResource("").getPath();
        File file = new File(basePath+"file01.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel fileChannel = fos.getChannel();
        //写数据     把缓冲区中的数据写到通道中
        fileChannel.write(byteBuffer);
        fileChannel.close();
        fos.close();
    }

    @Test
    void readTest() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String basePath = this.getClass().getClassLoader().getResource("").getPath();
        File file = new File(basePath+"copy.png");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        //读取通道中的数据放到缓冲区中
        channel.read(buf);
        channel.close();
        inputStream.close();
    }

    @Test
    void imageCopy() throws IOException {
        String basePath = this.getClass().getClassLoader().getResource("").getPath();
        File file = new File(basePath+"copy.png");
        if (!file.exists()) {
            throw new RuntimeException();
        }
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        //文件拷贝操作
        File file1 = new File(basePath + "copy_1.png");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file1);
        FileChannel fileChannel = fos.getChannel();
        fileChannel.transferFrom(channel,channel.position(),channel.size());
        fileChannel.close();
        channel.close();
        fos.close();
        fis.close();
    }
}
