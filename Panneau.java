package frac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.Console;

import javax.swing.JPanel;
import javax.swing.Box.Filler;

public class Panneau extends JPanel implements MouseListener, MouseMotionListener, ComponentListener, MouseWheelListener{

	private Boolean isClick, recalc;
	private int clicX0, clicY0, clicX1, clicY1;
	private double W, H, deltaX, deltaY, deltaX0, deltaY0, ratio, ratioOld;
	private BufferedImage bi;
	private BufferedImageOp bo;
	private double centerX, centerY;
	
	private RealImage		img;
	
	public Panneau()
	{
		isClick = false;
		recalc = true;
		clicX0 = clicY0 = 0;
		clicX1 = clicY1 = 0;
		
		img = new RealImage(0, 0, 2, 2);
				
		addMouseListener(this);
		addMouseMotionListener(this);
		addComponentListener(this);
		addMouseWheelListener(this);

	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		if (recalc){
			recalcMandel();
			recalc = false;
		}
		g2d.drawImage(bi, bo, 0, 0);

	}
	
	private void recalcMandel(){
		bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)bi.getGraphics();
		
		int w = getWidth();
		int h = getHeight();
				
		for (int x = 0; x < w; x++){
			for (int y = 0; y < h; y++){
				int mand = img.calcIter(x / (double)w, y / (double)h);
				
				int col = (int)(mand / (double)Mandel.NB_ITER_MAX * 255);
				if (col > 254) col = 0;
				g2d.setColor(new Color(col / 3, col / 2, col));
				g2d.fillRect(x, y, 1, 1);
			}
		}	
		ratioOld = ratio;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicX1 = e.getX();
		clicY1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		recalc = true;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
			deltaX -= e.getX() - clicX1;
			deltaY -= e.getY() - clicY1;

			img.doTranslate(-(e.getX() - clicX1) / (double)getWidth(),
					-(e.getY() - clicY1) / (double)getHeight());
			
			clicX1 = e.getX();
			clicY1 = e.getY();

			recalc = true;			
			repaint();
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {		
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		recalc = true;
		repaint();
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0)
		{
			img.doZoom(1.05);
		}
		if (e.getWheelRotation() > 0)
		{
			img.doZoom(0.95);
		}
		recalc = true;
		repaint();
	}
	
	
}
