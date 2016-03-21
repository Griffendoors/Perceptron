package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {


	private List<Image> images;
	private List<Feature> features;

	private int numRandFeats = 50;

	private Random random;

	private int randomSeed = 30;


	public Main(String fileLoc){
		random = new Random(randomSeed);
		images = new ArrayList<Image>();
		features = new ArrayList<Feature>();
		createRandomFeatures();
		readImageData(fileLoc);

		printRandomFeatures();

		new Perceptron(images);

	}


	private void printRandomFeatures(){
		System.out.println("Random Features Generated:");
		for(int i = 0; i < features.size(); i ++){
			System.out.println(features.get(i).getData());
		}
		System.out.println("\n================");
	}

	private void readImageData(String fileLoc){
		File file = new File(fileLoc);
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNext()){
				String p1 = scan.next();
				String comment = scan.next();
				int width = scan.nextInt();
				int height = scan.nextInt();
				String rawData = scan.next();
				rawData+=scan.next();
				String[]data = rawData.split("(?!^)");
				images.add(new Image(comment,width,height,data,features));
			}

		} catch (FileNotFoundException e) { e.printStackTrace(); }

	}

	private void createRandomFeatures(){
		for(int i = 0; i < numRandFeats; i ++){
			features.add(new Feature(random));
		}

	}

	public static void main(String args[]){
		new Main(args[0]);

	}
}
