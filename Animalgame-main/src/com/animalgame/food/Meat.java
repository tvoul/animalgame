package com.animalgame.food;

import com.animalgame.abstractmodels.Food;

/**
 * @author tvoul, knuttas
 * Meat extends and implents the abstract class Food
 */

public class Meat extends Food {
    private String foodName = "meat";

    /**
     * Set meat amount owned
     * @param amount
     */
    public void setAmountOwned(int amount){
        this.amountOwned += amount;
    }

    /**
     * Get amount meat owned
     * @return amount meat owned
     */
    public int getAmountOwned(){
        return this.amountOwned;
    }

    /**
     * Get food name
     * @return food name
     */
    public String getFoodName(){
        return this.foodName;
    }
}
