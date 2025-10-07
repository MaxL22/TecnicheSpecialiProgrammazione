/*
 * Exercise: Recognizing Elements.
 *
 * Let's consider a collection of Java classes and two string arrays.
 * The first array contains the names of the Java classes;
 * the second array contains the names of all the fields and all the methods declared in the classes
 * whose names are stored in the first array.
 * There is no relationship between the two arrays,
 * i.e., we don't know whether and in which class are declared
 * the names stored in the second array, nor if they represent a field or a method.
 * Let's write a program that
 *  i) checks that the names in the second array are all declared in one of the classes in the first array,
 *  ii) outputs, for each name where it is declared,
 *  iii) outputs whether it is a method or a field and
 *  iv) the full signature included types, visibility and scope.
 */
public class RecognizingElements {
    public static void main () {
        // Possible input
        String[] classNames = {
            "String",
            "ArrayList",
            "HashMap"
        };
        String[] memberNames = {
            // String methods
            "length",
            "charAt",
            "substring",
            "isEmpty",
            "toLowerCase",
            // String fields
            "CASE_INSENSITIVE_ORDER",
            // ArrayList methods
            "add",
            "remove",
            "get",
            "size",
            "clear",
            "ensureCapacity",
            // ArrayList fields
            "modCount",   // protected field in AbstractList
            "elementData", // protected transient Object[] in ArrayList
            // HashMap methods
            "put",
            "get",
            "remove",
            "size",
            "clear",
            "containsKey",
            // HashMap fields
            "DEFAULT_INITIAL_CAPACITY",
            "loadFactor",
            // Non-existent member for testing
            "nonexistentMember"
        };
    }
}
