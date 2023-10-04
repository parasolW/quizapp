package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Question;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    //if need of customization USE HQL (hinernates query language) OR JPQL
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);


    @Query(value = "SELECT right_answer FROM question q WHERE q.id=:id", nativeQuery = true)
    String getRightAnswerById(int id);
}
