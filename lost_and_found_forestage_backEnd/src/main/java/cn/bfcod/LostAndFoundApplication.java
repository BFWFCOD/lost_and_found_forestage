package cn.bfcod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("cn.bfcod.lost_and_found.dao")  (scanBasePackages = {"cn.bfcod.common.utils"})
@SpringBootApplication
public class LostAndFoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostAndFoundApplication.class, args);
    }

}
