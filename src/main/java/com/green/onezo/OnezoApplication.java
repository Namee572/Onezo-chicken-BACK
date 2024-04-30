package com.green.onezo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
//@EnableJpaAuditing//entity 객체 생성시 createDate, modifiedDate 자동 생성
public class OnezoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnezoApplication.class, args);

	}

}
