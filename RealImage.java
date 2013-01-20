package frac;

public class RealImage {
	
	private double centerX, centerY, width, height;
	private double fact;
	
	public RealImage(double cX, double cY, double w, double h){
		centerX = cX;
		centerY = cY;
		width = w;
		height = h;
		fact = 1.0;
	}
	
	public void doZoom(double factor){
		fact *= factor;
		width *= factor;
		height *= factor; 
	}
	
	/**
	 * 
	 * @param x between 0 and 1
	 * @param y same as x
	 */
	public void doTranslate(double x, double y){
		centerX += x * width;
		centerY += y  * height;
	}
	
	/**
	 * compute the iteration number to diverge
	 * @param x01 the x coordonate goves between 0 and 1 
	 * @param y01 the x coordonate goves between 0 and 1 
	 * @return the number of iteration
	 */
	public int calcIter(double x01, double y01)
	{
		double a, b;
		
		a = centerX - width / 2.0 + x01 * width;
		b = centerY - height / 2.0 + y01 * height;
		
		return Mandel.calcIter(a, b);
		
	}

}
