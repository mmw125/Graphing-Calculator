package graphingCalculator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow
  extends JFrame
{
  public JPanel Main_Panel;
  private static final long serialVersionUID = 1L;
  public final Calculator calculator;
  
  public MainWindow()
  {
    setResizable(false);
    this.calculator = new Calculator(this);
    this.calculator.setVisible(true);
    setTitle("Calc Application");
    setLayout(null);
    setSize(500, 500);
    setMinimumSize(getSize());
    setDefaultCloseOperation(2);
    MenuBar menuBar = new MenuBar(this);
    setJMenuBar(menuBar);
    this.Main_Panel = new JPanel();
    this.Main_Panel.setBounds(0, 0, getWidth(), getHeight());
    this.Main_Panel.setLayout(null);
    
    add(this.Main_Panel);
  }
  
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
      }
    });
  }
}