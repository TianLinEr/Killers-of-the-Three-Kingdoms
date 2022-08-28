package com.sanguo.dx;

import java.util.Random;

public abstract class Person {
    private String name;
    private int blood;
    private int numcard;
    private int[] card = new int[10];
    private int[] equipcard=new int[5];
    private static int[] cards = new int[1000];
    private static int count = 0;
    private static int distance=1;
    private static int equipdistance=0;

    //得到一张牌用作判断
    public static int getOneCard(){
        count++;
        return cards[count-1];
    }

    //和局
    public static boolean DrawnGame() {
        if(count>995){
            System.out.println("牌库全部使用完，和局");
            return true;
        }
        return false;
    }

    //摸牌
    public void addcard() {
        card[numcard] = cards[count];
        card[numcard+1] = cards[count+1];
        numcard+=2;
        count+=2;
    }

    //洗牌
    public static void pushcard() {
        Random rand = new Random();
        for (int i = 0; i < 500; i++) {
            cards[i]=rand.nextInt(3) + 1;
        }//基本牌
        for (int i = 500; i < 750; i++) {
            cards[i]=rand.nextInt(6) + 4;
        }//锦囊牌
        for (int i = 750; i < 1000; i++) {
            cards[i]=rand.nextInt(10) + 101;
        }//装备牌
        for (int i = 0; i < 1000; i++) {
            int card=rand.nextInt(1000);
            int temp=cards[i];
            cards[i]=cards[card];
            cards[card]=temp;
        }
    }

    public Person(String name, int blood, int numcard) {
        this.name = name;
        this.blood = blood;
        this.numcard = numcard;
        for (int i = 0; i < numcard; i++) {
            card[i] = cards[count];
            count++;
        }
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getNumcard() {
        return numcard;
    }

    public void setNumcard(int numcard) {
        this.numcard = numcard;
    }

    public int[] getCard() {
        return card;
    }

    public void setCard(int[] card) {
        this.card = card;
    }

    public int[] getEquipcard() {
        return equipcard;
    }

    public void setEquipcard(int[] equipcard) {
        this.equipcard = equipcard;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getEquipDistance() {
        return equipdistance;
    }

    public void setEquipDistance(int equipdistance) {
        this.distance = equipdistance;
    }
}
