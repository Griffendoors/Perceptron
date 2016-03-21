package main;

import java.util.Random;

public class Feature {

	int[]row;
	int[]col;
	boolean[]sign;


	public Feature(Random random){
		row = new int[4];
		col = new int[4];
		sign = new boolean[4];

		randomCreate(random);

	}

	private void randomCreate(Random random){
		for(int i = 0; i<4; i ++){
			row[i] = random.nextInt(10);
			col[i] = random.nextInt(10);
			sign[i] = random.nextBoolean();
		}
	}

	public String getData() {
		String ret = "rows: "+row[0]+","+row[1]+","+row[2]+","+row[3];
		ret+= " ~ cols: "+col[0]+","+col[1]+","+col[2]+","+col[3];
		ret+= " ~ sgns: "+sign[0]+","+sign[1]+","+sign[2]+","+sign[3];
		return ret;

	}



}
