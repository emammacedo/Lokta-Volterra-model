/* Exerc�cio de avalia��o 1
 * 
 * Ana Catarina Pais Pereira - 2019244449
 * Ema Margarida da Silva Macedo - 2019233271
 * 
 Modelo Predador - Presa */

public class func
{
	// Equa��es de Lotka-Volterra:

	// representa��o da equa��o dx/dt
	public static double f1(double x, double y, double alfa, double beta)
	{
		return x * (alfa - beta * y);
	}

	// representa��o da equa��o dy/dt
	public static double f2(double x, double y, double delta, double gama)
	{
		return y * (delta * x - gama);
	}

	// M�todo Runge-Kutta de 4� ordem
	public static double[] rk4(double x, double y, double alfa, double beta, double delta, double gama, double h)
	{
		double[] r = new double[2];
		// fun��o devolve um vetor na forma [x,y]

		double k1_f1 = f1(x, y, alfa, beta);
		double k1_f2 = f2(x, y, delta, gama);

		double k2_f1 = f1(x + (h * k1_f1 / 2), y + (h * k1_f2 / 2), alfa, beta);
		double k2_f2 = f2(x + (h * k1_f1 / 2), y + (h * k1_f2 / 2), delta, gama);

		double k3_f1 = f1(x + (h * k2_f1 / 2), y + (h * k2_f2 / 2), alfa, beta);
		double k3_f2 = f2(x + (h * k2_f1 / 2), y + (h * k2_f2 / 2), delta, gama);

		double k4_f1 = f1(x + (h * k3_f1), y + (h * k3_f2), alfa, beta);
		double k4_f2 = f2(x + (h * k3_f1), y + (h * k3_f2), delta, gama);

		r[0] = x + (h / 6) * (k1_f1 + 2 * k2_f1 + 2 * k3_f1 + k4_f1);
		r[1] = y + (h / 6) * (k1_f2 + 2 * k2_f2 + 2 * k3_f2 + k4_f2);

		return r;
	}
}
