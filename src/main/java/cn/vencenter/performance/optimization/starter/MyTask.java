package cn.vencenter.performance.optimization.starter;

import cn.hutool.core.util.RandomUtil;
import com.google.common.base.Stopwatch;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Classname MyTask
 * @description
 * @Author chuanfang
 * @Date 2021/1/14 15:41
 * @Version 1.0
 */
@Component
public class MyTask {

    private ArrayList list = new ArrayList<Integer>();

    // 秒 分 时  日 月 周(星期) 年    springtask 不支持年份  quartz支持
    @Scheduled(cron = "0/10 * * * * ?")
    public void sc() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        list.add(RandomUtil.randomInt(10));
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }




}
