package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.annotations.Autowired;
import eu.senla.naumovich.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AutowiredConstructorPostProcessor implements PostProcessor {

    @Override
    public Object process(Class<?> clazz, Context context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<?> constructor = autowiredConstructor(clazz);
        Class<?> clazzType = constructor.getParameterTypes()[0];
        Object newInstance = constructor.newInstance(context.getObject(clazzType));
        return newInstance;
    }

    public Constructor<?> autowiredConstructor(Class<?> clazz) {
        for (Constructor<?> declaredConstructor : clazz.getDeclaredConstructors()) {
            if (declaredConstructor.isAnnotationPresent(Autowired.class)) {
                declaredConstructor.setAccessible(true);
                return declaredConstructor;
            }
        }
        return null;
    }
}
