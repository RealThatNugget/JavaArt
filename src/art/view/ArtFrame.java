package art.view;

import art.controller.Controller;
import javax.swing.JFrame;

public class ArtFrame extends JFrame
{
	private Controller app;
	private ArtPanel panel;
	
	public ArtFrame(Controller app)
	{
		super();
		
		this.app = app;
		this.panel = new ArtPanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setSize(1200, 900);
		this.setTitle("My Java Artwork");
		this.setContentPane(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
