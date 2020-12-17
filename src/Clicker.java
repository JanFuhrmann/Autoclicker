import java.awt.Robot;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * Simple auto-clicker.
 * 
 * @author Jan Fuhrmann
 */
public class Clicker {
    private static int[][] D = new int[9][5];
	private final int CLICK_COUNTER = 1000; // number of desired clicks

    public static void click(String fileName) {
	int i = 0;
	for (int j = 5; j > 0; j--) {
	    try {
		System.out.println(j);
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
	    }
	}
	System.out.println("Bot startet...");
	Date date = new Date();
	try {
	    FileWriter fw = new FileWriter(fileName);
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write("Uhrzeit: " + date.getHours() + ":" + date.getMinutes()
		    + ":" + date.getSeconds());
	    bw.write(", Klicks: " + CLICK_COUNTER);
	    bw.flush();
	    bw.close();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	try {
	    while (i < CLICK_COUNTER) {
		i++;
		Robot robot = new Robot();
		// System.out.println("Noch " + (CLICK_COUNTER - i));
		Thread.sleep(50); // 50: 19/sek, 25: 38/sek,
				  // 15: 63/sek, 0: 89/sek
		if (i % 20 == 0) {
		    System.out.println("Bereits vergangene Zeit: " + (i / 20)
			    + " Sekunden, übrige Zeit: " + (250 - (i / 20))
			    + " Sekunden");
		}
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    }
	} catch (Exception e) {
	}
    }

    @SuppressWarnings("resource")
    public static void read(String fileName) {
	try {
	    Scanner input = new Scanner(new BufferedReader(new FileReader(
		    fileName)));
	    System.out.println("Es wurde in " + fileName
		    + " folgendes gespeichert: ");
	    while (input.hasNext()) {
		System.out.println(input.next());
	    }
	} catch (FileNotFoundException e) {
	    System.err.println("Error reading file " + fileName);
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	// String fileName = "klicks.txt";
	// click(fileName);
	// read(fileName);
	// taste(48770);
	//int[] a = next("dumdidumbum");
	//for (int i = 0; i < a.length; i++) {
	//    System.out.println(a[i]);
	//}
	System.out.println(backtrace(8,4));

    }

    public static void taste(int anzahl) {
	int i = 0;
	try {
	    Thread.sleep(3000);
	    while (i < anzahl) {
		i++;
		Robot robot = new Robot();
		Thread.sleep(20);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_ENTER);
		if (i % 10 == 0) {
		    System.out.println("Noch " + (anzahl - i) + " Tasten");
		}
	    }
	} catch (Exception e) {
	}
    }

    public static int[] next(String pat) { // berechne Präfixtabelle f
	int[] f = new int[pat.length()];
	int i = 1, j = 0;
	f[0] = 0;
	while (i < pat.length()) {
	    if (pat.charAt(j) == pat.charAt(i)) {
		f[i] = j + 1; // j+1 Zeichen identisch
		j++;
		i++;
	    } else if (j > 0) {
		j = f[j - 1];// j direkt nach passendem Präfix
	    } else {
		f[i] = 0; // kein Match
		i++;
	    }
	}
	return f;
    }

    public static String backtrace(int i, int j) {
	if (i > 0 && D[i - 1][j] + 1 == D[i][j])
	    return backtrace(i - 1, j) + " Del ";
	if (j > 0 && D[i][j - 1] + 1 == D[i][j])
	    return backtrace(i, j - 1) + " Ins ";
	if (i > 0 && j > 0 && D[i - 1][j - 1] + 1 == D[i][j])
	    return backtrace(i - 1, j - 1) + " Rep ";
	if (i > 0 && j > 0 && D[i - 1][j - 1] == D[i][j])
	    return backtrace(i - 1, j - 1) + " Eq ";
	return "";
    }
}