package com.sanguo.dx;

import com.sanguo.ui.GameJFrame;

import java.util.Objects;
import java.util.Random;

public interface Inter {

    private void countCard(Person person){
        int[] card= person.getCard();
        int len=0;
        for (int j : card) {
            if (j != 0)
                len++;
        }
        person.setNumcard(len);
    }

    //չʾ����
    static void show(Person person) {
        sort(person);
        int[] card = person.getCard();
        System.out.println(person.getName() + "������");
        for (int j : card) {
            switch (j) {
                case Data.sha -> System.out.print("ɱ ");
                case Data.shan -> System.out.print("�� ");
                case Data.tao -> System.out.print("�� ");
                case Data.outOfThinAir -> System.out.print("�������� ");
                case Data.arrows -> System.out.print("����뷢 ");
                case Data.naInvasion -> System.out.print("�������� ");
                case Data.duel -> System.out.print("���� ");
                case Data.pilfering -> System.out.print("˳��ǣ�� ");
                case Data.kickDownTheLadder -> System.out.print("���Ӳ��� ");
                case Data.horse_plus -> System.out.print("+1�� ");
                case Data.horse_minus -> System.out.print("-1�� ");
                case Data.crossbows -> System.out.print("������� ");
                case Data.eightDiagramTactics -> System.out.print("������ ");
                case Data.shield -> System.out.print("������ ");
                case Data.qingHongJian -> System.out.print("��罣 ");
                case Data.maleAndFemaleSword -> System.out.print("����˫�ɽ� ");
                case Data.greenDragonCrescentBlade -> System.out.print("�������µ� ");
                case Data.kirinbow -> System.out.print("���빭 ");
                case Data.penetrationCelts -> System.out.print("��ʯ�� ");
                case Data.spears -> System.out.print("�ɰ˳�ì ");
            }
        }
        System.out.println();
    }

    //���ƣ�AI��
    private static void discardAI(Person person) {
        int[] card = person.getCard();
        if (person.getNumcard() > person.getBlood()) {
            for (int i = person.getBlood(); i < person.getNumcard(); i++) {
                card[i] = 0;
            }
            person.setNumcard(person.getBlood());
            person.setCard(card);
        }
    }

    //����
    private static void sort(Person person) {
        int[] card = person.getCard();
        for (int i = 0; i < card.length; i++) {
            int n = card[i];
            for (int j = i + 1; j < card.length; j++) {
                if (n < card[j]) {
                    n = card[j];
                    card[j] = card[i];
                    card[i] = n;
                }
            }
        }
        for (int i = 0; i < card.length; i++) {
            int n;
            for (int j = i + 1; j < card.length; j++) {
                if (card[j] == Data.tao) {
                    n = card[j];
                    card[j] = card[i];
                    card[i] = n;
                }
            }
        }
        person.setCard(card);
    }

    //����AI
    default void outcardAI(Person player1, Person player2) {
        boolean kills = false;
        sort(player1);
        int[] card = player1.getCard();
        for (int i = 0; card[i] != 0; i++) {
            kills = menu(i, player1, player2, kills);
            countCard(player1);
        }
        System.out.println(player1.getName() + "���ƽ׶�");
        sort(player1);
        discardAI(player1);
    }

    //�������
    default void outcard(Person player1, Person player2) {
        boolean kills = false;
        int n;
        System.out.println(player1.getNumcard());
        System.out.println(player2.getNumcard());
        sort(player1);
        show(player1);
        if (Objects.equals(GameJFrame.done(), "null"))
            return;
        n = GameJFrame.getGame();
        System.out.println(n);
        if (n == 0) {
            System.out.println(player1.getName() + "���ƽ׶�");
            while (true) {
                if (player1.getNumcard() > player1.getBlood())
                    discard(player1);
                else
                    return;
            }
        } else if (n == Data.sha && kills){
            System.out.println(player1.getName() + "�Ѿ�����һ��ɱ");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "�Ѿ�����һ��ɱ";
            GameJFrame.stop++;
        }
        else{
            kills = menu(n - 1, player1, player2, kills);
            countCard(player1);
            sort(player1);
        }
        GameJFrame.did();
        outcard(player1, player2);
    }

    static void discard(Person player1) {
        sort(player1);
        //����
        int[] card = player1.getCard();
        if (player1.getNumcard() > player1.getBlood()) {
            int len = player1.getNumcard();
            for (int i = player1.getBlood(); i < len; i++) {
                System.out.println("��ѡ��һ����");
                if (Objects.equals(GameJFrame.done(), "null"))
                    return;
                int n = GameJFrame.getGame();
                if (n >= 1) {
                    if (flash(player1, card[n - 1])) {
                        System.out.println("δ�ҵ�����,����������");
                    }
                } else
                    System.out.println("���������������");
            }
            GameJFrame.did();
            discard(player1);
        }
    }

