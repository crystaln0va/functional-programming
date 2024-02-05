package com.RateMyProfessor.BackEndFunctionalProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

import com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming.*;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Answer;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Feedback;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.Professor;
import com.RateMyProfessor.BackEndFunctionalProgramming.domain.University;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyRunner implements CommandLineRunner {

    private University university;

    @Override
    public void run(String... args) throws Exception {
        
        List<String> listOfQuestions = MenuList();
        university = Metadata.getData();

        /*
        int count = 1;
        while(true){

            count = 1;
            System.out.println("\n\n\n\n\n=================================== Rate My Professor Menu ==============================================");
            for(String question: listOfQuestions){
                System.out.println(count + " - " + question);
                count++;
            }
            System.out.println("Write \"exit\" to exit");

            System.out.println("\nSelect your option according to the numbers above");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            System.out.println(line);
            if(line.equals("exit")) {
                System.out.println("e="+line);
                break;
            }
            try{
                int value=Integer.parseInt(line);
                if(value>=1 || value<=15){
                    switchCase(value);
                }
            }catch(Exception ex){
                System.out.println("invalid Input. Try again with a input from 1-15");
            }

        }

*/

     }

     public void switchCase(int option){

         switch (option) {
             case 1:  question1();
                 break;
             case 2:  question2();
                 break;
             case 3:  question3();
                 break;
             case 4:  question4();
                 break;
             case 5:  question5();
                 break;
             case 6:  question6();
                 break;
             case 7:  question7();
                 break;
             case 8:  question8();
                 break;
             case 9:  question9();
                 break;
             case 10: question10();
                 break;
             case 11: question11();
                 break;
             case 12: question12();
                 break;
             case 13:  question13();
                 break;
             case 14: question14();
                 break;
             case 15: question15();
                 break;
         }

     }

     //Mussie
    public void question1(){
        System.out.println("================ The highest rated professor(s) given{FPP} course in the Year{2020} ===================");

        List<Long> resultLongs = FuncUtil1.getHighestRatedProfessor.apply(university,"FPP", "2020");
        System.out.println("highest rated professor: ");
        for(Long r: resultLongs){
            System.out.println("ProfessorID: " +r);
        }
    }
    public void question2(){
        System.out.println("================ From a given university, specified year{2022} and list of students{1,2,3,4}, Find those students who didn't give feedback?  ===================");

        // TEST
        List<Long> forThisStudentIDs = new ArrayList<>();
        forThisStudentIDs.add(1l);
        forThisStudentIDs.add(2l);
        forThisStudentIDs.add(3l);
        forThisStudentIDs.add(4l);
        List<Long> resultLongsAfterFilter = FuncUtil1.StudentwhodontGiveFeedback.apply(forThisStudentIDs,university,"2022");
        System.out.println("Students who did not give feedback: ");
        for(Long r: resultLongsAfterFilter){
            System.out.println("StudentID: " +r);
        }

    }
    public void question3(){
        System.out.println("================ From a given University, FPP course and in 2022, Find the number of thumbs up for the highest rated professor?  ===================");
        Optional<Integer> resultforthumpsup = FuncUtil1.getThumpsUpForHighestProf.apply(university, "FPP", "2022");
        if(!resultforthumpsup.isEmpty())
            System.out.println("Number of thunbs up for the highest rated professor: "+resultforthumpsup.get());
    }

    //Amanuel
    public void question4(){
        System.out.println("================ Total Thumbs Down Count in the application  ===================");
        System.out.println(FuncUtil2.totalThumbsDownCount.apply(university));

    }
    public void question5(){
        System.out.println("================ Top Feedback ShortAnswer in the application  ===================");
        System.out.println(FuncUtil2.topFeedbackShortAnswer.apply(university));

    }
    public void question6(){
        System.out.println("================ Number Of Feedback X{Mateus jose} Professor For Xyear{2020}  ===================");
        System.out.println(FuncUtil2.noOfFeedbackXProfessorForXyear.apply(university, "Mateus jose", "2020" ));

    }
    //Mateus
    public void question7(){
        System.out.println("================ Year with the most reviews in the application  ===================");
        String yearWithTheMostReviews = FuncUtil3.yearWithTheMostReviews.apply(university);
        Long numberOfReviewsInKYear = FuncUtil3.numberOfReviewsInKYear.apply(university,yearWithTheMostReviews);
        System.out.println("Year with the most reviews: "+ yearWithTheMostReviews + " with " + numberOfReviewsInKYear+" feedbacks");

    }
    public void question8(){
        System.out.println("================ List of professorID with at least K average number of starts{2} in a year{2020}  ===================");

        List<Long> professors = FuncUtil3.professorsWithAtLeastKStartAverageInAYear.apply(university, "2020", 2);
        System.out.println("Number of professors that have received at least 1 feedback: "+ professors.size());
        for(Long professor: professors){
            System.out.println("professor ID: "+ professor);
        }
    }
    public void question9(){
        System.out.println("================ List of professorID with at least K average number of starts{2} in a year{2020}  ===================");
        String courseWithTheLowestRating = FuncUtil3.courseWithTheLowestRating.apply(university, "2020");
        String courseWithTheMostRating = FuncUtil3.courseWithTheHighestRating.apply(university, "2020");
        Long courseWithTheLowestRatingNumber = FuncUtil3.numberOfFeedbacksForACourse.apply(university,courseWithTheLowestRating, "2020");
        Long courseWithTheMostRatingNumber = FuncUtil3.numberOfFeedbacksForACourse.apply(university,courseWithTheMostRating, "2020");

        System.out.println("\ncourseWithTheLowestRating: "+ courseWithTheLowestRating +" with "+courseWithTheLowestRatingNumber+" total number of feedbacks");
        System.out.println("courseWithTheMostRating: "+ courseWithTheMostRating +" with "+courseWithTheMostRatingNumber+" total number of feedbacks");

    }
    //Crystal
    public void question10(){
        System.out.println("================ Get all the professors with at least N Stars{3} that teach a given Course{MPP}  ===================");
        List<Long> allProfs4Star = FuncUtil4.getAllProfsNStar.apply(university,"MPP",3);
        System.out.println("Professor ID: ");
        for (Long l : allProfs4Star) {
            System.out.println(l);
        }


    }
    public void question11(){
        System.out.println("================ Find the students who have left more than 1 one-star review for the same professor  ===================");
        List<Pair<Long,Long>> suspicUsers = FuncUtil4.findSuspicUsers.apply(university);
        for (Pair<Long,Long> p : suspicUsers) {
            System.out.println(p.getKey() + ", " + p.getValue());
        }


    }
    public void question12(){
        System.out.println("================ Get a list of professors who teach at least X courses (given: 2) and show their list of courses with the feedback for it ===================");

        List<Pair<Professor,List<Pair<String,Long>>>> busyProfRatings = FuncUtil4.getBusyProfRatings.apply(university,2);

        for(Pair<Professor,List<Pair<String,Long>>> p : busyProfRatings) {
            System.out.println("Professor: " + p.getKey().toString());

            List<Pair<String,Long>> stringLongList = (List<Pair<String,Long>>)p.getValue();
            for (Pair<String,Long> p1 : stringLongList) {
                System.out.print("Course: " + (String)p1.getKey() + ", # Feedbacks: " +(Long)p1.getValue());
            }
            System.out.println("\n");
        }
    }
    //Daniel
    public void question13(){
        List<Pair<String,Long>> profs1 = FuncUtil5.getListProfWithCountBadRate.apply(university);
        System.out.println("================ Professor with the count of bad feedback rating  ===================");
        for(Pair<String,Long> p : profs1 ) {
            System.out.println(p.toString());
        }
    }
    public void question14(){
        System.out.println("================ The best rated professor for the whole university ===================");
        String getBestRatedProf = FuncUtil5.getBestRatedProf.apply(university);
        System.out.println("The best rated professor for the whole university is:"+getBestRatedProf);
    }
    public void question15(){
        System.out.println("================ Which question is mostly disagreed by the students for each Professor ===================");
        List<Pair<String,String>> profs = FuncUtil5.getProfsWithDAQuest.apply(university);
        for(Pair<String,String> pr : profs ) {
            System.out.println(pr.toString());
        }
    }


     public static List<String> MenuList(){

        String problem1 = "Display the ID of a Professor who has the highest rate from a given course?";
        String problem2 = "Given a University depending on student ID List students never gave a feedback?";
        String problem3 = "How many thumps up does the highest rated professor got?";

        String problem4 = "Total Thumbs Down Count in the application";
        String problem5 = "Top Feedback ShortAnswer in the application";
        String problem6 = "Number Of Feedback X{Mateus jose} Professor For Xyear{2020}";
        
        String problem7 = "What year did the platform have the most reviews?";
        String problem8 = "List of professors created after 2019 who have at least 4 stars?";
        String problem9 = "Given a university, what is the course with the lowest rating for a given year";
        
        String problem10 = "Get all the professor with NStar{3} that teaches XCourse{MPP}";
        String problem11 = "Find the student who have left more than 1 one-star start reviews";
        String problem12 = "Get A list of professors who teach at least X corses and show their list of courses with the feedback";
        
        String problem13 = "Professor with the count of bad feedback rating";
        String problem14 = "The best rated professor for the whole university";
        String problem15 = "Professors with their most complained concern";


        List<String> listOfQuestions = new ArrayList<String>();
        listOfQuestions.add(problem1);
        listOfQuestions.add(problem2);
        listOfQuestions.add(problem3);
        listOfQuestions.add(problem4);
        listOfQuestions.add(problem5);
        listOfQuestions.add(problem6);
        listOfQuestions.add(problem7);
        listOfQuestions.add(problem8);
        listOfQuestions.add(problem9);
        listOfQuestions.add(problem10);
        listOfQuestions.add(problem11);
        listOfQuestions.add(problem12);
        listOfQuestions.add(problem13);
        listOfQuestions.add(problem14);
        listOfQuestions.add(problem15);

        return listOfQuestions;
     }
}
