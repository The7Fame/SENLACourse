package eu.senla.naumovich.post_processors;

import eu.senla.naumovich.Context;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface PostProcessor {
   void process(Object o, Context context) throws Exception;
}
