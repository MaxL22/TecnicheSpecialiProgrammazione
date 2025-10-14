import java.lang.annotation.*;

// The annotation is retained at runtime, and is appliable to types (classes, kinda), methods and fields
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Repeatable(ManyAnnotations.class)
public @interface MyAnnotation {
    String value(); // It contains a single string
}
