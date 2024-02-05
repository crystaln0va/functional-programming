package com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import com.RateMyProfessor.BackEndFunctionalProgramming.Pair;
import com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming.FuncUtilMain.Trifunction;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Feedback;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;

import java.util.stream.Collectors;

// class implementation for Mateus
public abstract class FuncUtil3 {


    //100% finished
    //this function returns the total number of professor for a given university
    public static Function<University,Long> numberOfProfessorsInTheUniversity = (university) -> 
        Stream.of(university).flatMap(u -> u.getProfessors().stream())
        .count();
        
    //100% finished
    //this function returns the years that the platform recorded at least one feedback
    public static Function<University,List<String>> yearsWithFeedback = (university) -> 
        Stream.of(university).flatMap(u -> u.getProfessors().stream())
        .flatMap(p -> p.getFeedbacks().stream())
        .collect(Collectors.groupingBy( f -> f.getDate().substring(0, 4))).entrySet().stream()
        .map(m -> m.getKey())
        .collect(Collectors.toList());


    
    //100% finished
    public static Function<University,String> yearWithTheMostReviews = (university) -> 
        Stream.of(university).flatMap(u -> u.getProfessors().stream())
        .flatMap(p -> p.getFeedbacks().stream())
        .collect(Collectors.groupingBy( f -> f.getDate().substring(0, 4))).entrySet().stream()
        .map(m -> new Pair<>(m.getKey(), m.getValue().stream().count()))
        .sorted((e1,e2)->(int)(e2.getValue() - e1.getValue()))
        .limit(1)
        .map(p-> p.getKey())
        .findFirst().get();
    //number of reviews in a given year
    public static BiFunction<University,String,Long> numberOfReviewsInKYear = (university,year) -> 
        Stream.of(university).flatMap(u -> u.getProfessors().stream())
        .flatMap(p -> p.getFeedbacks().stream())
        .filter(f -> f.getDate().substring(0, 4).equals(year))
        .count();


    //---------------------------------------------------------------------------------------

        
    //Question 1 - 100% finished.............................. professors with at least K number of average star in a given year ...........................................
    public static Function<List<Feedback>,Double> feedbackStarAveraging = (feedback) -> 
        feedback.stream().map(y -> y.getStarRating())
        .collect(Collectors.averagingLong(num -> num));

    public static Trifunction<University,String,Integer,List<Long>> professorsWithAtLeastKStartAverageInAYear = 
        (university,year,stars) -> 
        Stream.of(university).flatMap(u -> u.getProfessors().stream())
        .flatMap(p -> p.getFeedbacks().stream())
        .filter( f -> f.getDate().substring(0,4).equals(year))
        .collect(Collectors.groupingBy( f -> f.getProfessorID())).entrySet().stream()
        .map(m -> new Pair<Long,Double>(m.getKey(),feedbackStarAveraging.apply(m.getValue())))
        .filter(p -> p.getValue()>= stars)
        .map(p -> p.getKey())
        .collect(Collectors.toList());

    //..........................................................................

    
    //...................................... course with the lowest and highest rating ....................................
    //100% finished
    public static Trifunction<University,String,String,Long> numberOfFeedbacksForACourse = 
        (university,course,year) -> 
            Stream.of(university).flatMap(u -> u.getProfessors().stream())
            .flatMap(p -> p.getFeedbacks().stream())
            .filter( f -> f.getDate().substring(0,4).equals(year))
            .filter(f -> f.getCourse().equals(course))
            .count();


    public static BiFunction<University,String,List<Pair<String,Double>>> listOfPairOfCourseandItsAverageRating = 
        (university,year) -> 
            Stream.of(university).flatMap(u -> u.getProfessors().stream())
            .flatMap(p -> p.getFeedbacks().stream())
            .filter( f -> f.getDate().substring(0,4).equals(year))
            .collect(Collectors.groupingBy( f -> f.getCourse())).entrySet().stream()
            .map(m -> new Pair<String,Double>(m.getKey(), feedbackStarAveraging.apply(m.getValue())))
            .collect(Collectors.toList());


    public static BiFunction<University,String,String> courseWithTheLowestRating = 
        (university,year) -> 
            listOfPairOfCourseandItsAverageRating.apply(university,year).stream()
            .min((p1,p2) -> (int)(p1.getValue() - p2.getValue()))
            .map(p-> p.getKey())
            .get();

    public static BiFunction<University,String,String> courseWithTheHighestRating = 
        (university,year) -> 
            listOfPairOfCourseandItsAverageRating.apply(university,year).stream()
            .max((p1,p2) -> (int)(p1.getValue() - p2.getValue()))
            .map(p-> p.getKey())
            .get();
        
    //..........................................................................


}

