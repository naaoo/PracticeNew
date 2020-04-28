package com.company.database.repositories;

import com.company.database.model.Location;
import com.company.database.repositories.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class QueryConnection {
    private static DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    public static ResultSet makeQuery(String query) {
        ResultSet rs = dbConnector.fetchData(query);
        return rs;
    }
}
