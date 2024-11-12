/* Exerc�cio de avalia��o 1
 * 
 * Ana Catarina Pais Pereira - 2019244449
 * Ema Margarida da Silva Macedo - 2019233271
 * 
 Modelo Predador - Presa */

import java.util.Scanner;

public class Trabalho1
{
	/*
	 * t -> tempo; x -> presas; alfa -> taxa crescimento presas; beta -> taxa de decrescimo presas; y -> predadores;
	 * delta -> taxa crescimento predadores; gama -> taxa de decrescimo predadores; h -> intervalo de discretiza��o
	 * temporal
	 */

	//// Recolha dos valores dados pelo utilizador

	// N�MEROS DECIMAIS --> ,

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		// assume-se val como falso para testar a validade
		boolean valt = false;
		boolean valh = false;
		boolean valx = false;
		boolean valy = false;
		boolean valalfa = false;
		boolean valbeta = false;
		boolean valgama = false;
		boolean valdelta = false;

		// valor t
		do
		{
			System.out.println("Insira o tempo de simula��o:");
			if (sc.hasNextDouble()) // leitura e verifica��o de que um valor num�rico pode ser lido
			{
				double t_max = sc.nextDouble(); // aguardar por um double
				while (t_max <= 0)
				{
					System.out.println("Insira um n�mero superior a 0");
					t_max = sc.nextDouble();
				}
				valt = true;
				sc.nextLine();

				// valor h
				do
				{
					System.out.println("Intervalo de discretiza��o temporal:");

					if (sc.hasNextDouble())
					{
						double h = sc.nextDouble();
						while (h >= 1 || h <= 0)
						{
							System.out.println("Insira um n�mero entre 0 e 1");
							h = sc.nextDouble();
						}
						valh = true;
						sc.nextLine();

						// valor x_i
						do
						{
							System.out.println("Insira a popula��o inicial de presas:");
							if (sc.hasNextInt())
							{
								double x_i = sc.nextInt();
								/*
								 * aceita apenas um n�mero inteiro, mas a variav�l criada � um double para mais tarde
								 * ser utilizado nos c�lculos
								 */
								while (x_i <= 0)
								{
									System.out.println("Insira um n�mero superior a 0");
									x_i = sc.nextInt();
								}
								valx = true;
								sc.nextLine();

								// valor alfa
								do
								{
									System.out.println("Insira a taxa de crescimento da popula��o de presas:");
									if (sc.hasNextDouble())
									{
										double alfa = sc.nextDouble();
										while (alfa <= 0 || alfa > 1)
										{
											System.out.println("Insira um n�mero entre 0 e 1");
											alfa = sc.nextDouble();
										}
										valalfa = true;
										sc.nextLine();

										// valor beta
										do
										{
											System.out.println("Insira a taxa de decr�scimo da popula��o de presas:");
											if (sc.hasNextDouble())
											{
												double beta = sc.nextDouble();
												while (beta <= 0 || beta > 1)
												{
													System.out.println("Insira um n�mero entre 0 e 1");
													beta = sc.nextDouble();
												}
												valbeta = true;
												sc.nextLine();

												// valor y_i
												do
												{
													System.out.println("Insira a popula��o inicial de predadores:");
													if (sc.hasNextInt())
													{
														double y_i = sc.nextInt(); 
														
														while (y_i <= 0)
														{
															System.out.println("Insira um n�mero supeior a 0");
															y_i = sc.nextInt();
														}
														valy = true;
														sc.nextLine();

														// valor delta
														do
														{
															System.out.println(
																	"Insira a taxa de crescimento da popula��o de predadores:");
															if (sc.hasNextDouble())
															{
																double delta = sc.nextDouble();
																while (delta <= 0 || delta > 1)
																{
																	System.out.println("Insira um n�mero entre 0 e 1");
																	delta = sc.nextDouble();
																}
																valdelta = true;
																sc.nextLine();

																// valor gama
																do
																{
																	System.out.println(
																			"Insira a taxa de decr�scimo da popula��o de predadores:");
																	if (sc.hasNextDouble())
																	{
																		double gama = sc.nextDouble();
																		while (gama <= 0 || gama > 1)
																		{
																			System.out.println(
																					"Insira um n�mero entre 0 e 1");
																			gama = sc.nextDouble();
																		}
																		valgama = true;
																		sc.nextLine();

																		//// Execu��o
																		
																		// vetor c/ valores de x (presas)
																		double[] val_x = new double[(int) (t_max / h) + 1];
																		
																		// vetor c/ valores de y (predadores)
																		double[] val_y = new double[(int) (t_max / h) + 1];
																		
																		//adi��o dos valores iniciais pedidos ao utilizador
																		val_x[0] = x_i;
																		val_y[0] = y_i;

																		int n = 1; //indice dos valores calculados
																		for (double i = h; i <= t_max; i = i + h)
																		{
																			double[] r = new double[2];

																			r = func.rk4(x_i, y_i, alfa, beta, delta,
																					gama, h);

																			// adi��o dos valores aos vetores
																			val_x[n] = r[0];
																			val_y[n] = r[1];

																			n = n + 1; // atualiza��o do �ndice

																			// atualiza��o dos valores de x e y para a
																			// pr�xima integra��o
																			x_i = r[0];
																			y_i = r[1];
																		}

																		//// Legenda
																		System.out.println(
																				"---------------------------------");
																		System.out.println(
																				"| + --> Presa                    |");
																		System.out.println(
																				"| * --> Predador                 |");
																		System.out.println(
																				"---------------------------------");
																																			
																		//// Determina��o do valor m�ximo atingido
																		double max_x = 0;
																		double max_y = 0;
																		for (int j = 0; j < val_x.length; j++)
																		{
																			if (val_x[j] > max_x)
																			{
																				max_x = val_x[j];
																			}
																			if (val_y[j] > max_y)
																			{
																				max_y = val_y[j];
																			}
																		}														

																		//// Impress�o do valor m�ximo atingido
																		double v_max = 0; 
																		if (max_y > max_x)
																			v_max = max_y;
																		else
																			v_max = max_x;

																		System.out.println(v_max);

																		//// Gr�fico

																		// intervalo correspondente a cada tra�o eixo y
																		// n�mero de linhas do gr�fico = 35
																		double comp_inter = v_max / 35; 

																		// intervalo correspondente a cada tra�o eixo x
																		// n�mero de colunas do gr�fico = 124
																		double comp_tempo = t_max / 124; 

																		// vetores c/ valores de x e y m�dios
																		double[] val_x_new = new double[124];
																		double[] val_y_new = new double[124];

																		int novo = 0; //indice 
																		for (double i = 0; i < t_max; i = i
																				+ comp_tempo)
																		{
																			/*indice do 1� valor de x/y a ter em conta no c�lculo de 
																			 * x/y m�dio para o intervalo [i;i+comp_t]
																			*/
																			int nn = (int) (i / h); // assume o
																									// inteiro por
																									// defeito

																			int sum_x = 0;
																			int sum_y = 0;

																			double count = 0;

																			while (nn < (i + comp_tempo)/h)
																			{
																				sum_x += val_x[nn];
																				sum_y += val_y[nn];
																				nn += 1;
																				count += 1;
																			}

																			//valor x/y m�dio para o intervalo de tempo [i;i+comp_tempo]
																			val_x_new[novo] = sum_x / count;
																			val_y_new[novo] = sum_y / count;

																			novo += 1;

																		}

																		//matriz gr�fico
																		char[][] graf = new char[35][124]; 

																		// Impress�o do gr�fico
																		int d = 0;
																		double lim_sup = v_max; // limite superior do 1�
																								// valor m�dio = maximo

																		System.out.println('^');
																		for (int i = 0; i <= 35 - 1; i++)
																		{
																			System.out.print("|"); // em todas as linhas
																									// coloca um |

																			d += 1; // intervalo correspondente a cada
																					// valor m�dio � "somado" de linha
																					// para linha

																			for (int j = 0; j <= 124 - 1; j++)
																			{
																				/*limite inferior = limite superior - comprimento do 
																				 * intervalo, sendo que para os intervalores seguintes
																				 * tem-se em conta o anterior (d)*/
																				double lim_inf = v_max - d * comp_inter; 
																				
																				for (int m = 0; m < val_x_new.length; m++)
																				{
																					//se estiver dentro do intervalor em quest�o
																					if (val_y_new[m] < lim_sup
																							& val_y_new[m] >= lim_inf)
																					{
																						graf[i][m] = '*'; // predador
																					}
																					
																					//se estiver dentro do intervalor em quest�o
																					if (val_x_new[m] <= lim_sup
																							& val_x_new[m] >= lim_inf) 
																					{
																						graf[i][m] = '+'; // presa
																					}
																				}
																				lim_sup = lim_inf; // o limite superior
																									// do proximo
																									// intervalo
																									// corresponde ao
																									// limite inferior
																									// do anterior

																				System.out.print(graf[i][j]);

																			}
																			System.out.print("\n");
																		}

																		// eixo do tempo
																		for (int i = 1; i <= 126; i++) 
																		{
																			System.out.print("-");
																		}
																		System.out.print(">");
																		System.out.print("\n");

																		// valores do eixo do tempo
																		System.out.print("0");
																		for (int i = 1; i < 124 / 2; i++)
																		{
																			System.out.print(" ");
																		}
																		System.out.print((double) t_max / 2);
																		for (int i = 1; i < 124 / 2; i++)
																		{
																			System.out.print(" ");
																		}
																		System.out.print(t_max);

																	}
																	else
																	{
																		System.out.println(
																				"O n�mero inserido n�o � v�lido.");
																		sc.nextLine();
																	}
																}
																while (valgama == false);
															}
															else
															{
																System.out.println("O n�mero inserido n�o � v�lido.");
																sc.nextLine();
															}
														}
														while (valdelta == false);
													}
													else
													{
														System.out.println("O n�mero inserido n�o � v�lido.");
														sc.nextLine();
													}
												}
												while (valy == false);
											}
											else
											{
												System.out.println("O n�mero inserido n�o � v�lido.");
												sc.nextLine();
											}
										}
										while (valbeta == false);
									}
									else
									{
										System.out.println("O n�mero inserido n�o � v�lido.");
										sc.nextLine();
									}
								}
								while (valalfa == false);
							}
							else
							{
								System.out.println("O n�mero inserido n�o � v�lido.");
								sc.nextLine();
							}
						}
						while (valx == false);
					}
					else
					{
						System.out.println("O intervalo inserido n�o � v�lido.");
						sc.nextLine();
					}
				}
				while (valh == false);
			}
			else
			{
				System.out.println("O tempo inserido n�o � v�lido.");
				sc.nextLine();
			}
		}
		while (valt == false);

		sc.close(); // fecha scanner

	} 
} 