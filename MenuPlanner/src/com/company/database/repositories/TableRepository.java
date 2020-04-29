package com.company.database.repositories;

import com.company.database.model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableRepository implements Repository{
    private DatabaseConnector dbConnector;
    public ArrayList<Table> tablesArr = new ArrayList<>();

    public TableRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
        findAll();
    }

    @Override
    public List findAll() {
        ArrayList<Table> tables = new ArrayList<>();
        String queryTables = "SELECT * FROM tables";
        ResultSet rs = dbConnector.fetchData(queryTables);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int maxSeats = rs.getInt("max_seats");
                tables.add(new Table(id, maxSeats));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
        this.tablesArr = tables;
        return tables;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public void create(Object entity) {
        Table table = (Table)entity;
        String insertTable = "INSERT INTO tables (max_seats) " +
                "VALUES (" + table.maxSeats + ");";
        dbConnector.insert(insertTable);
        this.findAll();
    }
}
