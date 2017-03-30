public class ArbolBinarioSternBrocot{ 

	public class Vertice {

        public ParejaOrdenadaInt elemento;
        /** El padre del vértice. */
        public Vertice padre;
        /** El izquierdo del vértice. */
        public Vertice izquierdo;
        /** El derecho del vértice. */
        public Vertice derecho;

        public Vertice(ParejaOrdenadaInt elemento) {
            this.elemento = elemento;
        }

        public String toString() {
		    return elemento.toString();
        }

        public boolean hayPadre() {
            return padre != null;
        }

        public boolean hayIzquierdo() {
            return izquierdo != null;
        }

        public boolean hayDerecho() {
            return derecho != null;
        }

        public Vertice getPadre() {
	    	return padre;
        }

        public Vertice getIzquierdo() {
	    	return izquierdo;
        }

        public boolean soyDerecho() {
        	if (padre == null)
        		return false;
        	if (padre.derecho != null)
        		return padre.derecho.equals(this);
        	return false;
        }

        public boolean soyIzquierdo() {
        	if (padre == null) 
        		return false;
        	if (padre.izquierdo != null)
        		return padre.izquierdo.equals(this);
        	return false;
        }

        public void setIzquierdo(Vertice v) {
        	izquierdo = v;
        	v.padre = this; 
        }

        public Vertice getDerecho() {
	    	return derecho;
        }

        public void setDerecho(Vertice v){
        	derecho = v;
        	v.padre = this;
        }

        public ParejaOrdenadaInt get() {
            return elemento;
        }

		public boolean estoyEnRamaDerecha(){
			if (padre == null)
				return true;
			return soyDerecho() && padre.estoyEnRamaDerecha();
		}

		public boolean estoyEnRamaIzquierda(){
			if (padre == null)
				return true;
			return soyIzquierdo() && padre.estoyEnRamaIzquierda();
		}

        public boolean equals(Object o){
        	if (o == null)
        		return false;
        	if (o instanceof Vertice){
        		@SuppressWarnings("unchecked") Vertice vertice = (Vertice)o;
        		return vertice.get() == elemento;
        	}else
        		return false;
        }
	}

	public Vertice raiz;
	public int elementos;

	public ArbolBinarioSternBrocot(){
		// Esto garantiza que almenos tiene un elemento;
		raiz = new Vertice(new ParejaOrdenadaInt(1,1));
		elementos = 1;
	}

	public void agrega(ParejaOrdenadaInt po){
		if(raiz == null)
	    	raiz = new Vertice(po);
		else
	    	agrega(raiz, po);
		elementos++;
	}

	private void agrega(Vertice v, ParejaOrdenadaInt po){
		if (po.compareTo(v.elemento) > 0)
			if (v.hayDerecho())
				agrega(v.getDerecho(), po);
			else
				v.setDerecho(new Vertice(po));
		else
			if (v.hayIzquierdo())
				agrega(v.getIzquierdo(), po);
			else
				v.setIzquierdo(new Vertice(po));
	}

	// po no es null
	public Vertice dame(ParejaOrdenadaInt po){
		return dameRecursivo(raiz, po);
	}

	// Vertice nunca es null... ¿o si?...
	private Vertice dameRecursivo(Vertice vertice, ParejaOrdenadaInt po){

		int comparacion = po.compareTo(vertice.get());
		if (comparacion == 0)
			return vertice;
		Vertice padre = vertice.getPadre();

		if (comparacion > 0){
			if (vertice.estoyEnRamaDerecha()){
				vertice.setDerecho(new Vertice(new ParejaOrdenadaInt(vertice.get().getM() + 1, 1)));
				return dameRecursivo(vertice.derecho, po);
			}
			if (vertice.getDerecho() == null){ // Carnita

				if (vertice.soyDerecho()){
					Vertice v = cruzados(vertice);
					v = v.padre;
					vertice.setDerecho(new Vertice(new ParejaOrdenadaInt(v.get().getM() + vertice.get().getM(), v.get().getN() + vertice.get().getN())));
				}else
					vertice.setDerecho(new Vertice(new ParejaOrdenadaInt(vertice.padre.get().getM() + vertice.get().getM(), vertice.padre.get().getN() + vertice.get().getN()))); 
								
				return dameRecursivo(vertice.derecho, po);
			}else
				return dameRecursivo(vertice.derecho, po);
		}

		// Comparacion < 0

		if (vertice.estoyEnRamaIzquierda()){
			vertice.setIzquierdo(new Vertice(new ParejaOrdenadaInt(1, vertice.get().getN() + 1)));
			return dameRecursivo(vertice.izquierdo, po);
		}

		if (vertice.getIzquierdo() == null){

			if (vertice.soyIzquierdo()){
				Vertice v = cruzados(vertice);
				v = v.padre;
				vertice.setIzquierdo(new Vertice(new ParejaOrdenadaInt(v.get().getM() + vertice.get().getM(), vertice.get().getN() + v.get().getN())));
			}else
				vertice.setIzquierdo(new Vertice(new ParejaOrdenadaInt(vertice.get().getM() + vertice.padre.get().getM(), vertice.get().getN() + vertice.padre.get().getN())));
			return dameRecursivo(vertice.izquierdo, po);
		}else
			return dameRecursivo(vertice.izquierdo, po);

	}

	private Vertice cruzados(Vertice vertice){
		Vertice v = vertice.padre;
		if (vertice.soyDerecho())
			while (v.soyDerecho())
				v = v.padre;
		else
			while (v.soyIzquierdo())
				v = v.padre;
		return v;
	}

	public Vertice busca(ParejaOrdenadaInt po){
		return buscaRecursivo(raiz, po);
	}

	private Vertice buscaRecursivo(Vertice v, ParejaOrdenadaInt po){
		if (v == null || po.compareTo(v.get()) == 0)
			return v;
		int comparacion = po.compareTo(v.get());
		if (comparacion > 0)
			return buscaRecursivo(v.derecho, po);
		return buscaRecursivo(v.izquierdo, po);
	}

	public String cadenaSternBrocot(ParejaOrdenadaInt elemento){
		
		// Aqui marca el null pointer
		Vertice vertice = dame(elemento);
		if (vertice == null)
			return "";

		String cadena = "";
		Vertice padre = vertice.getPadre();
		
		if (padre == null) // Quiere decir que el elemento es la raiz "1 1"
			return "I";

		while (padre != null){
			cadena = ((vertice.soyDerecho())? "R":"L") + cadena;
			vertice = padre;
			padre = vertice.getPadre();
		}
		return cadena;
	}

}