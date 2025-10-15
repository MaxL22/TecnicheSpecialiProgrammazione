import java.util.stream.*;
import java.util.*;
import java.lang.reflect.*;

@SuppressWarnings("unchecked")
public class DeepCopy {
    private static final List<Class<?>> primitiveClasses =
        List.of( Byte.class
                 , Boolean.class
                 , Character.class
                 , Double.class
                 , Float.class
                 , Integer.class
                 , Long.class
                 , Short.class
                 , String.class );

    private static <T> List<Field> getAllInstanceFields(Class<T> cls) {
        return Stream.iterate(cls, (Class<?> c) -> c.getSuperclass())
               .takeWhile(Objects::nonNull)
               .flatMap((Class<?> c) -> Arrays.stream(c.getDeclaredFields()))
               .filter(f -> !Modifier.isStatic(f.getModifiers()))
               .collect(Collectors.toList());
    }

    private static <T> void copyField(T src, T dest, Field f) {
        try {
            f.setAccessible(true);
            f.set(dest, deepCopy(f.get(src))); // alternativamente f.set(dest, f.getType().cast(deepCopy(f.get(src)))) darà ClassCastException
            f.setAccessible(false);
        } catch (Throwable e) {
            System.out.println("[ERROR] " + e);
            return;
        }
    }

    private static <T> T[] copyArray(Class<?> type, T[] arr) {
        T[] newArr = (T[]) Array.newInstance(type, arr.length);
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = deepCopy(arr[i]);
        }
        return (T[]) newArr;
    }

    public static <T> T deepCopy(T src) {
        if (primitiveClasses.contains(src.getClass()))
            return src;
        else if (src.getClass().isArray())
            return (T) copyArray(src.getClass().getComponentType(), (T[]) src);
        else try {
                List<Field> fields = getAllInstanceFields(src.getClass());
                T newInstance = (T) src.getClass().getConstructor().newInstance();
                fields.forEach(f -> copyField(src, newInstance, f));
                return newInstance;
            } catch (Throwable e) {
                System.out.println(e);
                return null;
            }
    }
}
