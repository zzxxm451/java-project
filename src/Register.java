import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame implements MouseListener {
    public Register() {
        init();

        this.setVisible(true);
    }

    String path="app\\image\\register\\";
    JButton register=new JButton();
    JButton clear=new JButton();
    JTextField username = new JTextField();
    JPasswordField passwd = new JPasswordField();
    JPasswordField rePasswd = new JPasswordField();
    private void init(){
        this.setSize(500,450);

        this.setTitle("注册账号");

        this.setLocationRelativeTo(null);

        initName();
        initPasswd();
        initButton();

        JLabel loginbg = new JLabel(new ImageIcon(path+"background.png"));
        loginbg.setBounds(15,5,470,390);
        this.getContentPane().add(loginbg);
    }

    private void initName(){
        JLabel name = new JLabel(new ImageIcon(path+"uname.png"));
        name.setBounds(70,150,47,17);
        //用户名输入框
        username.setBounds(170,145,200,30);

        this.getContentPane().add(username);
        this.getContentPane().add(name);
    }

    private void initPasswd(){
        //密码
        JLabel password = new JLabel(new ImageIcon(path+"密码.png"));
        password.setBounds(70,200,47,17);
        passwd.setBounds(170,195,200,30);

        this.getContentPane().add(passwd);
        this.getContentPane().add(password);

        JLabel rePassword = new JLabel(new ImageIcon(path+"rePasswd.png"));
        rePassword.setBounds(50,250,96,17);
        rePasswd.setBounds(170,245,200,30);

        this.getContentPane().add(rePasswd);
        this.getContentPane().add(rePassword);


    }

    private void initButton(){
        register.setBounds(100,300,128,47);
        register.setIcon(new ImageIcon(path+"registerButton.png"));
        clear.setBounds(270,300,128,47);
        clear.setIcon(new ImageIcon(path+"clear.png"));

        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        clear.setBorderPainted(false);
        clear.setContentAreaFilled(false);
        clear.addMouseListener(this);
        this.getContentPane().add(clear);
    }

    private boolean checkPasswd(){

        String first=passwd.getText();
        String second = rePasswd.getText();
        //System.out.println(first+"a"+second);
        return first.equals(second);
    }


    private void saveAccount() throws IOException {
        String name = username.getText();
        String pass=passwd.getText();
        FileWriter account=new FileWriter("account.txt",true);
        account.write(name+":"+pass+"\n");
        account.close();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == register) {
            if (checkPasswd()) {
                try {
                    saveAccount();
                    dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                Error error = new Error("密码不一致");
            }
        }else if (e.getSource() == clear) {
            username.setText("");
            passwd.setText("");
            rePasswd.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon(path+"registerOn.png"));
        }else if (e.getSource() == clear) {
            clear.setIcon(new ImageIcon(path+"clearOn.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon(path+"registerButton.png"));
        }else if (e.getSource() == clear) {
            clear.setIcon(new ImageIcon(path+"clear.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}