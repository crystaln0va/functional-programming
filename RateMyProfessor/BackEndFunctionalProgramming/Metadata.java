package com.RateMyProfessor.BackEndFunctionalProgramming;

import java.util.ArrayList;
import java.util.List;

import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Answer;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Feedback;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Professor;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Question;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;

public class Metadata {
   Long answerID = 0l;

   public static University getData(){
      //createQuestion();


        Long universityID = 1L;
        University university = new University(universityID,"Maharishi International University","Iowa","Fairfield","1000 N 4th","www.miu.edu",new ArrayList<Professor>());
        List<Professor> professors = new ArrayList<Professor>();
        List<String> courses = new ArrayList<>();
        courses.add("MPP");
        courses.add("FPP");

        Professor p1 = new Professor((long)1,"msjose@miu","Mateus jose",courses,university.getId(),university.getName(),new ArrayList<>());
        Professor p2 = new Professor((long)2,"john@miu.edu","John King",courses,universityID,"Maharishi",new ArrayList<>());
        Professor p3 = new Professor((long)3,"asander@miu.edu","Anthoty Sander",courses,university.getId(),university.getName(),new ArrayList<>());
        Professor p4 = new Professor((long)4,"knip@miu.edu","Kit Nip",courses,university.getId(),university.getName(),new ArrayList<>());
        Professor p5 = new Professor((long)5,"scanter@miu.edu","Sandra Canter",courses,university.getId(),university.getName(),new ArrayList<>());
        Professor p6 = new Professor((long)6,"dman@miu.edu","David Man",courses,university.getId(),university.getName(),new ArrayList<>());
        Professor p7 = new Professor((long)7,"thaward@miu.edu","Terrence Haward",courses,university.getId(),university.getName(),new ArrayList<>());
        Professor p8 = new Professor((long)8,"emongo@miu.edu","Eliner Mongo",courses,university.getId(),university.getName(),new ArrayList<>());
        
        Metadata m = new Metadata();
        p1 = m.setBadFeedback(p1);
        p1 = m.setBadFeedback(p1);
        p2 = m.setGoodFeedback(p2);
        p2 = m.setGoodFeedback(p2);
        //p2 = m.setBadFeedback(p2);
     /*     p3 = m.setGoodFeedback(p3);
        p4 = m.setBadFeedback(p4);
        p5 = m.setGoodFeedback(p5);
        p6 = m.setGoodFeedback(p6);
        p7 = m.setBadFeedback(p7);
        p8 = m.setBadFeedback(p8);
*/
        professors.add(p1);
        professors.add(p2);
        professors.add(p3);
        professors.add(p4);
        professors.add(p5);
        professors.add(p6);
        professors.add(p7);
        professors.add(p8);


        university.setProfessors(professors);
        return university;
     }

     /*
     public static void createQuestion(){
      survey.add(new Question(1l,"Contribution of course to your skill/knowledge ?","MA"));
      survey.add(new Question(2l,"Skill and responsiveness of the instructor ?","MA"));
      survey.add(new Question(3l,"Course content is organized and well planned ?","MA"));
      survey.add(new Question(4l,"Course workload is appropriate ?","MA"));
     }*/

