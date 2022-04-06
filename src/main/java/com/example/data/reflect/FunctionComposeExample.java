package com.example.data.reflect;

import com.example.data.demo.domain.UserEntity;

import java.util.function.Function;

public class FunctionComposeExample {
    public static void main(String args[]) {
        Function<UserEntity, String> funcUserToString = UserEntity::getName;


        status(UserEntity::getName, "admin");

    }


    public static void status(Function<UserEntity, String> function, String value) {


        System.out.println(value);

    }

}

