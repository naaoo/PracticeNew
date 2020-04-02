package com.company;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String urlNotebook = "jdbc:mysql://localhost:3306/digital_notebook?user=root";
        readDatabase(urlNotebook);
        insertIntoDatabase(urlNotebook);
    }

    private static void insertIntoDatabase(String url) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        try {
            // Connection
            conn = DriverManager.getConnection(url);
            System.out.println("Connection successful");
            // get title
            System.out.println("Title of your note?");
            String title = scanner.nextLine();
            // get today
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(date);
            // get note text
            System.out.println("Note?");
            String text = scanner.nextLine();
            // generate query
            String queryInsertNote = "INSERT INTO notes (title, date, note_text) VALUES ('" + title + "','" + dateString + "','" + text + "')";
            // statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(queryInsertNote);
        } catch (SQLException ex) {
            throw new Error("Error", ex);
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

    private static void readDatabase(String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = null;
            String querySelectNotes = "SELECT * FROM notes";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(querySelectNotes);
                while (rs.next()) {
                    int noteId =  rs.getInt("note_id");
                    String title = rs.getString("title");
                    Date date = rs.getDate("date");
                    String text = rs.getString("note_text");
                    System.out.println(noteId + ": " + title + " (" + date + "): " + text);
                }
            } catch (SQLException ex) {
                throw new Error("Problem occurred", ex);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (SQLException ex) {
            throw new Error("Wasn't able to connect to database", ex);
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
}
