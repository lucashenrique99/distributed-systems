package com.cefetmg.sd.pif.model;

import com.cefetmg.sd.pif.util.CardUtils;

import java.util.Objects;

public class Card {

    private int code;
    private String name;

    public Card(int code) {
        this.code = code;
        this.name = CardUtils.getName(this.code);
    }

    public int getCode(){
        return this.code;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return code == card.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
