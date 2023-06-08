package pl.kurs.shape_api.dto;

public class ShapeChangesDto {

    private long shapeChangesId;
    private String fieldChanged;
    private String oldValue;
    private String newValue;

    public long getShapeChangesId() {
        return shapeChangesId;
    }

    public void setShapeChangesId(long shapeChangesId) {
        this.shapeChangesId = shapeChangesId;
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
}
