import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Login extends JFrame implements MouseListener {
    //登录页面
    public Login() {
        //初始化页面
        init();
        //显示
        this.setVisible(true);
    }

    String path = "image\\login\\";
    JButton loginButton=new JButton();
    JButton registerButton=new JButton();
    JLabel textContent =new JLabel();
    JTextField username =new JTextField();
    JTextField text=new JTextField();
    JPasswordField passwd = new JPasswordField();

    private void init(){
        //尺寸
        this.setSize(500,450);
        //标题
        this.setTitle("登录");
        //居中
        this.setLocationRelativeTo(null);
        //随机  初始化验证码
        dislocateCaptcha();
        initCaptcha();
        //初始化用户名模块
        initUserName();
        //初始化密码模块
        initPasswd();
        //初始化登录模块
        initLogin();
        //初始化注册模块
        initRegister();
        //背景图片
        JLabel loginbg = new JLabel(new ImageIcon(path+"background.png"));
        loginbg.setBounds(15,5,470,390);
        this.getContentPane().add(loginbg);
        //关闭模式
        this.setDefaultCloseOperation(3);

    }

    private void initRegister(){
        registerButton.setBounds(270,300,128,47);
        registerButton.setIcon(new ImageIcon(path+"registerButton.png"));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);

        registerButton.addMouseListener(this);

        this.getContentPane().add(registerButton);
    }
    private void initLogin(){
        loginButton.setBounds(95,300,128,47);
        loginButton.setIcon(new ImageIcon(path+"loginButton.png"));//设置背景
        loginButton.setBorderPainted(false);//取消默认边框
        loginButton.setContentAreaFilled(false);//取消默认背景

        loginButton.addMouseListener(this);

        this.getContentPane().add(loginButton);
    }
    private void initPasswd(){
        //密码
        JLabel password = new JLabel(new ImageIcon(path+"passwd.png"));
        password.setBounds(70,200,47,17);

        passwd.setBounds(150,195,200,30);

        this.getContentPane().add(passwd);
        this.getContentPane().add(password);
    }
    private void initUserName(){
        //用户名 图片
        JLabel name = new JLabel(new ImageIcon(path+"uname.png"));
        name.setBounds(70,150,47,17);
        //用户名输入框
        username.setBounds(150,145,200,30);

        this.getContentPane().add(username);
        this.getContentPane().add(name);
    }
    private void initCaptcha(){
        //验证码
        JLabel captcha =new JLabel(new ImageIcon(path+"captcha.png"));
        captcha.setBounds(70,250,56,21);
        text.setBounds(150,245,100,30);

        textContent.setText(content);
        textContent.setBounds(270,245,50,30);

        this.getContentPane().add(textContent);
        this.getContentPane().add(text);
        this.getContentPane().add(captcha);
        textContent.addMouseListener(this);
    }

    Random random =new Random();
    String content="";

    //随机输出验证码
    private void dislocateCaptcha(){
        char[][] data={
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                        'u', 'v', 'w', 'x', 'y', 'z'},
                {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                        'U', 'V', 'W', 'X', 'Y', 'Z'}
        };
        
        StringBuilder tem=new StringBuilder();
        char tmp;
        int i=0;
        int a = 0;
        int r;
        while (i<4){
            r=random.nextInt(3);
            if (r == 0) {
                a=random.nextInt(10);
            }else if (r == 1||r==2) {
                a=random.nextInt(26);
            }
            tem.append(data[r][a]);
            i++;
        }
        content=tem.toString();
        //System.out.println(content);
    }


    private boolean logi() throws IOException {
        String getName=username.getText();
        String getPasswd=passwd.getText();
        FileReader account =new FileReader("account.txt");
        BufferedReader bufferedReader =new BufferedReader(account);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] userData = line.split(":");
            if (userData[0].equals(getName)&&userData[1].equals(getPasswd)) {
                return true;
            }
        }
        return false;
    }

    //登录判断
    private void judgment() throws IOException {
        String getCaptcha=text.getText();
        if (getCaptcha.equalsIgnoreCase(content)) {//不区分大小写
            String getName=username.getText();
            String getPasswd=passwd.getText();
            if ( logi()) {
                new Game();
                dispose();
            }else {
                Error error= new Error("用户名或密码错误");
            }
        }else {
            //弹框提示
            Error error= new Error("验证码错误");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginButton) {//点击登录
            try {
                judgment();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //判断方法
        }else if (e.getSource() == registerButton) {//点击注册
            new Register();
        } else if (e.getSource()==textContent) {//更换验证码
            dislocateCaptcha();
            textContent.setText(content);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon(path+"loginOn.png"));
        }else if(e.getSource()==registerButton)
            registerButton.setIcon(new ImageIcon(path+"registerOn.png"));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon(path+"loginButton.png"));
        } else if (e.getSource()==registerButton) {
            registerButton.setIcon(new ImageIcon(path +"registerButton.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}