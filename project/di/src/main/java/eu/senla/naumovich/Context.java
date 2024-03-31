package eu.senla.naumovich;

import eu.senla.naumovich.annotations.Component;
import eu.senla.naumovich.bean_factory.BeanFactory;
import org.reflections.Reflections;

import java.util.*;

public class Context {
    private final BeanFactory beanFactory = BeanFactory.getInstance().initBeanFactory(Context.class);
    private final static Context instance = new Context();
    Set<Class<?>> components = new HashSet<>();
    final Map<Class<?>, Object> class2object = new HashMap<>();
    Properties props;
    Reflections reflection;
    public static Context getInstance(){
        return instance;
    }

    public Context initContext(Class<?> startClazz) {
        reflection = new Reflections(startClazz.getPackageName());
        props = new Properties();
        components = reflection.getTypesAnnotatedWith(Component.class);
        initBeans();
        return instance;
    }

    public <T> T getClass2Object(Class<T> clazz) {
        return (T) class2object.get(clazz);
    }

    public Properties getProps() {
        return props;
    }
    private void initBeans() {
        for(Class<?> clazz : components){
            class2object.put(clazz, beanFactory.createObject(clazz, instance));
        }
    }

    private Class<?> getImplementation(Class<?> clazzType) throws InstantiationException {
        if(class2object.containsKey(clazzType)){
            return clazzType;
        }

        if(clazzType.isInterface()){
            List<Class<?>> implementations = new ArrayList<>(reflection.getSubTypesOf(clazzType));
            if(implementations.size() > 1)
            {
                throw new InstantiationException("More than one implementation");
            }
            return implementations.getFirst();
        }
        return null;
    }

    public Object getObject(Class<?> clazzType) throws InstantiationException {
        Class<?> clazz = getImplementation(clazzType);
        if(clazz !=null){
            class2object.get(clazz);
        }
        return beanFactory.createObject(clazz, this);
    }
}
