import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encrypt {
    public static void main(String[] args) throws IOException {
        String filePath = args[1].replace('.', File.separatorChar);
        Cipher cipher = new CaesarCipher((byte) Integer.parseInt(args[0]));

        File classFile = new File(filePath + ".class");
        byte[] buf = new byte[(int) classFile.length()];

        FileInputStream bytes = new FileInputStream(classFile);
        bytes.read(buf);
        bytes.close();

        FileOutputStream out = new FileOutputStream(filePath + ".nidki");
        out.write(cipher.encrypt(buf));
        out.close();
    }
}
