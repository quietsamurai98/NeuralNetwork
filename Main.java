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
        int[] layerCounts = {1,3,3,1};
        Network nn = new Network(layerCounts);
        double[] inputs = {Math.PI/2.0};
        System.out.println("Inputs: " + Arrays.toString(inputs));
        System.out.println("Output: " + nn.calc(inputs)[0]);
        
        nn = train(nn, 1000000, 0.05);
        
        inputs[0] = Math.PI/2.0;
        System.out.println("Inputs: " + Arrays.toString(inputs));
        System.out.println("Output: " + nn.calc(inputs)[0]);
    }
    
    public static Network train(Network network, int maxGenerations, double maxChange){
    	Network bestNetwork  = network.clone();
    	Network childNetwork = network.clone();
    	for(int i=0; i<maxGenerations; i++){
    		childNetwork.mutateNodes(maxChange);
    		System.out.println("Fitness: " + calcFitness(childNetwork));
    		if(calcFitness(childNetwork)<calcFitness(bestNetwork)){ //Fitness of 0 = perfect
    			bestNetwork = childNetwork.clone();
    		}
    	}
    	return bestNetwork;
    }
    
    public static double calcFitness(Network network){
    	double avgFitness = 0.0;
    	double[] testInputs = new double[1000];
    	for(int i = 1; i<1001; i++){
    		testInputs[i-1]=2.0*Math.PI*0.001*i;
    	}
    	for(int i=0; i<1000; i++){
	    	double[] inputs = {testInputs[i]}; //Random double from -100 to 100
	    	double target = Math.sin(inputs[0]);		   //Target output
	    	double actual = network.calc(inputs)[0];		   //Actual output
	    	double percentError = Math.abs(actual-target)/Math.abs(target);
	    	avgFitness += percentError;
    	}
    	avgFitness/=1000;
    	return avgFitness;
    }
}
