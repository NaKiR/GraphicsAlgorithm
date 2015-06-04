import javax.swing.*;

public class MainClass {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainWindow view = new MainWindow();
    }
}
