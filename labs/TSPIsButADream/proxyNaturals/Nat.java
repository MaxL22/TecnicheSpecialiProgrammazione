public interface Nat {
    public static enum Z implements Nat {
        SINGLETON;

        public Integer number() {
            return 0;
        }

        public Nat add(Nat other) {
            // Z + x = x
            return other;
        }
    };

    Integer number();
    Nat add(Nat other);
}
