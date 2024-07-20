import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LogExecutionTimeProcessor {

    public static void process(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                LogExecutionTime annotation = method.getAnnotation(LogExecutionTime.class);
                String description = annotation.value().isEmpty() ? method.getName() : annotation.value();

                long startTime = System.nanoTime();
                try {
                    method.invoke(object); 
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                long endTime = System.nanoTime();

                long durationInMilliseconds = (endTime - startTime) / 1_000_000; 

                System.out.println("Method '" + method.getName() + "' executed in " + durationInMilliseconds + " ms. Description: " + description);
            }
        }
    }
}
