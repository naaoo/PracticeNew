package com.company.database.model;

import org.joda.time.DateTime;

public class Reservation {
    public Integer id;
    public int customerId;
    public int tableId;
    public int seats;
    public DateTime timeStart;
    // its assumed that one reservation has a duration of 1h30min
    public DateTime timeEnd;

    public Reservation(Integer id, int customerId, int tableId, int seats, DateTime timeStart, DateTime timeEnd) {
        this.id = id;
        this.customerId = customerId;
        this.tableId = tableId;
        this.seats = seats;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
}
