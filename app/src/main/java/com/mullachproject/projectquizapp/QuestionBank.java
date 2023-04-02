package com.mullachproject.projectquizapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.mullachproject.projectquizapp.MainActivity.*;

public class QuestionBank {
    private final ArrayList<QuestionsList> javaQuestions = new ArrayList<>();
    private final ArrayList<QuestionsList> cppQuestions = new ArrayList<>();
    private final ArrayList<QuestionsList> pythonQuestions = new ArrayList<>();
    private final ArrayList<QuestionsList> vbQuestions = new ArrayList<>();


    public void setJavaQuestions() {
        // Creating a connection to the database to fetch java Questions
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            // get random 5 lines from the database and store in QuestionsList question
            ResultSet rs = statement.executeQuery("select * from javaQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()) {
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));

                // add question to javaQuestions
                javaQuestions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<QuestionsList> getJavaQuestions() {
        // set javaQuestions
        setJavaQuestions();

        // return to caller
        return javaQuestions;
    }

    public void setPythonQuestions() {
        try {
            // Creating a connection to the database to fetch python Questions
            Class.forName("com.mysqljdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            // get random 5 lines from the database and store in QuestionsList question
            ResultSet rs = statement.executeQuery("select * from pythonQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()) {
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                // add question to pythonQuestions
                pythonQuestions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<QuestionsList> getPythonQuestions() {
        // set pythonQuestions
        setPythonQuestions();

        // return to caller
        return pythonQuestions;
    }

    public void setCppQuestions() {
        // Creating a connection to the database to fetch python Questions
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            // get random 5 lines from the database and store in QuestionsList question
            ResultSet rs = statement.executeQuery("select * from cppQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()) {
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                // add question to pythonQuestions
                cppQuestions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<QuestionsList> getCPPQuestions() {
        // set cppQuestions
        setCppQuestions();

        // return to caller
        return cppQuestions;
    }

    public void setVbQuestions() {
        // Creating a connection to the database to fetch python Questions
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Statement statement = connection.createStatement();

            // get random 5 lines from the database and store in QuestionsList question
            ResultSet rs = statement.executeQuery("select * from vbQuestion ORDER BY RAND() LIMIT 5");
            while (rs.next()) {
                QuestionsList question = new QuestionsList(rs.getString("question"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("answer"),
                        rs.getString("userSelectedAnswer"));
                // add question to pythonQuestions
                vbQuestions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<QuestionsList> getVBQuestions() {
        // set vbQuestions
        setVbQuestions();

        // return to caller
        return vbQuestions;
    }
}