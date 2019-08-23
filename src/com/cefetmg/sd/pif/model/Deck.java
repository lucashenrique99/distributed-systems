package com.cefetmg.sd.pif.model;

import java.util.*;

public class Deck {

    private Queue<Card> availableCards;
    private Stack<Card> discardedCards;

    public Deck() {
        this.availableCards = new LinkedList<>();
        for (int i = 1; i < 53; i++) {
            this.availableCards.add(new Card(i));
        }
        this.discardedCards = new Stack<>();

        this.init();
    }

    public void init() {
        this.discardedCards.addAll(this.availableCards);
        Collections.shuffle(this.discardedCards);

        this.availableCards.clear();
        this.availableCards.addAll(this.discardedCards);

        this.discardedCards.clear();
    }

    public void discardedCards(Card discarded){
        if (discarded != null) {
            this.discardedCards.add(discarded);
        }
    }

    public List<Card> initPlayerCards() throws DeckException{
        List<Card> cards = new ArrayList<>(3);
        for (int i = 1; i <= 3; i++) {
            cards.add(this.availableCards.poll());
        }
        return cards;
    }

}
