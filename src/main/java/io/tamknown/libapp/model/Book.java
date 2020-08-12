package io.tamknown.libapp.model;

import javax.persistence.*;
import java.lang.Integer;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String description;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "student_id")
    private Integer studentId;

    public Book() {
    }

    public Book(Integer id, String title, String description, Integer rating, Integer studentId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
