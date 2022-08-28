package com.sanguo.ui;

import com.sanguo.dx.Data;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class RegisterJFrame extends JFrame implements MouseListener {
    //��ע����صĴ��룬��д�����������

    JButton register = new JButton();
    JButton close = new JButton();

    static JTextField username = new JTextField();
    //JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField passwordsecond = new JPasswordField();
    JTextField code = new JTextField();

    //��ȷ����֤��
    JLabel rightCode = new JLabel();


    public RegisterJFrame(){
        initJFrame();

        initView();

        //����ʾ��ʾ����������д�����
        this.setVisible(true);


        getContentPane();
    }


    public void initView() {
        //1. ����û�������
        JLabel usernameText = new JLabel(new ImageIcon("./image/register/ע���û���.png"));
        usernameText.setBounds(110, 100, 80, 17);
        this.getContentPane().add(usernameText);

        //2.����û��������

        username.setBounds(195, 100, 200, 30);
        this.getContentPane().add(username);

        //3.�����������
        JLabel passwordText = new JLabel(new ImageIcon("./image/register/ע������.png"));
        passwordText.setBounds(115, 150, 80, 16);
        this.getContentPane().add(passwordText);

        //4.���������
        password.setBounds(195, 150, 200, 30);
        this.getContentPane().add(password);

        JLabel passwordsecondText = new JLabel(new ImageIcon("./image/register/�ٴ���������.png"));
        passwordsecondText.setBounds(90, 200, 100, 17);
        this.getContentPane().add(passwordsecondText);

        passwordsecond.setBounds(195, 200, 200, 30);
        this.getContentPane().add(passwordsecond);

        //��֤����ʾ
        JLabel codeText = new JLabel(new ImageIcon("./image/register/��֤��.png"));
        codeText.setBounds(133, 250, 50, 30);
        this.getContentPane().add(codeText);

        //��֤��������
        code.setBounds(195, 250, 100, 30);
        this.getContentPane().add(code);


        String codeStr = CodeUtil.getCode();
        //��������
        rightCode.setText(codeStr);
        //������¼�
        rightCode.addMouseListener(this);
        //λ�úͿ��
        rightCode.setBounds(300, 256, 50, 30);
        //��ӵ�����
        this.getContentPane().add(rightCode);

        //5.��Ӱ�ť
        register.setBounds(123, 310, 128, 47);
        register.setIcon(new ImageIcon("./image/register/ע�ᰴť.png"));
        //ȥ����ť�ı߿�
        register.setBorderPainted(false);
        //ȥ����ť�ı���
        register.setContentAreaFilled(false);
        //����¼��ť������¼�
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //6.��Ӱ�ť
        close.setBounds(256, 310, 128, 47);
        close.setIcon(new ImageIcon("./image/register/���ð�ť.png"));
        //ȥ����ť�ı߿�
        close.setBorderPainted(false);
        //ȥ����ť�ı���
        close.setContentAreaFilled(false);
        //��ע�ᰴť������¼�
        close.addMouseListener( this);
        this.getContentPane().add(close);


        //7.��ӱ���ͼƬ
        JLabel background = new JLabel(new ImageIcon("./image/register/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }


    public void initJFrame() {
        this.setSize(488, 430);//���ÿ��
        this.setTitle("����ɱ V1.0ע��");//���ñ���
        this.setDefaultCloseOperation(3);//���ùر�ģʽ
        this.setLocationRelativeTo(null);//����
        this.setAlwaysOnTop(true);//�ö�
        this.setLayout(null);//ȡ���ڲ�Ĭ�ϲ���
    }



    //���
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == register) {
            System.out.println("�����ע�ᰴť");
            //��ȡ�����ı�������е�����
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String passwordsecondInput = password.getText();
            //��ȡ�û��������֤��
            String codeInput = code.getText();

            if(Objects.equals(passwordInput, passwordsecondInput)){
                //����һ��User����
                User userInfo = new User(usernameInput, passwordInput);
                System.out.println("�û�������û���Ϊ" + usernameInput);
                System.out.println("�û����������Ϊ" + passwordInput);
                Data.allUsers.add(userInfo);

                if (codeInput.length() == 0) {
                    showJDialog("��֤�벻��Ϊ��");
                } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                    //У���û����������Ƿ�Ϊ��
                    System.out.println("�û�����������Ϊ��");

                    //����showJDialog������չʾ����
                    showJDialog("�û�����������Ϊ��");


                } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                    showJDialog("��֤���������");
                } else if (contains(userInfo)) {
                    System.out.println("�û�����������ȷ���Կ�ʼ����Ϸ��");
                    //�رյ�ǰ��¼����
                    this.setVisible(false);
                    //����Ϸ��������
                    //��Ҫ�ѵ�ǰ��¼���û������ݸ���Ϸ����
                    new GameJFrame();
                } else {
                    System.out.println("�û������������");
                    showJDialog("�û������������");
                }
            }else {
                System.out.println("�������벻ͬ");
                showJDialog("�������벻ͬ");
            }
        } else if (e.getSource() == close) {
            this.setVisible(false);
            new RegisterJFrame();
            System.out.println("��������ð�ť");
        } else if (e.getSource() == rightCode) {
            System.out.println("������֤��");
            //��ȡһ���µ���֤��
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }

    public void showJDialog(String content) {
        //����һ���������
        JDialog jDialog = new JDialog();
        //���������ô�С
        jDialog.setSize(200, 150);
        //�õ����ö�
        jDialog.setAlwaysOnTop(true);
        //�õ������
        jDialog.setLocationRelativeTo(null);
        //���򲻹ر���Զ�޷���������Ľ���
        jDialog.setModal(true);

        //����Jlabel����������ֲ���ӵ�������
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //�õ���չʾ����
        jDialog.setVisible(true);
    }

    //���²���
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("./image/register/ע�ᰴ��.png"));
        } else if (e.getSource() == close) {
            close.setIcon(new ImageIcon("./image/register/���ð���.png"));
        }
    }


    //�ɿ���ť
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("./image/login/��¼��ť.png"));
        } else if (e.getSource() == close) {
            close.setIcon(new ImageIcon("./image/login/ע�ᰴť.png"));
        }
    }

    //��껮��
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //��껮��
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //�ж��û��ڼ������Ƿ����
    public boolean contains(User userInput){
        for (int i = 0; i < Data.allUsers.size(); i++) {
            User rightUser = Data.allUsers.get(i);
            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
                //����ͬ�Ĵ�����ڣ�����true������Ĳ���Ҫ�ٱ���
                return true;
            }
        }
        //ѭ������֮��û���ҵ��ͱ�ʾ������
        return false;
    }


    public static String GameName(){
        return username.getText();
    }
}
