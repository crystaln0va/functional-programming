package com.RateMyProfessor.BackEndFunctionalProgramming.domain;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class University {
    private Long id;
    private String name;
    private String state;
    private String city;
    private String address;
    private String webAddress;
    private List<Professor> professors = new ArrayList<>();
    @Override
    public String toString(){
        String m = "********************************University information********************************\n";
        String info = "id: "+id+"\nname: "+name + "\nstate:" +state + "\ncity: "+city+ "\naddress: "+address +"\nwebAddress: "+webAddress;
        return m+info;
    }
}
