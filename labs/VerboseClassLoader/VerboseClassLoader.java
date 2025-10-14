import java.io.*;

public class VerboseClassLoader extends ClassLoader {

    public VerboseClassLoader(ClassLoader parent) {
        super(parent);
    }

    // If it gets to loadCLass() here it prints the name of the class
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class '" + name + "'");
        // hijacks loading the classes that don't start with `java`
        if (!name.startsWith("java"))
            return getClass(name);
        return super.loadClass(name);
    }

    /*
     * Commenting these two functions and removing the `if`
     * leads to less classes printed
     * This is because it starts loading classes,
     * then delegates to the father, which loades main
     * and with it all other classes
     */
    private Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        try {
            byte[] b = loadClassFileData(file);
            Class<?> c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
