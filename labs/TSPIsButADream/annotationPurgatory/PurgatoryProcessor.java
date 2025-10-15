import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.JavaFileObject;
import java.util.Set;
import java.util.Arrays;
import java.io.Writer;
import java.io.IOException;

/*
 * No main, it's just a processor,
 * the objective was to make a looping processor
 * which creates infinite new classes
 * (here limited to 300 for sanity's sake)
 *
 * Compiled with
 * > javac PurgatoryProcessor.java && javac -processor PurgatoryProcessor *.java
 *
 * Idea and execution by TititinoThePrologGuy
 */

@SupportedAnnotationTypes({"AlmostThere"})
@SupportedSourceVersion(SourceVersion.RELEASE_20)
public class PurgatoryProcessor extends AbstractProcessor {


    private static Integer counter = 0;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment round) {
        // Comment this if you want it to go infinite
        if (PurgatoryProcessor.counter >= 300)
            return true;

        PurgatoryProcessor.counter += 1;
        System.out.println(PurgatoryProcessor.counter);

        Element cls = round.getElementsAnnotatedWith(AlmostThere.class).toArray(new Element[0])[0];

        StringBuilder newClassSource = new StringBuilder();
        newClassSource.append("@AlmostThere\npublic class Figlio")
                      .append(PurgatoryProcessor.counter)
                      .append(" {}");

        String genClassName = "Figlio" + PurgatoryProcessor.counter.toString();
        try {
            JavaFileObject file = processingEnv.getFiler()
                                  .createSourceFile(genClassName, cls);
            try (Writer out = file.openWriter()) {
                out.write(newClassSource.toString());
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }
}
