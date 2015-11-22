package graphingCalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar
  extends JMenuBar
{
  int height = 30;
  int equationHeight = 10;
  private static final long serialVersionUID = 1L;
  
  MenuBar(final MainWindow mainWindow)
  {
    JMenuItem exit = new JMenuItem("Exit");
    
    JMenuItem newEquation = new JMenuItem("New Equation");
    JMenuItem newIntegralEquation = new JMenuItem("New Integral Equation");
    JMenuItem newSlider = new JMenuItem("New Variable Slider");
    JMenu file = new JMenu("File");
    JCheckBoxMenuItem displaySlope = new JCheckBoxMenuItem("Display Slope");
    JMenu view = new JMenu("View");
    JCheckBoxMenuItem dispGrid = new JCheckBoxMenuItem("Display Grid");
    view.add(dispGrid);
    
    add(file);
    add(view);
    file.add(newEquation);
    

    file.add(newSlider);
    file.add(exit);
    dispGrid.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (mainWindow.calculator.dpn1.displayGrid)
        {
          mainWindow.calculator.dpn1.displayGrid = false;
          mainWindow.calculator.dpn1.paint();
        }
        else
        {
          mainWindow.calculator.dpn1.displayGrid = true;
          mainWindow.calculator.dpn1.paint();
        }
      }
    });
    exit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    });
    newSlider.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VariableSlider slider = new VariableSlider(mainWindow.calculator);
        slider.setBounds(0, MenuBar.this.equationHeight, MenuBar.this.getWidth(), MenuBar.this.height);
        mainWindow.calculator.dpn1.newSlider(slider);
        mainWindow.Main_Panel.add(slider);
        mainWindow.Main_Panel.repaint();
        MenuBar.this.equationHeight = (MenuBar.this.equationHeight + MenuBar.this.height + 5);
      }
    });
    newEquation.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Equation equation = new Equation(mainWindow.calculator, MenuBar.this.height);
        mainWindow.calculator.dpn1.newEquation(equation);
        equation.setBounds(0, MenuBar.this.equationHeight, 2000, 16);
        mainWindow.Main_Panel.add(equation);
        equation.setVisible(true);
        mainWindow.Main_Panel.repaint();
        MenuBar.this.equationHeight += 25;
      }
    });
    newIntegralEquation.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        IntegralEquation equation = new IntegralEquation(mainWindow.calculator, MenuBar.this.height);
        mainWindow.calculator.dpn1.newIntegralEquation(equation);
        equation.setBounds(0, MenuBar.this.equationHeight, 2000, 16);
        mainWindow.Main_Panel.add(equation);
        equation.setVisible(true);
        mainWindow.Main_Panel.repaint();
        MenuBar.this.equationHeight += 25;
      }
    });
    displaySlope.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (!mainWindow.calculator.dpn1.displaySlope) {
          mainWindow.calculator.dpn1.displaySlope = true;
        } else {
          mainWindow.calculator.dpn1.displaySlope = false;
        }
      }
    });
  }
}