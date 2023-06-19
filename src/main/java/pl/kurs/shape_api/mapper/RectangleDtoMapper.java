package pl.kurs.shape_api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.CircleDto;
import pl.kurs.shape_api.dto.RectangleDto;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.models.Circle;
import pl.kurs.shape_api.models.Rectangle;
import pl.kurs.shape_api.models.Shape;

@Service
public class RectangleDtoMapper implements ShapeDtoMapper{


    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public ShapeDto map(Shape shape) {
        RectangleDto rectangleDto = new RectangleDto();
        rectangleDto.setArea(shape.getArea());
        rectangleDto.setCreatedAt(shape.getCreatedAt());
        rectangleDto.setVersion(shape.getVersion());
        rectangleDto.setId(shape.getId());
        rectangleDto.setType(shape.getType());
        rectangleDto.setCreatedBy(shape.getCreatedBy());
        rectangleDto.setLastModifiedAt(shape.getLastModifiedAt());
        rectangleDto.setLastModifiedBy(shape.getLastModifiedBy());
        rectangleDto.setPerimeter(shape.getPerimeter());
        rectangleDto.setWidth(((Rectangle) shape).getWidth());
        rectangleDto.setHeight(((Rectangle) shape).getHeight());
        return rectangleDto;
    }
}
