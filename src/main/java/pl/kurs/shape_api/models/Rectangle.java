package pl.kurs.shape_api.models;

import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rectangles")
public class Rectangle extends Shape {
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double height;

    @Formula(value = "2 * width + 2 * height")
    private double perimeter;

    @Formula(value = "width * height")
    private double area;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.setType("RECTANGLE");
    }

    public Rectangle(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double width, double height) {
        super(version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
        this.width = width;
        this.height = height;
    }



    @Transient
    @Override
    public double getPerimeter() {
        return 2 * width + 2 * height;
    }

    @Transient
    @Override
    public double getArea() {
        return width * height;
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
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.height, height) == 0 && Double.compare(rectangle.perimeter, perimeter) == 0 && Double.compare(rectangle.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, height, perimeter, area);
    }
}
