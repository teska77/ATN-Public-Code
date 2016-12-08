package userinterface;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;
import variables.Globals;

/*
 * TODO:
 * 	- Give this class private variables such as what piece of UI code is 
 * 		running at the moment, as well as a boolean to say whether it is 
 * 		finished / still doing work
 */

/**
 * A collection of methods used to change aspects of the screen (e.g. Print text
 * on center of display, show loading screen) <br>
 * <b>THIS MUST BE USED IN ALL POSSIBLE SCENARIOS INSTEAD OF THE LCD CLASS</b>,
 * as to not interfere with the other elements on the display
 * 
 * @author William Campany
 */

public class UI {
	/**
	 * Used to access the UserInterface, Only one of these should be defined in
	 * all of the code, otherwise the UI's will fight with one another <br>
	 * No parameters necessary, you don't need to specify which LCD to use since
	 * there is <b>only one</b> LCD on the NXT. <br>
	 * <i>TODO: Make a globally accessible UserInterface object</i>
	 */
	// public UserInterface() {
	// Nothing needed here, this just allows access to the UI class.
	// }

	////////////////////////////////////////////////////////////////////////////
	// The variables...
	////////////////////////////////////////////////////////////////////////////

	final static int SW = LCD.SCREEN_WIDTH;
	final static int SH = LCD.SCREEN_HEIGHT;
	private static int lastPercentage = 0;
	private static int selectedStation = 0;

	////////////////////////////////////////////////////////////////////////////
	// The code...
	////////////////////////////////////////////////////////////////////////////

	static Graphics g = new Graphics();
	static int chHeight = g.getFont().getHeight();

	/**
	 * Draws text horizontally centered on the screen
	 * 
	 * @param text
	 *            the text that you want to display on the screen
	 * @param lineNumber
	 *            what line number should the text be displayed on? (0-7)
	 */
	public static void drawCenteredString(String text, int lineNumber) {
		g.setFont(Font.getDefaultFont());
		g.drawString(text, SW / 2, chHeight * lineNumber, Graphics.HCENTER);
	}

	/**
	 * Draws text horizontally centered on the screen, also has the ability to
	 * pick a font size
	 * 
	 * @param text
	 *            the text that you want to display on the screen
	 * @param lineNumber
	 *            what line number should the text be displayed on? (0-7)
	 * @param fontSize
	 *            the font size you want to use (0 = small, 1 = regular, 2 =
	 *            large) (Default font size is regular for comparison)
	 */
	public static void drawCenteredString(String text, int lineNumber, int fontSize) {
		switch (fontSize) {
		case 0:
			g.setFont(Font.getSmallFont());
			break;
		case 1:
			g.setFont(Font.getDefaultFont());
			break;
		case 2:
			g.setFont(Font.getLargeFont());
			break;
		default:
			g.setFont(Font.getDefaultFont());
			break;
		}
		g.drawString(text, SW / 2, chHeight * lineNumber, Graphics.HCENTER);
	}

	/**
	 * Draws text at the specified position on the screen (essentially
	 * <i>LCD.drawString</i> but on steroids) <br>
	 * Also has the ability to specify a font size
	 * 
	 * @param text
	 *            the text that you want to display on the screen
	 * @param x
	 *            the x-coordinate to write the text on the screen (0-100)
	 * @param y
	 *            the y-coordinate to write the text on the screen (0-7)
	 * @param fontSize
	 *            the font size you want to use (0 = small, 1 = regular, 2 =
	 *            large) (Default font size is regular for comparison)
	 */
	public static void drawString(String text, int x, int y, int fontSize) {
		switch (fontSize) {
		case 0:
			g.setFont(Font.getSmallFont());
			break;
		case 1:
			g.setFont(Font.getDefaultFont());
			break;
		case 2:
			g.setFont(Font.getLargeFont());
			break;
		default:
			g.setFont(Font.getDefaultFont());
			break;
		}
		g.drawString(text, x, chHeight * y, Graphics.LEFT);
	}

