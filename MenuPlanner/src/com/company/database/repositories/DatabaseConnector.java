package com.company.database.repositories;

import java.sql.*;

public class DatabaseConnector {
    private Connection conn = null;
    private Statement stmt = null;
    private  String databaseUrl = "jdbc:mysql://localhost:3306/restaurant?user=root&allowMultiQueries=true";

    private static DatabaseConnector instance;

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    private DatabaseConnector() {
    }

    private void buildConnection() {
        try {
            conn = DriverManager.getConnection(databaseUrl);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("not able to build connection to database");
            ex.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("not able to close connection to database");
            ex.printStackTrace();
        }
    }

    // Connection needs to be closed after handling ResultSet
    public ResultSet fetchData(String sql) {
        buildConnection();
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("not able to fetch data");
            ex.printStackTrace();
            closeConnection();
        }
        return null;
    }

    public boolean delete(String sql) {
        buildConnection();
        try {
            int result = stmt.executeUpdate(sql);
            if (result == 0) {
                System.out.println("no matching entry found");
                return false;
            } else {
                System.out.println("delete successful, update your data");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("could not delete data");
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean update(String sql) {
        buildConnection();
        try {
            int result = stmt.executeUpdate(sql);
            if (result == 0) {
                System.out.println("no matching entry found");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("not able to update data");
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean insert(String sql) {
        buildConnection();
        try {
            int result = stmt.executeUpdate(sql);
            if (result == 0) {
                System.out.println("no matching entry found");
                return false;
            } else {
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Not possible. Maybe dish already exists");
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            System.out.println("not able to update data");
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }
}