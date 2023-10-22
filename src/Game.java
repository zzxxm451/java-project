import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener, ActionListener {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    Random random = new Random();

    public Game() {
        //初始化
        init();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();
        //随机选择图片
        ran();
        //初始化图片
        initImage();

        //添加键盘监听事件
        this.addKeyListener(this);
        //显示
        this.setVisible(true);


    }
    String[] pathway = {"animal","girl","sport"};
    String path = "image\\";
    int r = random.nextInt(3);
    int a;
    private void ran() {
        if(r==0) {
            a=random.nextInt(7);
        }else if (r==1) {
            a=random.nextInt(11);
        }else {
            a=random.nextInt(9);
        }
        ++a;
    }

    int step=0;

    private void initImage() {
        //清空已经加载图片
        this.getContentPane().removeAll();
        if(victory()){
            JLabel winJLabel=new JLabel(new ImageIcon(path+"win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        //计数器
        JLabel stepCount=new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //System.out.print(r+" "+a);
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path+pathway[r]+"\\"+pathway[r]+a+"\\"+ Array[i][j] +".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                //设置边框
                jLabel.setBorder(new BevelBorder(0));
                //把管理容器添加到界面中
                //先获取隐藏容器，再添加到隐藏容器
                this.getContentPane().add(jLabel);
            }
        }

        //背景图片   先加载的在上方
        JLabel bg = new JLabel(new ImageIcon(path+"background.png"));
        bg.setBounds(40,40,508,560);
        this.getContentPane().add(bg);
        //刷新
        this.getContentPane().repaint();
    }

    int[] Arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
    int[][] Array =new int [4][4];
    int x,y;

    //创建选项条目
    JMenuItem replay = new JMenuItem("重置");

    JMenuItem reLogin = new JMenuItem("重新登录");
    JMenuItem close = new JMenuItem("关闭");

    JMenuItem me = new JMenuItem("我");
    JMenu more = new JMenu("更多");

    //更换图片
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem girl = new JMenuItem("女孩");
    JMenuItem sport = new JMenuItem("运动");

    //乱序函数
    private void initData() {
        for(int i =0;i<Arr.length;i++) {
            int r = random.nextInt(Arr.length);
            int tem=Arr[i];
            Arr[i]=Arr[r];
            Arr[r]=tem;
        }

        for(int i=0;i<16;i++) {
            Array[i/4][i%4]=Arr[i];
            if(Arr[i]==0) {
                x=i/4;
                y=i%4;
            }
        }
    }

    //初始化页面
    private void init() {
        //登录页面
        this.setSize(603,680);
        //标题
        this.setTitle("拼图");
        //居中
        this.setLocationRelativeTo(null);
        //置顶
        //this.setAlwaysOnTop(true);
        //关闭
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
    }

    //初始化菜单
    private void initJMenuBar() {
        //菜单
        JMenuBar jmenubar = new JMenuBar();
        //菜单项
        JMenu fun = new JMenu("功能");
        JMenu about = new JMenu("关于");

        // 条目绑定事件
        replay.addActionListener(this);
        reLogin.addActionListener(this);
        close.addActionListener(this);
        me.addActionListener(this);
        more.addActionListener(this);

        animal.addActionListener(this);
        girl.addActionListener(this);
        sport.addActionListener(this);

        //条目添加到对应的菜单项
        fun.add(more);
        fun.add(replay);
        fun.add(reLogin);
        fun.add(close);

        about.add(me);

        more.add(animal);
        more.add(girl);
        more.add(sport);

        //将菜单项添加到菜单里
        jmenubar.add(fun);
        jmenubar.add(about);

        //设置菜单
        this.setJMenuBar(jmenubar);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    //按下不松会反复调用  查看效果图
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int code = e.getKeyCode();
        if(code==65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+pathway[r]+"\\"+pathway[r]+a+"\\" +"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            JLabel bg = new JLabel(new ImageIcon(path+"background.png"));
            bg.setBounds(40,40,508,560);
            this.getContentPane().add(bg);
            //刷新
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (victory() ) {
            return;
        }

        int code = e.getKeyCode();
        //System.out.print(code);
        switch (code){
            case 37://left
                if(y==3) {
                    break;
                }
                Array[x][y] = Array[x][y+1];
                Array[x][++y]=0;
                step++;
                break;
            case 38://on
                if(x==3) {
                    break;
                }
                Array[x][y] = Array[x+1][y];
                Array[++x][y]=0;
                step++;
                break;
            case 39://right
                if(y==0) {
                    break;
                }
                Array[x][y] = Array[x][y-1];
                Array[x][--y]=0;
                step++;
                break;
            case 40://under
                if(x==0) {
                    break;
                }
                Array[x][y] = Array[x-1][y];
                Array[--x][y]=0;
                step++;
                break;
            case 87://一键复原
                Array=new int[][] {
                        {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,0}
                };
                break;

            case 65:

        }
        initImage();

    }

    //胜利判断
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    private boolean victory(){
        for (int i = 0; i < win.length; i++) {
            for (int j = 0; j < win[i].length; j++) {
                if(win[i][j]!=Array[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取被点击对象
        Object obj=e.getSource();

        if (obj == replay) {
            step=0;
            initData();
            initImage();
        } else if (obj==reLogin) {
            new Login();
        } else if (obj==close) {
            System.exit(0);
        }else if(obj==me){
            JDialog jDialog=new JDialog();
            JLabel jLabel =new JLabel(new ImageIcon(path+"about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setLocationRelativeTo(null);
            jDialog.setAlwaysOnTop(true);
            //不关闭无法继续操作
            jDialog.setModal(true);
            //显示出来
            jDialog.setVisible(true);
        } else if (obj==animal) {
            r=0;
            step=0;
            ran();
            initData();
            initImage();
        }else if (obj == girl) {
            r=1;
            step=0;
            ran();
            initData();
            initImage();
        }else if (obj == sport) {
            r=2;
            step=0;
            ran();
            initData();
            initImage();
        }
    }
}