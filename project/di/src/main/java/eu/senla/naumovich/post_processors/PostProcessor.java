package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.Context;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface PostProcessor {
    public Object process(Class<?> clazz, Context context) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
