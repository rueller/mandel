package frac;

public class Mandel {

	public static int NB_ITER_MAX = 100;
	
	public static double sqrNorme(double x, double y){
		return x*x + y *y;
	}
	
	public static int calcIter(double a, double b){
		double xn = 0, yn = 0;
		
		for (int i = 0; i < NB_ITER_MAX; ++i){
			double xnp1, ynp1;
			xnp1 = xn * xn - yn * yn + a;
			ynp1 = 2 * xn * yn + b;
			xn = xnp1;
			yn = ynp1;
			if (sqrNorme(xn, yn) > 4){
				return i;
			}

		}
		return NB_ITER_MAX;
	}
		
}
