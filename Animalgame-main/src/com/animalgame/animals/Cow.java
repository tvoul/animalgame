package com.animalgame.animals;

import com.animalgame.abstractmodels.Animal;
import com.animalgame.abstractmodels.Food;

/**
 * @author tvoul, knuttas
 * Cow extends and implements abstract class Animal
 */
public class Cow extends Animal {
    public Cow(String name, String race, int gender, int health) {
        super(name, race, gender, health);
    }

    /**
     * Feed animal to increase health, if food type is correct
     * @param food receive food to check type
     */
    public void feedHealth(Food food){
        if (food.getFoodName().equals("hay")){
            System.out.println("I like hay! My health increased by 10!");
            this.health += 10;
            if (this.health > 100){
                this.health = 100;
            }
            food.setAmountOwned(-1);
        }
        else if (food.getFoodName().equals("salad")){
            System.out.println("Hmm, not my first choice but i'll take it! My health increased by 5");
            this.health += 5;
            if (this.health > 100){
                this.health = 100;
            }
            food.setAmountOwned(-1);
        }
        else if (food.getFoodName().equals("meat")){
            System.out.println("I don't like meat!");
        }
    }
}
