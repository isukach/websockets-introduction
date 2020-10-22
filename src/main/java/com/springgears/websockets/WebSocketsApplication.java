package com.springgears.websockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class WebSocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketsApplication.class, args);
	}
}

