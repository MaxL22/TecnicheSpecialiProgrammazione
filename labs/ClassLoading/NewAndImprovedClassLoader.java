import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NewAndImprovedClassLoader extends ClassLoader {
    private int counter;
    private static final String[] whitelist = {
        "java.lang.Object",
        "java.lang.Throwable",
        "java.lang.String",
    };

    protected static boolean checkList(String name) {
        for (String string: whitelist) {
            if (string.equals(name))
                return true;
        }
        return false;
    }

    public NewAndImprovedClassLoader (ClassLoader parent) {
        super(parent);
        this.counter = 0;
    }

    @Override
    public Class<?> loadClass (String name) throws ClassNotFoundException {
        counter++;
        System.out.println("[LOADER] Loading class " + counter + name);
        String n = ClassLoading.class.getName();
        if (name.equals(n)) {
            try {
                File f = new File(n + ".class");
                byte buf[] = new byte[(int) f.length()];
                FileInputStream bytes = new FileInputStream(f);
                bytes.read(buf);
                bytes.close();
                return defineClass(n, buf, 0, buf.length);
            } catch (IOException e) {
                throw new ClassNotFoundException();
            }
        }

        if ((name.startsWith("java.lang") || name.startsWith("java.util")) && !checkList(name)) {
            System.out.println("Fuck you " + name);
            throw new RuntimeException();
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

}
