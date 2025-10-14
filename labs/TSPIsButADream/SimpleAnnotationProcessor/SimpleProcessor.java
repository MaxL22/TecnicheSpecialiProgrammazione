import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.Set;

// It has to extend AbstractProcessor
// The annotation is needed to know the array of annotations (interfaces) supported
@SupportedAnnotationTypes({"MyAnnotation", "ManyAnnotations"})
@SupportedSourceVersion(SourceVersion.RELEASE_25)
public class SimpleProcessor extends AbstractProcessor {
    // process() is the method used to apply the behavior to each annotation type
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation: annotations) {
            // get all elements in the env annotated with the specific annotation
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element e : elements) {
                System.out.println("[PROCESSOR] Found annotation: " + annotation.getSimpleName() + " on element: " + e.getSimpleName());
            }
        }
        // To not claim the annotations exclusively
        return false;
    }
}
