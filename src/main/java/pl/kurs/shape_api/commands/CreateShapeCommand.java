package pl.kurs.shape_api.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class CreateShapeCommand {

    @NotBlank
    private String type;
    @NotEmpty
    private Map<String, Object> parameters;

    public String getType() {
        return type;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

}

