package com.sanguo.ui;

import com.sanguo.dx.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;

public class GameJFrame extends JFrame implements KeyListener, ActionListener, MouseListener, Inter {

    //创建整个的菜单对象
    JMenuBar jMenuBar = new JMenuBar();

    //创建菜单上面的两个选项的对象 （功能  关于我们）
    JMenu functionJMenu = new JMenu("功能");
    JMenu aboutJMenu = new JMenu("玩法指南");

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("帮助");

    static JTextField game = new JTextField();

    JButton play = new JButton();
    JButton discard = new JButton();


    AI computer;
    Player player;

    static boolean flag = false;
    public static int stop = 0;
    public static String[] cardtext = new String[50];

    public GameJFrame() {
        Person.pushcard();

        computer = new AI("电脑", 4, 4);
        String name2;
        if (LoginJFrame.GameName() != null)
            name2 = LoginJFrame.GameName();
        else
            name2 = RegisterJFrame.GameName();
        player = new Player(name2, 4, 4);

        domain(player, computer);

    }

    private static void showEquipBase(Person person) {
        System.out.println(person.getName() + "的装备有：");
        for (int i = 0; i < person.getEquipcard().length; i++) {
            switch (person.getEquipcard()[i]) {
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
            }
        }
        System.out.println();
    }

