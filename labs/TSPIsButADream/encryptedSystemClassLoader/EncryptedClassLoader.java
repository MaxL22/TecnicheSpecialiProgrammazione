import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EncryptedClassLoader extends ClassLoader {
    private final Cipher cipher;

    private static final byte KEY = 10;

    public EncryptedClassLoader(ClassLoader parent) {
        super(parent);
        this.cipher = new CaesarCipher(KEY);
    }

    public EncryptedClassLoader(Cipher cipher) {
        super();
        this.cipher = cipher;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("Main")) {
            try {
                File classFile = new File("Main.class");
                byte[] buf = new byte[(int) classFile.length()];

                FileInputStream bytes = new FileInputStream(classFile);
                bytes.read(buf);
                bytes.close();
                return defineClass(name, buf, 0, buf.length);
            } catch (IOException e) {
                throw new ClassNotFoundException();
            }
        } else {
            return super.loadClass(name);
        }
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = this.cipher.decrypt(getClassData(name));
        return defineClass(name, b, 0, b.length);
    }

    private byte[] getClassData(String name) throws ClassNotFoundException {
        String classPath = name.replace('.', '/');
        try {
            File classFile = new File(classPath + ".nidki");
            byte[] buf = new byte[(int) classFile.length()];

            FileInputStream bytes = new FileInputStream(classFile);
            bytes.read(buf);
            bytes.close();
            return buf;
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

}
