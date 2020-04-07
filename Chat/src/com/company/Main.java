package com.company;

import java.sql.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    // Note for future projects:
    // I always used ex.getMessage() after catching SQLExceptions --> ex.printStackTrace() would have given more information...

    static int messagesDisplayed = 0;
    static boolean running = true;
    static int id = 0;

    public static void main(String[] args) {
        id = logInName();
        System.out.println("If you ever want to close the chat type in 'close chat'\n(This also deletes all messages)");
        updateChatroom();
        startPeriodicalUpdate();
        while(running) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            boolean closeChat = checkCloseChat(message);
            if (closeChat) {
                running = false;
                clearChat();
                // Todo: Bug... for some reason it keeps scanning for messages even when running is false (chat is cleared, periodical Update should be stopped) --> System.exit is needed... but why?
                System.exit(0);
            } else {
                sendMessage(message);
            }
        }
    }

    public static void clearChat() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
            String deleteMessages = "DELETE FROM message";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(deleteMessages);
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

    // updates chatroom every second
    private static void startPeriodicalUpdate() {
        Timer timer = new Timer();
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                updateChatroom();
            }
        };
        timer.scheduleAtFixedRate(update, 0, 1000);
        // not really needed since System.exit is used
        if (!running) {
            update.cancel();
            timer.cancel();
        }
    }

    public static void sendMessage(String message) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
            String sendMessage = "INSERT INTO message (text, authorId) VALUES ('" + message + "','" + id + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sendMessage);
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

    private static boolean checkCloseChat(String message) {
        if (message.equalsIgnoreCase("close chat")) {
            return true;
        } else {
            return false;
        }
    }

    public static void updateChatroom() {
        Connection conn = null;
        // needed to only select posts which haven't been displayed already:
        if (!running) {
            return;
        }
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
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryCountPosts);
            while (rs.next()) {
                count = rs.getInt("count");
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

    // checks if user is already known, if not creates new user
    // assumes that each name can only exist once
    public static int logInName() {
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your name?");
        String userName = scanner.nextLine();
        id = checkForID(userName);
        // create new author if id == 0 (means user is not known)
        if (id == 0) {
            Connection conn;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
                String createNewAuthor = "INSERT INTO author (`name`) VALUES ('" + userName + "')";
                Statement stmt1 = conn.createStatement();
                stmt1.executeUpdate(createNewAuthor);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            // retrieve newly generated userID
            id = checkForID (userName);
        }
        System.out.println("Login successful");
        return id;
    }

    public static int checkForID (String userName) {
        int id = 0; //if 0 = userNotKnown
        // check if user already has id
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?user=root");
            Statement stmt;
            String getAuthors = "SELECT * FROM author";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getAuthors);
            // if name is in database, id is taken and isNotKnown is false
            while (rs.next() && id == 0) {
                if (rs.getString("name").equalsIgnoreCase(userName)) {
                    id = rs.getInt("author_id");
                }
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
        return id;
    }

    // "old" logIn function
    public static int logInID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your user ID?");
        int id = scanner.nextInt();
        return id;
    }

}
