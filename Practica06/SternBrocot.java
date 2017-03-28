import java.util.ArrayList;

public class SternBrocot{
	
	private ArrayList<ParejaOrdenada<Integer>> lista;
	private ArbolBinarioSternBrocot<ParejaOrdenada<Integer>> arbol;

	public SternBrocot(){
		lista = new ArrayList<>();
		lista.add(new ParejaOrdenada<Integer>(0,1));
		lista.add(new ParejaOrdenada<Integer>(1,0));
		arbol = new ArbolBinarioSternBrocot<>();
	}

	public ArrayList<ParejaOrdenada<Integer>> getLista(){
		return lista;
	}

	public ArbolBinarioSternBrocot<ParejaOrdenada<Integer>> getArbol(){
		return arbol;
	}

	public void siguientePaso(){
		 @SuppressWarnings("unchecked") ArrayList<ParejaOrdenada<Integer>> copia = 
		 								(ArrayList<ParejaOrdenada<Integer>>) lista.clone();
		int aux = 1;
		int i = 1;
		while(i != copia.size()){
			int m = copia.get(i).getM() + copia.get(i-1).getM();
			int n = copia.get(i).getN() + copia.get(i-1).getN();
			lista.add(aux, new ParejaOrdenada<Integer>(m,n));
			arbol.agrega(new ParejaOrdenada<Integer>(m,n));
			i ++;
			aux += 2;
		}
	}

	public static boolean sonPrimosRelativos(int m, int n){
		if(n == 1)
			return true;
		int entero = m/n;
		double defined = (double) entero;
		double decimal = (double) m/n;
		boolean alpha = defined != decimal;

		entero = n/m;
		defined = (double) entero;
		decimal = (double) n/m;
		boolean betha = defined != decimal;

		return alpha && betha;
	}
}