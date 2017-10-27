import java.util.ArrayList;
import java.io.*;

public class Programa{

	public static void main(String[] args) {
		BufferedReader bufferedReader = null;

		if (args.length == 0){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String intrucciones = "\nBienvenido a SternBrocot, teclea un par de numeros (que sean primos relativos entre si)\ny asi poder regresar la representacion de la fraccion proporcionada entre ambos numeros en el\nsistema numerico de Stern-Brocot. Puedes teclear tantos como desees; cuando quieras detenerte poner \"1 1\"\n\nEjemplo de entrada:\n5 7\n878 323\n1 1\n\nAutomaticamente despues de eso se generara un archivo salida.txt con las cadenas que representen las\ncantidades ingresadas.Comencemos!\n";
			System.out.println(intrucciones);
			String pareja = "";
			try{
				pareja = br.readLine();
			}catch(Exception exp){return;}

			ArrayList<String> cadenas = new ArrayList<>();
			while(!pareja.trim().equals("1 1")){
				cadenas.add(pareja + "\n");
				try{
					pareja = br.readLine();
				}catch(Exception exp){return;}
			}			
			cadenas.add("1 1");

			FileWriter fileWriter = null;
			try{
				fileWriter = new FileWriter(new File("entrada.txt"));
			}catch(Exception exp){return;}
			
			try{
				for(String str: cadenas)
					fileWriter.write(str);
				fileWriter.close();
				bufferedReader = new BufferedReader(new FileReader(new File("entrada.txt")));
			}catch(Exception exp){return;}

 		}else
 			try{
 				bufferedReader = new BufferedReader(new FileReader(new File(args[0])));
 			}catch(Exception e){return;}
		
		// En este punto con bufferedReader obtenemos las lineas que hemos leido 

 		ArrayList<ParejaOrdenadaInt> parejas = new ArrayList<>();			

 		String pareja = "";
 		try{
 			pareja = bufferedReader.readLine();
 			pareja = pareja.trim();
 		}catch(Exception exp){return;}

		while (!pareja.equals("1 1")){
			try{
				String[] numeros = pareja.split(" ");
				if(numeros.length != 2){
					parejas.add(new ParejaOrdenadaInt(pareja + " : No cumplen la sintaxis requerida"));
					pareja = bufferedReader.readLine();
					pareja = pareja.trim();

				}else{
					int m, n;
					try{
						m = Integer.parseInt(numeros[0]);
						n = Integer.parseInt(numeros[1]);
						if(n == 0 || m == 0)
							parejas.add(new ParejaOrdenadaInt(pareja + " : Entrada invalida (valor igual a cero)."));
						else
							if (n < 0 || m < 0)
								parejas.add(new ParejaOrdenadaInt(pareja + " : Algun numero ingresado es negativo, verifique."));
							else
								if (SternBrocot.sonPrimosRelativos(m,n))
									parejas.add(new ParejaOrdenadaInt(m,n));
								else
									parejas.add(new ParejaOrdenadaInt(pareja + " : No son primos relativos. Maximo Comun divisor : " + SternBrocot.MCD(m,n)));
						pareja = bufferedReader.readLine();
						pareja = pareja.trim();
					}catch(Exception e){
						parejas.add(new ParejaOrdenadaInt(pareja + " : No son valores enteros."));
						pareja = bufferedReader.readLine();
						pareja = pareja.trim();
					}
				}
			}catch(Exception e){
				System.out.println("El archivo no tiene el par ordenado \"1 1\", verifique.");
				return;
			}
		}

		// En este punto, parejas tiene todas las ParejasOrdenadas que fueron leidas.

		SternBrocot sb = new SternBrocot();
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"));
			System.out.println("");
			for (ParejaOrdenadaInt po: parejas)
				if (po.esValida()){
					bw.write(String.format("%s : %s\n",po.toString(), sb.getArbol().cadenaSternBrocot(po)));
					System.out.print(String.format("%s : %s\n",po.toString(), sb.getArbol().cadenaSternBrocot(po)));
				}else{
					bw.write(String.format("%s\n", po.toString()));
					System.out.print(String.format("%s\n", po.toString()));
				}
			bw.close();
		}catch(NullPointerException e){
			System.out.println("NullPointerException");
			return;
		}catch(IOException io){
			System.out.println("IOException");
			return;
		}
		System.out.println("");
	}

}