package org.example.service;

import org.example.entity.User;

import java.sql.Connection;
import java.util.Scanner;

public class UserService {
    public  User createUser(String username, String password){
       return new User(username, password);
    }
}
