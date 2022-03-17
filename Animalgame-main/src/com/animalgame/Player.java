package com.animalgame;

import com.animalgame.abstractmodels.Animal;
import com.animalgame.abstractmodels.Food;
import com.animalgame.food.Hay;
import com.animalgame.food.Meat;
import com.animalgame.food.Salad;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author tvoul, knuttas
 * Stores player information (name, money, animals, food) and has methods to return and adjust these values/list
 */

public class Player {
    private String name;
    private int money;
    private ArrayList<Animal> animalsOwned = new ArrayList<>();
    private Food hay = new Hay();
    private Food meat = new Meat();
    private Food salad = new Salad();

    Scanner scan = new Scanner(System.in);

    public Player(String name, int money){
        this.name = name;
        this.money = money;
    }

    /**
     * Get player name
     * @return player name
     */
    public String getPlayerName(){
        return this.name;
    }

    /**
     * Get player money
     * @return player money
     */
    public int getPlayerMoney() { return this.money; }

    /**
     * Set player money
     * @param amount amount of money
     */
    public void setPlayerMoney(int amount) {
        this.money += amount;
    }

    /**
     * Add animal to players animal list
     * @param animal animal to add
     */
    public void addAnimalToList(Animal animal){
        animalsOwned.add(animal);
    }

    /**
     *
     * @param index
     */
    public void removeAnimalFromList(int index){
        animalsOwned.remove(index);
    }

    /**
     * Get animal list size
     * @return animal list size
     */
    public int getAnimalListSize(){
        return animalsOwned.size();
    }

    /**
     * Get animal from list
     * @param index send place in list
     * @return animal at this index
     */
    public Animal getAnimalFromList(int index){
        return animalsOwned.get(index);
    }

    /**
     * Print animal list
     */
    public void printAnimalList(){
        int counter = 1;
        for (Animal x : animalsOwned){
            System.out.println(counter + ". " + x.getName() + " " + x.getRace() + " " + (x.getGender() == 1 ? "male" : "female") + " " + x.getHealth() + "% health");
            counter++;
        }
    }

    /**
     * Prints the animal list at the start of a round and depreciates health.
     */

    public void printAnimalListAndDepreciateHealth(){
        int counter = 1;
        for (Animal x : animalsOwned){
            int healthLost = x.depreciateHealth();
            if (x.getHealth() > 0){
                System.out.println(counter + ". " + x.getName() + " - "+ (x.getGender() == 1 ? "male" : "female") + " " + x.getRace() + " " + x.getHealth() + "% health (" + healthLost + "% lost since last round)");
                counter++;
            }
        }
    }

    /**
     * Print breed - used in pairing for example
     */
    public void printBreed(){
        int counter =1;
        for (Animal x : animalsOwned){
            System.out.println(counter + ". " + x.getRace() + " " + x.getName() + " " + (x.getGender() == 1 ? "male" : "female"));
            counter++;
        }
    }

    /**
     * Feed animals you own
     */
    public void feedAnimal() {
        int counter = 1;
        for (Animal x : animalsOwned) {
            System.out.println(counter + ". " + x.getName() + " " + x.getHealth() + "% health");
            counter++;
        }
        if (meat.getAmountOwned() == 0 && hay.getAmountOwned() == 0 && salad.getAmountOwned() == 0) {
            System.out.println("You have no food. Do you want to go to the store to buy?");
            System.out.println("1. Go to store  2. End turn");
            int goToStore = scan.nextInt();
            if (goToStore == 1) {
                Store.buyFood(this);
            }
        } else {
            System.out.println("Please choose which animal to feed");
            int animalToFeed = scan.nextInt();
            while (animalToFeed <= 0 || animalToFeed > animalsOwned.size()) {
                System.out.println("Please choose a valid number.");
                animalToFeed = scan.nextInt();
            }
            System.out.println("Please choose which food:");
            System.out.println("Meat: " + meat.getAmountOwned() + " kgs owned.");
            System.out.println("Hay: " + hay.getAmountOwned() + " kgs owned.");
            System.out.println("Salad: " + salad.getAmountOwned() + " kgs owned.");
            System.out.println("1.Meat 2.Hay 3.Salad");
            int foodChosen = scan.nextInt();
            while (foodChosen <= 0 || foodChosen > 3) {
                System.out.println("Please enter a number between 1 and 3.");
                foodChosen = scan.nextInt();
            }
            if (foodChosen == 1 && meat.getAmountOwned() > 0 && animalsOwned.get(animalToFeed - 1).getHealth() < 100) {
                animalsOwned.get(animalToFeed - 1).feedHealth(meat);
            } else if (foodChosen == 2 && hay.getAmountOwned() > 0 && animalsOwned.get(animalToFeed - 1).getHealth() < 100) {
                animalsOwned.get(animalToFeed - 1).feedHealth(hay);
            } else if (foodChosen == 3 && salad.getAmountOwned() > 0 && animalsOwned.get(animalToFeed - 1).getHealth() < 100) {
                animalsOwned.get(animalToFeed - 1).feedHealth(salad);
            } else {
                System.out.println("Invalid choice, not enough amount of chosen food or animal health already 100%.");
            }

            if (hay.getAmountOwned() >= 1 || salad.getAmountOwned() >= 1 || meat.getAmountOwned() >= 1) {
                System.out.println("Do you want to feed your animal again or feed another animal?");
                System.out.println("1. Feed again  2. End turn");
                int feedAgain = scan.nextInt();
                while (feedAgain < 1 || feedAgain > 2) {
                    System.out.println("Invalid choice, please enter 1 or 2");
                    feedAgain = scan.nextInt();
                }
                if (feedAgain == 1) {
                    feedAnimal();
                }
            }
            else if (hay.getAmountOwned() < 1 && salad.getAmountOwned() < 1 && meat.getAmountOwned() < 1){
                System.out.println("You are out of food, ending turn");
            }
        }
    }

    /**
     * Adjust amount meat owned
     * @param amount of meat
     */
    public void setMeatOwned(int amount){
        meat.setAmountOwned(amount);
    }

    /**
     * Adjust amount hay owned
     * @param amount of hay
     */
    public void setHayOwned(int amount){
        hay.setAmountOwned(amount);
    }

    /**
     * Adjust amount salad owned
     * @param amount of salad
     */
    public void setSaladOwned(int amount){
        salad.setAmountOwned(amount);
    }

    /**
     * Returns the amount of meat owned
     * @return meat amount owned
     */
    public int getMeatOwned(){ return meat.getAmountOwned(); }

    /**
     * Returns the amount of hay owned
     * @return hay amount owned
     */
    public int getHayOwned(){ return hay.getAmountOwned(); }

    /**
     * Returns the amount of salad owned
     * @return salad amount owned
     */
    public int getSaladOwned(){ return salad.getAmountOwned(); }



}
