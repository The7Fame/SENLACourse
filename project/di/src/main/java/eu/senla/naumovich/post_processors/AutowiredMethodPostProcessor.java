package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.Context;
import eu.senla.naumovich.annotations.Autowired;

import java.lang.reflect.Method;

public class AutowiredMethodPostProcessor implements PostProcessor{
    @Override
    public void process(Object object, Context context) throws Exception {

        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Autowired.class)) {
                declaredMethod.setAccessible(true);
                Class<?> clazz = declaredMethod.getParameterTypes()[0];
                declaredMethod.invoke(object, context.getObject(clazz));
                declaredMethod.setAccessible(false);
            }
        }
    }
}
