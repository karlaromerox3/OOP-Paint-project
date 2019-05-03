/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintproject;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.util.LinkedList;

/**
 *
 * @author kdani
 */
public class Draw extends Canvas implements ActionListener, MouseListener, MouseMotionListener{
    
    private LinkedList rectangles = new LinkedList();
    private LinkedList circles = new LinkedList();
    private LinkedList shapes = new LinkedList();
    public Color color;
    public int stroke;
    private Path2D shape;
    private int uX, uY, x , y;
    public boolean brush = false, rectangle= false, circle=false;
    
    public Draw(){
        this.color = Color.black;
        this.setBackground(Color.white);
        this.shape = new Path2D.Float();
        this.stroke = 5;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D draw = (Graphics2D) g;
        
        for(int i = 0; i<this.rectangles.size(); i++){
            LinkedList insite = (LinkedList)this.rectangles.get(i);
            int t_x = Integer.parseInt(insite.get(0).toString());
            int t_y = Integer.parseInt(insite.get(1).toString());
            int s_x = Integer.parseInt(insite.get(2).toString());
            int s_y = Integer.parseInt(insite.get(3).toString());
            draw.setColor((Color)insite.get(4));
            draw.setStroke(new BasicStroke((Integer)insite.get(5)));  
            draw.drawRect(t_x,t_y, Math.abs(s_x-t_x) ,Math.abs(s_y-t_y));
        }
        
        for(int i = 0; i<this.circles.size(); i++){
            LinkedList insite = (LinkedList)this.circles.get(i);
            int t_x = Integer.parseInt(insite.get(0).toString());
            int t_y = Integer.parseInt(insite.get(1).toString());
            int s_x = Integer.parseInt(insite.get(2).toString());
            int s_y = Integer.parseInt(insite.get(3).toString());
            draw.setColor((Color)insite.get(4));
            draw.setStroke(new BasicStroke((Integer)insite.get(5)));  
            draw.drawOval(t_x,t_y,Math.abs(s_x-t_x),Math.abs(s_y-t_y));
        }
        for(int i = 0; i<this.shapes.size(); i++){
            LinkedList insite = (LinkedList)this.shapes.get(i);
            draw.setColor((Color)insite.get(1));
            draw.setStroke(new BasicStroke((Integer)insite.get(2)));
            draw.draw((Path2D)insite.get(0));
        }
        
        
        draw.setStroke(new BasicStroke(this.stroke));
        draw.setColor(this.color);
        draw.draw(this.shape);
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        this.uX= point.x;
        this.uY = point.y;
        if(this.brush)this.shape.moveTo(point.x,point.y);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if(this.rectangle)this.rectangles.add(addItem(this.uX, this.uY, this.x, this.y ));
       if(this.circle) this.circles.add(addItem(this.uX, this.uY, this.x, this.y));
       repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
   
    
    public void mouseDragged(MouseEvent e){
        Point point = e.getPoint();
        this.x = point.x;
        this.y= point.y;
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(this.color);
        g.setStroke(new BasicStroke(this.stroke));
        //if(this.rectangle) g.drawRect(this.uX, this.uY, point.x-this.uX, point.y-this.uY);
        //if(this.circle) g.drawOval(this.uX, this.uY, point.x-this.uX, point.y-this.uY);
        if(this.brush){
          this.shape.lineTo(this.x, this.y);
        }
        repaint();       
                
    }
    
    public void setColor(Color color){
       this.color = color;
    }
    private LinkedList addItem(int v1, int v2, int v3, int v4){
        LinkedList aux = new LinkedList();
        aux.add(v1);
        aux.add(v2);
        aux.add(v3);
        aux.add(v4);
        aux.add(this.color);
        aux.add(this.stroke);
        return aux;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
        public void changeColor(Color color){
        LinkedList aux = new LinkedList();
        aux.add(this.shape);
        aux.add(this.color);
        aux.add(this.stroke);
        this.color= color;
        this.shapes.add(aux);
        this.shape = new Path2D.Float();
        
    }
    
    public void changeStroke(int newStroke){
        LinkedList aux = new LinkedList();
        aux.add(this.shape);
        aux.add(this.color);
        aux.add(this.stroke);
        this.stroke= newStroke;
        this.shapes.add(aux);
        this.shape = new Path2D.Float();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}