    default boolean menu(int n, Person player1, Person player2, Boolean kills) {
        switch (player1.getCard()[n]) {
            case 0 -> System.out.println("������û�������ƣ����������룺");
            case Data.sha -> kills = kill(kills, player1, player2);
            case Data.tao -> recover(player1);
            case Data.outOfThinAir -> OutOfThinAir(player1);
            case Data.arrows -> Arrows(player1, player2);
            case Data.naInvasion -> NaInvasion(player1, player2);
            case Data.duel -> Duel(player1, player2);
            case Data.pilfering -> Pilfering(player1, player2);
            case Data.kickDownTheLadder -> KickDownTheLadder(player1, player2);
            case Data.horse_plus -> Equip.Horse_Plus(player1, player2);
            case Data.horse_minus -> Equip.Horse_Minus(player1);
            case Data.crossbows -> Equip.Crossbows(player1);
            case Data.eightDiagramTactics -> Equip.EightDiagramTactics(player1);
            case Data.shield -> Equip.Shield(player1);
            case Data.qingHongJian -> Equip.QingHongJian(player1);
            case Data.maleAndFemaleSword -> Equip.MaleAndFemaleSword(player1);
            case Data.greenDragonCrescentBlade -> Equip.GreenDragonCrescentBlade(player1);
            case Data.kirinbow -> Equip.KirinBow(player1);
            case Data.penetrationCelts -> Equip.PenetrationCelts(player1);
            case Data.spears -> Equip.Spears(player1);
            case Data.useSkill -> {
                if (player1.getEquipcard()[Equip.weapon_neck] == Data.spears)
                    Equip.UseSpears(player1);
                else
                    System.out.println("��ҵ������޷������������ܣ�");
            }
        }
        return kills;
    }

    //��
    private static void recover(Person person) {
        if (person.getBlood() < 4) {
            flash(person, Data.tao);
            System.out.println(person.getName() + "����һ����,�ָ�һ��Ѫ");
            person.setBlood(person.getBlood() + 1);
        }
    }

    //ɱ
    private static boolean kill(boolean kills, Person player1, Person player2) {
        if (kills)
            return true;
        if (Math.max(player1.getDistance(), player1.getEquipDistance())
                - player2.getDistance() < 0)
            return false;
        flash(player1, Data.sha);
        System.out.println(player1.getName() + "����һ��ɱ");
        if (player2.getEquipcard()[Equip.weapon_neck] == Data.maleAndFemaleSword)
            Equip.UseMaleAndFemaleSword(player1, player2);
        boolean flag = false;
        if (player2.getEquipcard()[Equip.armor_neck] != 0) {
            if (player1.getEquipcard()[Equip.weapon_neck] == Data.qingHongJian)
                flag = Equip.UseQingHongJian(player1);
            else
                flag = switch (player2.getEquipcard()[Equip.armor_neck]) {
                    case Data.eightDiagramTactics -> Equip.UseEightDiagramTactics(player2);
                    case Data.shield -> Equip.UseShield(player2);
                    default -> false;
                };
        }
        if (flag) {
            if (player1.getEquipcard()[Equip.weapon_neck] == Data.greenDragonCrescentBlade) {
                kills = Equip.UseGreenDragonCrescentBlade(player1);
                return kills;
            }
        } else {
            flag = flash(player2, Data.shan);
            if (flag) {
                player2.setBlood(player2.getBlood() - 1);
                System.out.println(player2.getName() + "δ��������һ��Ѫ");
                GameJFrame.cardtext[GameJFrame.stop]=player2.getName()+"δ��������һ��Ѫ";
                GameJFrame.stop++;
                if (player1.getEquipcard()[Equip.weapon_neck] == Data.kirinbow) {
                    Equip.UseKirinBow(player1, player2);
                }
            } else {
                System.out.println(player2.getName() + "����һ����");
                GameJFrame.cardtext[GameJFrame.stop]=player2.getName()+"����һ����";
                GameJFrame.stop++;
                if (player1.getEquipcard()[Equip.weapon_neck] == Data.greenDragonCrescentBlade) {
                    kills = Equip.UseGreenDragonCrescentBlade(player1);
                    return kills;
                }
                if (player1.getEquipcard()[Equip.weapon_neck] == Data.penetrationCelts) {
                    Equip.UsePenetrationCelts(player1, player2);
                }
            }
            if (player1.getEquipcard()[Equip.weapon_neck] == Data.crossbows)
                return Equip.UseCrossbows(player1);
        }
        return true;
    }

