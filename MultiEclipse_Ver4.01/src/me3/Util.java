package me3;

import java.util.Random;

public final class Util {

	public Util() {}
	
	static public final int randInt(int min, int max) {
		Random rand = new Random();
		if (max <= 0) max = 1;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}	
	
	static public final int calcRealCoord(long coord, double radius){
		return (int) (coord - (radius / 2));
	}
	
	

}
