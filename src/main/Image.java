package main;

import java.util.ArrayList;
import java.util.List;

public class Image {

	private boolean[][] pixels;

	private String comment;
	private int width, height;
	private boolean isX;

	private List<Feature>features;

	private List<Integer> values;

	public Image(String comment, int width, int height, String[] data, List<Feature>features){
		this.comment = comment;
		this.width = width;
		this.height = height;
		this.features = features;
		loadPixels(data);
		isX = checkX();

		calculateValues();
	}

	public void loadPixels(String[] data){
		this.pixels = new boolean[width][height];
	    int count=0;
	    for(int i=0;i<width;i++){
	    	for(int j=0;j<height;j++){
	    		if(count==data.length) break;
	    		pixels[i][j]=Boolean.parseBoolean(data[count]);
	    		count++;
	    	}
	    }
	}

	private void calculateValues(){
		values = new ArrayList<Integer>();
		for(int j = 0; j < features.size(); j++){
			values.add(getValue(features.get(j)));
		}
	}


	private int getValue(Feature feature){
		int sum=0;
		for(int i=0; i < 4; i++){
			if (pixels[feature.row[i]] [feature.col[i]]==feature.sign[i]) sum++;
		}
		return (sum>=3)?1:0;
	}




	private boolean checkX(){
		return comment.equals("#Yes");

	}

	public String getComment(){
		return comment;
	}

	public Feature getFeature(int index){
		return features.get(index);
	}

	public int getValue(int index){
		return values.get(index);
	}


	public boolean getIsX(){
		return isX;
	}

	public boolean[][] getPixels() {
		return pixels;
	}

	public List<Feature> getFeatures(){
		return features;
	}

}
