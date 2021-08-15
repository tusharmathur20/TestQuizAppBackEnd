package com.exam.service;

import java.util.Set;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;

public interface QuestionsService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion (Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(long questionId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(long quesId);
	
	public Question get(Long questionId);
	

}
