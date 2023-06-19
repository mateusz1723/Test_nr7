package pl.kurs.shape_api.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.models.Shape;

@Service
public class ShapeMapperDtoConverter implements Converter<Shape, ShapeDto> {

    private final ShapeMapper shapeMapper;

    public ShapeMapperDtoConverter(ShapeMapper shapeMapper) {
        this.shapeMapper = shapeMapper;
    }

    @Override
    public ShapeDto convert(MappingContext<Shape, ShapeDto> mappingContext) {
        Shape shape = mappingContext.getSource();
        return shapeMapper.mapToDto(shape);
    }
}
