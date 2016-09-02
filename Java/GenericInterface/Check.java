package main;

public interface Check<T extends Comparable<T>> {

    Boolean eqls(T e, T ex);
}
