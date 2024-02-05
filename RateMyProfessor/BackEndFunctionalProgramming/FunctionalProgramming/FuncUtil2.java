package com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming.FuncUtilMain.Trifunction;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;

// class implementation for Amanuel 
public abstract class FuncUtil2 {

        public static Function<University, Integer> totalThumbsDownCount = (univ) -> Stream.of(univ)
                .flatMap(u -> u.getProfessors().stream())
                .flatMap(p -> p.getFeedbacks().stream())
                .mapToInt(f -> f.getThumbsDown())
                .sum();

        public static Function<University, List<String>> topFeedbackShortAnswer = (univ) ->
                Stream.of(univ)
                        .flatMap(u -> u.getProfessors().stream())
                        .flatMap(p -> p.getFeedbacks().stream())
                        .sorted((x,y) -> y.getThumbsUp() - x.getThumbsUp())
                        .flatMap(f -> f.getAnswers().stream())
                        .filter(a -> a.getQuestion().getType().equals("SA"))
                        .map(a -> a.getAnswer())
                        .limit(2)
                        .collect(Collectors.toList());

        public static Trifunction<University, String, String, Long> noOfFeedbackXProfessorForXyear = (univ, prof, year) ->
                Stream.of(univ)
                    .flatMap(u -> u.getProfessors().stream())
                        .filter(p -> p.getFullName().equals(prof))
                        .flatMap(p -> p.getFeedbacks().stream())
                        .filter(f -> f.getDate().substring(0, 4).equals(year))
                        .count();

}
