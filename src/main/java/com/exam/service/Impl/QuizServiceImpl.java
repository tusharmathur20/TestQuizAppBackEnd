package com.exam.service.Impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepo;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return this.quizRepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		return new HashSet<>( this.quizRepo.findAll());
	}

	@Override
	public Quiz getQuiz(long quizId) {
		return this.quizRepo.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(long quizId) {
	
				this.quizRepo.deleteById(quizId);
		
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepo.findBycategory(category);
	}

	//get ACtive qUIZZES

	@Override
	public List<Quiz> getActiveQuizzes() {
		// TODO Auto-generated method stub
		return this.quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizRepo.findByCategoryAndActive(c, true);
	}

	
	/*
	 {
    
"title":"Phython Basics Questions",
"description":"This Quiz has basic Phython questions",
"maxMarks":200,
"numberOfQuestions":20,
"category":{
    "cid":17
}

}
	 */
}
