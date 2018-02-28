package com.graduationdesign.expresstakeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ExpresstakeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpresstakeappApplication.class, args);
	}
}
