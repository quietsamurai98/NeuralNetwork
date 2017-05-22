/**
 * @(#)Main.java
 *
 *
 * @author 
 * @version 1.00 2017/5/18
 */
import java.util.Arrays;
import java.util.Scanner;
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
        int[] layerCounts = {1,4,3,2,1};
        Network nn = new Network(layerCounts);
        Evolver darwin = new Evolver(50, 0.05, 0.01, 1000);
        
        //nn = darwin.evolve(nn, 1000);
        
        Scanner kb = new Scanner(System.in);
        while(true){
        	System.out.println(nn);
        	System.out.println("Current fitness: " + darwin.calcFitness(nn));
        	System.out.println("Enter a number to manually test the neural network, or enter anything else to train for 10000 generations.");
        	String input = kb.nextLine();
        	while(isNumeric(input)){
        		double inputNum = Double.parseDouble(input);
        		System.out.println("Target: " + Evolver.function(inputNum));
        		double[] inputs = {inputNum};
        		System.out.println("Actual: " + nn.calc(inputs)[0]);
        		input = kb.nextLine();
        	}
			System.out.println("Training... please wait.");
			nn = darwin.evolve(nn, 10000);
        }
    }
    
    public static boolean isNumeric(String str){  
		try{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe){  
			return false;  
		}  
		return true;  
	}
}
