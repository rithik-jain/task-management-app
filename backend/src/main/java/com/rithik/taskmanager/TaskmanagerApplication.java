package com.rithik.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TaskmanagerApplication {
	private static final Logger logger =
			LoggerFactory.getLogger(TaskmanagerApplication.class);
	public static void main(String[] args) {
		logger.info("Starting Task Manager Application...");
		SpringApplication.run(TaskmanagerApplication.class, args);
		logger.info("Starting Task Manager Application...");
	}

}
