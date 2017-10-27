public class ParejaOrdenadaInt implements Comparable<ParejaOrdenadaInt>{

	private int m;
	private int n;
	private String str;
	private boolean valida;

	public ParejaOrdenadaInt(String str){
		m = 0;
		n = 0;
		this.str = str;
		valida = false;
	}

	public ParejaOrdenadaInt(int m, int n){
		this.m = m;
		this.n = n;
		this.str = String.format("%d %d", m, n);
		this.valida = true;
	}

	@Override
	public String toString(){
		return str;
	}

	public boolean esValida(){
		return valida;
	}

	public int getM(){
		return m;
	}

	public int getN(){
		return n;
	}

	public void setM(int m){
		this.m = m;
	}

	public void setN(int n){
		this.n = n;
	}

	public boolean equals(Object o){
		if (o instanceof ParejaOrdenadaInt){
			@SuppressWarnings("unchecked") ParejaOrdenadaInt po = (ParejaOrdenadaInt) o;
			return (po.getM() == m && po.getN() == n);
		}else
			return false;
	}

	@Override
	public int compareTo(ParejaOrdenadaInt po){
		
		double a = (double) po.getM() / po.getN();
		double b = (double) m/n;

		if(a == b)
			return 0;
		return (b > a)? 1 : -1;
	}
}