package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionsService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionsController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuestionsService questionsService;
	
	@PostMapping("/")
	public ResponseEntity< Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionsService.addQuestion(question));
	}
		//update the Question
		@PutMapping("/")
		public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
		{
			return ResponseEntity.ok(this.questionsService.updateQuestion(question));
		
		}
		
		//get questions of quiz
		
		@GetMapping("/quiz/{qid}")
		public ResponseEntity<?>getQuestionOfQuiz(@PathVariable("qid")long qid){
	
//			Quiz quiz = new Quiz();
//			quiz.setQid(qid);
//			Set<Question> questionsOfQuiz = this.questionsService.getQuestionsOfQuiz(quiz);
//		return ResponseEntity.ok(questionsOfQuiz);
			
			Quiz quiz = this.quizService.getQuiz(qid);
			Set<Question> questions = quiz.getQuestions();
		
			
			List<Question> list=new ArrayList(questions);
			if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
				list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
				
			}
			list.forEach((q)->{
				q.setAnswer("");
			});
			
			Collections.shuffle(list);
			
			return ResponseEntity.ok(list);
			
		}
		@GetMapping("/quiz/all/{qid}")
		public ResponseEntity<?>getQuestionOfQuizAdmin(@PathVariable("qid")long qid){
	
			Quiz quiz = new Quiz();
			quiz.setQid(qid);
			Set<Question> questionsOfQuiz = this.questionsService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);
			
		
			
	
			
		}
		
		
		//get single questions
		@GetMapping("{quesId}")
		public Question get(@PathVariable("quesId")long quesId) {
			return this.questionsService.getQuestion(quesId);
		}
		
		//delete Questions
		@DeleteMapping("/{quesId}")
		public void delete(@PathVariable("quesId")long quesId) {
			this.questionsService.deleteQuestion(quesId);
		}
	

//evalQuestion
		@PostMapping("/eval-quiz")
		public ResponseEntity<?>evalQuiz(@RequestBody List<Question>questions){
			
			double	marksGot = 0;
				int correctAnswers = 0;
			int	attempted = 0;
			System.out.println(questions);
			
				for(Question q:questions){
					//single questions
				Question question = this.questionsService.get(q.getQuesId());
				if(question.getAnswer().equals(q.getGivenAnswer())) {
					//correct
					correctAnswers++;
					 double marksSingle =Double.parseDouble( questions.get(0).getQuiz().getMaxMarks())/questions.size();
//							           this.questions[0].quiz.maxMarks / this.questions.length;
							       marksGot += marksSingle;
//					
				}if ( q.getGivenAnswer()!=null ) {
				         attempted++;
				       }
				
				};
			Map<String, Object>map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
			return ResponseEntity.ok(map);
		}

}
	

