package me3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class EclipseTime {
	// default click = hour;
	private double ss_click;
	private double unitsPrClick = 0.01;
	private double standardUnitPrClick = unitsPrClick;
	int linje = 20;
	int indryk = 20;
	boolean drawLegend = false;

	public boolean isDrawLegend() {
		return drawLegend;
	}

	public void setDrawLegend(boolean drawLegend) {
		this.drawLegend = drawLegend;
	}

	DecimalFormat yearFormat = new DecimalFormat("#.####");

	public void newLine() {
		linje += 20;
	}

	public EclipseTime() {
		ss_click = 0;
	}

	public void click() {
		clickHour();
	}

	public void clickHour() {
		ss_click += unitsPrClick;
	}

	public double getSSClick() {
		return ss_click;
	}

	public void setUnitPrClick(double i) {
		this.unitsPrClick = i;
	}

	public double getUnitPrClick() {
		return this.unitsPrClick;
	}

	public void setUnitPrClickToStandard() {
		this.unitsPrClick = standardUnitPrClick;
	}

	public void reset() {
		ss_click = 0;
	}

	public void draw(Graphics2D g, ArrayList<Planet> arr) {
		if (drawLegend) {
			g.setColor(Color.white);
			Font c = new Font("Monospaced.plain", Font.PLAIN, 16);
			g.setFont(c);
			g.drawString("EclipseTime :", indryk, linje);
			// g.drawString("Clicks : " + ss_click, indryk, linje * 2);
			g.drawString("Key (*)   = Init Unitverse", indryk, linje * 3);
			g.drawString("Key (r)   = Rays To Moons on", indryk, linje * 4);
			g.drawString("Key (R)   = Rays To Moons off", indryk, linje * 5);
			g.drawString("Key (+)   = Speed +", indryk, linje * 6);
			g.drawString("Key (-)   = Speed", indryk, linje * 7);
			g.drawString("Key (P)   = Pause", indryk, linje * 8);
			g.drawString("ArrowKeys = move", indryk, linje * 9);
			g.drawString("Mouse click Left/Right = zoom in/out", indryk, linje * 10);
			g.drawString("Key (m/M) = Moons on/off", indryk, linje * 11);
			g.drawString("Key (a)   = Analyse mode (0,1,2) ", indryk, linje * 12);
			g.drawString("Key (d/D) = debug ", indryk, linje * 13);
			g.drawString("Time Clic = " + unitsPrClick, indryk, linje * 14);

			for (int i = 0; i < arr.size(); i++) {
				Planet x = arr.get(i);
				g.drawString(x.name + " �r  : " + yearFormat.format((x.planetYears)), indryk, linje * (i + 15));
			}
		}
	}
}
