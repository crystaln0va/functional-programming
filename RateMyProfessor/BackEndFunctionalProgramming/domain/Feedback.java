package com.RateMyProfessor.BackEndFunctionalProgramming.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    Long id;
    Long studentID;
    Long professorID;
    int starRating;
    String course;
    //boolean isThumbsUp=false;
    int thumbsUp;
    int thumbsDown;
    String date;
    List<Answer> answers;

    @Override
    public String toString(){
        String info = "{\nFeedback "+id+": studentID-"+studentID + ", professorID-" +professorID + ",starRating-"+starRating+ ",thumbsUp-"+thumbsUp+ ",thumbsDown-"+thumbsDown+ ",date-"+date;
        String a ="";
        for(Answer answer : answers){
            a = a+"\n"+answer.toString();
        }
        return info+a+"\n}";
    }

}
