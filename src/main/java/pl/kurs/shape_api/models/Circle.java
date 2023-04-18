package pl.kurs.shape_api.models;

import javax.persistence.*;
import java.time.LocalDate;
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
        this.setPerimeter(2 * Math.PI * radius);
        this.setArea(Math.PI * Math.pow(radius, 2));
        this.setType("CIRCLE");
    }

    public Circle(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double radius) {
        super(version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
        this.radius = radius;
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
