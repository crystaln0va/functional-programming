package com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Professor;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Student;

import com.RateMyProfessor.BackEndFunctionalProgramming.Pair;
import com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming.FuncUtilMain.Trifunction;


public abstract class FuncUtil4 {
    
    
    /**
     * For a given course at this university, retrieve all professors with an OVERALL star rating of at least <Integer>
     */
     

    public static BiFunction<University,Long, OptionalDouble> getThisProfOverallRate =
            (university, professorLong) -> Stream.of(university)
                    .flatMap(u -> u.getProfessors().stream())
                    .filter(p -> p.getId().equals(professorLong))
                    .flatMap(p -> p.getFeedbacks().stream())
                    .mapToInt(f -> f.getStarRating())
                    .average();

    public static Trifunction<University,String,Integer, List<Long>> getAllProfsNStar =          // called in main
            (university,courseString,star) -> Stream.of(university)
                    .flatMap(u -> u.getProfessors().stream())
                    // I have a list of professors
                    .flatMap(p -> p.getFeedbacks().stream())
                    // I have a list of feedback
                    .filter(f -> f.getCourse().equals(courseString))
                    // I have a list feedback for ONLY this course
                    .collect(Collectors.groupingBy(f -> f.getProfessorID()))
                    // I have a Map of LongProf this course,ListFB for this course
                    .entrySet().stream()
                    .map(es -> es.getKey())
                    // I have a List profIDs this course
                    .map(p -> new Pair<Long,OptionalDouble>(p,getThisProfOverallRate.apply(university,p)))
                    // I have a list of pairings: Prof IDs (Long) to his OverallRating, if not null
                    .filter(pr -> pr.getValue().getAsDouble() >= star)
                    // I have only those pairs for Profs who got 4 or 5 stars
                    .map(pr -> pr.getKey())
                    // I have isolated the ID of LongProfs with 4+ stars
                    .collect(Collectors.toList());



    /**
     * #6: For a certain university, find all students who have left more than one 1-star review for the
     * same professor, and return a list ranking these students by how many professors they have done this for
     */ // ** Should the return type be Optional List? ** //



    public static Function<Professor, List<Long>> perProfStudentsMore1 =
            (professor) -> Stream.of(professor)
                    .flatMap(p -> p.getFeedbacks().stream())
                    // I have a list of FB for THIS professor
                    .collect(Collectors.groupingBy(f -> f.getStudentID()))
                    // I have a map of pairs: StudentLong to List of feedback
                    .entrySet().stream()
                    .filter(sf -> sf.getValue().stream().count() > 1)
                    // I have a entryset of pairs, StudentLong to List of feedback, only if more than 1 feedback for THIS professor
                    .map(sf -> new Pair<Long,Long>(sf.getKey(),sf.getValue().stream().filter(f -> f.getStarRating() == 1).count()))
                    // I have a map of pairs, StudentLong to count of 1-star reviews for this professor
                    .filter(sf -> sf.getValue() > 1L)
                    // I have StudentLong-Long pair where the student has more than one 1-star review for this professor
                    .map(pr-> pr.getKey())
                    // I have a stream of StudentLongs who have more than 1 feedback for THIS professor
                    .collect(Collectors.toList());

    public static Function<University,List<Pair<Long,Long>>> findSuspicUsers =          // called in main
            (university) -> Stream.of(university)
                    .flatMap(u -> u.getProfessors().stream())
                    // I have a list of professors
                    .flatMap(p -> perProfStudentsMore1.apply(p).stream())
                    // I have a list of studentLongID incidences of more than one 1-star review for a professor
                    .collect(Collectors.groupingBy(s -> s,Collectors.counting()))
                    // Display studentLong as key and number of professors they left more than one 1-star review for as value
                    .entrySet().stream()
                    .sorted( (e1,e2) -> (int) (e2.getValue() - e1.getValue()))
                    // Rank students from most incidences to fewest
                    .map(sle -> new Pair<Long,Long>(sle.getKey(),sle.getValue()))
                    // StudentIds vs num professors they left more than 1 1-star review for
                    .collect(Collectors.toList());







    /**
     * #7: Make a list of teachers who teach <Integer> or more classes that shows each of their courses and how many reviews
     * have been left for each one
     */

    public static Function<Professor, List<Pair<String,Long>>> feedbackPerCourse1Prof =
            (professor) -> Stream.of(professor)
                    .flatMap(p -> p.getFeedbacks().stream())
                    // I have list of feedbacks
                    .collect(Collectors.groupingBy(f -> f.getCourse()))
                    // I have map of: courseString-FeedbackList
                    .entrySet().stream()
                    .map(cf -> new Pair<String,Long>(cf.getKey(),cf.getValue().stream().count()))
                    // I have a list of pairs between courseString name-the number of feedbacks for each one
                    .collect(Collectors.toList());

    
    public static BiFunction<University,Integer,List<Pair<Professor,List<Pair<String,Long>>>>> getBusyProfRatings =       // used in main
            (university,integer) -> Stream.of(university)
                    .flatMap(u -> u.getProfessors().stream())
                    // I have list of professors
                    .map(p -> new Pair<Professor,List<String>>(p,p.getCourses()))
                    // I have a pair: Professor-List courses
                    .filter(pr -> pr.getValue().stream().count() >= integer)
                    // I have only the Professor-Courselist pairs for those who taught 3 or more courses
                    .map(pr -> pr.getKey())
                    // I have only the professors
                    .map(p -> new Pair<Professor,List<Pair<String,Long>>>(p,feedbackPerCourse1Prof.apply(p)))
                    .collect(Collectors.toList());


    
}
