package com.example.metric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.metric")
public class MetricApplication {

  public static void main(String[] args) {
    SpringApplication.run(MetricApplication.class, args);
  }
}
