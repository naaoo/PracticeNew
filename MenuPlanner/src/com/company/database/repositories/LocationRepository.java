package com.company.database.repositories;

import com.company.database.model.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository implements Repository{
    private DatabaseConnector dbConnector;
    public ArrayList<Location> locationsArr = new ArrayList<>();

    public LocationRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
        findAll();
    }

    @Override
    public List findAll() {
        ArrayList<Location> locations = new ArrayList<>();
        String queryIngredients = "SELECT * FROM locations";
        ResultSet rs = dbConnector.fetchData(queryIngredients);
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                double price = Math.round(rs.getDouble("price") * 100.0) / 100.0;
                locations.add(new Location(name, price));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
        this.locationsArr = locations;
        return locations;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public void create(Object entity) {
        Location location = (Location)entity;
        String insertLocation = "INSERT INTO locations (name, price) " +
                "VALUES ('" + location.name + "', '" + location.deliveryCosts + "');";
        dbConnector.insert(insertLocation);
        System.out.println("Location " + location.name + " added");
    }
}