	/**
	 * Draws text at the specified position on the screen (essentially
	 * <i>LCD.drawString</i> but on steroids). Uses the default font size.
	 * 
	 * @param text
	 *            the text that you want to display on the screen
	 * @param x
	 *            the x-coordinate to write the text on the screen (0-100)
	 * @param y
	 *            the y-coordinate to write the text on the screen (0-7)
	 */
	public static void drawString(String text, int x, int y) {
		g.drawString(text, x, chHeight * y, Graphics.LEFT);
	}

	/**
	 * Effectively the main menu for the robot.
	 */
	public static void displayDestinationScreen() {
		LCD.clear();
		drawCenteredString(".:Main Menu:.", 0);
		LCD.drawString("Destinations: ", 0, 1);
		drawCenteredString("A	B	C	D", 3, 2);
		drawCenteredString("^	 	 	 ", 5, 2);
		selectedStation = Globals.STATION_A;
		Globals.currentStation = selectedStation;
		//drawCenteredString("_	 	 	 ", 3, 2);
		//drawCenteredString("_	 	 	 ", 2, 2);
	}
	
	/**
	 * Moves the cursor on screen to select a different station (most likely to be used with the left and right arrow keys)
	 * @param station the station to visit - use the types STATION_X from Globals (e.g. Globals.STAION_A)
	 */
	public static void changeDestinationScreen(int station){
		LCD.clear();
		drawCenteredString(".:Main Menu:.", 0);
		LCD.drawString("Destinations: ", 0, 1);
		switch (station){
			case Globals.STATION_A:
				selectedStation = station;
				drawCenteredString("A	B	C	D", 3, 2);
				drawCenteredString("^	 	 	 ", 5, 2);
				//drawCenteredString("_	 	 	 ", 1, 2);
				break;
			case Globals.STATION_B:
				selectedStation = station;
				drawCenteredString("A	B	C	D", 3, 2);
				drawCenteredString(" 	^	 	 ", 5, 2);
				//drawCenteredString(" 	_	 	 ", 1, 2);
				break;
			case Globals.STATION_C:
				selectedStation = station;
				drawCenteredString("A	B	C	D", 3, 2);
				drawCenteredString(" 	 	^	 ", 5, 2);
				//drawCenteredString(" 	 	_	 ", 1, 2);
				break;
			case Globals.STATION_D:
				selectedStation = station;
				drawCenteredString("A	B	C	D", 3, 2);
				drawCenteredString(" 	 	 	^", 5, 2);
				//drawCenteredString(" 	 	 	_", 1, 2);
				break;
			default:
				selectedStation = Globals.STATION_A;
				drawCenteredString("A	B	C	D", 3, 2);
				drawCenteredString("^	 	 	 ", 5, 2);
				//drawCenteredString("_	 	 	 ", 1, 2);
				break;
		}
		LCD.refresh();
		Globals.currentStation = selectedStation;
		RConsole.println(getStationFromIdentifier(Globals.currentStation, true));
		//drawCenteredString("Going to: " + selectedStation);
	}
	/**
	 * Returns the selected station (what the GUI is set to and what it is going to drive to)
	 * @param toString (true/false) if true will return a complete version e.g. <b>Station A</b>, if false will return just the station letter
	 * @return Station name (depending on if toString is set to true or false)
	 */
	public static String getDestinationStation(boolean toString){
		selectedStation = Globals.currentStation;
		switch (selectedStation){
			case Globals.STATION_A:
				if (toString)
					return "Station A";
				else
					return "A";
			case Globals.STATION_B:
				if (toString)
					return "Station B";
				else
					return "B";
			case Globals.STATION_C:
				if (toString)
					return "Station C";
				else
					return "C";
			case Globals.STATION_D:
				if (toString)
					return "Station D";
				else
					return "D";
			default:
				if (toString)
					return "Station A";
				else
					return "A";
		}
	}
	/**
	 * Gets the name of the station from the station identifiers <br>
	 * Example: Passing Globals.STATION_A will return <b>Station A</b> or <b>A</b> depending on if toString is set to true or false.
	 * @param station station identifier (e.g. Globals.STATION_A)
	 * @param toString whether it should return the whole name or just the letter
	 * @return the name of the station.
	 */
	public static String getStationFromIdentifier(int station, boolean toString){
		switch (station){
			case Globals.STATION_A:
				if (toString)
					return "Station A";
				else
					return "A";
			case Globals.STATION_B:
				if (toString)
					return "Station B";
				else
					return "B";
			case Globals.STATION_C:
				if (toString)
					return "Station C";
				else
					return "C";
			case Globals.STATION_D:
				if (toString)
					return "Station D";
				else
					return "D";
			default:
				if (toString)
					return "Station A";
				else
					return "A";
		}
	}
	
