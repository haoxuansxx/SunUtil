package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Plugins插件库  -- 程序入口
 *
 * @Author Sun
 * @date 2019-03-11
 */
@SpringBootApplication
@ComponentScan(basePackages = {"valid", "util"})
public class SpringRunApplication {

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringRunApplication.class, args);
    }

}