    public void ks() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //让界面显示出来，建议写在最后
        //this.setVisible(true);
    }

    public static void did() {
        game.setText("null");
    }

    public static String done() {
        return game.getText();
    }

    public void domain(Person player, Person computer) {
        stop = 0;
        flag = false;
        System.out.println("!!!#####$$" + computer.getName() + "回合开始");
        System.out.println(computer.getName() + "出牌");
        computer.addcard();
        outcardAI(computer, player);
        System.out.println(computer.getName() + "回合结束");
        System.out.println("!!!#####$$" + player.getName() + "回合开始");
        System.out.println(player.getName() + "出牌");
        player.addcard();
        Inter.show(player);
        showcard(player);
    }

    private boolean Gameover() {
        if (computer.getBlood() <= 0) {
            this.getContentPane().removeAll();
            System.out.println("恭喜" + player.getName() + "获胜");
            JLabel game = new JLabel(new ImageIcon("./image/login/胜利.jpg"));
            game.setBounds(0, -100, 1000, 800);
            this.getContentPane().add(game);
            this.setVisible(true);
            return true;
        } else if (player.getBlood() <= 0) {
            this.getContentPane().removeAll();
            System.out.println("很遗憾，" + player.getName() + "失败了");
            JLabel game = new JLabel(new ImageIcon("./image/login/失败.jpg"));
            game.setBounds(0, -100, 1000, 800);
            this.getContentPane().add(game);
            this.setVisible(true);
            return true;
        }
        return false;
    }

    public static int getGame() {
        if (Objects.equals(game.getText(), "0"))
            return 0;
        if (!Objects.equals(game.getText(), "null"))
            return Integer.parseInt(game.getText());
        return -1;
    }

    public void showcard(Person person) {
        this.remove(functionJMenu);
        this.remove(aboutJMenu);
        this.getContentPane().removeAll();
        if (Person.DrawnGame()) {
            JLabel game;
            game = new JLabel(new ImageIcon("./image/login/平局.jpg"));
            game.setBounds(0, -100, 1000, 800);
            this.getContentPane().add(game);
            this.setVisible(true);
            return;
        }
        ks();
        int[] card = person.getCard();
        for (int i = 0; i < card.length; i++) {
            JLabel cards;
            //手牌
            switch (card[i]) {
                case Data.sha -> cards = new JLabel(new ImageIcon("./image/game/sha.png"));
                case Data.shan -> cards = new JLabel(new ImageIcon("./image/game/shan.png"));
                case Data.tao -> cards = new JLabel(new ImageIcon("./image/game/tao.png"));
                case Data.outOfThinAir -> cards = new JLabel(new ImageIcon("./image/game/outOfThinAir.png"));
                case Data.arrows -> cards = new JLabel(new ImageIcon("./image/game/arrows.png"));
                case Data.naInvasion -> cards = new JLabel(new ImageIcon("./image/game/naInvasion.png"));
                case Data.duel -> cards = new JLabel(new ImageIcon("./image/game/duel.png"));
                case Data.pilfering -> cards = new JLabel(new ImageIcon("./image/game/pilfering.png"));
                case Data.kickDownTheLadder -> cards = new JLabel(new ImageIcon("./image/game/kickDownTheLadder.png"));
                case Data.horse_plus -> cards = new JLabel(new ImageIcon("./image/game/horse_plus.png"));
                case Data.horse_minus -> cards = new JLabel(new ImageIcon("./image/game/horse_minus.png"));
                case Data.crossbows -> cards = new JLabel(new ImageIcon("./image/game/crossbows.png"));
                case Data.eightDiagramTactics ->
                        cards = new JLabel(new ImageIcon("./image/game/eightDiagramTactics.png"));
                case Data.shield -> cards = new JLabel(new ImageIcon("./image/game/shield.png"));
                case Data.qingHongJian -> cards = new JLabel(new ImageIcon("./image/game/qingHongJian.png"));
                case Data.maleAndFemaleSword ->
                        cards = new JLabel(new ImageIcon("./image/game/maleAndFemaleSword.png"));
                case Data.greenDragonCrescentBlade ->
                        cards = new JLabel(new ImageIcon("./image/game/greenDragonCrescentBlade.png"));
                case Data.kirinbow -> cards = new JLabel(new ImageIcon("./image/game/kirinbow.png"));
                case Data.penetrationCelts -> cards = new JLabel(new ImageIcon("./image/game/penetrationCelts.png"));
                case Data.spears -> cards = new JLabel(new ImageIcon("./image/game/spears.png"));
                default -> cards = new JLabel();
            }
            cards.setBounds(50 + 70 * i, 420, 135, 200);
            this.getContentPane().add(cards);
        }

        for (int i = 0; i < cardtext.length; i++) {
            JLabel gametext = new JLabel(cardtext[i]);
            //
            gametext.setFont(new Font("宋体", Font.BOLD, 18));
            gametext.setForeground(Color.red);
            gametext.setBounds(50 + (i / 17) * 250, 15 + (i % 17) * 20, 420, 20);
            //
            this.getContentPane().add(gametext);
        }

        //装备栏
        showEquip(player);
        showEquip(computer);

        //背景图片
        JLabel background = new JLabel(new ImageIcon("./image/game/background.jpg"));
        background.setBounds(0, 0, 950, 670);
        this.getContentPane().add(background);

        this.setVisible(true);
    }

    private void showEquip(Person person) {
        JLabel text;
        int len = 0;
        if (person != player)
            len = 330;
        if (person.getEquipcard()[Equip.horse_plus_neck] != 0) {
            text = new JLabel("+1马");
            text.setFont(new Font("宋体", Font.BOLD, 25));
            text.setBounds(770, 480 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
        if (person.getEquipcard()[Equip.horse_minus_neck] != 0) {
            text = new JLabel("-1马");
            text.setFont(new Font("宋体", Font.BOLD, 25));
            text.setBounds(770, 510 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
        if (person.getEquipcard()[Equip.weapon_neck] != 0) {
            text = new JLabel(switch (person.getEquipcard()[Equip.weapon_neck]) {
                case Data.crossbows -> "诸葛连弩";
                case Data.qingHongJian -> "青虹剑";
                case Data.maleAndFemaleSword -> "雌雄双股剑";
                case Data.greenDragonCrescentBlade -> "青龙偃月刀";
                case Data.kirinbow -> "麒麟弓";
                case Data.penetrationCelts -> "贯石斧";
                case Data.spears -> "丈八长矛";
                default -> null;
            });
            text.setFont(new Font("宋体", Font.BOLD, 25));
            text.setBounds(770, 540 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
        if (person.getEquipcard()[Equip.armor_neck] != 0) {
            text = new JLabel(switch (person.getEquipcard()[Equip.armor_neck]) {
                case Data.eightDiagramTactics -> "八卦阵";
                case Data.shield -> "仁王盾";
                default -> null;
            });
            text.setFont(new Font("宋体", Font.BOLD, 25));
            text.setBounds(770, 570 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
    }

    private void initJMenuBar() {

        //将每一个选项下面的条目天极爱到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);

        JLabel gametext = new JLabel("请输入出第几张牌");
        gametext.setFont(new Font("楷体", Font.BOLD, 20));
        gametext.setForeground(Color.blue);
        gametext.setBounds(110, 380, 220, 30);
        this.getContentPane().add(gametext);

        JLabel AItext = new JLabel("AI还有 " + computer.getNumcard() + " 张手牌");
        AItext.setFont(new Font("楷体", Font.BOLD, 30));
        AItext.setForeground(Color.black);
        AItext.setBounds(740, 70, 260, 30);
        this.getContentPane().add(AItext);

        game.setBounds(300, 380, 200, 30);
        this.getContentPane().add(game);

        game.setText("null");

        play.setBounds(540, 380, 128, 47);
        play.setIcon(new ImageIcon("./image/game/出牌.png"));
        //去除按钮的边框
        play.setBorderPainted(false);
        //去除按钮的背景
        play.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        play.addMouseListener(this);
        this.getContentPane().add(play);

        discard.setBounds(680, 380, 128, 47);
        discard.setIcon(new ImageIcon("./image/game/弃牌.png"));
        //去除按钮的边框
        discard.setBorderPainted(false);
        //去除按钮的背景
        discard.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        discard.addMouseListener(this);
        this.getContentPane().add(discard);

        JLabel cards;
        for (int i = 0; i < player.getBlood(); i++) {
            cards = new JLabel(new ImageIcon("./image/game/blood.png"));
            cards.setBounds(770 + 50 * i, 430, 50, 50);
            this.getContentPane().add(cards);
        }
        for (int i = 0; i < computer.getBlood(); i++) {
            cards = new JLabel(new ImageIcon("./image/game/blood.png"));
            cards.setBounds(770 + 50 * i, 100, 50, 50);
            this.getContentPane().add(cards);
        }

    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(1000, 700);
        //设置界面的标题
        this.setTitle("三国杀 v1.0");
        //设置界面置顶
        //this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        //this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {

    }

    //松开按键的时候会调用这个方法
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayItem) {
            System.out.println("重新游戏");
            this.setVisible(false);
            new GameJFrame();
        } else if (e.getSource() == reLoginItem) {
            System.out.println("重新登录");
            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (e.getSource() == closeItem) {
            System.out.println("关闭游戏");
            //直接关闭虚拟机即可
            System.exit(0);
        } else if (e.getSource() == accountItem) {
            //玩法指南
            showJDialog();
            System.out.println("玩法指南");
        }
    }

    private void showJDialog() {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(600, 210);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);
        jDialog.setLayout(null);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning1 = new JLabel("玩法指南");
        warning1.setBounds(0, 0, 150, 40);
        warning1.setFont(new Font("楷体", Font.BOLD, 35));
        JLabel warning2 = new JLabel("输入几并点击出牌后，即出第几张牌");
        warning2.setBounds(0, 40, 420, 25);
        warning2.setFont(new Font("楷体", Font.BOLD, 20));
        JLabel warning3 = new JLabel("玩家弃牌后，不能继续出牌");
        warning3.setFont(new Font("楷体", Font.BOLD, 20));
        warning3.setBounds(0, 70, 400, 25);
        JLabel warning4 = new JLabel("玩家想要结束回合时，应输入“0”，并点击弃牌");
        warning4.setFont(new Font("楷体", Font.BOLD, 20));
        warning4.setBounds(0, 100, 570, 25);
        JLabel warning5 = new JLabel("玩家弃牌时，应点击弃牌按钮");
        warning5.setFont(new Font("楷体", Font.BOLD, 20));
        warning5.setBounds(0, 130, 450, 25);

        jDialog.getContentPane().add(warning1);
        jDialog.getContentPane().add(warning2);
        jDialog.getContentPane().add(warning3);
        jDialog.getContentPane().add(warning4);
        jDialog.getContentPane().add(warning5);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public boolean menu(int n, Person player1, Person player2, Boolean kills) {
        if ((player1.getCard()[n] == Data.sha && kills) || player1.getCard()[n] == Data.shan
                || (player1.getBlood() == 4 && player1.getCard()[n] == Data.tao))
            return Inter.super.menu(n, player1, player2, kills);
        cardtext[stop] = player1.getName() + addtext(player1.getCard()[n]);
        stop++;
        return Inter.super.menu(n, player1, player2, kills);
    }

    public String addtext(int a) {
        return "使用手牌  " + switch (a) {
            case Data.sha -> "杀 ";
            case Data.shan -> "闪 ";
            case Data.tao -> "桃 ";
            case Data.outOfThinAir -> "无中生有 ";
            case Data.arrows -> "万箭齐发 ";
            case Data.naInvasion -> "南蛮入侵 ";
            case Data.duel -> "决斗 ";
            case Data.pilfering -> "顺手牵羊 ";
            case Data.kickDownTheLadder -> "过河拆桥 ";
            case Data.horse_plus -> "+1马 ";
            case Data.horse_minus -> "-1马 ";
            case Data.crossbows -> "诸葛连弩 ";
            case Data.eightDiagramTactics -> "八卦阵 ";
            case Data.shield -> "仁王盾 ";
            case Data.qingHongJian -> "青虹剑 ";
            case Data.maleAndFemaleSword -> "雌雄双股剑 ";
            case Data.greenDragonCrescentBlade -> "青龙偃月刀 ";
            case Data.kirinbow -> "麒麟弓 ";
            case Data.penetrationCelts -> "贯石斧 ";
            case Data.spears -> "丈八长矛 ";
            default -> "无该卡牌";
        };
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (stop > 30) {
            stop = 0;
            Arrays.fill(cardtext, null);
        }
        if (e.getSource() == play && !flag) {
            System.out.println("点击了出牌按钮");
            String regex = "[1-9]+";
            if (!Objects.equals(game.getText(), "null")) {
                if (game.getText().matches(regex)) {
                    outcard(player, computer);
                    this.setVisible(false);
                    this.getContentPane().removeAll();
                    showcard(player);
                }
            }
        } else if (e.getSource() == discard) {
            System.out.println("点击了弃牌按钮");
            String regex = "[1-9]+";
            if (game.getText().matches(regex)) {
                flag = true;
                if (player.getNumcard() > player.getBlood()) {
                    Inter.discard(player);
                    this.setVisible(false);
                    showcard(player);
                } else System.out.println("不用弃牌");
            }
            if (Objects.equals(game.getText(), "0") && player.getNumcard() <= player.getBlood()) {
                System.out.println(player.getName() + "回合结束");
                System.out.println(player.getName() + "还有" + player.getBlood() + "滴血");
                showEquipBase(player);
                System.out.println(computer.getName() + "还有" + computer.getBlood() + "滴血");
                showEquipBase(computer);
                if (Gameover()) {
                    return;
                }
                flag = false;
                Arrays.fill(cardtext, null);
                domain(player, computer);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
