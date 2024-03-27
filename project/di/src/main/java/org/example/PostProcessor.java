package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class PostProcessor {
    private final static PostProcessor instance = new PostProcessor();
    public final static Properties props = new Properties();
    public static PostProcessor getInstance(){
        return instance;
    }
    public Constructor<?> annotatedConstructor(Class<?> klass) {
        for (Constructor<?> declaredConstructor : klass.getDeclaredConstructors()) {
            if (declaredConstructor.isAnnotationPresent(Autowired.class)) {
                declaredConstructor.setAccessible(true);
                return declaredConstructor;
            }
        }
        return null;
    }

    public Method annotatedMethod(Class<?> klass) {
        for (Method declaredMethod : klass.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Autowired.class)) {
                declaredMethod.setAccessible(true);
                return declaredMethod;
            }
        }
        return null;
    }

    public Field annotatedField(Class<?> klass) {
        for (Field declaredField : klass.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        return null;
    }

    public Field annotatedValue(Class<?> klass) {
        for (Field declaredField : klass.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Value.class)) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        return null;
    }

    public void valueHandler(Object object, Field field) throws IOException, IllegalAccessException {
        props.load(new FileInputStream("./main/src/main/resources/application.properties"));
        Value annotation = field.getAnnotation(Value.class);
        String annotationName = annotation.value().replaceAll("[{}$]", "");
        String propValue = props.getProperty(annotationName);
        field.set(object,propValue);
    }
}
