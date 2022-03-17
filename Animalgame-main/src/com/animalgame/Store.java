package com.animalgame;

import com.animalgame.animals.*;

import java.util.Scanner;

/**
 * A static store where players can can buy/sell animals and only buy food.
 * @author knuttas, tvoul
 */
public class Store {
    static Scanner scan = new Scanner(System.in);

    /**
     * Buy animal
     * @param player receive player to adjust animal list, food owned, money
     */
    public static void buyAnimal(Player player) {
        int dogCost = 100, horseCost = 200, lizardCost = 300, cowCost = 400, sheepCost = 500;
        System.out.println("Pick animal: 1.Dog 100$  2.Horse 200$  3.Lizard 300$  4.Cow 400$  5.Sheep 500$");
        int animalPick = scan.nextInt();
        while (animalPick <= 0 || animalPick > 5) {
            System.out.println("Please enter a number between 1 and 5.");
            animalPick = scan.nextInt();
        }
        System.out.println("How many of chosen animal category do you want?");
        int amountOfAnimals = scan.nextInt();
        while (amountOfAnimals < 1){
            System.out.println("Please input a number larger than 0");
            amountOfAnimals = scan.nextInt();
        }
        if (animalPick == 1) {
            while (amountOfAnimals * dogCost > player.getPlayerMoney()){
                System.out.println("You cannot afford that!");
                System.out.print("Enter new amount of dogs: ");
                amountOfAnimals = scan.nextInt();
            }
        }
        else if (animalPick == 2){
            while (amountOfAnimals * horseCost > player.getPlayerMoney()){
                System.out.println("You cannot afford that!");
                System.out.print("Enter new amount of horses: ");
                amountOfAnimals = scan.nextInt();
            }
        }
        else if (animalPick == 3){
            while (amountOfAnimals * lizardCost > player.getPlayerMoney()){
                System.out.println("You cannot afford that!");
                System.out.print("Enter new amount of lizards: ");
                amountOfAnimals = scan.nextInt();
            }
        }
        else if (animalPick == 4){
            while (amountOfAnimals * cowCost > player.getPlayerMoney()){
                System.out.println("You cannot afford that!");
                System.out.print("Enter new amount of cows: ");
                amountOfAnimals = scan.nextInt();
            }
        }
        else if (animalPick == 5){
            while (amountOfAnimals * sheepCost > player.getPlayerMoney()){
                System.out.println("You cannot afford that!");
                System.out.print("Enter new amount of sheep: ");
                amountOfAnimals = scan.nextInt();
            }
        }


        for (int i = 0; i < amountOfAnimals; i++) {
            System.out.println("Pick gender - 1 male, 2 female");
            int gender = scan.nextInt();
            while (gender <= 0 || gender > 2){
                System.out.println("Please enter 1 or 2.");
                gender = scan.nextInt();
            }
            System.out.println("Enter name");
            String name = scan.next();
            if (animalPick == 1) {
                Dog dog = new Dog(name, "dog", gender, 100);
                player.addAnimalToList(dog);
                player.setPlayerMoney(-dogCost);
            } else if (animalPick == 2) {
                Horse horse = new Horse(name, "horse", gender, 100);
                player.addAnimalToList(horse);
                player.setPlayerMoney(-horseCost);
            } else if (animalPick == 3) {
                Lizard lizard = new Lizard(name,"lizard", gender, 100);
                player.addAnimalToList(lizard);
                player.setPlayerMoney(-lizardCost);
            } else if (animalPick == 4) {
                Cow cow = new Cow(name, "cow", gender, 100);
                player.addAnimalToList(cow);
                player.setPlayerMoney(-cowCost);
            } else if (animalPick == 5) {
                Sheep sheep = new Sheep(name, "sheep", gender, 100);
                player.addAnimalToList(sheep);
                player.setPlayerMoney(-sheepCost);
            }
        }
        System.out.println("Money left: " + player.getPlayerMoney());
        System.out.println("Do you want to buy more animals?");
        System.out.println("1. Buy more animals  2. End turn");
        int buyMore = scan.nextInt();
        while (buyMore < 1 || buyMore > 2){
            System.out.println("Invalid choice, please enter 1 or 2");
            buyMore = scan.nextInt();
        }
        if (buyMore == 1){
            buyAnimal(player);
        }
    }

