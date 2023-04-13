package art.controller;

import java.awt.Component;

import javax.swing.JOptionPane;
import art.view.ArtFrame;

public class Controller
{
	private ArtFrame frame;
	
	public void Controller()
	{
		this.frame = new ArtFrame(this);
	}
	
	public void start()
	{
		this.frame = new ArtFrame(this);
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(frame,  error.getMessage(), "Whoops?", JOptionPane.ERROR_MESSAGE);
	}
	
}
