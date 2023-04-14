package art.view;

import art.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArtPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JLabel demoLabel;
	private CanvasPanel canvas;
	private JButton blackButton;
	private JButton blueButton;
	private JButton brownButton;
	private JButton redButton;
	private JButton greenButton;
	private JButton purpleButton;
	private JButton yellowButton;
	private JButton choiceButton;
	private JButton saveButton;
	private JButton loadButton;
	
	public ArtPanel(Controller app)
	{
		super();
		
		this.app =app;
		this.canvas = new CanvasPanel(app);
		this.layout = new SpringLayout();
		this.demoLabel = new JLabel("Placeholder Text");
		this.saveButton = new JButton("Save Image?");
		this.loadButton = new JButton("Load Image?");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.ORANGE);
		this.add(demoLabel);
		this.add(canvas);
		this.add(loadButton);
		this.add(saveButton);
	}
	
	private void setupListeners()
	{
		
		canvas.addMouseListener(new MouseListener()
		{

			public void mouseClicked(MouseEvent click)
			{
				int x = click.getX();
				int y = click.getY();
				
				canvas.drawDot(x, y, 25);
			}
		
			public void mousePressed(MouseEvent press)
			{
				
			}
		
			public void mouseReleased(MouseEvent release)
			{
				canvas.resetPoint();
			}

			public void mouseEntered(MouseEvent enter)
			{
			
			}

			public void mouseExited(MouseEvent exit)
			{
				canvas.resetPoint();
			}
			
		});
		
		canvas.addMouseMotionListener(new MouseMotionListener()
		{
				
			public void mouseDragged(MouseEvent drag)
			{
				int x = drag.getX();
				int y = drag.getY();
				
				canvas.drawLine(x, y, 25);
			}

			public void mouseMoved(MouseEvent move)
			{
				int x = move.getX();
				int y = move.getY();
				
				String message = "tThe mouse is at X;" + x + " Y: " +  y;
				demoLabel.setText(message);
			}
			
		});
		
	
		
		saveButton.addActionListener(click -> canvas.saveImage());
		loadButton.addActionListener(click -> canvas.loadImage());
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, canvas, 34, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, canvas, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, canvas, -34, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, canvas, -750, SpringLayout.EAST, this);
	}
}
