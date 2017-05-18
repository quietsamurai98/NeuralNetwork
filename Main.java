/**
 * @(#)Main.java
 *
 *
 * @author 
 * @version 1.00 2017/5/18
 */
import java.util.Arrays;
public class Main {
        
    /**
     * Creates a new instance of <code>Main</code>.
     */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] layerCounts = {2,3,3,1};
        Network nn = new Network(layerCounts);
        double[] inputs = {1.0, 0.0};
        System.out.println("Inputs: " + Arrays.toString(inputs));
        System.out.println("Output: " + nn.calc(inputs)[0]);
    }
}
