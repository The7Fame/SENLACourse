package eu.senla.naumovich;

import eu.senla.naumovich.annotations.Component;
import eu.senla.naumovich.bean_factory.BeanFactory;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.*;

public class Context {
    private final BeanFactory beanFactory = BeanFactory.getInstance().initBeanFactory(Context.class);
    private final static Context instance = new Context();
    Set<Class<?>> components = new HashSet<>();
    final Map<Class<?>, Object> class2object = new HashMap<>();
    final Map<Class<?>, List<Class<?>>> interface2implementations = new HashMap<>();
    final Properties props = new Properties();
    Reflections reflection;
    public static Context getInstance(){
        return instance;
    }

    public Context initContext(Class<?> startClazz) throws Exception {
        reflection = new Reflections(startClazz.getPackageName());
        components = reflection.getTypesAnnotatedWith(Component.class);
        initInterface2Implementations(components);
        initProps(props);
        initBeans();
        return instance;
    }
    public <T> T getBean(Class<T> clazz) {
        return (T) class2object.get(clazz);
    }


    public Properties getProps() {
        return props;
    }

    public Object getObject(Class<?> clazzType) throws Exception {
        Class<?> clazz = getImplementation(clazzType);
        if(clazz !=null){
            class2object.get(clazz);
        }
        return beanFactory.createObject(clazz, this);
    }

    private void initBeans() throws Exception {
        for(Class<?> clazz : components){
            class2object.put(clazz, beanFactory.createObject(clazz, instance));
        }
    }

    private void initProps(Properties props) throws IOException {
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
    }
    private void initInterface2Implementations(Set<Class<?>> components){
        for (Class<?> impl : components) {
            for (Class<?> intfc : impl.getInterfaces()) {
                List<Class<?>> implementations = interface2implementations.get(intfc);
                if (implementations == null) {
                    implementations = new ArrayList<>();
                    interface2implementations.put(intfc, implementations);
                }
                implementations.add(impl);
            }
        }
    }

    private Class<?> getImplementation(Class<?> clazzType) throws InstantiationException {
        if(class2object.containsKey(clazzType)){
            return clazzType;
        }

        if(clazzType.isInterface()){
            List<Class<?>> implementations = interface2implementations.get(clazzType);
            if(implementations.size() > 1)
            {
                throw new InstantiationException("More than one implementation");
            }
            return implementations.getFirst();
        }
        return null;
    }
}

