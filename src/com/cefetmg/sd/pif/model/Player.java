package com.cefetmg.sd.pif.model;

import java.util.List;

public class Player {

    private int id;
    private List<Card> cards;
    private int score;
    private static Deck deck;

    public Player(int id, List<Card> cards) {
        this.id = id;
        this.cards = cards;
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public int getId(){
        return this.id;
    }

}
