package de.hhn.maXx;

public class Game {
    private static Game instance = null;
    private Game(){}

    public Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }
}
