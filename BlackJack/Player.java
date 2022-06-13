public class Player{
    //Atributos
    private String alias;

    //Constructor
    public Player(String alias){
        this.alias=alias;
    }

    public String getAlias(){
        return alias;
    }

    public void setAlias(String alias){
        this.alias=alias;
    }

    public String winner(){
        return "HAS GANADO LA PARTIDA! FELICIDADES!\n\n";
    }

    public String loser(){
        return "HAS PERDIDO LA PARTIDA!\n\n";
    }

}