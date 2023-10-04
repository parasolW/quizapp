package com.quiz.quizapp.service;

import com.quiz.quizapp.dao.QuestionDao;
import com.quiz.quizapp.dao.QuizDao;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionWrapper;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setNumQ(numQ);
        quiz.setCaregory(category);
        List<Question> questions = questDao.findRandomQuestionsByCategory(category, numQ);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Ok", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuiz(int id) {
        quizDao.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    public ResponseEntity<Quiz> getQuizByTitle(String title) {
        try {
            return new ResponseEntity<Quiz>(quizDao.findByTitle(title), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Quiz(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByTitle(String title) {
        Quiz q = quizDao.findByTitle(title);
        List<Question> questions = q.getQuestions(); //QuestionWrapper(q.getId(),q.getTitle(),q.getTitle())
        List<QuestionWrapper> questWrappers = new ArrayList<>();
        for (int i = 0; i<questions.size();i++){

            QuestionWrapper tmp = new QuestionWrapper(questions.get(i).getId(),questions.get(i).getQuestionTitle(),questions.get(i).getOption1(),
                    questions.get(i).getOption2(),questions.get(i).getOption3(),questions.get(i).getOption4());
            questWrappers.add(tmp);
        }
        return new ResponseEntity<List<QuestionWrapper>>(questWrappers,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(int id) {
        try {
            Optional<Quiz> q = quizDao.findById(id);
            List<Question> questions = q.get().getQuestions(); //QuestionWrapper(q.getId(),q.getTitle(),q.getTitle())
            List<QuestionWrapper> questWrappers = new ArrayList<>();
            for (int i = 0; i<questions.size();i++){

                QuestionWrapper tmp = new QuestionWrapper(questions.get(i).getId(),questions.get(i).getQuestionTitle(),questions.get(i).getOption1(),
                        questions.get(i).getOption2(),questions.get(i).getOption3(),questions.get(i).getOption4());
                questWrappers.add(tmp);
            }
            return new ResponseEntity<>(questWrappers, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public int calculateResult(int id, List<Response> responses) {
        int score = 0;
        Optional<Quiz> q = quizDao.findById(id);
        int i = 0;
        List<Question> questions = q.get().getQuestions();
        try{
            for (Response response: responses){
                if(response.getId()!=questions.get(i).getId())
                    throw new Exception();
                if (response.getResponse().equals(questions.get(i).getRightAnswer())){
                    score++;
                }
                i++;
            /*My own implementation, let's try another alternative
            String tmp = questDao.getRightAnswerById(response.getId());
            if (tmp.equals(response.getResponse())){
                score++;
            }*/
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

        return score;
    }
}
