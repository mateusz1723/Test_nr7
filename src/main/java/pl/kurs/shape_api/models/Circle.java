package pl.kurs.shape_api.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "circles")
public class Circle extends Shape {
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double radius;


    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(long id, int version, ShapeType type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double area, double perimeter) {
        super(id, version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy, area, perimeter);
    }

    public Circle(long id, int version, ShapeType type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double area, double perimeter, double radius) {
        super(id, version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy, area, perimeter);
        this.radius = radius;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }


    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }
}
