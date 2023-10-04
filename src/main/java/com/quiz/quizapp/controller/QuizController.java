package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionWrapper;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Response;
import com.quiz.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    //http://localhost:8080/quiz/create?category=Java&numQ=5&title=JQuiz
    @Autowired
    QuizService qs;
    @PostMapping("create")
    //use requestparam to receive url variable.
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return qs.createQuiz(category,numQ, title);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int id){
        return qs.deleteQuiz(id);
    }
    @GetMapping("start/{title}")
    public ResponseEntity<Quiz> getQuizByTitle(@PathVariable String title){
        return qs.getQuizByTitle(title);
    }
    @GetMapping("get/{title}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByTitle(@PathVariable String title){
        return qs.getQuizQuestionsByTitle(title);
    }
    @GetMapping("get/ids/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable int id){
        return qs.getQuizQuestionsById(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitAnswers(@PathVariable int id, @RequestBody List<Response> responses){
        return new ResponseEntity<>(qs.calculateResult(id, responses), HttpStatus.OK);
    }





}
