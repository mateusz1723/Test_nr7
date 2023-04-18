package pl.kurs.shape_api.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "squares")
public class Square extends Shape {
    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private double sideLength;


    public Square() {
    }

    public Square(double sideLength) {
        this.sideLength = sideLength;
        this.setPerimeter(4 * sideLength);
        this.setArea(sideLength * sideLength);
        this.setType("SQUARE");
    }

    public Square(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy, double sideLength) {
        super(version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy);
        this.sideLength = sideLength;
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
