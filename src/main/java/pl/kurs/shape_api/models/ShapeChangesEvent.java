package pl.kurs.shape_api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShapeChangesEvent implements Serializable {
    public static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shape_changes_event")
    private long id;
    private LocalDate changedDate;
    private long shapeId;
    private String changesBy;
    @OneToMany(mappedBy = "shapeChangesEvent", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<ShapeChanges> changes = new HashSet<>();

    public ShapeChangesEvent() {
    }


    public ShapeChangesEvent(LocalDate changedDate, long shapeId, String changesBy) {
        this.changedDate = changedDate;
        this.shapeId = shapeId;
        this.changesBy = changesBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(LocalDate date) {
        this.changedDate = date;
    }

    public long getShapeId() {
        return shapeId;
    }

    public void setShapeId(long shapeId) {
        this.shapeId = shapeId;
    }

    public String getChangesBy() {
        return changesBy;
    }

    public void setChangesBy(String changesBy) {
        this.changesBy = changesBy;
    }

    public Set<ShapeChanges> getChanges() {
        return changes;
    }

    public void setChanges(Set<ShapeChanges> changes) {
        this.changes = changes;
    }

    public void addChanges(ShapeChanges shapeChanges) {
        shapeChanges.setShapeChangesEvent(this);
        changes.add(shapeChanges);
    }
}
