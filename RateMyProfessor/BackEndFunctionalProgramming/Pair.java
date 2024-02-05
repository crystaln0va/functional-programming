package com.RateMyProfessor.BackEndFunctionalProgramming;

import java.util.Objects;

public class Pair<T,V> {
    private T key;
    private V value;
    
    public Pair(T key, V value) {
      this.key = key;
      this.value = value;
    }
    public T getKey() {
        return this.key;
    }
    public V getValue() {
        return this.value;
    }
    public String toString() {
        return "[" + this.key + "," + this.value + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return getKey().equals(pair.getKey()) && getValue().equals(pair.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}

