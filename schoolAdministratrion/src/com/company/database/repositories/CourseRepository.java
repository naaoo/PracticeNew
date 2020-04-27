package com.company.database.repositories;

import com.company.Main;
import com.company.database.model.Course;
import com.company.database.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements Repository{
    private DatabaseConnector dbConnector;
    public ArrayList<Course> coursesArr = new ArrayList<>();

    public CourseRepository() {
        this.dbConnector = DatabaseConnector.getInstance();
    }

    @Override
    public List findAll() {
        ArrayList<Course> courses = new ArrayList<>();
        String queryUser = "SELECT * FROM course";
        ResultSet rs = dbConnector.fetchData(queryUser);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int seats = rs.getInt("max_amount_seats");
                int teacherId = rs.getInt("teacher_id");
                Person teacher = Main.personRepo.personsArr.get(teacherId - 1);
                Course course = new Course(id, name, seats, teacher);
                courses.add(course);
            }
            dbConnector.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.coursesArr = courses;
        return courses;
    }

    // could be found in database as well, but to me it makes more sense to find it in existing repo,
    // so no duplicates are created
    @Override
    public Object findOne(int id) {
        Course course = Main.courseRepo.coursesArr.get(id);
        return course;
    }

    @Override
    public void create(Object entity) {
        Course course = (Course) entity;
        String name = course.name;
        int capacity = course.maxSeats;
        int teacherId = course.teacher.id;
        dbConnector.insert("INSERT INTO course (name, max_amount_seats, teacher_id)" +
                "VALUES ('" + name + "', '" + capacity + "', '" + teacherId + "');");
        Main.courseRepo.findAll();

    }
}
