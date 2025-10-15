/*
 * The idea is to make peano's numbers, along with addition,
 * all by instantiating a proxy for each natural number
 *
 * I don't really know what else to say, it's dumb
 * This CLEARLY has the mark of TitinoThePrologGuy
 */

public class Main {
    public static void main(String[] args) {
        Nat tre = Nats.succ(Nats.succ(Nats.succ(Nats.zero())));
        Nat quattro = Nats.succ(tre);

        System.out.println(tre.number().toString() + " + " + quattro.number().toString() + " = " + tre.add(quattro).number().toString());
        return;
    }
}