    //��������ɱ����
    static boolean flash(Person person, int a) {
        int[] card = person.getCard();
        if (a == 0)
            return true;
        for (int i = 0; i < card.length; i++) {
            if (card[i] == a) {
                card[i] = 0;
                person.setNumcard(person.getNumcard() - 1);
                person.setCard(card);
                sort(person);
                return false;
            }
        }
        return true;
    }

    //��������
    private static void OutOfThinAir(Person person) {
        System.out.println(person.getName() + "ʹ����������,��������");
        flash(person, Data.outOfThinAir);
        person.addcard();
    }

    //����뷢
    private static void Arrows(Person player1, Person player2) {
        flash(player1, Data.arrows);
        System.out.println(player1.getName() + "ʹ������뷢");
        if (flash(player2, Data.shan)) {
            if (player2.getEquipcard()[Equip.armor_neck] != 0) {
                Equip.UseEightDiagramTactics(player2);
                return;
            }
            player2.setBlood(player1.getBlood() - 1);
            System.out.println(player2.getName() + "δʹ��������һ��Ѫ");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "δʹ��������һ��Ѫ";
            GameJFrame.stop++;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "��ʹ����";
            GameJFrame.stop++;
            System.out.println(player2.getName() + "��ʹ����");
        }

    }

    //��������
    private static void NaInvasion(Person player1, Person player2) {
        flash(player1, Data.naInvasion);
        System.out.println(player1.getName() + "ʹ����������");
        if (flash(player2, Data.sha)) {
            player2.setBlood(player2.getBlood() - 1);
            System.out.println(player2.getName() + "δʹ��ɱ����һ��Ѫ");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "δʹ��ɱ����һ��Ѫ";
            GameJFrame.stop++;
        } else{
            System.out.println(player2.getName() + "��ʹ��ɱ");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "��ʹ��ɱ";
            GameJFrame.stop++;
        }

    }

    //����
    private static void Duel(Person player1, Person player2) {
        flash(player1, Data.duel);
        System.out.println(player1.getName() + "ʹ�þ���");
        while (true) {
            if (flash(player2, Data.sha)) {
                player2.setBlood(player2.getBlood() - 1);
                System.out.println(player2.getName() + "δʹ��ɱ����һ��Ѫ");
                GameJFrame.cardtext[GameJFrame.stop]=player2.getName()+"δʹ��ɱ����һ��Ѫ";
                GameJFrame.stop++;
                break;
            } else
                System.out.println(player2.getName() + "��ʹ��ɱ");
            if (flash(player1, Data.sha)) {
                player1.setBlood(player1.getBlood() - 1);
                System.out.println(player1.getName() + "δʹ��ɱ����һ��Ѫ");
                GameJFrame.cardtext[GameJFrame.stop]=player1.getName()+"δʹ��ɱ����һ��Ѫ";
                GameJFrame.stop++;
                break;
            } else
                System.out.println(player1.getName() + "��ʹ��ɱ");
        }
    }

    //˳��ǣ��
    private static void Pilfering(Person player1, Person player2) {
        if (Math.max(player1.getDistance(), player1.getEquipDistance())
                - player2.getDistance() < 0)
            return;
        sort(player1);
        sort(player2);
        int[] card1 = player1.getCard();
        int[] card2 = player2.getCard();
        if (player2.getNumcard() > 0) {
            flash(player1, Data.pilfering);
            System.out.println(player1.getName() + "ʹ��˳��ǣ��");
            Random random = new Random();
            int x = random.nextInt(player2.getNumcard());
            card1[player1.getNumcard()] = card2[x];
            card2[x] = 0;
            player2.setCard(card2);
            player2.setNumcard(player2.getNumcard() - 1);
            player1.setCard(card1);
            player1.setNumcard(player1.getNumcard() + 1);
            System.out.println(player1.getName() + "���" + player2.getName() + "һ������");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "���" + player2.getName() + "һ������";
            GameJFrame.stop++;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "û������";
            GameJFrame.stop++;
            System.out.println(player2.getName() + "û������");
        }
    }

    //���Ӳ���
    private static void KickDownTheLadder(Person player1, Person player2) {
        sort(player2);
        if (player2.getNumcard() > 0) {
            flash(player1, Data.kickDownTheLadder);
            System.out.println(player1.getName() + "ʹ�ù��Ӳ���");
            Random random = new Random();
            flash(player2, random.nextInt(player2.getNumcard()));
            System.out.println(player2.getName() + "������һ����");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "������һ����";
            GameJFrame.stop++;
        } else{
            System.out.println(player2.getName() + "û������");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "û������";
            GameJFrame.stop++;
        }
    }

    //
}