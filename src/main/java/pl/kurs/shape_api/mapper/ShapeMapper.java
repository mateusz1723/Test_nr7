package pl.kurs.shape_api.mapper;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.models.Shape;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class ShapeMapper {


    private final Map<String, ShapeDtoMapper> mappers;

    public ShapeMapper(Set<ShapeDtoMapper> mappers){
        this.mappers = mappers.stream().collect(Collectors.toMap(ShapeDtoMapper::getType, Function.identity()));
    }

    public ShapeDto mapToDto(Shape shape){
        return mappers.get(shape.getType()).map(shape);
    }


}
