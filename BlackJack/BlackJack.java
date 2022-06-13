import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String alias;
        int numJugadores;
        int ans;

        do {

            System.out.println("BIENVENIDO A BLACKJACK");
            System.out.println("Empezar a jugar? S=1/N=0");
            ans = entrada.nextInt();

            if (ans == 1) {

                do {
                    System.out.println("Cuantos jugadores van a jugar? (1-4)");
                    numJugadores = entrada.nextInt();
                } while (numJugadores >= 5);

                Player jugadores[] = new Player[numJugadores];
                Deck cartasJugador[] = new Deck[numJugadores];
                Deck baraja = new Deck();
                baraja.createFullDeck();
                baraja.shuffle();

                for (int i = 0; i < numJugadores; i++) {
                    entrada.nextLine();
                    System.out.print("Digite el alias de jugador " + (i + 1) + ": ");
                    alias = entrada.nextLine();

                    jugadores[i] = new Player(alias);
                    cartasJugador[i] = new Deck();
                }

                Deck cartasDealer = new Deck();
                cartasDealer.draw(baraja);
                cartasDealer.draw(baraja);

                for (int i = 0; i < numJugadores; i++) { // Ciclo de turno de cada jugador
                    System.out.println("Turno de jugador " + jugadores[i].getAlias() + " : ");
                    cartasJugador[i].draw(baraja);
                    cartasJugador[i].draw(baraja);
                    while (true) {
                        // Muestra las cartas del jugador
                        System.out.println("Tu mano:" + cartasJugador[i].toString());

                        // Muestra el valor de las cartas
                        System.out.println("Tu mano esta valuada en: " + cartasJugador[i].cardsValue());

                        // Se muestra la mano del dealer
                        System.out.println("Mano del dealer: " + cartasDealer.getCard(0).toString() + " y [Secreta]");

                        if (cartasJugador[i].cardsValue() > 21) {
                            System.out.println(jugadores[i].loser());
                            break;
                        }

                        // Decision del jugador
                        System.out.println("Quieres pedir carta(1) o Plantarte(2)?");
                        int response = entrada.nextInt();

                        // Pedir carta
                        if (response == 1) {
                            cartasJugador[i].draw(baraja);
                            System.out.println(
                                    "Tu mano:" + cartasJugador[i].getCard(cartasJugador[i].deckSize() - 1).toString());
                        }

                        // Plantarse
                        if (response == 2) {
                            System.out.println("\n");
                            break;
                        }

                    }

                }

                // Cartas del dealer
                System.out.println("\n\n\nCartas del dealer: " + cartasDealer.toString());
                System.out.println("Cartas valuadas en: " + cartasDealer.cardsValue());
                if ((cartasDealer.cardsValue() > 21)) {
                    System.out.println("El dealer ha perdido!");
                } else if (cartasDealer.cardsValue() == 21) {
                    System.out.println("El dealer ha hecho blackJack!");
                }

                for (int i = 0; i < numJugadores; i++) {
                    System.out.println("\nJUGADOR " + jugadores[i].getAlias() + ":");
                    // Determina si el jugador perdio por pasarse de 21.
                    if (cartasJugador[i].cardsValue() > 21) {
                        System.out.println("Tu mano esta valuada en: " + cartasJugador[i].cardsValue());
                        System.out.println(jugadores[i].loser());
                    }
                    // Determina si el jugador hizo BlackJack
                    else if (cartasJugador[i].cardsValue() == 21) {
                        System.out.println("HAS HECHO BLACKJACK!");
                        System.out.println(jugadores[i].winner());
                    }
                    // Determina si el dealer gano al jugador.
                    else if ((cartasDealer.cardsValue() > cartasJugador[i].cardsValue())) {
                        System.out.println(
                                "El dealer te gano " + cartasDealer.cardsValue() + " a "
                                        + cartasJugador[i].cardsValue());
                    }
                    // Determina si es empate
                    else if ((cartasDealer.cardsValue() == cartasJugador[i].cardsValue())) {
                        System.out.println("EMPATE CON EL DEALER!");
                    }
                    // Determina que jugador gana
                    else if ((cartasJugador[i].cardsValue() > cartasDealer.cardsValue())) {
                        System.out.println("LE HAS GANADO AL DEALER!" + cartasJugador[i].cardsValue() + " a "
                                + cartasDealer.cardsValue());
                    } else {// Dealer gana
                        System.out.println("DEALER GANA!");
                    }

                }

                System.out.println("\n\nFIN DEL JUEGO\n\n");

            } else {
                System.out.println("Saliendo...");
            }

        } while (ans == 1);

    }
}