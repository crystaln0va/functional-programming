package com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.RateMyProfessor.BackEndFunctionalProgramming.Options;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Answer;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Professor;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Question;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;
import com.RateMyProfessor.BackEndFunctionalProgramming.Pair;

// class implementation for Daniel
public abstract class FuncUtil5 {


    //For each professor, what is the question that has the most disagreement feedback?

        public static Function<Professor, String> getProfDAQuest =
            (p) -> p.getFeedbacks().stream()
                    .flatMap(fb -> fb.getAnswers().stream())
                        .filter(a -> a.getAnswer().equals("DISAGREE"))
                        .collect(Collectors.groupingBy(a -> a.getQuestion().getQuestion()))
                            .entrySet().stream()
                            .map(ans -> new Pair<String,Integer>(ans.getKey(),ans.getValue().size()))
                                .sorted((p1,p2) -> p2.getValue()-p1.getValue())
                                .map(x -> x.getKey())
                                .findFirst().orElse("");

    public static Function<University,List<Pair<String,String>>> getProfsWithDAQuest = 
      (university) ->     Stream.of(university).flatMap(u -> u.getProfessors().stream())
            .map(p -> new Pair<String,String>(p.getFullName(),getProfDAQuest.apply(p)))
                .collect(Collectors.toList());

       
      //List of professors with their count of bad feedback (with less than 3 stars) 
      public static Function<Professor,Long> getCountBadRating = 
              (p) -> Stream.of(p).flatMap(x -> x.getFeedbacks().stream())
                       .filter(f -> f.getStarRating() < 3)
                         .count();
            
      public static Function<University,List<Pair<String,Long>>> getListProfWithCountBadRate = 
              (u) -> Stream.of(u).flatMap(x -> x.getProfessors().stream())
                       .map(p -> new Pair<String,Long>(p.getFullName(), getCountBadRating.apply(p))).collect(Collectors.toList());
                         //.toList();
                 
     //Best rated professor for the whole university
     public static Function<Professor,Double> getProfRating = 
         (p) -> Stream.of(p).flatMap(x -> x.getFeedbacks().stream())
                  .map(y -> y.getStarRating())
                    .collect(Collectors.averagingLong(num -> num));
         
      public static Function<University,String> getBestRatedProf = 
         (u) -> Stream.of(u).flatMap(p -> p.getProfessors().stream())
                  .map(n -> new Pair<String,Double>(n.getFullName(), getProfRating.apply(n)))
                    .sorted((p1,p2)-> p2.getValue().compareTo(p1.getValue()))
                      .findFirst().map(y -> y.getKey()).get();

}
