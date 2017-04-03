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
		return (MCD(m,n) == 1)? true: false;
	}

	/**
	* Método que regresa el Maximo comun divisor entre dos numeros.
	* Aplicando el Algoritmo de Euclides.
	**/
	public static int MCD(int m, int n){

		int mayor = (m > n)? m: n;
		int menor = (mayor == m)? n: m;
		int aux = 0;
		int residuo = -1;
		int maximoComunDivisor = 1;

		if((double)mayor/menor == mayor/menor)
			return menor;

		while (residuo != 0){
			aux = mayor/menor;
			residuo = mayor - (menor * aux);
			if (residuo == 0)
				return maximoComunDivisor;
			maximoComunDivisor = residuo;
			mayor = menor;
			menor = residuo;
		}
		return 1;
	}
}