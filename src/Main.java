import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    JPanel mainPanel;
    public Main(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setVisible(true);
    }
}