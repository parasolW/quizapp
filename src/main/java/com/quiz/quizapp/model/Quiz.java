package com.quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numQ;
    private String caregory;
    private String title;
    @ManyToMany
    private List<Question> questions;


}
