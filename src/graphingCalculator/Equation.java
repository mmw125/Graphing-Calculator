package graphingCalculator;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.nfunk.jep.JEP;

public class Equation
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  JTextField userEquation;
  public JEP myParser;
  final JPanel colorPanel;
  
  Equation(final Calculator calculator, int height)
  {
    this.myParser = new JEP();
    setLayout(null);
    this.userEquation = new JTextField();
    this.userEquation.setToolTipText("Enter your equation here");
    this.userEquation.setBounds(30, 0, 450, 16);
    add(this.userEquation);
    this.myParser.addStandardConstants();
    this.myParser.addStandardFunctions();
    this.myParser.addComplex();
    this.myParser.setAllowUndeclared(true);
    this.myParser.setImplicitMul(true);
    this.myParser.setAllowAssignment(true);
    this.myParser.addVariable("x", 0.0D);
    this.userEquation.addCaretListener(new CaretListener()
    {
      public void caretUpdate(CaretEvent arg0)
      {
        Equation.this.myParser.parseExpression(Equation.this.userEquation.getText());
        calculator.dpn1.paint();
      }
    });
    this.colorPanel = new JPanel();
    this.colorPanel.setBounds(10, 3, 10, 10);
    this.colorPanel.setBackground(Color.BLACK);
    add(this.colorPanel);
    this.colorPanel.setToolTipText("Sets the color of the line on the graph");
    this.colorPanel.addMouseListener(new MouseListener()
    {
      public void mouseReleased(MouseEvent arg0) {}
      
      public void mousePressed(MouseEvent arg0) {}
      
      public void mouseExited(MouseEvent arg0) {}
      
      public void mouseEntered(MouseEvent arg0) {}
      
      public void mouseClicked(MouseEvent arg0)
      {
        Color color = JColorChooser.showDialog(new JPanel(), "Choose Color", Equation.this.colorPanel.getBackground());
        Equation.this.colorPanel.setBackground(color);
        calculator.dpn1.paint();
      }
    });
  }
  
  public Color getColor()
  {
    return this.colorPanel.getBackground();
  }
}