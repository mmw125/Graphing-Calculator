package graphingCalculator;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;

public class Calculator
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  public int height = 20;
  public DrawPanel dpn1;
  public Point pMouse = new Point();
  
  public Calculator(MainWindow controlls)
  {
    setTitle("Calculator");
    setLayout(null);
    setSize(1000, 1000);
    setDefaultCloseOperation(2);
    this.dpn1 = new DrawPanel();
    this.dpn1.setBounds(0, 0, getWidth(), getHeight());
    add(this.dpn1);
    setResizable(false);
    addMouseWheelListener(new MouseWheelListener()
    {
      public void mouseWheelMoved(MouseWheelEvent e)
      {
        Calculator.this.dpn1.zoom -= e.getWheelRotation();
        if (Calculator.this.dpn1.zoom <= 0.0D) {
          Calculator.this.dpn1.zoom = 1.0D;
        } else if (Calculator.this.dpn1.zoom >= 100.0D) {
          Calculator.this.dpn1.zoom = 100.0D;
        } else {
          Calculator.this.dpn1.paint();
        }
      }
    });
    addMouseMotionListener(new MouseMotionListener()
    {
      public void mouseMoved(MouseEvent e)
      {
        Calculator.this.pMouse = e.getPoint();
      }
      
      public void mouseDragged(MouseEvent e)
      {
        Calculator.this.dpn1.offset.x = (Calculator.this.dpn1.offset.x - Calculator.this.pMouse.x + e.getX());
        Calculator.this.dpn1.offset.y = (Calculator.this.dpn1.offset.y - Calculator.this.pMouse.y + e.getY());
        Calculator.this.pMouse = e.getPoint();
        Calculator.this.dpn1.paint();
      }
    });
  }
}