package pl.kurs.shape_api.commands;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class UpdateShapeCommand {


    @NotEmpty
    private Map<String, Object> parameters;

    private int version;

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public int getVersion() {
        return version;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
