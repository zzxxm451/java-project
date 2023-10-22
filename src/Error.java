import javax.swing.*;
import java.awt.*;

public class Error extends JDialog {

    public Error(String errorMessage){

        this.init(errorMessage);
    }
    private void init(String errorMessage){
        this.setTitle("错误");
        this.setSize(210,90);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        JLabel e=new JLabel();
        e.setText(errorMessage);
        e.setFont(new Font("微软雅黑", Font.BOLD, 16));
        e.setForeground(Color.red);
        e.setHorizontalAlignment(SwingConstants.CENTER);

        this.getContentPane().add(e);


        this.setVisible(true);
    }


}
