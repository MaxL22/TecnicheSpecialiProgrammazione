/*
 * Exercise: Proxy.
 *
 * Let's write a meta-object (through the Proxy class)
 * to be linked to the instances of the class below
 * that shows the object state before and after the invocation of each method.
 *
 * With MethodHandles this time!
 */

import java.util.Date;

public class ProxyTestingFields implements ITestingFields {
    private Double[] d;
    private Date dd;
    private int the_answer = 42;

    public ProxyTestingFields(int n, double val) {
        dd = new Date();
        d = new Double[n];
        for(int i=0; i<n; i++) d[i] = i*val;
    }

    public void setAnswer(int a) {
        the_answer = a;
    }
    public String message() {
        return "The answer is " + the_answer;
    }
}
