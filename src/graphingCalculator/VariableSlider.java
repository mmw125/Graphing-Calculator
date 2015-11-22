package graphingCalculator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VariableSlider extends JPanel{
  private static final long serialVersionUID = 6663480961556120886L;
  JSlider slider;
  JTextField userEquation;
  JLabel number;
  
  public VariableSlider(final Calculator calculator){
    setLayout(null);
    this.userEquation = new JTextField();
    this.userEquation.setBounds(10, 0, 80, 16);
    this.number = new JLabel("0");
    this.number.setBounds(100, 0, 20, 16);
    this.slider = new JSlider(-20, 20, 0);
    this.slider.setBounds(120, 0, 300, 16);
    add(this.userEquation);
    add(this.number);
    add(this.slider);
    this.userEquation.addCaretListener(new CaretListener()
    {
      public void caretUpdate(CaretEvent arg0)
      {
        System.out.println(VariableSlider.this.userEquation.getText());
        calculator.dpn1.paint();
      }
    });
    this.slider.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent arg0)
      {
          number.setText(slider.getValue()+"");
        //Variable_Slider.this.number.setText(Variable_Slider.this.slider.getValue());
        calculator.dpn1.paint();
      }
    });
  }
  
  public double getValue()
  {
    double x = this.slider.getValue();
    return x;
  }
  
  public String getVar()
  {
    String name = this.userEquation.getText();
    return name;
  }
}