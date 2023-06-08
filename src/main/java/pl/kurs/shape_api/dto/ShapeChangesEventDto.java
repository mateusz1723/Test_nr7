package pl.kurs.shape_api.dto;

import java.time.LocalDate;
import java.util.Set;

public class ShapeChangesEventDto {

    private long id;
    private LocalDate changedDate;
    private long shapeId;
    private String changesBy;
    private Set<ShapeChangesDto> changes;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<ShapeChangesDto> getChanges() {
        return changes;
    }

    public void setChanges(Set<ShapeChangesDto> changes) {
        this.changes = changes;
    }

    public String getChangesBy() {
        return changesBy;
    }

    public void setChangesBy(String changesBy) {
        this.changesBy = changesBy;
    }
}
