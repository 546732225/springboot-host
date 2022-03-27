package com.example.data.demo.query;


import com.example.data.annotation.CheckSqlInjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuery {


    @CheckSqlInjection
    private String username;

    @CheckSqlInjection
    private String password;

    @CheckSqlInjection
    private String orderBy;
}
