package pl.kurs.shape_api.mapper;


import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.models.Shape;


public interface ShapeDtoMapper {

     String getType();
     ShapeDto map(Shape shape);

}
