package com.nguyenvm.galleryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@ComponentScan(basePackages = {"com.nguyenvm.common", "com.nguyenvm.galleryservice"})
public class GalleryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleryServiceApplication.class, args);
	}

}
