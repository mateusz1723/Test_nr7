package pl.kurs.shape_api.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "squares")
public class Square extends Shape{
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double sideLength;


    public Square() {
    }

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public Square(long id, int version, ShapeType type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double area, double perimeter) {
        super(id, version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy, area, perimeter);
    }

    public Square(long id, int version, ShapeType type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double area, double perimeter, double sideLength) {
        super(id, version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy, area, perimeter);
        this.sideLength = sideLength;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.SQUARE;
    }


    @Override
    public double calculatePerimeter() {
        return 4 * sideLength;
    }

    @Override
    public double calculateArea() {
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

    @Override
    public String toString() {
        return "Square{" +
                "sideLength=" + sideLength +
                '}';
    }
}
