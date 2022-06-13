import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck(){
		//Crea una nueva baraja
		this.cards = new ArrayList<Card>();
	
	}
	
	//Se agregan 52 cartas a una baraja
	public void createFullDeck(){
		//Generan cartas
		//Bucle a travez de los palos
		for(Palo cardPalo : Palo.values()){
			//bucle a traves de los valores
			for(Valor cardValue : Valor.values()){
				//agrega la nueva carta
				this.cards.add(new Card(cardPalo,cardValue));
			}
		}
	}
	
	
//Barajear las cartas
public void shuffle(){
	//Se crea un array para mantener las cartas barajeadas temporalmente
	ArrayList<Card> tmpDeck = new ArrayList<Card>();
	//Se escoje al azar una carta de la baraja antigua y lo copia en la nueva.
	Random random = new Random();
	int randomCardIndex = 0;
	int originalSize = this.cards.size();
	for(int i = 0; i<originalSize;i++){
		//gen random num according to int randomNum = rand.nextInt((max - min) + 1) + min;
		randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
		//throw random card into new deck
		tmpDeck.add(this.cards.get(randomCardIndex));
		//remove picked from old deck
		this.cards.remove(randomCardIndex);
	}
	//se reemplaza la baraja barajeada, por la baraja anterior
	this.cards = tmpDeck;
}
	
	
	//Remover carta de la baraja
	public void removeCard(int i){
		this.cards.remove(i);
	}

	//Obtener carta de la baraja
	public Card getCard(int i){
		return this.cards.get(i);
	}
	
	//agrega carta a la baraja
	public void addCard(Card addCard){
		this.cards.add(addCard);
	}
	
	//Se muestra la primera carta de la baraja en juego
	public void draw(Deck barajaV){
		//Se agrega la carta a la baraja
		this.cards.add(barajaV.getCard(0));
		barajaV.removeCard(0);
	}
	
	//Se muestra en pantalla las cartas en juego
	public String toString(){
		String cardListOutput = "";
		int i = 0;
		for(Card aCard : this.cards){
			cardListOutput += "\n" + aCard.toString();
			i++;
		}
		return cardListOutput;
	}
		
	public int deckSize(){
		return this.cards.size();
	}
	
	//Calcular el valor de las cartas
	public int cardsValue(){
		int totalValue = 0;
		int aces = 0;
		for(Card aCard : this.cards){
			switch(aCard.getValue()){
			case DOS: totalValue += 2; break;
			case TRES: totalValue += 3; break;
			case CUATRO: totalValue += 4; break;
			case CINCO: totalValue += 5; break;
			case SEIS: totalValue += 6; break;
			case SIETE: totalValue += 7; break;
			case OCHO: totalValue += 8; break;
			case NUEVE: totalValue += 9; break;
			case DIEZ: totalValue += 10; break;
			case JACK: totalValue += 10; break;
			case REINA: totalValue += 10; break;
			case REY: totalValue += 10; break;
			case AS: aces += 1; break;
			}			
		}
		
		//Determina el valor con los aces
		//Aces pueden valer 1 u 11, si el valor de la mano pasa de 10, la mano estaria en 22, perdiendo.
		for(int i = 0; i < aces; i++){
			if (totalValue > 10){
				//Si el valor de la mano pasa de 10, el As vale 1.
				totalValue += 1;
			}
			else{
				//Si el valor de la mano es menor a 10, el As vale 11.
				totalValue += 11;
			}
		}
		return totalValue;
	}
	
	
}