    /**
     * Sell animal
     * @param player receive player to adjust animal list and money
     */
    public static void sellAnimal(Player player){
        int dogValue = 100, horseValue = 150, lizardValue = 225, cowValue = 300, sheepValue = 375;

        if (player.getAnimalListSize() == 0) {
            System.out.println("You don't own any animals, do you want to buy at the store or end your turn?");
            System.out.println("1. Go to store  2. End turn");
            int goToStore = scan.nextInt();
            if (goToStore == 1) {
                buyAnimal(player);
            }
        } else {
            System.out.println("These are your animals, which one would you like to sell?");
            player.printAnimalList();
            int whichToSell = scan.nextInt();
            while (whichToSell <= 0 || whichToSell > player.getAnimalListSize()){
                System.out.println("Please enter a valid number.");
                whichToSell = scan.nextInt();
            }

            switch (player.getAnimalFromList(whichToSell -1).getRace()){
                case "dog":
                    player.setPlayerMoney(player.getAnimalFromList(whichToSell - 1).getHealth() * dogValue / 100);
                    System.out.println(player.getAnimalFromList(whichToSell - 1).getName() + " sold - gained " + player.getAnimalFromList(whichToSell - 1).getHealth() * dogValue / 100 + "$");
                    System.out.println("New amount of money: " + player.getPlayerMoney());
                    break;
                case "horse":
                    player.setPlayerMoney(player.getAnimalFromList(whichToSell - 1).getHealth() * horseValue / 100);
                    System.out.println(player.getAnimalFromList(whichToSell - 1).getName() + " sold - gained " + player.getAnimalFromList(whichToSell - 1).getHealth() * horseValue / 100 + "$");
                    System.out.println("New amount of money: " + player.getPlayerMoney());
                    break;
                case "lizard":
                    player.setPlayerMoney(player.getAnimalFromList(whichToSell - 1).getHealth() * lizardValue / 100);
                    System.out.println(player.getAnimalFromList(whichToSell - 1).getName() + " sold - gained " + player.getAnimalFromList(whichToSell - 1).getHealth() * lizardValue / 100 + "$");
                    System.out.println("New amount of money: " + player.getPlayerMoney());
                    break;
                case "cow":
                    player.setPlayerMoney(player.getAnimalFromList(whichToSell - 1).getHealth() * cowValue / 100);
                    System.out.println(player.getAnimalFromList(whichToSell - 1).getName() + " sold - gained " + player.getAnimalFromList(whichToSell - 1).getHealth() * cowValue / 100 + "$");
                    System.out.println("New amount of money: " + player.getPlayerMoney());
                    break;
                case "sheep":
                    player.setPlayerMoney(player.getAnimalFromList(whichToSell - 1).getHealth() * sheepValue / 100);
                    System.out.println(player.getAnimalFromList(whichToSell - 1).getName() + " sold - gained " + player.getAnimalFromList(whichToSell - 1).getHealth() * sheepValue / 100 + "$");
                    System.out.println("New amount of money: " + player.getPlayerMoney());
                    break;
            }
            player.removeAnimalFromList(whichToSell -1);
            if (player.getAnimalListSize() == 0){
                System.out.println("You have no more animals, ending turn");
            }
            else if (player.getAnimalListSize() > 0) {
                System.out.println("Do you wish to sell another animal?");
                System.out.println("1. Sell animal  2. End turn");
                int sellAgain = scan.nextInt();
                while (sellAgain < 1 || sellAgain > 2) {
                    System.out.println("Invalid choice, please enter 1 or 2");
                    sellAgain = scan.nextInt();
                }
                if (sellAgain == 1) {
                    sellAnimal(player);
                }
            }
        }
    }

    /**
     * Buy food
     * @param player receive player to adjust food owned and money
     */
    public static void buyFood(Player player){
            int meatCost = 100, hayCost = 50, saladCost = 30;
            System.out.println("What food do you want? 1.Meat 100$/kg  2.Hay 50$/kg  3.Salad 30$/kg");
            int foodChosen = scan.nextInt();
            while (foodChosen <1 || foodChosen > 3){
                System.out.println("Please choose a number between 1 and 3.");
                foodChosen = scan.nextInt();
            }
            System.out.println("How many kgs?");
            int amount = scan.nextInt();
            while (amount < 1){
                System.out.println("Please enter a number larger than 1");
                amount = scan.nextInt();
            }
            if (foodChosen == 1) {
                if (player.getPlayerMoney() > 99) {
                    while (meatCost * amount > player.getPlayerMoney()) {
                        System.out.println("You cannot afford that, try again");
                        System.out.print("Enter a new amount for kgs of meat: ");
                        amount = scan.nextInt();
                    }
                    player.setMeatOwned(amount);
                    player.setPlayerMoney(-amount * meatCost);
                    System.out.println("Money left: " + player.getPlayerMoney());
                }
                else if (player.getPlayerMoney() < 100){
                    System.out.println("You cannot afford that.");
                }
            }
            else if (foodChosen == 2) {
                if (player.getPlayerMoney() > 49) {
                    while (hayCost * amount > player.getPlayerMoney()) {
                        System.out.println("You cannot afford that, try again");
                        System.out.print("Enter a new amount for kgs of hay: ");
                        amount = scan.nextInt();
                    }
                    player.setHayOwned(amount);
                    player.setPlayerMoney(-amount * hayCost);
                    System.out.println("Money left: " + player.getPlayerMoney());
                }
                else if (player.getPlayerMoney() < 50){
                    System.out.println("You cannot afford that.");
                }
            }
            else if (foodChosen == 3) {
                if (player.getPlayerMoney() > 29) {
                    while (saladCost * amount > player.getPlayerMoney()) {
                        System.out.println("You cannot afford that, try again");
                        System.out.print("Enter a new amount for kgs of salad: ");
                        amount = scan.nextInt();
                    }
                    player.setSaladOwned(amount);
                    player.setPlayerMoney(-amount * saladCost);
                    System.out.println("Money left: " + player.getPlayerMoney());
                }
                else if (player.getPlayerMoney() < 30){
                    System.out.println("You cannot afford that.");
                }
            }

            if (player.getPlayerMoney() > 30){
                System.out.println("Do you want to buy more food?");
                System.out.println("1. Buy more food  2. End turn");
                int buyAgain = scan.nextInt();
                while (buyAgain < 1 || buyAgain > 2){
                    System.out.println("Invalid choice, please enter 1 or 2");
                    buyAgain = scan.nextInt();
                }
                if (buyAgain == 1){
                    buyFood(player);
                }
            }
            else if (player.getPlayerMoney() < 30){
                System.out.println("You cannot afford more food, ending turn");
            }
        }
    }

