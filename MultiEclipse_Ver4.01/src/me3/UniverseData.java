package me3;

import java.awt.Color;
import java.util.ArrayList;

public final class UniverseData {

	static final int MAX_PLANETS = 10;
	static final int SUN = 0;
	static final int MERKUR = 1;
	static final int VENUS = 2;
	static final int JORDEN = 3;
	static final int MARS = 4;
	static final int JUPITOR = 5;
	static final int SATURN = 6;
	static final int URANUS = 7;
	static final int NEPTUN = 8;
	static final int PLUTO = 9;

	ArrayList<Planet> allPlanetsToSun = new ArrayList<Planet>();

	// path to images
	static final String IMAGEPATH = "";

	public UniverseData(boolean debug) {
		for (int nyPlanetCounter = 0; nyPlanetCounter < UniverseData.MAX_PLANETS; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allPlanetsToSun.add(nyPlanet);
			allPlanetsToSun.get(nyPlanetCounter).name = UniverseData.planetNames[nyPlanetCounter];
			allPlanetsToSun.get(nyPlanetCounter).planetLevel = 1; 
			allPlanetsToSun.get(nyPlanetCounter).color = UniverseData.planetColor[nyPlanetCounter];
			allPlanetsToSun.get(nyPlanetCounter).setPlanetIndex(nyPlanetCounter);
			allPlanetsToSun.get(nyPlanetCounter).planetensHastighed = planetSpeed[nyPlanetCounter];
			if (!debug)
				allPlanetsToSun.get(nyPlanetCounter)
						.setRadiusPaaKredsloeb(meanDistanceFromSunAU[nyPlanetCounter] * 100);
			else
				allPlanetsToSun.get(nyPlanetCounter)
						.setRadiusPaaKredsloeb(planetsRadiusFromSunArrInAU_DEBUG[nyPlanetCounter]);
			if (!debug)
				allPlanetsToSun.get(nyPlanetCounter).setRadius(planetSize[nyPlanetCounter] * 10);
			else
				allPlanetsToSun.get(nyPlanetCounter).setRadius(planetSize_DEBUG[nyPlanetCounter]);

			// allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstand
			// = 0;
			// allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart
			// = Util.randInt(0,
			// (int)
			// allPlanetsToSun.get(nyPlanetCounter).getOmkredsPaaKredsloebet());
			// start alle planet i o afstand
			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = 0;
			allPlanetsToSun.get(nyPlanetCounter).setDrawRayToPlanet(false);
			allPlanetsToSun.get(nyPlanetCounter).setImage();

		}

		
		allPlanetsToSun.get(UniverseData.JORDEN).moonGenerator(1);
		allPlanetsToSun.get(UniverseData.MARS).moonGenerator(2);
		allPlanetsToSun.get(UniverseData.JUPITOR).moonGenerator(67);
		allPlanetsToSun.get(UniverseData.SATURN).moonGenerator(53);
		allPlanetsToSun.get(UniverseData.URANUS).moonGenerator(27);
		allPlanetsToSun.get(UniverseData.NEPTUN).moonGenerator(13);
		allPlanetsToSun.get(UniverseData.PLUTO).moonGenerator(5);
       
	}

	static final String[] planetNames = { "Sun", "Merkur", "Venus", "Jorden", "Mars", "Jupiter", "Saturn", "Uranus",
			"Neptun", "Pluto" };

	static final Color[] planetColor = { Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.GREEN, Color.RED, Color.CYAN,
			Color.YELLOW, Color.LIGHT_GRAY, Color.WHITE, Color.BLUE };
	// ******************************* REAL DATA
	// ************************************************
	// Meanc distance from the sun earth = 1 AU
	static final double[] meanDistanceFromSunAU = { 0.001, 0.39, 0.72, 1, 1.52, 5.20, 9.54, 19.18, 30.06, 39.44 };

	// Speed of the planets in km/s
	static final double[] planetSpeed = { 0, 47.89, 35.03, 29.79, 24.13, 13.06, 9.64, 6.81, 5.43, 4.74 };

	// Planets size in radius
	static final double[] planetSize = { 1, 0.382, 0.949, 1, 0.532, 11.209, 9.44, 4.007, 3.883, 0.180 };
	// ***********************************************************************************************
	// Debug Data
	static int[] planetsRadiusFromSunArrInAU_DEBUG = { 10, 50, 100, 150, 200, 250, 300, 400, 500, 600 };
	static int[] planetSpeed_DEBUG = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
	static int[] planetSize_DEBUG = { 10, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	static final public void debug() {

	}
}
