package com.mullachproject.projectquizapp;

import java.sql.*;
import java.util.ArrayList;
import static com.mullachproject.projectquizapp.MainActivity.*;

public class QuestionBank {
    ArrayList<QuestionsList> questions = new ArrayList<>();
    public ArrayList<QuestionsList> getJavaQuestions(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from javaQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()){
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                questions.add(question);
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return questions;
    }

    public ArrayList<QuestionsList> getPythonQuestions(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from pythonQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()){
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                questions.add(question);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return questions;
    }

    public ArrayList<QuestionsList> getCPPQuestions(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from cppQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()){
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                questions.add(question);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return questions;
    }

    public ArrayList<QuestionsList> getVBQuestions(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from vbQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()){
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                questions.add(question);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return questions;
    }
}

