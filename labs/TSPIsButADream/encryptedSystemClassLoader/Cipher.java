public interface Cipher {
    byte[] encrypt(byte[] data);
    byte[] decrypt(byte[] data);
}
