package com.company.database.repositories;

import com.company.database.model.Reservation;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements Repository {
    private DatabaseConnector dbConnector;
    public ArrayList<Reservation> reservationArr = new ArrayList<>();

    public ReservationRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
        findAll();
    }

    @Override
    public List findAll() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String queryReservation = "SELECT * FROM reservations";
        ResultSet rs = dbConnector.fetchData(queryReservation);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int tableId = rs.getInt("table_id");
                int seats = rs.getInt("seats");
                String timeStartString = rs.getString("time_start");
                String timeEndString = rs.getString("time_end");
                DateTime timeStart = df.parseDateTime(timeStartString);
                DateTime timeEnd = df.parseDateTime(timeEndString);
                reservations.add(new Reservation(id, customerId, tableId, seats, timeStart, timeEnd));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dbConnector.closeConnection();
        this.reservationArr = reservations;
        return reservations;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    @Override
    public void create(Object entity) {
        Reservation reservation = (Reservation)entity;
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String timeStart = df.print(reservation.timeStart);
        String timeEnd = df.print(reservation.timeEnd);
        String insertReservation = "INSERT INTO reservations (customer_id, table_id, seats, time_start, time_end) " +
                "VALUES ('" + reservation.customerId + "', '" + reservation.tableId + "', '" + reservation.seats +
                "', CAST('" + timeStart + "' AS DATETIME), CAST('" + timeEnd + "' AS DATETIME))";
        dbConnector.insert(insertReservation);
        this.findAll();
    }

    public void cancel(int id) {
        String delete = "DELETE FROM reservations WHERE id = " + id;
        dbConnector.delete(delete);
        this.findAll();
    }
}
