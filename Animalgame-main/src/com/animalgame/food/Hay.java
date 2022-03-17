package com.animalgame.food;

import com.animalgame.abstractmodels.Food;

/**
 * @author tvoul, knuttas
 * Hay extends and implents the abstract class Food
 */
public class Hay extends Food {
    private String foodName = "hay";

    /**
     * Adjust amount owned
     * @param amount
     */
    public void setAmountOwned(int amount){
        this.amountOwned += amount;
    }

    /**
     * Get amount owned
     * @return amount owned
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
