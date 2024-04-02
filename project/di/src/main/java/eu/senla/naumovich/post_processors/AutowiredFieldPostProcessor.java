package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.Context;
import eu.senla.naumovich.annotations.Autowired;

import java.lang.reflect.Field;

public class AutowiredFieldPostProcessor implements PostProcessor{
    @Override
    public void process(Object object, Context context) throws Exception {

        for (Field declaredField : object.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                declaredField.setAccessible(true);
                Class<?> clazzType = declaredField.getType();
                declaredField.set(object, context.getObject(clazzType));
                declaredField.setAccessible(false);
            }
        }
    }
}
