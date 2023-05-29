package pl.kurs.shape_api.dto;

import pl.kurs.shape_api.models.ShapeChanges;

import java.time.LocalDate;
import java.util.Set;

public class ShapeChangesEventDto {

    private long shapeChangesEventId;
    private LocalDate changedDate;
    private long shapeId;
    private String changesBy;
    private Set<ShapeChanges> changes;

    public long getShapeChangesEventId() {
        return shapeChangesEventId;
    }

    public void setShapeChangesEventId(long shapeChangesEventId) {
        this.shapeChangesEventId = shapeChangesEventId;
    }

    public LocalDate getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(LocalDate changedDate) {
        this.changedDate = changedDate;
    }

    public long getShapeId() {
        return shapeId;
    }

    public void setShapeId(long shapeId) {
        this.shapeId = shapeId;
    }

    public Set<ShapeChanges> getChanges() {
        return changes;
    }

    public void setChanges(Set<ShapeChanges> changes) {
        this.changes = changes;
    }

    public String getChangesBy() {
        return changesBy;
    }

    public void setChangesBy(String changesBy) {
        this.changesBy = changesBy;
    }
}
