package pl.kurs.shape_api.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "squares")
public class Square extends Shape {
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double sideLength;

    @Formula(value = "4 * side_length")
    private double perimeter;

    @Formula(value = "side_length * side_length")
    private double area;

    public Square() {
    }

    public Square(double sideLength) {
        this.sideLength = sideLength;
        this.setType("SQUARE");
    }

    public Square(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double sideLength) {
        super(version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
        this.sideLength = sideLength;
    }


    @Transient
    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Transient
    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.sideLength, sideLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideLength);
    }

}
