package com.sanguo.ui;

import com.sanguo.dx.Data;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class RegisterJFrame extends JFrame implements MouseListener {
    //跟注册相关的代码，都写在这个界面中

    JButton register = new JButton();
    JButton close = new JButton();

    static JTextField username = new JTextField();
    //JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField passwordsecond = new JPasswordField();
    JTextField code = new JTextField();

    //正确的验证码
    JLabel rightCode = new JLabel();


    public RegisterJFrame(){
        initJFrame();

        initView();

        //让显示显示出来，建议写在最后
        this.setVisible(true);


        getContentPane();
    }


    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("./image/register/注册用户名.png"));
        usernameText.setBounds(110, 100, 80, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框

        username.setBounds(195, 100, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("./image/register/注册密码.png"));
        passwordText.setBounds(115, 150, 80, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 150, 200, 30);
        this.getContentPane().add(password);

        JLabel passwordsecondText = new JLabel(new ImageIcon("./image/register/再次输入密码.png"));
        passwordsecondText.setBounds(90, 200, 100, 17);
        this.getContentPane().add(passwordsecondText);

        passwordsecond.setBounds(195, 200, 200, 30);
        this.getContentPane().add(passwordsecond);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("./image/register/验证码.png"));
        codeText.setBounds(133, 250, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(195, 250, 100, 30);
        this.getContentPane().add(code);


        String codeStr = CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加按钮
        register.setBounds(123, 310, 128, 47);
        register.setIcon(new ImageIcon("./image/register/注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //6.添加按钮
        close.setBounds(256, 310, 128, 47);
        close.setIcon(new ImageIcon("./image/register/重置按钮.png"));
        //去除按钮的边框
        close.setBorderPainted(false);
        //去除按钮的背景
        close.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        close.addMouseListener( this);
        this.getContentPane().add(close);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("./image/register/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }


    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("三国杀 V1.0注册");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }



    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String passwordsecondInput = password.getText();
            //获取用户输入的验证码
            String codeInput = code.getText();

            if(Objects.equals(passwordInput, passwordsecondInput)){
                //创建一个User对象
                User userInfo = new User(usernameInput, passwordInput);
                System.out.println("用户输入的用户名为" + usernameInput);
                System.out.println("用户输入的密码为" + passwordInput);
                Data.allUsers.add(userInfo);

                if (codeInput.length() == 0) {
                    showJDialog("验证码不能为空");
                } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                    //校验用户名和密码是否为空
                    System.out.println("用户名或者密码为空");

                    //调用showJDialog方法并展示弹框
                    showJDialog("用户名或者密码为空");


                } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                    showJDialog("验证码输入错误");
                } else if (contains(userInfo)) {
                    System.out.println("用户名和密码正确可以开始玩游戏了");
                    //关闭当前登录界面
                    this.setVisible(false);
                    //打开游戏的主界面
                    //需要把当前登录的用户名传递给游戏界面
                    new GameJFrame();
                } else {
                    System.out.println("用户名或密码错误");
                    showJDialog("用户名或密码错误");
                }
            }else {
                System.out.println("两次密码不同");
                showJDialog("两次密码不同");
            }
        } else if (e.getSource() == close) {
            this.setVisible(false);
            new RegisterJFrame();
            System.out.println("点击了重置按钮");
        } else if (e.getSource() == rightCode) {
            System.out.println("更换验证码");
            //获取一个新的验证码
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    //按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("./image/register/注册按下.png"));
        } else if (e.getSource() == close) {
            close.setIcon(new ImageIcon("./image/register/重置按下.png"));
        }
    }


    //松开按钮
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("./image/login/登录按钮.png"));
        } else if (e.getSource() == close) {
            close.setIcon(new ImageIcon("./image/login/注册按钮.png"));
        }
    }

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //判断用户在集合中是否存在
    public boolean contains(User userInput){
        for (int i = 0; i < Data.allUsers.size(); i++) {
            User rightUser = Data.allUsers.get(i);
            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
                //有相同的代表存在，返回true，后面的不需要再比了
                return true;
            }
        }
        //循环结束之后还没有找到就表示不存在
        return false;
    }


    public static String GameName(){
        return username.getText();
    }
}
