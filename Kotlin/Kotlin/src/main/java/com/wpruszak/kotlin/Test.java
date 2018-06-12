package com.wpruszak.kotlin;

public class Test implements Interfaces {
    @Override
    public void method() {
        System.out.println("Something.");
    }

    public static void main(String[] args) {
        User user = new User("user", 1, 2);
        System.out.println(user);
    }
}
