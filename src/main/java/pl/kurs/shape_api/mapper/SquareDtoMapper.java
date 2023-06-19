package pl.kurs.shape_api.mapper;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.dto.SquareDto;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.Square;

@Service
public class SquareDtoMapper implements ShapeDtoMapper{

    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public ShapeDto map(Shape shape) {
        SquareDto squareDto = new SquareDto();
        squareDto.setArea(shape.getArea());
        squareDto.setCreatedAt(shape.getCreatedAt());
        squareDto.setVersion(shape.getVersion());
        squareDto.setId(shape.getId());
        squareDto.setType(shape.getType());
        squareDto.setCreatedBy(shape.getCreatedBy());
        squareDto.setLastModifiedAt(shape.getLastModifiedAt());
        squareDto.setLastModifiedBy(shape.getLastModifiedBy());
        squareDto.setPerimeter(shape.getPerimeter());
        squareDto.setSideLength(((Square) shape).getSideLength());
        return squareDto;
    }
}
