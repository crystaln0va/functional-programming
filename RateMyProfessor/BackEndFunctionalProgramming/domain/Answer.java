package com.RateMyProfessor.BackEndFunctionalProgramming.domain;

import com.RateMyProfessor.BackEndFunctionalProgramming.Options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Answer {

    private Long id;
    private Long feedbackID;
    private String answer;
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Answer)) return false;
        Answer that = (Answer) o;
        return that.getId().equals(this.getId() )
                && that.getQuestion().equals(this.getQuestion()) && (that.getFeedbackID().equals(this.getFeedbackID() )
                && that.getAnswer().equals(this.getAnswer()));
    }

    @Override
    public String toString(){
        return "{question:"+question+",answer:"+answer+"}";
    }

}
