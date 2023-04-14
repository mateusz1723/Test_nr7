package pl.kurs.shape_api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.RectangleDto;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.models.Shape;

@Service
public class RectangleDtoMapper implements ShapeDtoMapper{

    private final ModelMapper mapper;

    public RectangleDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public ShapeDto map(Shape shape) {
        return mapper.map(shape, RectangleDto.class);
    }
}
