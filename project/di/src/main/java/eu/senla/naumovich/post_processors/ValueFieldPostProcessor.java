package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.annotations.Value;
import eu.senla.naumovich.Context;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ValueFieldPostProcessor implements PostProcessor{
    @Override
    public Object process(Class<?> clazz, Context context) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field field = valueField(clazz);
        Object newInstance = clazz.getConstructor().newInstance();
        valueHandler(newInstance, field, context);
        return newInstance;
    }

    public Field valueField(Class<?> clazz) {
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Value.class)) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        return null;
    }

    public void valueHandler(Object object, Field field, Context context) throws IOException, IllegalAccessException {
        context.getProps().load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        Value annotation = field.getAnnotation(Value.class);
        String annotationName = annotation.value().replaceAll("[{}$]", "");
        String propValue = context.getProps().getProperty(annotationName);
        field.set(object,propValue);
    }
}
