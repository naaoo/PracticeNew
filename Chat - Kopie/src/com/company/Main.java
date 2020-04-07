package com.company;

import java.sql.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static int messagesDisplayed = 0;

    public static void main(String[] args) {

        int id = logIn();

        updateChatroom();
        startPeriodicalUpdate();
        while(true) {
            sendMessage(id);
        }
    }

    private static void startPeriodicalUpdate() {
        Timer timer = new Timer();
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                updateChatroom();
            }
        };
        timer.scheduleAtFixedRate(update, 0, 1000);
    }

    public static void sendMessage(int id) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
            String message = scanner.nextLine();
            String querySendMessage = "INSERT INTO message (text, authorId) VALUES ('" + message + "','" + id + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(querySendMessage);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void updateChatroom() {
        Connection conn = null;
        // needed to only select posts which haven't been displayed already
        int postsInDatabase = countPosts();
        int notDisplayedPosts = postsInDatabase - messagesDisplayed;
        messagesDisplayed = postsInDatabase;
        // --> in next update all notDisplayedPosts will be SELECTED
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
            Statement stmt;

            String querySelectMessages = "SELECT * FROM (SELECT * FROM message INNER JOIN author ON message.authorId = author_id ORDER BY message_id DESC LIMIT " + notDisplayedPosts + ") SQ ORDER BY message_id ASC";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(querySelectMessages);
                while (rs.next()) {
                    String author = rs.getString("name");
                    String message = rs.getString("text");
                    String time = rs.getString("time");
                    String[] timeArr = time.split(" ");
                    time = timeArr[1].substring(0,5);
                    System.out.println(author + " (" + time + "): " + message);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static int countPosts() {
        Connection conn = null;
        int count = 0;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
            Statement stmt;
            String queryCountPosts = "SELECT count(*) AS count FROM message";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(queryCountPosts);
                while (rs.next()) {
                    count = rs.getInt("count");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return count;
    }

    public static int logIn() {
        //Todo: check if user exists, if not create new (checked for name)
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your user ID?");
        int id = scanner.nextInt();
        return id;
    }

}
