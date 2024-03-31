package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.Context;
import eu.senla.naumovich.annotations.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AutowiredFieldPostProcessor implements PostProcessor{
    @Override
    public Object process(Class<?> clazz, Context context) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Field field = autowiredField(clazz);
        Class<?> clazzType = field.getType();
        Object newInstance = clazz.getConstructor().newInstance();
        field.set(newInstance, context.getObject(clazzType));
        return newInstance;
    }

    public Field autowiredField(Class<?> clazz) {
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        return null;
    }
}
