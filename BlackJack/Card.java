public class Card {

	//Atributos
	private Palo palo;
	private Valor valor;
	
	//Constructor
	public Card(Palo palo, Valor valor){
		this.palo = palo;
		this.valor = valor;
	}
	
	public String toString(){
		return this.valor.toString() + "-"+ this.palo.toString();
	}
	
	public Valor getValue(){
		return this.valor;
	}
	
}
