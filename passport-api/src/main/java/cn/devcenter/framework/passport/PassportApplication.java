package cn.devcenter.framework.passport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@Slf4j
@EnableFeignClients
public class PassportApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassportApplication.class, args);
        log.info("========== Passport service started ==========");
    }

}
