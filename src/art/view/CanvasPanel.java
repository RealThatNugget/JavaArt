package art.view;

import art.controller.Controller;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CanvasPanel extends JPanel
{
	private Controller app;
	private Color currentColor;
	private BufferedImage currentCanvas;
	private BufferedImage stamp;
	private int previousX;
	private int previousY;
	
	public CanvasPanel(Controller app)
	{
		super();
		
		this.app = app;
		this.currentCanvas = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
		this.stamp = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		
		setupPanel();
		resetPoint();
	}
	
	public void resetPoint()
	{
		previousX = Integer.MIN_VALUE;
		previousY = Integer.MAX_VALUE;
	}
	
	public void setupPanel()
	{
		this.setPreferredSize(new Dimension(700, 700));
		this.setBackground(Color.RED);
		this.currentColor = Color.CYAN;
	}
	
}
