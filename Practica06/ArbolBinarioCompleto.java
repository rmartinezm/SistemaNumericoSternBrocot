import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Iterator;
import java.lang.Object;

public class ArbolBinarioCompleto<T> implements Iterable<T> {

	protected class Nodo<T>{

		private T elemento;
		private Nodo<T> izquierdo;
		private Nodo<T> derecho;
		private Nodo<T> padre;

		public Nodo(T elemento){
			this.elemento = elemento;
			izquierdo = null;
			derecho = null;
			padre = null;
		}

		public Nodo(T elemento, Nodo<T> padre){
			this.elemento = elemento;
			this.padre = padre;
			izquierdo = null;
			derecho = null;
		}

		public void setIzquierdo(Nodo<T> izquierdo){
			this.izquierdo = izquierdo;
		}

		public Nodo<T> getIzquierdo(){
			return izquierdo;
		}

		public boolean soyIzquierdo() throws Exception{
			if(padre == null)
				throw new Exception();
			return padre.izquierdo.equals(this);
		}

		public int hashCode(){
			return super.hashCode();
		}

		public void setDerecho(Nodo<T> derecho){
			this.derecho = derecho;
		}

		public Nodo<T> getDerecho(){
			return derecho;
		}

		public boolean soyDerecho() throws Exception{
			if(padre == null)
				throw new Exception();
			return padre.derecho.equals(this);
		}

		public T getElemento(){
			return elemento;
		}

		public Nodo<T> getPadre(){
			return padre;
		}


		/**
		* Metodo que revisa si un nodo es igual a un objeto que le pasan. Si el objeto es 
		* instancia de Nodo entonces se verifica si el elemento es igual, y ademas el metodo
		* sigue recursivamente con sus hijos.
		**/
		public boolean equals(Object o){
			if(o != null && o instanceof Nodo){
	    		@SuppressWarnings("unchecked") Nodo<T> nodo = (Nodo<T>)o;
				if(nodo.getElemento().equals(elemento)){
					boolean alpha = true, betha = true;
					if (nodo.getDerecho() != null && derecho != null)
						alpha = derecho.equals(nodo.getDerecho());
					else
						if ((nodo.getDerecho() == null && derecho != null) ||
							     (derecho == null && nodo.getDerecho() != null))
							return false;
					if (nodo.getIzquierdo() != null && izquierdo != null)
						betha = izquierdo.equals(nodo.getIzquierdo());
					else
						if((nodo.getIzquierdo() == null && izquierdo != null) ||
								(izquierdo == null && nodo.getIzquierdo() != null))
							return false;
					return alpha && betha;

				}else
					return false;    
		   	}else
		   		return false;
		}		

	}

	/* Clase Iterador privada para iteradores. */
    private class Iterador implements Iterator<T> {

        public ArrayList<ArbolBinarioCompleto<T>.Nodo<T>> cola;

        public Iterador() {
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !cola.isEmpty();
        }

        /* Nos da el elemento siguiente. */
        @Override public T next(){
        	if (hasNext()){
        		ArbolBinarioCompleto<T>.Nodo<T> nodo = cola.get(0);
        		T elemento = nodo.getElemento();
        		if (nodo.getIzquierdo() != null)
        			cola.add(nodo.getIzquierdo());
        		if (nodo.getDerecho() != null)
        			cola.add(nodo.getDerecho());
        		cola.remove(0);
        		return elemento;
        	}else 
        		return null;
		}

        /* Mueve el iterador al inicio de la lista. */
        public void start() {
        	cola = new ArrayList<>();
        	if (raiz != null)
        		cola.add(raiz);
		}

        /* No implementamos este método. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

	protected Nodo<T> raiz;
	public int elementos;

	public ArbolBinarioCompleto(){
		raiz = null;
		elementos = 0;
	}

	public void agrega(T elemento){
		if (raiz == null)
			raiz = new Nodo<>(elemento);
		else{
			ArrayList<Nodo<T>> cola = new ArrayList<>();
			cola.add(raiz);
			agregaRecursivo(cola, elemento);
		}
		elementos++;
	}

	/**
	* Estamos seguros de que cola nunca es vacia
	**/
	private void agregaRecursivo(ArrayList<Nodo<T>> cola, T elemento){
		Nodo<T> n = cola.get(0);
		cola.remove(0);
		Nodo<T> nuevo;
		if (n.getIzquierdo() == null){
			nuevo = new Nodo<>(elemento, n);
			n.izquierdo = nuevo;
		}else{
			if (n.getDerecho() == null){
				nuevo = new Nodo<>(elemento, n);
				n.derecho = nuevo; 
			}else{  //Ambos lugares estan ocupados
				cola.add(n.getIzquierdo());
				cola.add(n.getDerecho());
				agregaRecursivo(cola, elemento);
			}
		}
	}

	public boolean esVacio(){
		return raiz == null;
	}

	public int size(){
		return elementos;
	}

	public Nodo<T> busca(T elemento){
		 ArrayList<Nodo<T>> cola = new ArrayList<>();		 
       	if (raiz != null)
       		cola.add(raiz);

       	while(!cola.isEmpty()){
        	Nodo<T> nodo = cola.get(0);
        	T t = nodo.getElemento();
        	if (nodo.getIzquierdo() != null)
        		cola.add(nodo.getIzquierdo());
        	if (nodo.getDerecho() != null)
        		cola.add(nodo.getDerecho());
        	cola.remove(0);
        	if (t.equals(elemento))
        		return nodo;
       	}
       	return null;
	}

    @Override 
    public Iterator<T> iterator() {
        @SuppressWarnings("unchecked") Iterator<T> i = (Iterator<T>) new Iterador();
        return i;
    }

}