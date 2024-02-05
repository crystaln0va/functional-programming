package com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming.FuncUtilMain.Trifunction;
import com.RateMyProfessor.BackEndFunctionalProgramming.Pair;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Professor;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Student;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Feedback;

//Class implementation for Mussie
public abstract class FuncUtil1 {


    static Function<List<Feedback>,Double>  Average= (feedbacks ) -> (feedbacks).stream().collect(Collectors.averagingDouble(p->p.getStarRating()));
    static Function<List<Feedback>,Integer> SumOfThumps = (feedbacks -> (feedbacks).stream().collect(Collectors.summingInt(p->p.getThumbsUp())));

    /*The highest rated professor(s) given course*/

    public static Trifunction<University,String,String,List<Long>> getHighestRatedProfessor = (university,courseName,year) ->
            Stream.of(university)
                    .flatMap(u->u.getProfessors().stream())
                    .flatMap(p->p.getFeedbacks().stream())
                    .filter(p->p.getCourse().equals(courseName))
                    .filter(f->f.getDate().substring(0, 4).equals(year))
                    .collect(Collectors.groupingBy(p->p.getProfessorID(),
                            Collectors.averagingLong(p->p.getStarRating())))
                    .entrySet()
                    .stream()
                    .sorted((p1,p2)->(int)(p2.getValue()-p1.getValue()))
                    //.limit(1)
                    .map(p->p.getKey())
                    .collect(Collectors.toList());

    //This is for query number two//
    /*From a given university, specified year and list of students, Find those students who didn't give feedback? */


    static BiFunction<University, String, List<Long>> StudentWhoGiveFeeback = (university, year) ->
            Stream.of(university).flatMap(p->p.getProfessors().stream())
                    .flatMap(f->f.getFeedbacks().stream())
                    .filter(d->d.getDate().substring(0,4).equals(year))
                    .collect(Collectors.groupingBy(s->s.getStudentID()))
                    .entrySet()
                    .stream()
                    .map(s->s.getKey())
                    .distinct()
                    .collect(Collectors.toList())
            ;

    public static Trifunction<List<Long>,University,String,List<Long>> StudentwhodontGiveFeedback = (overallStudsIdLongs,university,year) ->
            Stream.concat(overallStudsIdLongs.stream(),StudentWhoGiveFeeback.apply(university, year).stream())
                    .collect(Collectors.groupingBy(l -> l))
                    .entrySet().stream()
                    .filter(pr -> pr.getValue().size()==1)
                    .map(pr -> (Long) pr.getKey())
                    .collect(Collectors.toList())
            ;

    //This is for query number three//
    /*From a given University, course name and year, Find the number of thumbs up for the highest rated professor?*/


    static BiFunction<Professor,String, Integer>  getthumpsUp= (professor,year) ->
            Stream.of(professor).flatMap(p->p.getFeedbacks().stream())
                    .filter(f->f.getDate().substring(0,4).equals(year))
                    .map(f->f.getThumbsUp())
                    .collect(Collectors.summingInt(g -> g.intValue()));

    static BiFunction<Professor,String, Double> getAverageRating = (professor, year) -> Stream.of(professor)
            .flatMap(p->p.getFeedbacks().stream())
            .filter(f->f.getDate().substring(0,4).equals(year))
            .collect(Collectors.averagingDouble(f->f.getStarRating()));

    public static Trifunction<University,String,String, Optional<Integer>> getThumpsUpForHighestProf = (university, CourseName, year) ->
            Stream.of(university)
                    .flatMap(u->u.getProfessors().stream())
                    .filter(p->p.getCourses().contains(CourseName))
                    .map(p-> new Pair<Integer, Double>(getthumpsUp.apply(p,year), getAverageRating.apply(p,year)))
                    .sorted(((p1,p2)-> (int) ((p2.getValue()- p1.getValue()))))
                    .map(p->p.getKey())
                    .findFirst();

}