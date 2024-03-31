package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.Context;
import eu.senla.naumovich.annotations.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AutowiredMethodPostProcessor implements PostProcessor{
    @Override
    public Object process(Class<?> clazz, Context context) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method method = autowiredMethod(clazz);
        Class<?> clazzType = method.getParameterTypes()[0];
        Object newInstance = clazz.getConstructor().newInstance();
        method.invoke(newInstance, context.getObject(clazzType));
        return newInstance;
    }

    public Method autowiredMethod(Class<?> clazz) {
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Autowired.class)) {
                declaredMethod.setAccessible(true);
                return declaredMethod;
            }
        }
        return null;
    }
}
