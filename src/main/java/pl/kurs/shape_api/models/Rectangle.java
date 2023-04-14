package pl.kurs.shape_api.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rectangles")
public class Rectangle extends Shape {
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double height;


    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.setPerimeter(2 * width + 2 * height);
        this.setArea(width * height);
        this.setType("RECTANGLE");
    }

    public Rectangle(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double width, double height) {
        super(version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
        this.width = width;
        this.height = height;
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
