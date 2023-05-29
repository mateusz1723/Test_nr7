package pl.kurs.shape_api.models;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "circles")
public class Circle extends Shape {
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double radius;

    @Formula(value = "2 * PI() * radius")
    private double perimeter;

    @Formula(value = "PI() * power(radius,2)")
    private double area;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
        this.setType("CIRCLE");
    }

    public Circle(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double radius) {
        super(version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
        this.radius = radius;
    }


    @Transient
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Transient
    @Override
    public double getArea() {
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
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 && Double.compare(circle.perimeter, perimeter) == 0 && Double.compare(circle.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius, perimeter, area);
    }
}
