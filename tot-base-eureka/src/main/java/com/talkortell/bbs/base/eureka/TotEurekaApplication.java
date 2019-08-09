package com.talkortell.bbs.base.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TotEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotEurekaApplication.class, args);
	}

}
