package org.example;

import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Context {
    private final static Context instance = new Context();
    private final Set<Class<?>> components1 = new HashSet<>();
    final Map<Class<?>, Object> beans = new HashMap<>();
    final List<Class<?>> components2 = new ArrayList<>();
    private final PostProcessor postProcessor = PostProcessor.getInstance();
    public static Context getInstance(){
        return instance;
    }
    public Context initContext(Class<?> startClazz) throws Exception {
        Reflections reflections = new Reflections(startClazz.getPackageName());
        Set<Class<?>> beans = reflections.getTypesAnnotatedWith(Component.class);
        components1.addAll(beans);
        components2.addAll(beans);
        initBeans(reflections);
        return instance;
    }

    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    private void initBeans(Reflections reflections) throws
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {
        while (!components2.isEmpty()) {
            Class<?> clazz = components2.removeFirst();
            initBean(clazz, reflections);
        }
    }
    private void initBean(Class<?> clazz, Reflections reflections) throws
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {

        Constructor<?> annotatedConstructor = postProcessor.annotatedConstructor(clazz);
        Method annotatedMethod = postProcessor.annotatedMethod(clazz);
        Field annotatedField = postProcessor.annotatedField(clazz);
        Field annotatedValue = postProcessor.annotatedValue(clazz);

        Object newInstance;

        if (annotatedConstructor != null) {
            newInstance = annotatedConstructor(annotatedConstructor, reflections);
            if (newInstance == null){
                components2.add(clazz);
            }
            else {
                beans.put(clazz, newInstance);
            }
        }

        if (annotatedMethod != null) {
            newInstance = annotatedMethod(annotatedMethod, reflections, clazz);
            if (newInstance == null) {
                components2.add(clazz);
            } else {
                beans.put(clazz, newInstance);

            }
        }

        if (annotatedField != null) {
            newInstance = annotatedField(annotatedField, reflections, clazz);
            if (newInstance == null) {
                components2.add(clazz);
            } else {
                beans.put(clazz, newInstance);
            }
        }

        if (annotatedValue != null) {
            newInstance = annotatedValue(annotatedValue, clazz);
            beans.put(clazz, newInstance);
        }
    }
    
    Object annotatedConstructor(Constructor<?> annotatedConstructor, Reflections reflections) throws InstantiationException, InvocationTargetException, IllegalAccessException {
        Class<?> clazzType = annotatedConstructor.getParameterTypes()[0];
        Object newInstance = null;
        if (checkBean(clazzType, reflections)) {
            newInstance = annotatedConstructor.newInstance(beans.get(getImplementation(clazzType, reflections)));
        }
        return newInstance;
    }

    Object annotatedMethod(Method annotatedMethod, Reflections reflections, Class<?> clazz) throws NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
        Class<?> clazzType = annotatedMethod.getParameterTypes()[0];
        Object newInstance = null;
        if (checkBean(clazzType, reflections)) {
            newInstance = clazz.getConstructor().newInstance();
            annotatedMethod.invoke(newInstance, beans.get(getImplementation(clazzType, reflections)));
        }
        return newInstance;
    }

    Object annotatedField(Field annotatedField, Reflections reflections, Class<?> clazz) throws InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clazzType = annotatedField.getType();
        Object newInstance = null;
        if (checkBean(clazzType, reflections)) {
            newInstance = clazz.getConstructor().newInstance();
            annotatedField.set(newInstance, beans.get(getImplementation(clazzType, reflections)));
        }
        return newInstance;
    }

    Object annotatedValue(Field annotatedValue, Class<?> clazz) throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object newInstance = clazz.getConstructor().newInstance();
        postProcessor.valueHandler(newInstance, annotatedValue);
        return newInstance;
    }
    private boolean checkBean(Class<?> clazz, Reflections reflections) {
        return beans.containsKey(clazz)|| reflections.getSubTypesOf(clazz).stream().anyMatch(beans::containsKey) ;
    }

    private Class<?> getImplementation(Class<?> clazzType, Reflections reflections) throws InstantiationException {
        List<Class<?>> implementations = new ArrayList<>();
        if (beans.containsKey(clazzType)) {
            return clazzType;
        } else {
            for (Class<?> clazz : reflections.getSubTypesOf(clazzType)) {
                if (components1.contains(clazz)) {
                    implementations.add(clazz);
                }
            }
            if(implementations.size() > 1){
                throw new InstantiationException("More than one implementation");
            }
            return implementations.get(0);
        }
    }
}
