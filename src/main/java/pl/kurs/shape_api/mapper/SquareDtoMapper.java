package pl.kurs.shape_api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.dto.SquareDto;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.ShapeType;

@Service
public class SquareDtoMapper implements ShapeDtoMapper{

    private final ModelMapper mapper;

    public SquareDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getType() {
        return ShapeType.SQUARE.name();
    }

    @Override
    public ShapeDto map(Shape shape) {
        return mapper.map(shape, SquareDto.class);
    }
}