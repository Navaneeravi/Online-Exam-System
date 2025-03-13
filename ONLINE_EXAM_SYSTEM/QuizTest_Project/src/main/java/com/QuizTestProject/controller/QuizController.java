package com.QuizTestProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.QuizTestProject.model.Question;
import com.QuizTestProject.service.QuizService;

@Controller
@SessionAttributes({"currentQuestionIndex", "quizSubmitted"})
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public String startQuiz(Model model) {
        List<Question> questions = quizService.getAllQuestions();
        model.addAttribute("questions", questions);

        model.addAttribute("currentQuestionIndex", 0); 
        model.addAttribute("quizSubmitted", false); 

        return "quiz";
    }

    @GetMapping("/answer")
    public String answerQuestion(@RequestParam("questionId") int questionId,
                                 @RequestParam(value = "answer", required = false) Integer answer,
                                 @SessionAttribute("currentQuestionIndex") Integer currentQuestionIndex,
                                 @SessionAttribute("quizSubmitted") boolean quizSubmitted,
                                 Model model) {

        List<Question> questions = quizService.getAllQuestions();

 
        if (answer == null) {
            model.addAttribute("errorMessage", "Please select an option before submitting.");
            model.addAttribute("questions", questions);
            model.addAttribute("currentQuestionIndex", currentQuestionIndex);
            return "quiz"; 
        }

        boolean correct = quizService.isAnswerCorrect(questionId, answer);

        if (!correct) {
            model.addAttribute("errorMessage", "Incorrect answer. Please try again.");
        } else {
            if (currentQuestionIndex + 1 < questions.size()) {
                model.addAttribute("currentQuestionIndex", currentQuestionIndex + 1);
            } else {
                model.addAttribute("successMessage", "Congratulations! You've completed the quiz.");
                model.addAttribute("quizSubmitted", true);
            }
        }

        model.addAttribute("questions", questions);
        return "quiz";
    }



}
