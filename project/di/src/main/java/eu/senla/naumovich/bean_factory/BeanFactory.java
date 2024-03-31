package eu.senla.naumovich.bean_factory;

import eu.senla.naumovich.Context;
import eu.senla.naumovich.post_processors.PostProcessor;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanFactory {
    private static final BeanFactory instance = new BeanFactory();
    Set<PostProcessor> processors = new HashSet<>();
    public static BeanFactory getInstance(){
        return instance;
    }

    public BeanFactory initBeanFactory(Class<?> clazz){
        Reflections reflection = new Reflections(clazz.getPackageName());
        processors = reflection.getSubTypesOf(PostProcessor.class)
                .stream()
                .map(processor -> {
                    try {
                        return processor.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException("Can not create instance of processor: " + processor.getName());
                    }
                })
                .collect(Collectors.toSet());
        return instance;
    }

    public Object createObject(Class<?> clazz, Context context){
        for(PostProcessor processor : processors){
            try {
                return processor.process(clazz, context);
            } catch (NullPointerException | NoSuchMethodException e){
            } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Can not create instance of class: " + clazz.getName());
            }
        }
        return null;
    }
}
