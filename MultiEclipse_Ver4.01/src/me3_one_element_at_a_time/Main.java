package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

	private static final long serialVersionUID = 1L;
	static LokalKoordinatsystem aScreen = new LokalKoordinatsystem(GetScreenWorkingWidth() - 400,
			GetScreenWorkingHeight());
	static EclipseTime ec = new EclipseTime();
	static int analyse = 2;
	static boolean use_real_values = true;
	static UniverseData ud = new UniverseData(false);
	static double zoomIndex = 1;

	public static int GetScreenWorkingWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	public Main() {
	}

	public void drawGrid(Graphics2D g2d) {
		// g2d.drawLine(0, 0, aScreen.maxX, aScreen.maxY);
		// g2d.drawLine(aScreen.maxX, 0, 0, aScreen.maxY);

		g2d.drawLine(aScreen.relX(0), 0, aScreen.relX(0), aScreen.maxY);
		g2d.drawLine(0, aScreen.relY(0), aScreen.maxX, aScreen.relY(0));
		g2d.setColor(Color.yellow);
		// g2d.drawLine(0, 0, aScreen.relX(0), aScreen.relY(0));
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		this.setBackground(Color.black);
		//g2d.translate(aScreen.center_x,aScreen.center_y);

		g2d.scale(zoomIndex, zoomIndex);

		for (Planet planet : ud.allPlanetsToSun) {
			planet.scale = zoomIndex;
			g.setColor(planet.color);
			planet.calcPlanet(ec);
			planet.calcOrbit();
			planet.draw(g2d, ec, analyse, ud.allPlanetsToSun);

		}

		ec.draw(g2d, ud.allPlanetsToSun);
	}

	static public void initUniverse() {

		for (int nyPlanetCounter = 0; nyPlanetCounter < UniverseData.MAX_PLANETS; nyPlanetCounter++) {
			ud.allPlanetsToSun.get(nyPlanetCounter).centerX = aScreen.relX(0);
			ud.allPlanetsToSun.get(nyPlanetCounter).centerY = aScreen.relY(0);
		}
		ec.reset();
		// Alloker plads i array of fyld data fra standard arrays ud...

	}

	static public KeyListener myKL = new KeyListener() {
		// + = Speed +
		// - = Speed -
		// Højre museklik = Zoom +
		// Venstre museklik = Zoom -
		// Space = pause
		// r = rayToPlanets
		boolean pause = false;

		@Override

		public void keyTyped(KeyEvent e) {

			System.out.println("Code : " + e.getKeyCode());
			System.out.println("Char : " + e.getKeyChar());
			if (e.getKeyChar() == 'r') {
				for (Planet x : ud.allPlanetsToSun) {
					x.setDrawRayToPlanet(true);
					for (Planet moon : x.moons) {
						moon.setDrawRayToPlanet(true);
					}
				}
				;
			}
			if (e.getKeyChar() == 'R') {
				for (Planet x : ud.allPlanetsToSun) {
					x.setDrawRayToPlanet(false);
					for (Planet moon : x.moons) {
						moon.setDrawRayToPlanet(false);
					}
				}
				;
			}
			if (e.getKeyChar() == '*') {
				initUniverse();
			}
			if (e.getKeyChar() == '+') {
				speed(1.5);
			}
			if (e.getKeyChar() == '-') {
				speed(0.50);
			}
			if (e.getKeyChar() == 'M') {
				for (Planet x : ud.allPlanetsToSun) {
					x.setDrawMoons(false);
					for (Planet moon : x.moons) {
						moon.setDrawMoons(false);
					}
				}
			}
			if (e.getKeyChar() == 'm') {
				for (Planet x : ud.allPlanetsToSun) {
					x.setDrawMoons(true);
					for (Planet moon : x.moons) {
						moon.setDrawMoons(true);
					}
				}
			}
			if (e.getKeyChar() == 'p') {
				if (!pause) {
					ec.setUnitPrClick(0);
					pause = true;
				} else {
					ec.setUnitPrClickToStandard();
					pause = false;
				}
			}
			if (e.getKeyChar() == 'a') {
				analyse += 1;
				if (analyse > 2)
					analyse = 0;
			}

			if (e.getKeyChar() == 'd') {
				if (use_real_values == true)
					use_real_values = false;
				else
					use_real_values = true;
				initUniverse();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				shiftUpDown(100);
				break;
			case KeyEvent.VK_DOWN:
				shiftUpDown(-100);
				break;
			case KeyEvent.VK_LEFT:
				shiftLeftRight(100);
				break;
			case KeyEvent.VK_RIGHT:
				shiftLeftRight(-100);
				break;
			}

		}

		private void shiftLeftRight(int i) {
			for (Planet x : ud.allPlanetsToSun) {
				x.centerX += i;
			}

		}

		private void shiftUpDown(int i) {
			for (Planet x : ud.allPlanetsToSun) {
				x.centerY += i;
			}

		}
	};

	static public MouseAdapter ma = new MouseAdapter() {

		public void mouseClicked(MouseEvent evt) {
			if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				zoom(0.1);

			}
			if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
				zoom(-0.1);
			}
		}
	};

	public static void zoom(double d) {
		zoomIndex += d;
	}

	public static void speed(double d) {
		ec.setUnitPrClick(ec.getUnitPrClick() * d);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("MultiEclipse");
		Main universe = new Main();
		initUniverse();

		frame.add(universe);
		frame.addMouseListener(ma);
		frame.addKeyListener(myKL);
		frame.setSize(aScreen.maxX, aScreen.maxY);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			Thread.sleep(10);
			ec.click();
			universe.repaint();
		}

	}

}
