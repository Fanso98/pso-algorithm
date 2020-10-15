package org.inz.pso;

// Przykladowy problem
// znajduje minimum funkcji (szukam X i Y)
// 2 <= x <= 5, and -2 <= y <= 2 przykladowe ograniczeni

// jesli wiecej wymiarow musimy zrobic wiecej zmiennych od X i Y

public class Problem {
	
	public static final double LOC_X_MIN = 2;
	public static final double LOC_X_MAX = 5;
	public static final double LOC_Y_MIN = -2;
	public static final double LOC_Y_MAX = 2;
	public static final double VEL_MIN = -1;
	public static final double VEL_MAX = 1;
	
	public static final double ERR_TOL = 1E-13; // Regulacja dokladnosci lepsza dokladnosc == wiecej iteracji
		//podstawienie naszej lokacji pod funkcje fitness ktorej minimum szukamy
	public static double evaluate(Location location) {
		double result = 0;
		double x = location.getLoc()[0]; // lokacja na x
		double y = location.getLoc()[1]; // lokacja na y
		//przykladowa funkcja ktorej minima szukamy
		result = Math.pow(2 - x + x * Math.pow(y, 3), 2) + Math.pow(1.5 - x + x * y * 1, 2);
		
		return result;
	}
}