     public Professor setBadFeedback(Professor professor){
      List<Question> survey = new ArrayList<>(); 
      survey.add(new Question(1l,"Contribution of course to your skill/knowledge ?","MA"));
      survey.add(new Question(2l,"Skill and responsiveness of the instructor ?","MA"));
      survey.add(new Question(3l,"Course content is organized and well planned ?","MA"));
      survey.add(new Question(4l,"Course workload is appropriate ?","MA"));

         List<Feedback> feedbacks = new ArrayList<Feedback>();

         Feedback f1 = new Feedback(answerID++,1l,professor.getId(),3,"FPP",2,40,"20220114", new ArrayList<Answer>());
         List<Answer> answer1 = new ArrayList<Answer>();
         answer1.add(new Answer(answerID++,f1.getId(),"AGREE",survey.get(0)));
         answer1.add(new Answer(answerID++,f1.getId(),"DISAGREE",survey.get(1)));
         answer1.add(new Answer(answerID++,f1.getId(),"DISAGREE",survey.get(2)));
         answer1.add(new Answer(answerID++,f1.getId(),"DISAGREE",survey.get(3)));
         f1.setAnswers(answer1);


         Feedback f2 = new Feedback(2l,1l,professor.getId(),1,"FPP",12,34,"20211204", new ArrayList<Answer>());
         List<Answer> answer2 = new ArrayList<Answer>();
         answer2.add(new Answer(answerID++,f2.getId(),"AGREE",survey.get(0)));
         answer2.add(new Answer(answerID++,f2.getId(),"DISAGREE",survey.get(1)));
         answer2.add(new Answer(answerID++,f2.getId(),"AGREE",survey.get(2)));
         answer2.add(new Answer(answerID++,f2.getId(),"AGREE",survey.get(3)));
         f2.setAnswers(answer2);


         Feedback f3 = new Feedback(2l,1l,professor.getId(),3,"MPP",12,67,"20170923", new ArrayList<Answer>());
         List<Answer> answer3 = new ArrayList<Answer>();
         answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(0)));
         answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(1)));
         answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(2)));
         answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(3)));
         f3.setAnswers(answer3);


         Feedback f4 = new Feedback(2l,1l,professor.getId(),2,"MPP",3,89,"20200828", new ArrayList<Answer>());
         List<Answer> answer4 = new ArrayList<Answer>();
         answer4.add(new Answer(answerID++,f4.getId(),"DISAGREE",survey.get(0)));
         answer4.add(new Answer(answerID++,f4.getId(),"AGREE",survey.get(1)));
         answer4.add(new Answer(answerID++,f4.getId(),"AGREE",survey.get(2)));
         answer4.add(new Answer(answerID++,f4.getId(),"AGREE",survey.get(3)));
         f4.setAnswers(answer4);

         feedbacks.add(f1);
         feedbacks.add(f2);
         feedbacks.add(f3);
         feedbacks.add(f4);
         List<Feedback> oldFeedbacks = professor.getFeedbacks();
         oldFeedbacks.addAll(feedbacks);

         professor.setFeedbacks(oldFeedbacks);
         return professor;
     }

     
     public Professor setGoodFeedback(Professor professor){
      List<Question> survey = new ArrayList<>(); 
      survey.add(new Question(1l,"Contribution of course to your skill/knowledge ?","MA"));
      survey.add(new Question(2l,"Skill and responsiveness of the instructor ?","MA"));
      survey.add(new Question(3l,"Course content is organized and well planned ?","MA"));
      survey.add(new Question(4l,"Course workload is appropriate ?","MA"));
      survey.add(new Question(5l,"Express your experience","SA"));

      List<Feedback> feedbacks = new ArrayList<Feedback>();

      Feedback f1 = new Feedback(1l,1l,professor.getId(),5,"MPP",47,9,"20200304", new ArrayList<Answer>());
      List<Answer> answer1 = new ArrayList<Answer>();
      answer1.add(new Answer(answerID++,f1.getId(),"AGREE",survey.get(0)));
      answer1.add(new Answer(answerID++,f1.getId(),"DISAGREE",survey.get(1)));
      answer1.add(new Answer(answerID++,f1.getId(),"DISAGREE",survey.get(2)));
      answer1.add(new Answer(answerID++,f1.getId(),"DISAGREE",survey.get(3)));
      answer1.add(new Answer(answerID++,f1.getId(),"He is a great Professor",survey.get(4)));

         f1.setAnswers(answer1);


      Feedback f2 = new Feedback(2l,1l,1l,4,"MPP",25,8,"20190923", new ArrayList<Answer>());
      List<Answer> answer2 = new ArrayList<Answer>();
      answer2.add(new Answer(answerID++,f2.getId(),"AGREE",survey.get(0)));
      answer2.add(new Answer(answerID++,f2.getId(),"DISAGREE",survey.get(1)));
      answer2.add(new Answer(answerID++,f2.getId(),"AGREE",survey.get(2)));
      answer2.add(new Answer(answerID++,f2.getId(),"AGREE",survey.get(3)));
      answer2.add(new Answer(answerID++,f2.getId(),"He is a great professor",survey.get(4)));

         f2.setAnswers(answer2);

      Feedback f3 = new Feedback(2l,1l,1l,5,"MPP",20,2,"20201204", new ArrayList<Answer>());
      List<Answer> answer3 = new ArrayList<Answer>();
      answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(0)));
      answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(1)));
      answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(2)));
      answer3.add(new Answer(answerID++,f3.getId(),"DISAGREE",survey.get(3)));
      answer3.add(new Answer(answerID++,f3.getId(),"I love taking classes with him",survey.get(4)));

         f3.setAnswers(answer3);


      Feedback f4 = new Feedback(2l,1l,1l,5,"FPP",18,4,"20201129", new ArrayList<Answer>());
      List<Answer> answer4 = new ArrayList<Answer>();
      answer4.add(new Answer(answerID++,f4.getId(),"DISAGREE",survey.get(0)));
      answer4.add(new Answer(answerID++,f4.getId(),"AGREE",survey.get(1)));
      answer4.add(new Answer(answerID++,f4.getId(),"AGREE",survey.get(2)));
      answer4.add(new Answer(answerID++,f4.getId(),"AGREE",survey.get(3)));
      answer4.add(new Answer(answerID++,f4.getId(),"He is okay",survey.get(4)));

         f4.setAnswers(answer4);

      feedbacks.add(f1);
      feedbacks.add(f2);
      feedbacks.add(f3);
      feedbacks.add(f4);

      List<Feedback> oldFeedbacks = professor.getFeedbacks();
      oldFeedbacks.addAll(feedbacks);

      professor.setFeedbacks(oldFeedbacks);
      return professor;
  }

 
}
