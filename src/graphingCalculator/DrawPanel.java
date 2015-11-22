package graphingCalculator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

class DrawPanel
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  public ArrayList<Equation> Equations = new ArrayList<Equation>();
  public ArrayList<VariableSlider> variable_Sliders = new ArrayList<VariableSlider>();
  public ArrayList<IntegralEquation> integralEquations = new ArrayList<IntegralEquation>();
  public Point offset = new Point();
  public double zoom = 10.0D;
  public boolean displayGrid = false;
  public boolean displaySlope = false;
  public static Point origin = new Point();
  
  public DrawPanel()
  {
    setBackground(Color.white);
    this.offset.x = 0;
    this.offset.y = 0;
    setDoubleBuffered(true);
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    origin.x = (getWidth() / 2);
    origin.y = (getHeight() / 2);
    Graphics2D g2d = (Graphics2D)g;
    g2d.setColor(Color.gray);
    if (this.displayGrid)
    {
      for (double x = (-origin.x - this.offset.x) / this.zoom; x < (origin.x - this.offset.x) / this.zoom; x += 0.01D) {
        if ((this.displayGrid) && (x % 1.0D < 0.01D) && (x % 1.0D > -0.01D))
        {
          double xA = x * this.zoom;
          g2d.drawLine((int)(origin.x + xA + this.offset.x), 0, (int)(origin.x + xA + this.offset.x), getHeight());
        }
      }
      for (double y = (-origin.y - this.offset.y) / this.zoom; y < (origin.y - this.offset.y) / this.zoom; y += 0.01D) {
        if ((this.displayGrid) && (y % 1.0D < 0.01D) && (y % 1.0D > -0.01D))
        {
          double yA = y * this.zoom;
          g2d.drawLine(0, (int)(origin.y + yA + this.offset.y), getHeight(), (int)(origin.y + yA + this.offset.y));
        }
      }
    }
    g2d.setColor(Color.black);
    float width = 5.0F;
    g2d.setStroke(new BasicStroke(width));
    g2d.drawLine(getWidth() / 2 + this.offset.x, 0, getWidth() / 2 + this.offset.x, getHeight());
    g2d.drawLine(0, getHeight() / 2 + this.offset.y, getWidth(), getHeight() / 2 + this.offset.y);
    width = 3.0F;
    g2d.setColor(Color.darkGray);
    g2d.setStroke(new BasicStroke(width));
    for (int a = 0; a < this.Equations.size(); a++)
    {
      Equation equation = (Equation)this.Equations.get(a);
      g2d.setColor(equation.getColor());
      double pX = 1000.0D;double pY = 0.0D;
      for (double x = (-origin.x - this.offset.x - this.zoom) / this.zoom; x < (origin.x - this.offset.x + this.zoom) / this.zoom; x += 0.1D)
      {
        equation.myParser.addVariable("x", x);
        for (int b = 0; b < this.variable_Sliders.size(); b++)
        {
          String variableName = ((VariableSlider)this.variable_Sliders.get(b)).getVar();
          Double variableValue = Double.valueOf(((VariableSlider)this.variable_Sliders.get(b)).getValue());
          equation.myParser.addVariable(variableName, variableValue);
        }
        double y = equation.myParser.getValue();
        y *= this.zoom;
        double xA = x * this.zoom;
        if (pX != 1000.0D) {
          g2d.drawLine((int)(origin.x + pX + this.offset.x), (int)(origin.y - pY + this.offset.y), (int)(origin.x + xA + this.offset.x), (int)(origin.y - y + this.offset.y));
        }
        pX = xA;
        pY = y;
      }
    }
    for (int a = 0; a < this.integralEquations.size(); a++)
    {
      IntegralEquation integralequation = (IntegralEquation)this.integralEquations.get(a);
      double pX = 1000.0D;double pY = 0.0D;
      for (double x = (-origin.x - this.offset.x - this.zoom) / this.zoom; x < (origin.x - this.offset.x + this.zoom) / this.zoom; x += 0.1D)
      {
        integralequation.equation.myParser.addVariable("x", x);
        for (int b = 0; b < this.variable_Sliders.size(); b++)
        {
          String variableName = ((VariableSlider)this.variable_Sliders.get(b)).getVar();
          Double variableValue = Double.valueOf(((VariableSlider)this.variable_Sliders.get(b)).getValue());
          integralequation.equation.myParser.addVariable(variableName, variableValue);
        }
        double y = integralequation.equation.myParser.getValue();
        y *= this.zoom;
        double xA = x * this.zoom;
        if (pX != 1000.0D) {
          g2d.drawLine((int)(origin.x + pX + this.offset.x), (int)(origin.y - pY + this.offset.y), (int)(origin.x + xA + this.offset.x), (int)(origin.y - y + this.offset.y));
        }
        pX = xA;
        pY = y;
      }
    }
  }
  
  public void newEquation(Equation equation)
  {
    this.Equations.add(equation);
    paintComponent(getGraphics());
  }
  
  public void newSlider(VariableSlider slider)
  {
    this.variable_Sliders.add(slider);
    paintComponent(getGraphics());
  }
  
  public void paint()
  {
    paintComponent(getGraphics());
  }
  
  public void newIntegralEquation(IntegralEquation equation)
  {
    this.integralEquations.add(equation);
    paintComponent(getGraphics());
  }
}
