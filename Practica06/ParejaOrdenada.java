public class ParejaOrdenada<T>{

	private T m;
	private T n;
	private String str;
	private boolean valida;

	public ParejaOrdenada(String str){
		m = null;
		n = null;
		this.str = str;
		valida = false;
	}

	public ParejaOrdenada(T m, T n){
		this.m = m;
		this.n = n;
		this.str = String.format("%s %s", m.toString(), n.toString());
		this.valida = true;
	}

	@Override
	public String toString(){
		return str;
	}

	public boolean esValida(){
		return valida;
	}

	public T getM(){
		return m;
	}

	public T getN(){
		return n;
	}

	public void setM(T m){
		this.m = m;
	}

	public void setN(T n){
		this.n = n;
	}

	public boolean equals(Object o){
		if (o instanceof ParejaOrdenada){
			@SuppressWarnings("unchecked") ParejaOrdenada<T> po = (ParejaOrdenada<T>) o;
			return (po.getM().equals(m) && po.getN().equals(n));
		}else
			return false;
	}
}