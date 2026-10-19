package model;

public class TehnickiProizvod extends Proizvod{

	private String tehnickeOsobine;
	private int garancija;
	private String uputstvo;
	
	public TehnickiProizvod(){
		super();
	}
	public TehnickiProizvod(int proizvodID, String naziv, double cena, String tehnickeOsobine, int garancija, String uputstvo) {
		super(proizvodID, naziv, cena);
		this.tehnickeOsobine = tehnickeOsobine;
		this.garancija = garancija;
		this.uputstvo = uputstvo;
	}
	public int getGarancija() {
		return garancija;
	}
	public void setGarancija(int garancija) {
		this.garancija = garancija;
	}
	public String getTehnickeOsobine() {
		return tehnickeOsobine;
	}
	public void setTehnickeOsobine(String tehnickeOsobine) {
		this.tehnickeOsobine = tehnickeOsobine;
	}
	public String getUputstvo() {
		return uputstvo;
	}
	public void setUputstvo(String uputstvo) {
		this.uputstvo = uputstvo;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ ", Tehnicke osobine: "+tehnickeOsobine+", Garancija: "+garancija+", Uputstvo: "+uputstvo;
	}
}
