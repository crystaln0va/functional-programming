package com.RateMyProfessor.BackEndFunctionalProgramming.FunctionalProgramming;

public class FuncUtilMain {
    @FunctionalInterface
	public interface Trifunction<X, Y, Z, R>{
		R apply(X x, Y y, Z z);
	}
	
	@FunctionalInterface
	public interface Quafunction<X, Y, Z, W, R>{
		R apply(X x, Y y, Z z, W w);
	}
    
}
