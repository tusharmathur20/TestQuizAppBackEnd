package com.exam.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.service.QuestionsService;

@Service
public class QuestionServiceImpl implements QuestionsService {

	@Autowired
	private QuestionRepository quesRepo;
	
	
	@Override
	public Question addQuestion(Question question) {
		return this.quesRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.quesRepo.save(question);
		
	}

	@Override
	public Set<Question> getQuestions() {
return new HashSet<>(this.quesRepo.findAll());
	}

	@Override
	public Question getQuestion(long questionId) {
		// TODO Auto-generated method stub
		return this.quesRepo.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		return this.quesRepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(long quesId) {
	Question question = new Question();
		question.setQuesId(quesId);
		this.quesRepo.delete(question);
	}

	@Override
	public Question get(Long questionId) {
		// TODO Auto-generated method stub
		return this.quesRepo.getOne(questionId);
	}
	
}
