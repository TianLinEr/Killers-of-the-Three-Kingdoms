package com.sanguo.dx;

import com.sanguo.ui.GameJFrame;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public interface Equip {
    int horse_plus_neck = 1;
    int horse_minus_neck = 2;
    int weapon_neck = 3;
    int armor_neck = 4;

    //装备+1马
    static void Horse_Plus(Person player1, Person player2) {
        Inter.flash(player1, Data.horse_plus);
        int[] card = player1.getEquipcard();
        if (card[horse_plus_neck] != 0) {
            System.out.println(player1.getName() + "已装备+1马");
            return;
        }
        card[horse_plus_neck] = Data.horse_plus;
        player2.setDistance(player2.getDistance() - 1);
        player1.setEquipcard(card);
        System.out.println(player1.getName() + "装备+1马");
    }

    //装备-1马
    static void Horse_Minus(Person person) {
        Inter.flash(person, Data.horse_minus);
        int[] card = person.getEquipcard();
        if (card[horse_minus_neck] != 0) {
            System.out.println(person.getName() + "已装备-1马");
            return;
        }
        card[horse_minus_neck] = Data.horse_minus;
        person.setDistance(person.getDistance() + 1);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备-1马");
    }

    //装备诸葛连弩
    static void Crossbows(Person person) {
        Inter.flash(person, Data.crossbows);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为诸葛连弩");
        }
        card[weapon_neck] = Data.crossbows;
        person.setEquipDistance(1);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备诸葛连弩");
    }

    //使用诸葛连弩
    static boolean UseCrossbows(Person person) {
        System.out.println(person.getName() + "使用诸葛连弩");
        return false;
    }

    //装备八卦阵
    static void EightDiagramTactics(Person person) {
        Inter.flash(person, Data.eightDiagramTactics);
        int[] card = person.getEquipcard();
        if (card[armor_neck] != 0) {
            System.out.println(person.getName() + "将防具更换为八卦阵");
        }
        card[armor_neck] = Data.eightDiagramTactics;
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备八卦阵");
    }

    //使用八卦阵
    static boolean UseEightDiagramTactics(Person person) {
        int flag = Person.getOneCard() % 2;
        if (flag == 0) {
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "未触发八卦阵";
            GameJFrame.stop++;
            System.out.println(person.getName() + "未触发八卦阵");
            return false;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "触发八卦阵";
            GameJFrame.stop++;
            System.out.println(person.getName() + "触发八卦阵");
        }
        return true;
    }

    //装备仁王盾
    static void Shield(Person person) {
        Inter.flash(person, Data.shield);
        int[] card = person.getEquipcard();
        if (card[armor_neck] != 0) {
            System.out.println(person.getName() + "将防具更换为仁王盾");
        }
        card[armor_neck] = Data.shield;
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备仁王盾");
    }

    //使用仁王盾
    static boolean UseShield(Person person) {
        int flag = Person.getOneCard() % 2;
        if (flag == 1) {
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "未触发仁王盾";
            GameJFrame.stop++;
            System.out.println(person.getName() + "未触发仁王盾");
            return false;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "触发仁王盾";
            GameJFrame.stop++;
            System.out.println(person.getName() + "触发仁王盾");
        }

        return true;
    }

    //装备青虹剑
    static void QingHongJian(Person person) {
        Inter.flash(person, Data.qingHongJian);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为青虹剑");
        }
        card[weapon_neck] = Data.qingHongJian;
        person.setEquipDistance(2);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备青虹剑");
    }

    //使用青虹剑
    static boolean UseQingHongJian(Person person) {
        System.out.println(person.getName() + "使用青虹剑");
        GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "使用青虹剑";
        GameJFrame.stop++;
        return false;
    }

    //装备雌雄双股剑
    static void MaleAndFemaleSword(Person person) {
        Inter.flash(person, Data.maleAndFemaleSword);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为雌雄双股剑");
        }
        card[weapon_neck] = Data.maleAndFemaleSword;
        person.setEquipDistance(2);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备雌雄双股剑");
    }

    //使用雌雄双股剑
    static void UseMaleAndFemaleSword(Person player1, Person player2) {
        if (Person.getOneCard() % 2 == 0) {
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "触发雌雄双股剑";
            GameJFrame.stop++;;
            System.out.println(player2.getName() + "触发雌雄双股剑");
            Scanner scanner = new Scanner(System.in);
            int flag;
            flag = Person.getOneCard() % 2 + 1;
            while (true) {
                switch (flag) {
                    case 1 -> {
                        int[] card1 = player1.getCard();
                        card1[player1.getNumcard()] = Person.getOneCard();
                        player1.setCard(card1);
                        player1.setNumcard(player1.getNumcard() + 1);
                        return;
                    }
                    case 2 -> {
                        int[] card2 = player2.getCard();
                        if (player2.getNumcard() < 1) {
                            flag = 1;
                            if (Objects.equals(player2.getName(), "电脑")){
                                System.out.println("玩家手牌不足，不能选择该选项");
                                GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "玩家手牌不足，不能选择该选项";
                                GameJFrame.stop++;;
                            }
                        } else {
                            if (Objects.equals(player2.getName(), "电脑")) {
                                System.out.println("随机弃一张牌");
                                GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "随机弃一张牌";
                                GameJFrame.stop++;;
                                Inter.flash(player2, card2[new Random().nextInt(player2.getNumcard())]);
                            } else {
                                Random random = new Random();
                                card2[random.nextInt(player2.getNumcard())] = 0;
                                player2.setCard(card2);
                                player2.setNumcard(player2.getNumcard() - 1);
                            }
                            return;
                        }
                    }
                    default -> {
                        System.out.println("没有该选项，请重新输入：");
                        flag = new Random().nextInt()%2+1;
                    }
                }
            }
        } else {
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "未触发雌雄双股剑";
            GameJFrame.stop++;;
            System.out.println(player2.getName() + "未触发雌雄双股剑");
        }
    }

    //装备青龙偃月刀
    static void GreenDragonCrescentBlade(Person person) {
        Inter.flash(person, Data.greenDragonCrescentBlade);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为青龙偃月刀");
        }
        card[weapon_neck] = Data.greenDragonCrescentBlade;
        person.setEquipDistance(3);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备青龙偃月刀");
    }

    //使用青龙偃月刀
    static boolean UseGreenDragonCrescentBlade(Person person) {
        System.out.println(person.getName() + "使用青龙偃月刀");
        GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "使用青龙偃月刀";
        GameJFrame.stop++;;
        return false;
    }

    //装备麒麟弓
    static void KirinBow(Person person) {
        Inter.flash(person, Data.kirinbow);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为麒麟弓");
        }
        card[weapon_neck] = Data.kirinbow;
        person.setEquipDistance(5);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备麒麟弓");
    }

    //使用麒麟弓
    static void UseKirinBow(Person player1, Person player2) {
        if (player2.getEquipcard()[horse_plus_neck] != 0) {
            System.out.println(player1.getName() + "触发麒麟弓");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "触发麒麟弓";
            GameJFrame.stop++;;
            int[] card = player2.getEquipcard();
            card[horse_plus_neck] = 0;
            player2.setEquipcard(card);
        } else if (player2.getEquipcard()[horse_minus_neck] != 0) {
            System.out.println(player1.getName() + "触发麒麟弓");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "触发麒麟弓";
            GameJFrame.stop++;;
            int[] card = player2.getEquipcard();
            card[horse_minus_neck] = 0;
            player2.setEquipcard(card);
        } else{
            System.out.println(player1.getName() + "未触发麒麟弓");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "未触发麒麟弓";
            GameJFrame.stop++;;
        }
    }

    //装备贯石斧
    static void PenetrationCelts(Person person) {
        Inter.flash(person, Data.penetrationCelts);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为贯石斧");
        }
        card[weapon_neck] = Data.penetrationCelts;
        person.setEquipDistance(3);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备贯石斧");
    }

    //使用贯石斧
    static void UsePenetrationCelts(Person player1, Person player2) {
        if (!Objects.equals(player1.getName(), "电脑")) {
            Random random=new Random();
            if (random.nextInt()%2 == 1) {
                if (player1.getNumcard() >= 2) {
                    for (int i = 0; i < 2; i++) {
                        Inter.flash(player1, player1.getCard()[new Random().nextInt(player1.getNumcard())]);
                    }
                    System.out.println(player1.getName() + "使用贯石斧造成一点伤害");
                    GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "使用贯石斧造成一点伤害";
                    GameJFrame.stop++;;
                    player2.setBlood(player2.getBlood() - 1);
                } else {
                    GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "手牌不足，不能发动技能";
                    GameJFrame.stop++;;
                    System.out.println("手牌不足，不能发动技能");
                }
            }
        } else {
            if (player1.getNumcard() >= 2) {
                int[] card = player1.getCard();
                card[player1.getNumcard() - 1] = 0;
                card[player1.getNumcard() - 2] = 0;
                player1.setCard(card);
                player1.setNumcard(player1.getNumcard() - 2);
                System.out.println(player1.getName() + "使用贯石斧造成一点伤害");
                GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "使用贯石斧造成一点伤害";
                GameJFrame.stop++;;
                player2.setBlood(player2.getBlood() - 1);
            }
        }
    }

    //装备丈八长矛
    static void Spears(Person person) {
        Inter.flash(person, Data.spears);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "将武器更换为丈八长矛");
        }
        card[weapon_neck] = Data.spears;
        person.setEquipDistance(3);
        person.setEquipcard(card);
        System.out.println(person.getName() + "装备丈八长矛");
    }

    //使用长矛
    static void UseSpears(Person person) {
        if (person.getNumcard() < 2) {
            System.out.println("手牌不足，不能使用武器技能");
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "手牌不足，不能使用武器技能";
            GameJFrame.stop++;;
        } else {
            Random random=new Random();
            int[] card = person.getCard();
            int len = person.getNumcard();
            for (int i = 0; i < 2; i++) {
                Inter.flash(person, card[random.nextInt(person.getNumcard())]);
            }
            card[len] = Data.sha;
            person.setCard(card);
            person.setNumcard(person.getNumcard() + 1);
            System.out.println(person.getName() + "使用丈八长矛，弃置两种牌，得到一张杀");
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "使用丈八长矛，弃置两种牌，得到一张杀";
            GameJFrame.stop++;;
        }
    }
    //
}
