package art.view;

import art.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Hashtable;

public class ArtPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JLabel demoLabel;
	private CanvasPanel canvas;
	private JPanel menuPanel;
	private JPanel colorPanel;
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
	private JButton eraseButton;
	private final int MINIMUM_WIDTH = 1;
	private final int MAXIMUM_WIDTH = 50;
	private JSlider widthSlider;
	
	public ArtPanel(Controller app)
	{
		super();
		
		this.app =app;
		this.canvas = new CanvasPanel(app);
		this.layout = new SpringLayout();
		this.demoLabel = new JLabel("Placeholder Text");
		this.saveButton = new JButton("Save Image?");
		this.loadButton = new JButton("Load Image?");
		this.menuPanel = new JPanel(new GridLayout(0,1));
		this.colorPanel = new JPanel(new GridLayout(0,2));
		this.blackButton = new JButton("Black");
		this.blueButton = new JButton("Blue");
		this.brownButton = new JButton("Cyan");
		this.redButton = new JButton("Red");
		this.greenButton = new JButton("Green");
		this.purpleButton = new JButton("Pink");
		this.yellowButton = new JButton("Yellow");
		this.choiceButton = new JButton("Choose Your Color");
		this.eraseButton = new JButton("Erase Canvas");
		this.widthSlider = new JSlider(MINIMUM_WIDTH);
		
		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setLabelTable()
	{
		
	}

	
	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.ORANGE);
		this.add(demoLabel);
		this.add(canvas);
		this.add(menuPanel);
		menuPanel.add(loadButton);
		menuPanel.add(saveButton);
		menuPanel.add(eraseButton);
		this.add(colorPanel);
		colorPanel.add(blackButton);
		colorPanel.add(blueButton);
		colorPanel.add(brownButton);
		colorPanel.add(redButton);
		colorPanel.add(greenButton);
		colorPanel.add(purpleButton);
		colorPanel.add(yellowButton);
		colorPanel.add(choiceButton);
	}
	
	public void updateColor()
	{
		Color userColor = JColorChooser.showDialog(this, "Pick your color!", Color.WHITE);
		canvas.setColor(userColor);
		choiceButton.setBackground(userColor);
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
				
				String message = "The mouse is at X;" + x + " Y: " +  y;
				demoLabel.setText(message);
			}
			
		});
		
	
		
		saveButton.addActionListener(click -> canvas.saveImage());
		loadButton.addActionListener(click -> canvas.loadImage());
		eraseButton.addActionListener(click -> canvas.eraseCanvas());
		blackButton.addActionListener(click -> canvas.setColor(Color.BLACK));
		blueButton.addActionListener(click -> canvas.setColor(Color.BLUE));
		brownButton.addActionListener(click -> canvas.setColor(Color.CYAN));
		redButton.addActionListener(click -> canvas.setColor(Color.RED));
		greenButton.addActionListener(click -> canvas.setColor(Color.GREEN));
		purpleButton.addActionListener(click -> canvas.setColor(Color.PINK));
		yellowButton.addActionListener(click -> canvas.setColor(Color.YELLOW));
		choiceButton.addActionListener(click -> updateColor());
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, canvas, 34, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, canvas, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, canvas, -34, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, canvas, -750, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 86, SpringLayout.SOUTH, colorPanel);
		layout.putConstraint(SpringLayout.NORTH, colorPanel, 17, SpringLayout.NORTH, this);
	}
}
