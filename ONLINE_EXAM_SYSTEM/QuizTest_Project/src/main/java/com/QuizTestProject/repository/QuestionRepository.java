package com.QuizTestProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuizTestProject.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
