package org.example;


import java.util.Random;



class Character {
    private int power;
    private int hp;

    public void kick(Character c){
    int actual_hp = c.getHp();
    if (actual_hp < power){
        c.setHp(0);
    }
    else{
        c.setHp(actual_hp - power);
    }
    }
    public boolean isAlive(){
      if (hp == 0){
          return false;
      }
      else{
          return true;
      }
    }
    public int getPower() {
        return power;
    }
    public int getHp() {
        return hp;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setHp(int hp) {
        if (hp > 0) {
            this.hp = hp;
        }
        else{
            this.hp = 0;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' +
                "hp=" + hp +
                ", power=" + power +
                '}';
    }
}
class Hobbit extends Character {
    public Hobbit() {
        super.setHp(3);
        super.setPower(0);
    }
//    void kick(Character c) {
//        toCry()
//    }
}
class Elf extends Character{
    public Elf() {
        super.setHp(10);
        super.setPower(10);
    }
    @Override
    public void kick(Character c) {
        super.kick(c);
        if (c.getHp() > super.getPower()){
            super.setPower(super.getPower() - 1);
        }
    }


}

class King extends Character{
    public King(){
        Random rand = new Random();
        int hp = 5 + rand.nextInt(11);
        int power = 5 + rand.nextInt(11);
        super.setHp(hp);
        super.setPower(power);
    }
//    void kick(Character c) {
//        decrease number of hp of the enemy by random
//        number which will be in the range of his power
//    }
}
class Knight extends Character{
    public Knight() {
        Random rand = new Random();
        int hp = 2 + rand.nextInt(11);
        int power = 5 + rand.nextInt(8);
        super.setHp(hp);
        super.setPower(power);
    }
    public void kick(Character c) {
        if (c instanceof King) {
        }else {
            super.kick(c);
        }
    }
}
class CharacterFactory {
    private Character character;
    Character createCharacter() {
        Random rand = new Random();
        int value = rand.nextInt(4);
        if (value == 0){
            character = new Hobbit();
        } else if (value == 1) {
            character = new Elf();
        } else if (value == 2) {
            character = new Knight();
        }else {
            character = new King();
        }
        return character;
    }
}
class GameManager{
   void fight(Character c1, Character c2) {
           while (c1.isAlive() && c2.isAlive()){
               System.out.println(c1);
               System.out.println(c2);
               if (c1.isAlive()) {
                   c1.kick(c2);
                   System.out.println(c1);
                   System.out.println(c2);
               }
               if (c2.isAlive()) {
                   c2.kick(c1);
                   System.out.println(c1);
                   System.out.println(c2);
               }
           }
           }
}


public class Main {
    public static void main(String[] args) {
        CharacterFactory fac = new CharacterFactory();
        GameManager game = new GameManager();
        Character c1 = fac.createCharacter();
        Character c2 = fac.createCharacter();
        game.fight(c1,c2);
    }
}