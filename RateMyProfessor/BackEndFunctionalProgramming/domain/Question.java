package com.RateMyProfessor.BackEndFunctionalProgramming.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    Long id;
    String question;
    //String[] options;
    String type;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Question)) return false;
        Question that = (Question) o;
        return that.getId().equals(this.getId() )
                && that.getQuestion().equals(this.getQuestion()) && (that.getType().equals(this.getType() )
                );
    }
}
