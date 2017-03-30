import java.util.ArrayList;

public class SternBrocot{
	
	private ArbolBinarioSternBrocot arbol;

	public SternBrocot(){
		arbol = new ArbolBinarioSternBrocot();
	}

	public ArbolBinarioSternBrocot getArbol(){
		return arbol;
	}

	public static boolean sonPrimosRelativos(int m, int n){
		if(n == 1 || m == 1)
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