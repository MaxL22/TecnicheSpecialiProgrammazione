import java.lang.annotation.*;

// This is an array of annotations, used to "enable" the Repeatable tag on the specified annotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface ManyAnnotations {
    MyAnnotation[] value();
}
