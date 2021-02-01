package cn.vencenter.performance.optimization;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 查询全国省市区数据  根据这些数据优化
 * @author chuanfang
 */
@SpringBootApplication
@EnableSwagger2Doc
@EnableScheduling
public class PerformanceOptimizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceOptimizationApplication.class, args);
    }


}
