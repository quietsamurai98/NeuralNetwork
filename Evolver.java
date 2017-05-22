/**
 * @(#)Evolver.java
 *
 *
 * @author 
 * @version 1.00 2017/5/19
 */


public class Evolver {
	int	   populationSize;
	double maxWeightChange;
	double funcMutateProb;
	double[] testInputs;
	
	
    public Evolver(int populationSize, double maxWeightChange, double funcMutateProb, int numTests){
    	this.populationSize = populationSize;
    	this.maxWeightChange= maxWeightChange;
    	this.funcMutateProb = funcMutateProb;
    	testInputs = new double[numTests];
    	for(int i = 0; i<numTests; i++){
    		testInputs[i]=((double)i)/((double) numTests)*10.0; //uniform values from 0 to 100
    	}
    }
    
    public Network evolve(Network network, int maxGenerations){
    	Network inputNetwork = network.clone();
    	Network bestNetwork = inputNetwork.clone();
    	for(int i=0; i<maxGenerations; i++){
    		Network[] population = createChildren(bestNetwork);
    		bestNetwork = findMostFit(population).clone();
    		if(i%(maxGenerations/23)==0){
    			System.out.print("#");
    		}
    	}
    	System.out.println();
    	return bestNetwork;
    }
    
    public Network[] createChildren(Network parent){
    	Network[] output = new Network[populationSize];
    	
    	output[0] = parent.clone(); //Best network is always preserved as is
    	for(int i=1; i<populationSize; i++){
    		output[i] = parent.clone();
    		output[i].mutateNodes(maxWeightChange, funcMutateProb);
    	}
    	return output;
    }
    
    public Network findMostFit(Network population[]){
    	int index = 0;
    	double maxFitness = calcFitness(population[0]);
    	for(int i=1; i<populationSize; i++){
    		double fitness = calcFitness(population[i]);
    		if(fitness<maxFitness){
    			maxFitness = fitness;
    			index = i;
    		}
    	}
//		System.out.print("Fitness = ");
//    	System.out.printf("%10.5e",maxFitness);
//    	if(index!=0){
//			System.out.print(" (Improved!)");
//    	}
//    	System.out.println();
    	return population[index];
    }
    
    public double calcFitness(Network network){
    	double avgFitness = 0.0;
    	for(int i=0; i<testInputs.length; i++){
	    	double[] inputs = {testInputs[i]};
	    	double target = function(inputs[0]);	   //Target output
	    	double actual = network.calc(inputs)[0];   //Actual output
	    	avgFitness += Math.abs(target-actual)/target;
    	}
    	avgFitness/=testInputs.length;
    	return avgFitness;
    }
    
    public static double function(double x){
    	return (x+0.5)/(x*x*x+1);
    }
}