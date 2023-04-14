package pl.kurs.shape_api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.CircleDto;
import pl.kurs.shape_api.dto.ShapeDto;

import pl.kurs.shape_api.models.Shape;

@Service
public class CircleDtoMapper implements ShapeDtoMapper{

    private final ModelMapper mapper;

    public CircleDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public ShapeDto map(Shape shape) {
        return mapper.map(shape, CircleDto.class);
    }
}
