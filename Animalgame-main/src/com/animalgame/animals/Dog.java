package com.animalgame.animals;

import com.animalgame.abstractmodels.Animal;
import com.animalgame.abstractmodels.Food;

/**
 * @author tvoul, knuttas
 * Dog extends and implements abstract class Animal
 */
public class Dog extends Animal {
    public Dog(String name, String race, int gender, int health) {
        super(name, race, gender, health);
    }

    /**
     * Feed animal to increase health, if food type is correct
     * @param food receive food to check type
     */
    public void feedHealth(Food food) {
        if (food.getFoodName().equals("meat")) {
            System.out.println("I like meat! My health increased by 10!");
            this.health += 10;
            if (this.health > 100){
                this.health = 100;
            }
            food.setAmountOwned(-1);
        }
        else if (food.getFoodName().equals("hay")){
            System.out.println("I do not like hay!");
        }
        else if (food.getFoodName().equals("salad")){
            System.out.println("I do not like salad!");
        }
    }
}
