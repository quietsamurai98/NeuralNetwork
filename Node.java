/**
 * @(#)Node.java
 *
 *
 * @Kenneth Palmer 
 * @version 1.00 2017/5/18
 *
 * Object describing an individual node in neural network
 */

import java.util.Arrays;
public class Node {
	double[] weights;
	boolean isInput;
    public Node() {
    	//No parameters = input node
    	isInput = true;
    }
    public Node(int numWeights){
    	//Creates node with numWeights input weights, plus 1 bias weight
    	isInput = false;
    	weights = new double[numWeights + 1];
    	double initialWeight = 1; //Math.sqrt(2/(numWeights+1));
    	for(int i=0; i<numWeights; i++){
    		weights[i] = (Math.random()*2.0-1.0)*initialWeight; //Initial weight is random number between -sqrt(2/(n+1)) and sqrt(2/(n+1))
    	}
    }
    public double calc(double[] inputs){ //Returns the neuron output given a list of inputs
    	return activationFunc(sumInputs(inputs));
    }
    
    private double sumInputs(double[] inputs){ //Returns sum of weighted inputs+bias
    	if(inputs.length+1!=weights.length){
    		System.out.println("Number of inputs did not match number of weights!");
    		System.out.println("    Number of inputs : "+inputs.length+" ("+ inputs.length+1 + " including bias neuron)");
    		System.out.println("    Number of weights: "+weights.length); 
    	}
    	double sum = 0.0;
    	for(int i=0; i<inputs.length; i++){
    		sum += inputs[i] * weights[i];
    	}
    	sum += 1 * weights[weights.length-1]; //add weighted bias
    	return sum;
    }
    private double activationFunc(double sum){ //Applys activation function to sum
    	return Math.tanh(sum); //Activation function is tanh
    }
}