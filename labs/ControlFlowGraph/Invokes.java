import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Invocations.class)
public @interface Invokes {
    String methodName();
    String callerMethod();
}
