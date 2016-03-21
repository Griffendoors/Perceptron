package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Perceptron {


	private List<Image>images;

	private double[] weights;

	private int numFeatures;

	private final double threshold = 0.0;

	private double  learningRate = 0.01;



	public Perceptron(List<Image> images){
		this.images = images;
		numFeatures = images.get(0).getFeatures().size();
		weights = new double[numFeatures + 1]; // + 1 for Dummy
		randomizeWeights(numFeatures);
		train();
	}



	private void randomizeWeights(int length){
		Random random = new Random();
		for(int i = 0; i < length; i++){
			weights[i] = random.nextDouble();
		}
		weights[length] = 1;
	}

	private void train(){
		int iterations = 1;
		int maxIterations = 100;
		List<Boolean> completleyCorrect = new ArrayList<Boolean>();

		int totalClassified = 0;
		int totalCorrect = 0;

		System.out.println("\nAccuracy iterations:");
		do{

			for(int imageIndex = 0; imageIndex<images.size(); imageIndex++){
				double sum = 0.0;

				for(int featureIndex = 0; featureIndex< numFeatures; featureIndex++){
					double wi = weights[featureIndex];
					double fi = images.get(imageIndex).getValue(featureIndex);
					sum += (wi*fi);
				}
				if(sum>threshold && images.get(imageIndex).getIsX()){//thinks X, is X (CORRECT)
					completleyCorrect.add(true);
					totalCorrect++;

				}
				if(sum<=threshold && !images.get(imageIndex).getIsX()){//thinks 0, is 0 (CORRECT)
					completleyCorrect.add(true);
					totalCorrect++;
				}
				if(sum>threshold && !images.get(imageIndex).getIsX()){//thinks X, isnt X (INCORRECT) //sub
					for(int weightIndex = 0; weightIndex< numFeatures; weightIndex++){
						weights[weightIndex]-= learningRate * images.get(imageIndex).getValue(weightIndex);
					}
					completleyCorrect.add(false);
				}
				if(sum<=threshold && images.get(imageIndex).getIsX()){//thinks O, is X (INCORRECT) //add
					for(int weightIndex = 0; weightIndex< numFeatures; weightIndex++){
						weights[weightIndex]+= learningRate *images.get(imageIndex).getValue(weightIndex);
					}
					completleyCorrect.add(false);
				}
				totalClassified++;
			}

			double accuracy = ((1.0*totalCorrect)/(1.0*totalClassified))*100;
			System.out.println("accuracy: "+accuracy+"%"+" after "+iterations+" iterations");
			iterations++;


		}while(completleyCorrect.contains(false) && iterations < maxIterations);



		//Report the final weight vector equation
		System.out.println("\n================");
		System.out.println("\nFinal Output weight vector:");

		for(int i = 0; i < weights.length; i ++){
			System.out.println("weights["+i+"] = " + weights[i]);
		}


		double accuracy = ((1.0*totalCorrect)/(1.0*totalClassified))*100;
		int incorrect = completleyCorrect.size()-totalCorrect;


		// report on the number of correct and incorrectly classified instances.
		System.out.println("\n================");
		System.out.println("\nClassification statistics:");
		System.out.println("Final Iterations: "+iterations);
		System.out.println("totalCorrect: "+totalCorrect);

		System.out.println("totalIncorrect: "+incorrect+"");
		System.out.println("Final Accuracy: "+accuracy+"%");

	}


}
