package com.QuizTestProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.QuizTestProject.model.Question;
import com.QuizTestProject.service.QuestionService;

@Controller
public class StaffController {

    private final QuestionService questionService;

    @Autowired
    public StaffController(QuestionService questionService) {
        this.questionService = questionService;
    }

    
    @GetMapping("/questions")
    public String manageQuestions(Model model,
                                  @RequestParam(value = "successMessage", required = false) String successMessage) {
        model.addAttribute("questions", questionService.getAllQuestions());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        return "Show_question"; 
    }

    @GetMapping("/addQuestion")
    public String showAddQuestionForm() {
        return "add_question";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestParam String questionText,
                              @RequestParam String option1,
                              @RequestParam String option2,
                              @RequestParam String option3,
                              @RequestParam String option4,
                              @RequestParam int correctOption) {
        Question question = new Question();
        question.setQuestionText(questionText);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectOption(correctOption);

        questionService.saveQuestion(question);

        return "redirect:/questions?successMessage=Question added successfully!";
    }
}
