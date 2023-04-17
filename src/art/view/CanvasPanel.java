package art.view;

import art.controller.Controller;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		this.currentCanvas = new BufferedImage(700, 1000, BufferedImage.TYPE_INT_ARGB);
		this.stamp = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		
		setupPanel();
		resetPoint();
	}
	
	public void resetPoint()
	{
		previousX = Integer.MIN_VALUE;
		previousY = Integer.MAX_VALUE;
	}
	
	public void drawDot(int currentX, int currentY, int width)
	{
		Graphics2D currentGraphics = currentCanvas.createGraphics();
		currentGraphics.setColor(currentColor);
		currentGraphics.setStroke(new BasicStroke(width));
		repaint();
	}
	
	public void drawLine(int currentX, int currentY, int width)
	{
		Graphics2D currentGraphics = currentCanvas.createGraphics();
		currentGraphics.setColor(currentColor);
		currentGraphics.setStroke(new BasicStroke(width));
		if (previousX == Integer.MIN_VALUE) 
		{
		currentGraphics.drawOval(currentX, currentY, width, width);
		}
		else
		{
			currentGraphics.drawLine(previousX, previousY, currentX, currentY);
		}
		
		previousX = currentX;
		previousY = currentY;
		repaint();
	}
	
	public void setupPanel()
	{
		this.setPreferredSize(new Dimension(700, 1000));
		this.setBackground(Color.RED);
		this.currentColor = Color.CYAN;
	}
	
	public void saveImage()
	{
		try
		{
			JFileChooser saveDialog = new JFileChooser();
			saveDialog.showSaveDialog(this);
			String savePath = saveDialog.getSelectedFile().getPath();
			
			if (!savePath.endsWith(".png"))
			{
				savePath += ".png";
			}
			
			ImageIO.write(currentCanvas, "PNG", new File(savePath));
		}
		catch (IOException | NullPointerException error)
		{
			app.handleError(error);
		}
	}
	
	public void loadImage()
	{
		try
		{
			JFileChooser imageChooser = new JFileChooser();
			imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files Only!!", ImageIO.getReaderFileSuffixes());
			
			if (imageChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				File resultingFile = imageChooser.getSelectedFile();
				BufferedImage updatedCanvas = ImageIO.read(resultingFile);
				currentCanvas = updatedCanvas;
				
				this.setPreferredSize(new Dimension(currentCanvas.getWidth(), currentCanvas.getHeight()));
				
			}
			
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(currentCanvas, 0, 0, null);
	}
	
}
