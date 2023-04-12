package pl.kurs.shape_api.shapeFactory;

import pl.kurs.shape_api.models.Shape;

import java.util.Map;

public interface ShapeCreator {

     String getType();
     Shape create(Map<String, Object> parameters);


     default Double getDoubleParameters(String name, Map<String, Object> parameters){
         return (Double) parameters.get(name);
     }
}