	/**
	 * Attempts to draw a long string by wrapping it down to another line <br>
	 * <i>May soon become depreciated due to the length of letters not staying
	 * the same</i>
	 * 
	 * @param text
	 *            the text that should be wrapped.
	 * @param line
	 *            the starting line for the text to be written.
	 */
	public static void drawWrappedString(String text, int line) {
		int stringWidth = text.length() * LCD.FONT_WIDTH;
		int stringLength = text.length();
		int linesNeeded = (((stringWidth + 99) / 100) * 100) / LCD.SCREEN_WIDTH;
		for (int i = 0; i < linesNeeded; i++) {
			if (i + 1 == linesNeeded)
				LCD.drawString(text.substring(i * 20, stringLength), 0, line + i);
			else
				LCD.drawString(text.substring(i * 20, (i * 20 + 20)), 0, line + i);
		}

	}
	
	/**
	 * Displays the calibration screen (mainly used on startup (this function actually calibrates the sensors as well so it is a one-stop shop really.
	 * @param whiteCalibration - Set <b>true</b> to calibrate the high value, set <b>false</b> to calibrate the low value.
	 */
	
	private static void displayLoadScreen(int timeToShow) {
		LCD.clear();
		variables.Version myVersion = variables.Globals.CURRENT_VERSION;
		drawCenteredString(myVersion.toString(), 0);
		drawCenteredString("ATN", 2, 2);
		drawCenteredString("Automated Track Navigator", 4, 0);
		drawCenteredString("Initializing...", 6);
		Delay.msDelay(timeToShow * 1000);
		for (int i = 0; i <= 60; i++) {
			g.fillRect(0, Font.getDefaultFont().getHeight() * 7, LCD.FONT_WIDTH / 2 * i,
					Font.getDefaultFont().getHeight());
			Delay.msDelay(100);
			LCD.refresh();
		}
		LCD.clear();
	}

	/**
	 * Displays the initial loading screen with information on what it is
	 * currently doing. To be used in conjunction with updateLoadScreen() (Use
	 * the other variant for a fixed display time)
	 * 
	 * @param statusText
	 *            what the robot is currently doing (e.g. loading map)
	 */
	public static void displayLoadScreen(String statusText) {
		Sound.beepSequenceUp();
		LCD.clear();
		variables.Version myVersion = variables.Globals.CURRENT_VERSION;
		drawCenteredString(myVersion.toString(), 0);
		drawCenteredString("ATN", 2, 2);
		drawCenteredString("Automated Track Navigator", 4, 0);
		drawCenteredString(statusText, 6);
	}


