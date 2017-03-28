public class ArbolBinarioSternBrocot<T> extends ArbolBinarioCompleto<T>{ 

	public ArbolBinarioSternBrocot(){
		raiz = null;
		elementos = 0;
	}

	public String cadenaSternBrocot(T elemento){
		Nodo<T> nodo = busca(elemento);
		if (nodo == null)
			return "";

		String cadena = "";
		Nodo<T> padre = nodo.getPadre();
		
		if (padre == null) // Quiere decir que el elemento es la raiz "1 1"
			return "I";
		while (padre != null){
			try{
				cadena = ((nodo.soyDerecho())? "R":"L") + cadena;
			}catch(Exception e){return "";}
			nodo = padre;
			padre = nodo.getPadre();
		}
		return cadena;
	}

}