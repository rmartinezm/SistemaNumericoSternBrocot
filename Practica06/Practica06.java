import java.util.ArrayList;
import java.io.*;

public class Practica06{

	public static void main(String[] args){

		BufferedReader bufferedReader = null;

		if(args.length == 0){ 
			// Los datos seran ingresados mediante entrada standard.
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

 		ArrayList<ParejaOrdenada<Integer>> parejas = new ArrayList<>();			

 		String pareja = "";
 		try{
 			pareja = bufferedReader.readLine();
 			pareja = pareja.trim();
 		}catch(Exception exp){return;}

		while (!pareja.equals("1 1")){
			try{
				String[] numeros = pareja.split(" ");
				if(numeros.length != 2){
					parejas.add(new ParejaOrdenada<Integer>(pareja + " : No cumplen la sintaxis requerida"));
					pareja = bufferedReader.readLine();
					pareja = pareja.trim();
				}else{
					int m;
					int n;
					try{
						m = Integer.parseInt(numeros[0]);
						n = Integer.parseInt(numeros[1]);
						if (n == 0)							
							parejas.add(new ParejaOrdenada<Integer>(pareja + " : Segunda entrada invalida (n = 0)."));
						else
							if (n < 0 || m < 0)
								parejas.add(new ParejaOrdenada<Integer>(pareja + " : Algun numero ingresado es negativo."));
							else
								if (SternBrocot.sonPrimosRelativos(m,n))
									parejas.add(new ParejaOrdenada<Integer>(m,n));
								else
									parejas.add(new ParejaOrdenada<Integer>(pareja + " : No son Primos relativos"));
						pareja = bufferedReader.readLine();
						pareja = pareja.trim();
					}catch(Exception exp){
						parejas.add(new ParejaOrdenada<Integer>(pareja + " : No son valores enteros"));
						pareja = bufferedReader.readLine();
						pareja = pareja.trim();						
					}
				}
			}catch(Exception exeption){
				System.out.println("El archivo no tiene el par ordenado \"1 1\", verifique");
				return;
			}
		}

		// En este punto, parejas contiene todas las ParejasOrdenadas que fueron leidas 
		SternBrocot sb = new SternBrocot();
		try{ 
			BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"));
			for (ParejaOrdenada<Integer> po: parejas)
				if (po.esValida()){
					String csb = sb.getArbol().cadenaSternBrocot(po);
					while (csb.isEmpty()){
						sb.siguientePaso();
						csb = sb.getArbol().cadenaSternBrocot(po);
					}
					bw.write(String.format("%s : %s\n", po.toString(), csb));
				}
				else
					bw.write(String.format("%s\n", po.toString()));
			bw.close();
		}catch(Exception exp){return;}
	}

}