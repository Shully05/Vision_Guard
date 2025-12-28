package cn.pxl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.pxl.mapper")
public class Spring001Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring001Application.class, args);
    }

}
