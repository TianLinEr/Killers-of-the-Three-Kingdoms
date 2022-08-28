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

    //展示手牌
    static void show(Person person) {
        sort(person);
        int[] card = person.getCard();
        System.out.println(person.getName() + "的手牌");
        for (int j : card) {
            switch (j) {
                case Data.sha -> System.out.print("杀 ");
                case Data.shan -> System.out.print("闪 ");
                case Data.tao -> System.out.print("桃 ");
                case Data.outOfThinAir -> System.out.print("无中生有 ");
                case Data.arrows -> System.out.print("万箭齐发 ");
                case Data.naInvasion -> System.out.print("南蛮入侵 ");
                case Data.duel -> System.out.print("决斗 ");
                case Data.pilfering -> System.out.print("顺手牵羊 ");
                case Data.kickDownTheLadder -> System.out.print("过河拆桥 ");
                case Data.horse_plus -> System.out.print("+1马 ");
                case Data.horse_minus -> System.out.print("-1马 ");
                case Data.crossbows -> System.out.print("诸葛连弩 ");
                case Data.eightDiagramTactics -> System.out.print("八卦阵 ");
                case Data.shield -> System.out.print("仁王盾 ");
                case Data.qingHongJian -> System.out.print("青虹剑 ");
                case Data.maleAndFemaleSword -> System.out.print("雌雄双股剑 ");
                case Data.greenDragonCrescentBlade -> System.out.print("青龙偃月刀 ");
                case Data.kirinbow -> System.out.print("麒麟弓 ");
                case Data.penetrationCelts -> System.out.print("贯石斧 ");
                case Data.spears -> System.out.print("丈八长矛 ");
            }
        }
        System.out.println();
    }

    //弃牌（AI）
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

    //排序
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

    //出牌AI
    default void outcardAI(Person player1, Person player2) {
        boolean kills = false;
        sort(player1);
        int[] card = player1.getCard();
        for (int i = 0; card[i] != 0; i++) {
            kills = menu(i, player1, player2, kills);
            countCard(player1);
        }
        System.out.println(player1.getName() + "弃牌阶段");
        sort(player1);
        discardAI(player1);
    }

    //出牌玩家
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
            System.out.println(player1.getName() + "弃牌阶段");
            while (true) {
                if (player1.getNumcard() > player1.getBlood())
                    discard(player1);
                else
                    return;
            }
        } else if (n == Data.sha && kills){
            System.out.println(player1.getName() + "已经出过一张杀");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "已经出过一张杀";
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
        //弃牌
        int[] card = player1.getCard();
        if (player1.getNumcard() > player1.getBlood()) {
            int len = player1.getNumcard();
            for (int i = player1.getBlood(); i < len; i++) {
                System.out.println("请选择一张牌");
                if (Objects.equals(GameJFrame.done(), "null"))
                    return;
                int n = GameJFrame.getGame();
                if (n >= 1) {
                    if (flash(player1, card[n - 1])) {
                        System.out.println("未找到此牌,请重新弃牌");
                    }
                } else
                    System.out.println("传入的数字有问题");
            }
            GameJFrame.did();
            discard(player1);
        }
    }

    default boolean menu(int n, Person player1, Person player2, Boolean kills) {
        switch (player1.getCard()[n]) {
            case 0 -> System.out.println("手牌中没有这张牌，请重新输入：");
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
                    System.out.println("玩家的武器无法自主发动技能！");
            }
        }
        return kills;
    }

    //桃
    private static void recover(Person person) {
        if (person.getBlood() < 4) {
            flash(person, Data.tao);
            System.out.println(person.getName() + "出了一张桃,恢复一滴血");
            person.setBlood(person.getBlood() + 1);
        }
    }

    //杀
    private static boolean kill(boolean kills, Person player1, Person player2) {
        if (kills)
            return true;
        if (Math.max(player1.getDistance(), player1.getEquipDistance())
                - player2.getDistance() < 0)
            return false;
        flash(player1, Data.sha);
        System.out.println(player1.getName() + "出了一张杀");
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
                System.out.println(player2.getName() + "未出闪，扣一滴血");
                GameJFrame.cardtext[GameJFrame.stop]=player2.getName()+"未出闪，扣一滴血";
                GameJFrame.stop++;
                if (player1.getEquipcard()[Equip.weapon_neck] == Data.kirinbow) {
                    Equip.UseKirinBow(player1, player2);
                }
            } else {
                System.out.println(player2.getName() + "出了一张闪");
                GameJFrame.cardtext[GameJFrame.stop]=player2.getName()+"出了一张闪";
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

    //查找闪，杀，桃
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

    //无中生有
    private static void OutOfThinAir(Person person) {
        System.out.println(person.getName() + "使用无中生有,摸两张牌");
        flash(person, Data.outOfThinAir);
        person.addcard();
    }

    //万箭齐发
    private static void Arrows(Person player1, Person player2) {
        flash(player1, Data.arrows);
        System.out.println(player1.getName() + "使用万箭齐发");
        if (flash(player2, Data.shan)) {
            if (player2.getEquipcard()[Equip.armor_neck] != 0) {
                Equip.UseEightDiagramTactics(player2);
                return;
            }
            player2.setBlood(player1.getBlood() - 1);
            System.out.println(player2.getName() + "未使用闪，扣一滴血");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "未使用闪，扣一滴血";
            GameJFrame.stop++;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "已使用闪";
            GameJFrame.stop++;
            System.out.println(player2.getName() + "已使用闪");
        }

    }

    //南蛮入侵
    private static void NaInvasion(Person player1, Person player2) {
        flash(player1, Data.naInvasion);
        System.out.println(player1.getName() + "使用南蛮入侵");
        if (flash(player2, Data.sha)) {
            player2.setBlood(player2.getBlood() - 1);
            System.out.println(player2.getName() + "未使用杀，扣一滴血");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "未使用杀，扣一滴血";
            GameJFrame.stop++;
        } else{
            System.out.println(player2.getName() + "已使用杀");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "已使用杀";
            GameJFrame.stop++;
        }

    }

    //决斗
    private static void Duel(Person player1, Person player2) {
        flash(player1, Data.duel);
        System.out.println(player1.getName() + "使用决斗");
        while (true) {
            if (flash(player2, Data.sha)) {
                player2.setBlood(player2.getBlood() - 1);
                System.out.println(player2.getName() + "未使用杀，扣一滴血");
                GameJFrame.cardtext[GameJFrame.stop]=player2.getName()+"未使用杀，扣一滴血";
                GameJFrame.stop++;
                break;
            } else
                System.out.println(player2.getName() + "已使用杀");
            if (flash(player1, Data.sha)) {
                player1.setBlood(player1.getBlood() - 1);
                System.out.println(player1.getName() + "未使用杀，扣一滴血");
                GameJFrame.cardtext[GameJFrame.stop]=player1.getName()+"未使用杀，扣一滴血";
                GameJFrame.stop++;
                break;
            } else
                System.out.println(player1.getName() + "已使用杀");
        }
    }

    //顺手牵羊
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
            System.out.println(player1.getName() + "使用顺手牵羊");
            Random random = new Random();
            int x = random.nextInt(player2.getNumcard());
            card1[player1.getNumcard()] = card2[x];
            card2[x] = 0;
            player2.setCard(card2);
            player2.setNumcard(player2.getNumcard() - 1);
            player1.setCard(card1);
            player1.setNumcard(player1.getNumcard() + 1);
            System.out.println(player1.getName() + "获得" + player2.getName() + "一张手牌");
            GameJFrame.cardtext[GameJFrame.stop]=player1.getName() + "获得" + player2.getName() + "一张手牌";
            GameJFrame.stop++;
        } else{
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "没有手牌";
            GameJFrame.stop++;
            System.out.println(player2.getName() + "没有手牌");
        }
    }

    //过河拆桥
    private static void KickDownTheLadder(Person player1, Person player2) {
        sort(player2);
        if (player2.getNumcard() > 0) {
            flash(player1, Data.kickDownTheLadder);
            System.out.println(player1.getName() + "使用过河拆桥");
            Random random = new Random();
            flash(player2, random.nextInt(player2.getNumcard()));
            System.out.println(player2.getName() + "被弃置一张牌");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "被弃置一张牌";
            GameJFrame.stop++;
        } else{
            System.out.println(player2.getName() + "没有手牌");
            GameJFrame.cardtext[GameJFrame.stop]=player2.getName() + "没有手牌";
            GameJFrame.stop++;
        }
    }

    //
}