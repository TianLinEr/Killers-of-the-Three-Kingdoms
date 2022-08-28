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

    //װ��+1��
    static void Horse_Plus(Person player1, Person player2) {
        Inter.flash(player1, Data.horse_plus);
        int[] card = player1.getEquipcard();
        if (card[horse_plus_neck] != 0) {
            System.out.println(player1.getName() + "��װ��+1��");
            return;
        }
        card[horse_plus_neck] = Data.horse_plus;
        player2.setDistance(player2.getDistance() - 1);
        player1.setEquipcard(card);
        System.out.println(player1.getName() + "װ��+1��");
    }

    //װ��-1��
    static void Horse_Minus(Person person) {
        Inter.flash(person, Data.horse_minus);
        int[] card = person.getEquipcard();
        if (card[horse_minus_neck] != 0) {
            System.out.println(person.getName() + "��װ��-1��");
            return;
        }
        card[horse_minus_neck] = Data.horse_minus;
        person.setDistance(person.getDistance() + 1);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ��-1��");
    }

    //װ���������
    static void Crossbows(Person person) {
        Inter.flash(person, Data.crossbows);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ�������");
        }
        card[weapon_neck] = Data.crossbows;
        person.setEquipDistance(1);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ���������");
    }

    //ʹ���������
    static boolean UseCrossbows(Person person) {
        System.out.println(person.getName() + "ʹ���������");
        return false;
    }

    //װ��������
    static void EightDiagramTactics(Person person) {
        Inter.flash(person, Data.eightDiagramTactics);
        int[] card = person.getEquipcard();
        if (card[armor_neck] != 0) {
            System.out.println(person.getName() + "�����߸���Ϊ������");
        }
        card[armor_neck] = Data.eightDiagramTactics;
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ��������");
    }

    //ʹ�ð�����
    static boolean UseEightDiagramTactics(Person person) {
        int flag = Person.getOneCard() % 2;
        if (flag == 0) {
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "δ����������";
            GameJFrame.stop++;
            System.out.println(person.getName() + "δ����������");
            return false;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "����������";
            GameJFrame.stop++;
            System.out.println(person.getName() + "����������");
        }
        return true;
    }

    //װ��������
    static void Shield(Person person) {
        Inter.flash(person, Data.shield);
        int[] card = person.getEquipcard();
        if (card[armor_neck] != 0) {
            System.out.println(person.getName() + "�����߸���Ϊ������");
        }
        card[armor_neck] = Data.shield;
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ��������");
    }

    //ʹ��������
    static boolean UseShield(Person person) {
        int flag = Person.getOneCard() % 2;
        if (flag == 1) {
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "δ����������";
            GameJFrame.stop++;
            System.out.println(person.getName() + "δ����������");
            return false;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "����������";
            GameJFrame.stop++;
            System.out.println(person.getName() + "����������");
        }

        return true;
    }

    //װ����罣
    static void QingHongJian(Person person) {
        Inter.flash(person, Data.qingHongJian);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ��罣");
        }
        card[weapon_neck] = Data.qingHongJian;
        person.setEquipDistance(2);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ����罣");
    }

    //ʹ����罣
    static boolean UseQingHongJian(Person person) {
        System.out.println(person.getName() + "ʹ����罣");
        GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "ʹ����罣";
        GameJFrame.stop++;
        return false;
    }

    //װ������˫�ɽ�
    static void MaleAndFemaleSword(Person person) {
        Inter.flash(person, Data.maleAndFemaleSword);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ����˫�ɽ�");
        }
        card[weapon_neck] = Data.maleAndFemaleSword;
        person.setEquipDistance(2);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ������˫�ɽ�");
    }

    //ʹ�ô���˫�ɽ�
    static void UseMaleAndFemaleSword(Person player1, Person player2) {
        if (Person.getOneCard() % 2 == 0) {
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "��������˫�ɽ�";
            GameJFrame.stop++;;
            System.out.println(player2.getName() + "��������˫�ɽ�");
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
                            if (Objects.equals(player2.getName(), "����")){
                                System.out.println("������Ʋ��㣬����ѡ���ѡ��");
                                GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "������Ʋ��㣬����ѡ���ѡ��";
                                GameJFrame.stop++;;
                            }
                        } else {
                            if (Objects.equals(player2.getName(), "����")) {
                                System.out.println("�����һ����");
                                GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "�����һ����";
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
                        System.out.println("û�и�ѡ����������룺");
                        flag = new Random().nextInt()%2+1;
                    }
                }
            }
        } else {
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "δ��������˫�ɽ�";
            GameJFrame.stop++;;
            System.out.println(player2.getName() + "δ��������˫�ɽ�");
        }
    }

    //װ���������µ�
    static void GreenDragonCrescentBlade(Person person) {
        Inter.flash(person, Data.greenDragonCrescentBlade);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ�������µ�");
        }
        card[weapon_neck] = Data.greenDragonCrescentBlade;
        person.setEquipDistance(3);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ���������µ�");
    }

    //ʹ���������µ�
    static boolean UseGreenDragonCrescentBlade(Person person) {
        System.out.println(person.getName() + "ʹ���������µ�");
        GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "ʹ���������µ�";
        GameJFrame.stop++;;
        return false;
    }

    //װ�����빭
    static void KirinBow(Person person) {
        Inter.flash(person, Data.kirinbow);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ���빭");
        }
        card[weapon_neck] = Data.kirinbow;
        person.setEquipDistance(5);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ�����빭");
    }

    //ʹ�����빭
    static void UseKirinBow(Person player1, Person player2) {
        if (player2.getEquipcard()[horse_plus_neck] != 0) {
            System.out.println(player1.getName() + "�������빭");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "�������빭";
            GameJFrame.stop++;;
            int[] card = player2.getEquipcard();
            card[horse_plus_neck] = 0;
            player2.setEquipcard(card);
        } else if (player2.getEquipcard()[horse_minus_neck] != 0) {
            System.out.println(player1.getName() + "�������빭");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "�������빭";
            GameJFrame.stop++;;
            int[] card = player2.getEquipcard();
            card[horse_minus_neck] = 0;
            player2.setEquipcard(card);
        } else{
            System.out.println(player1.getName() + "δ�������빭");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "δ�������빭";
            GameJFrame.stop++;;
        }
    }

    //װ����ʯ��
    static void PenetrationCelts(Person person) {
        Inter.flash(person, Data.penetrationCelts);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ��ʯ��");
        }
        card[weapon_neck] = Data.penetrationCelts;
        person.setEquipDistance(3);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ����ʯ��");
    }

    //ʹ�ù�ʯ��
    static void UsePenetrationCelts(Person player1, Person player2) {
        if (!Objects.equals(player1.getName(), "����")) {
            Random random=new Random();
            if (random.nextInt()%2 == 1) {
                if (player1.getNumcard() >= 2) {
                    for (int i = 0; i < 2; i++) {
                        Inter.flash(player1, player1.getCard()[new Random().nextInt(player1.getNumcard())]);
                    }
                    System.out.println(player1.getName() + "ʹ�ù�ʯ�����һ���˺�");
                    GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "ʹ�ù�ʯ�����һ���˺�";
                    GameJFrame.stop++;;
                    player2.setBlood(player2.getBlood() - 1);
                } else {
                    GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "���Ʋ��㣬���ܷ�������";
                    GameJFrame.stop++;;
                    System.out.println("���Ʋ��㣬���ܷ�������");
                }
            }
        } else {
            if (player1.getNumcard() >= 2) {
                int[] card = player1.getCard();
                card[player1.getNumcard() - 1] = 0;
                card[player1.getNumcard() - 2] = 0;
                player1.setCard(card);
                player1.setNumcard(player1.getNumcard() - 2);
                System.out.println(player1.getName() + "ʹ�ù�ʯ�����һ���˺�");
                GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "ʹ�ù�ʯ�����һ���˺�";
                GameJFrame.stop++;;
                player2.setBlood(player2.getBlood() - 1);
            }
        }
    }

    //װ���ɰ˳�ì
    static void Spears(Person person) {
        Inter.flash(person, Data.spears);
        int[] card = person.getEquipcard();
        if (card[weapon_neck] != 0) {
            System.out.println(person.getName() + "����������Ϊ�ɰ˳�ì");
        }
        card[weapon_neck] = Data.spears;
        person.setEquipDistance(3);
        person.setEquipcard(card);
        System.out.println(person.getName() + "װ���ɰ˳�ì");
    }

    //ʹ�ó�ì
    static void UseSpears(Person person) {
        if (person.getNumcard() < 2) {
            System.out.println("���Ʋ��㣬����ʹ����������");
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "���Ʋ��㣬����ʹ����������";
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
            System.out.println(person.getName() + "ʹ���ɰ˳�ì�����������ƣ��õ�һ��ɱ");
            GameJFrame.cardtext[GameJFrame.stop]=person.getName() + "ʹ���ɰ˳�ì�����������ƣ��õ�һ��ɱ";
            GameJFrame.stop++;;
        }
    }
    //
}
