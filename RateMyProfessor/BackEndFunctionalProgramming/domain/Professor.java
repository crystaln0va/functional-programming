package com.RateMyProfessor.BackEndFunctionalProgramming.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    Long id;
    String email;
    String fullName;
    List<String> courses ;
    Long universityID;
    String university;
    List<Feedback> feedbacks;// = new ArrayList<>();

    @Override
    public String toString(){
        String info = "=====Professor "+id+"=====\n"+"{email: "+email + "\nfullName:" +fullName + "\nuniversity: "+university;
        String f ="";

      
        for(Feedback feedback : feedbacks){
            f = f+"\n"+feedback.toString();
        }

        return info+f+"}";
    }
}
