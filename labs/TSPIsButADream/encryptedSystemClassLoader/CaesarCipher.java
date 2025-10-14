public class CaesarCipher implements Cipher {

    private final byte key;

    public CaesarCipher(byte key) {
        this.key = key;
    }

    public byte[] encrypt(byte[] data) {
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++)
            out[i] = (byte) (data[i] + this.key);
        return out;
    }

    public byte[] decrypt(byte[] data) {
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++)
            out[i] = (byte) (data[i] - this.key);
        return out;
    }
}
