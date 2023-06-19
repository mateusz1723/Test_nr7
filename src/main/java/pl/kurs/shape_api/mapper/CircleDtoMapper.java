package pl.kurs.shape_api.mapper;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.CircleDto;
import pl.kurs.shape_api.dto.ShapeDto;

import pl.kurs.shape_api.models.Circle;
import pl.kurs.shape_api.models.Shape;

@Service
public class CircleDtoMapper implements ShapeDtoMapper{


    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public ShapeDto map(Shape shape) {
        CircleDto circleDto = new CircleDto();
        circleDto.setRadius(((Circle) shape).getRadius());
        circleDto.setArea(shape.getArea());
        circleDto.setCreatedAt(shape.getCreatedAt());
        circleDto.setVersion(shape.getVersion());
        circleDto.setId(shape.getId());
        circleDto.setType(shape.getType());
        circleDto.setCreatedBy(shape.getCreatedBy());
        circleDto.setLastModifiedAt(shape.getLastModifiedAt());
        circleDto.setLastModifiedBy(shape.getLastModifiedBy());
        circleDto.setPerimeter(shape.getPerimeter());
        return circleDto;
    }
}
