package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//only works with questions
//localhost:8080/question/allQuestions

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService qs;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return qs.getAllQuestions();
    }
    @GetMapping("category/{category}")
    //if  getmapping({cat}) diff name,@PathVariable("cat")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return qs.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){

        return qs.addQuestion(question);

    }

}
