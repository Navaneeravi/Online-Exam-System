package com.QuizTestProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizTestProject.model.Question;
import com.QuizTestProject.repository.QuestionRepository;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

   
    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }



    public boolean isAnswerCorrect(int questionId, int answer) {
        Optional<Question> question = getQuestionById(questionId);
        return question.isPresent() && question.get().getCorrectOption() == answer;
    }
}
