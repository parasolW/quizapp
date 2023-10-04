package com.quiz.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizappApplication {
//we have three layers USER-> Controller Layer -> Service Layer -> DAO -> Database
	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);
	}

}
