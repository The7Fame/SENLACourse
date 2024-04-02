package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.annotations.Value;
import eu.senla.naumovich.Context;

import java.io.IOException;
import java.lang.reflect.Field;

public class ValueFieldPostProcessor implements PostProcessor{
    @Override
    public void process(Object object, Context context) throws IOException, IllegalAccessException {

        for (Field declaredField : object.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Value.class)) {
                declaredField.setAccessible(true);
                valueHandler(object, declaredField, context);
                declaredField.setAccessible(false);
            }
        }
    }

    private void valueHandler(Object object, Field field, Context context) throws IOException, IllegalAccessException {
        Value declaredValue = field.getAnnotation(Value.class);
        String annotationValue = declaredValue.value().replaceAll("[{}$]", "");
        String propValue = context.getProps().getProperty(annotationValue);
        field.set(object, propValue);
    }
}