	/**
	 * Displays the initial loading screen without current status (useful for
	 * when the robot first turns on). Includes a delay function to keep the
	 * screen on for a specified number of milliseconds. Change notFake to false
	 * to simulate a loading screen behavior.
	 * 
	 * @param delay
	 *            how long the screen should stay before moving on to the next
	 *            line of code.
	 * @param notFake
	 *            if set to false will simulate a loading screen, keep true if
	 *            you are going to use updateLoadScreen()
	 * 
	 */
	public static void displayLoadScreen(int delay, boolean notFake) {
		Sound.beepSequenceUp();
		if (notFake) {
			LCD.clear();
			variables.Version myVersion = variables.Globals.CURRENT_VERSION;
			drawCenteredString(myVersion.toString(), 0);
			drawCenteredString("ATN", 2, 2);
			drawCenteredString("Automated Track Navigator", 4, 0);
			Delay.msDelay(delay);
			if (Button.ENTER.isDown())
				System.exit(0);
		} else {
			displayLoadScreen(delay);
		}
	}

	/**
	 * Used to change the status message on the loading screen as well as
	 * changing the position of the progress bar <br>
	 * Useful for providing the user with visual feedback for tasks (rather than
	 * just a pause between actions)
	 * 
	 * @param statusText
	 *            what task the robot is currently performing (e.g. Calculating
	 *            Route)
	 * @param percentage
	 *            (from 0-100) How far the operation is to completion (in total)
	 */

	public static void updateLoadScreen(String statusText, int percentage) {
		LCD.clear(6);
		drawCenteredString(statusText, 6);
		if (lastPercentage < percentage) {
			LCD.clear(7);
			for (int i = lastPercentage; i <= percentage; i++) {
				g.fillRect(0, Font.getDefaultFont().getHeight() * 7, LCD.FONT_WIDTH / 5 * i,
						Font.getDefaultFont().getHeight());
				Delay.msDelay(50);
				LCD.refresh();
			}
			lastPercentage = percentage;
		}
	}

	/**
	 * Used to change the status message on the loading screen as well as
	 * changing the position of the progress bar <br>
	 * Useful for providing the user with visual feedback for tasks (rather than
	 * just a pause between actions)
	 * 
	 * @param statusText
	 *            what task the robot is currently performing (e.g. Calculating
	 *            Route)
	 * @param percentage
	 *            (from 0-100) How far the operation is to completion (in total)
	 * @param waitInMilliseconds
	 *            how long it should take for the percentage to change (in
	 *            milliseconds)
	 */
	public static void updateLoadScreen(String statusText, int percentage, int waitInMilliseconds) {
		LCD.clear(6);
		drawCenteredString(statusText, 6);
		if (lastPercentage < percentage) {
			LCD.clear(7);
			for (int i = lastPercentage; i <= percentage; i++) {
				g.fillRect(0, Font.getDefaultFont().getHeight() * 7, LCD.FONT_WIDTH / 5 * i,
						Font.getDefaultFont().getHeight());
				Delay.msDelay(waitInMilliseconds / (percentage - lastPercentage));
				LCD.refresh();
			}
			lastPercentage = percentage;
		}
	}

	/**
	 * This shows the splash screen (contains the robot name and version number)
	 * and then waits for the specified number of seconds before clearing the
	 * display and returning
	 * 
	 * @param timeToShow
	 *            number of seconds to display the splash screen before clearing
	 *            the display and returning.
	 */

	public static void displaySplashScreen(int timeToShow) {
		LCD.clear();
		variables.Version myVersion = variables.Globals.CURRENT_VERSION;
		drawCenteredString(myVersion.toString(), 5);
		drawCenteredString("ATN", 2, 2);
		drawCenteredString("Automated Track Navigator", 4, 0);
		Delay.msDelay(timeToShow * 1000);
		LCD.clear();
	}

	/**
	 * This shows the splash screen (contains the robot name and version number)
	 * and then waits for the user to press ENTER in order for the screen to go
	 * away (e.g. useful for a credits page)
	 */
	public static void displaySplashScreen() {
		LCD.clear();
		variables.Version myVersion = variables.Globals.CURRENT_VERSION;
		drawCenteredString(myVersion.toString(), 5);
		drawCenteredString("ATN", 2, 2);
		drawCenteredString("Automated Track Navigator", 4, 0);
		drawCenteredString("Press ENTER", 7);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}

}
