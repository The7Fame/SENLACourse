package eu.senla.naumovich.bean_factory;

import eu.senla.naumovich.Context;
import eu.senla.naumovich.annotations.Autowired;
import eu.senla.naumovich.post_processors.PostProcessor;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
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

    public Object createObject(Class<?> clazz, Context context) throws Exception {
        final Object object = createInstance(clazz, context);
        for(PostProcessor processor : processors){
            try {
                processor.process(object, context);
            } catch (NullPointerException | NoSuchMethodException e){
            } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Can not create instance of class: " + clazz.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return object;
    }

    public <T> T createInstance(Class<?> clazz, Context context) throws Exception {
        Optional<Constructor<?>> autowiredConstructor = Arrays.stream(clazz.getDeclaredConstructors())
                .filter(constructor -> constructor.isAnnotationPresent(Autowired.class))
                .findFirst();

        if (autowiredConstructor.isPresent()) {
            Constructor<?> constructor = autowiredConstructor.get();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            List<Object> parameters = Arrays.stream(parameterTypes)
                    .map(clazzType -> {
                        try {
                            return context.getObject(clazzType);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());

            return (T) constructor.newInstance(parameters.toArray());
        } else {
            Constructor<?> defaultConstructor = clazz.getConstructor();
            return (T) defaultConstructor.newInstance();
        }
    }
}
