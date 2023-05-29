package pl.kurs.shape_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ShapeChanges implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shape_changes")
    private long id;

    private String fieldChanged;
    private String oldValue;
    private String newValue;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shape_changes_event_id")
    private ShapeChangesEvent shapeChangesEvent;

    public ShapeChanges() {
    }

    public ShapeChanges(String fieldChanged, String oldValue, String newValue) {
        this.fieldChanged = fieldChanged;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFieldChanged() {
        return fieldChanged;
    }

    public void setFieldChanged(String fieldChanged) {
        this.fieldChanged = fieldChanged;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public ShapeChangesEvent getShapeChangesEvent() {
        return shapeChangesEvent;
    }

    public void setShapeChangesEvent(ShapeChangesEvent shapeChangesEvent) {
        this.shapeChangesEvent = shapeChangesEvent;
    }

    @Override
    public String toString() {
        return "ShapeChanges{" +
                "id=" + id +
                ", fieldChanged='" + fieldChanged + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", shapeChangesEvent=" + shapeChangesEvent +
                '}';
    }
}
