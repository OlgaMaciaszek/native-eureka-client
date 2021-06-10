package io.github.olgamaciaszek.nativeeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class NativeEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(NativeEurekaClientApplication.class);
		application.setApplicationStartup(new BufferingApplicationStartup(10000));
		application.run(args);
	}

}
