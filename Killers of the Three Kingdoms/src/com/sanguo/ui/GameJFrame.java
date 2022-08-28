package com.sanguo.ui;

import com.sanguo.dx.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;

public class GameJFrame extends JFrame implements KeyListener, ActionListener, MouseListener, Inter {

    //���������Ĳ˵�����
    JMenuBar jMenuBar = new JMenuBar();

    //�����˵����������ѡ��Ķ��� ������  �������ǣ�
    JMenu functionJMenu = new JMenu("����");
    JMenu aboutJMenu = new JMenu("�淨ָ��");

    //����ѡ���������Ŀ����
    JMenuItem replayItem = new JMenuItem("������Ϸ");
    JMenuItem reLoginItem = new JMenuItem("���µ�¼");
    JMenuItem closeItem = new JMenuItem("�ر���Ϸ");

    JMenuItem accountItem = new JMenuItem("����");

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

        computer = new AI("����", 4, 4);
        String name2;
        if (LoginJFrame.GameName() != null)
            name2 = LoginJFrame.GameName();
        else
            name2 = RegisterJFrame.GameName();
        player = new Player(name2, 4, 4);

        domain(player, computer);

    }

    private static void showEquipBase(Person person) {
        System.out.println(person.getName() + "��װ���У�");
        for (int i = 0; i < person.getEquipcard().length; i++) {
            switch (person.getEquipcard()[i]) {
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
            }
        }
        System.out.println();
    }

    public void ks() {
        //��ʼ������
        initJFrame();

        //��ʼ���˵�
        initJMenuBar();

        //�ý�����ʾ����������д�����
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
        System.out.println("!!!#####$$" + computer.getName() + "�غϿ�ʼ");
        System.out.println(computer.getName() + "����");
        computer.addcard();
        outcardAI(computer, player);
        System.out.println(computer.getName() + "�غϽ���");
        System.out.println("!!!#####$$" + player.getName() + "�غϿ�ʼ");
        System.out.println(player.getName() + "����");
        player.addcard();
        Inter.show(player);
        showcard(player);
    }

    private boolean Gameover() {
        if (computer.getBlood() <= 0) {
            this.getContentPane().removeAll();
            System.out.println("��ϲ" + player.getName() + "��ʤ");
            JLabel game = new JLabel(new ImageIcon("./image/login/ʤ��.jpg"));
            game.setBounds(0, -100, 1000, 800);
            this.getContentPane().add(game);
            this.setVisible(true);
            return true;
        } else if (player.getBlood() <= 0) {
            this.getContentPane().removeAll();
            System.out.println("���ź���" + player.getName() + "ʧ����");
            JLabel game = new JLabel(new ImageIcon("./image/login/ʧ��.jpg"));
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
            game = new JLabel(new ImageIcon("./image/login/ƽ��.jpg"));
            game.setBounds(0, -100, 1000, 800);
            this.getContentPane().add(game);
            this.setVisible(true);
            return;
        }
        ks();
        int[] card = person.getCard();
        for (int i = 0; i < card.length; i++) {
            JLabel cards;
            //����
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
            gametext.setFont(new Font("����", Font.BOLD, 18));
            gametext.setForeground(Color.red);
            gametext.setBounds(50 + (i / 17) * 250, 15 + (i % 17) * 20, 420, 20);
            //
            this.getContentPane().add(gametext);
        }

        //װ����
        showEquip(player);
        showEquip(computer);

        //����ͼƬ
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
            text = new JLabel("+1��");
            text.setFont(new Font("����", Font.BOLD, 25));
            text.setBounds(770, 480 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
        if (person.getEquipcard()[Equip.horse_minus_neck] != 0) {
            text = new JLabel("-1��");
            text.setFont(new Font("����", Font.BOLD, 25));
            text.setBounds(770, 510 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
        if (person.getEquipcard()[Equip.weapon_neck] != 0) {
            text = new JLabel(switch (person.getEquipcard()[Equip.weapon_neck]) {
                case Data.crossbows -> "�������";
                case Data.qingHongJian -> "��罣";
                case Data.maleAndFemaleSword -> "����˫�ɽ�";
                case Data.greenDragonCrescentBlade -> "�������µ�";
                case Data.kirinbow -> "���빭";
                case Data.penetrationCelts -> "��ʯ��";
                case Data.spears -> "�ɰ˳�ì";
                default -> null;
            });
            text.setFont(new Font("����", Font.BOLD, 25));
            text.setBounds(770, 540 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
        if (person.getEquipcard()[Equip.armor_neck] != 0) {
            text = new JLabel(switch (person.getEquipcard()[Equip.armor_neck]) {
                case Data.eightDiagramTactics -> "������";
                case Data.shield -> "������";
                default -> null;
            });
            text.setFont(new Font("����", Font.BOLD, 25));
            text.setBounds(770, 570 - len, 210, 28);
            text.setForeground(Color.yellow);
            this.getContentPane().add(text);
        }
    }

    private void initJMenuBar() {

        //��ÿһ��ѡ���������Ŀ�켫����ѡ���
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //����Ŀ���¼�
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //���˵����������ѡ����ӵ��˵�����
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //�������������ò˵�
        this.setJMenuBar(jMenuBar);

        JLabel gametext = new JLabel("��������ڼ�����");
        gametext.setFont(new Font("����", Font.BOLD, 20));
        gametext.setForeground(Color.blue);
        gametext.setBounds(110, 380, 220, 30);
        this.getContentPane().add(gametext);

        JLabel AItext = new JLabel("AI���� " + computer.getNumcard() + " ������");
        AItext.setFont(new Font("����", Font.BOLD, 30));
        AItext.setForeground(Color.black);
        AItext.setBounds(740, 70, 260, 30);
        this.getContentPane().add(AItext);

        game.setBounds(300, 380, 200, 30);
        this.getContentPane().add(game);

        game.setText("null");

        play.setBounds(540, 380, 128, 47);
        play.setIcon(new ImageIcon("./image/game/����.png"));
        //ȥ����ť�ı߿�
        play.setBorderPainted(false);
        //ȥ����ť�ı���
        play.setContentAreaFilled(false);
        //����¼��ť������¼�
        play.addMouseListener(this);
        this.getContentPane().add(play);

        discard.setBounds(680, 380, 128, 47);
        discard.setIcon(new ImageIcon("./image/game/����.png"));
        //ȥ����ť�ı߿�
        discard.setBorderPainted(false);
        //ȥ����ť�ı���
        discard.setContentAreaFilled(false);
        //����¼��ť������¼�
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
        //���ý���Ŀ��
        this.setSize(1000, 700);
        //���ý���ı���
        this.setTitle("����ɱ v1.0");
        //���ý����ö�
        //this.setAlwaysOnTop(true);
        //���ý������
        this.setLocationRelativeTo(null);
        //���ùر�ģʽ
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //ȡ��Ĭ�ϵľ��з��ã�ֻ��ȡ���˲Żᰴ��XY�����ʽ������
        //this.setLayout(null);
        //������������Ӽ��̼����¼�
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //���²���ʱ������������
    @Override
    public void keyPressed(KeyEvent e) {

    }

    //�ɿ�������ʱ�������������
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayItem) {
            System.out.println("������Ϸ");
            this.setVisible(false);
            new GameJFrame();
        } else if (e.getSource() == reLoginItem) {
            System.out.println("���µ�¼");
            //�رյ�ǰ����Ϸ����
            this.setVisible(false);
            //�򿪵�¼����
            new LoginJFrame();
        } else if (e.getSource() == closeItem) {
            System.out.println("�ر���Ϸ");
            //ֱ�ӹر����������
            System.exit(0);
        } else if (e.getSource() == accountItem) {
            //�淨ָ��
            showJDialog();
            System.out.println("�淨ָ��");
        }
    }

    private void showJDialog() {
        //����һ���������
        JDialog jDialog = new JDialog();
        //���������ô�С
        jDialog.setSize(600, 210);
        //�õ����ö�
        jDialog.setAlwaysOnTop(true);
        //�õ������
        jDialog.setLocationRelativeTo(null);
        //���򲻹ر���Զ�޷���������Ľ���
        jDialog.setModal(true);
        jDialog.setLayout(null);

        //����Jlabel����������ֲ���ӵ�������
        JLabel warning1 = new JLabel("�淨ָ��");
        warning1.setBounds(0, 0, 150, 40);
        warning1.setFont(new Font("����", Font.BOLD, 35));
        JLabel warning2 = new JLabel("���뼸��������ƺ󣬼����ڼ�����");
        warning2.setBounds(0, 40, 420, 25);
        warning2.setFont(new Font("����", Font.BOLD, 20));
        JLabel warning3 = new JLabel("������ƺ󣬲��ܼ�������");
        warning3.setFont(new Font("����", Font.BOLD, 20));
        warning3.setBounds(0, 70, 400, 25);
        JLabel warning4 = new JLabel("�����Ҫ�����غ�ʱ��Ӧ���롰0�������������");
        warning4.setFont(new Font("����", Font.BOLD, 20));
        warning4.setBounds(0, 100, 570, 25);
        JLabel warning5 = new JLabel("�������ʱ��Ӧ������ư�ť");
        warning5.setFont(new Font("����", Font.BOLD, 20));
        warning5.setBounds(0, 130, 450, 25);

        jDialog.getContentPane().add(warning1);
        jDialog.getContentPane().add(warning2);
        jDialog.getContentPane().add(warning3);
        jDialog.getContentPane().add(warning4);
        jDialog.getContentPane().add(warning5);

        //�õ���չʾ����
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
        return "ʹ������  " + switch (a) {
            case Data.sha -> "ɱ ";
            case Data.shan -> "�� ";
            case Data.tao -> "�� ";
            case Data.outOfThinAir -> "�������� ";
            case Data.arrows -> "����뷢 ";
            case Data.naInvasion -> "�������� ";
            case Data.duel -> "���� ";
            case Data.pilfering -> "˳��ǣ�� ";
            case Data.kickDownTheLadder -> "���Ӳ��� ";
            case Data.horse_plus -> "+1�� ";
            case Data.horse_minus -> "-1�� ";
            case Data.crossbows -> "������� ";
            case Data.eightDiagramTactics -> "������ ";
            case Data.shield -> "������ ";
            case Data.qingHongJian -> "��罣 ";
            case Data.maleAndFemaleSword -> "����˫�ɽ� ";
            case Data.greenDragonCrescentBlade -> "�������µ� ";
            case Data.kirinbow -> "���빭 ";
            case Data.penetrationCelts -> "��ʯ�� ";
            case Data.spears -> "�ɰ˳�ì ";
            default -> "�޸ÿ���";
        };
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (stop > 30) {
            stop = 0;
            Arrays.fill(cardtext, null);
        }
        if (e.getSource() == play && !flag) {
            System.out.println("����˳��ư�ť");
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
            System.out.println("��������ư�ť");
            String regex = "[1-9]+";
            if (game.getText().matches(regex)) {
                flag = true;
                if (player.getNumcard() > player.getBlood()) {
                    Inter.discard(player);
                    this.setVisible(false);
                    showcard(player);
                } else System.out.println("��������");
            }
            if (Objects.equals(game.getText(), "0") && player.getNumcard() <= player.getBlood()) {
                System.out.println(player.getName() + "�غϽ���");
                System.out.println(player.getName() + "����" + player.getBlood() + "��Ѫ");
                showEquipBase(player);
                System.out.println(computer.getName() + "����" + computer.getBlood() + "��Ѫ");
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
