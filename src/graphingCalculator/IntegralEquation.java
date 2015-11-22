package graphingCalculator;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.nfunk.jep.JEP;

public class IntegralEquation
  extends JPanel
{
  private static final long serialVersionUID = -587395925753253053L;
  JTextField integralStart;
  JTextField integralEnd;
  public JEP myParser;
  public Equation equation;
  
  IntegralEquation(Calculator calculator, int Height)
  {
    this.equation = new Equation(calculator, Height);
    this.equation.setBounds(200, 0, 450, 16);
    add(this.equation);
    this.equation.userEquation.setBounds(30, 0, 250, 16);
    JLabel integral = new JLabel("Intergral from");
    integral.setBounds(10, 0, 80, 16);
    add(integral);
    




    this.integralStart = new JTextField();
    this.integralStart.setBounds(95, 0, 20, 16);
    add(this.integralStart);
    final JLabel number = new JLabel();
    number.setBounds(130, 0, 30, 16);
    add(number);
    this.integralStart.addCaretListener(new CaretListener()
    {
      public void caretUpdate(CaretEvent arg0)
      {
        number.setText(IntegralEquation.this.integralStart.getText());
      }
    });
  }
}
