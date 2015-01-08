/**
 * 
 */
package com.stassociates.jwilliard.robotvision;

/**
 * @author Jack Williard
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Visualizer extends JFrame 
	implements ActionListener {

	private static final long serialVersionUID = 1L;

	class ImageJPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		ProcessedImage myImage = null;
		public void setMyImage(ProcessedImage img) {
			this.myImage = img;
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(this.myImage.bImage, 0, 0, null);
		}		
	};
	
	
	ProcessedImage myImage = new ProcessedImage();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Visualizer();
            }
        });
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.okButton) {
			System.exit(0);
		} else if (e.getSource() == this.loadFile) {
			JFileChooser loadFC = new JFileChooser();
			//loadFC.addChoosableFileFilter(new FileFilter("*.jpg"));
			loadFC.showOpenDialog(null);
			try {
				myImage.loadImage(loadFC.getSelectedFile());
				//this.imagePanel.set myImage.bImage.getHeight()
				//this.imagePanel.setPreferredSize(this.imagePanel.getPreferredSize());
				this.imagePanel.repaint();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error: File not Found");
			}
		} else if (e.getSource() == this.actionThreshold) {
			myImage.convertToGreyscale();
			myImage.thresholdImage(0xF5);
			this.imagePanel.repaint();
		}
		
	}
	

	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    Visualizer() {
    	//Create and set up the window.
    	super("Robot Vision Learning Tool");
    	this.setLayout(new BorderLayout());
    	this.setBackground(Color.GRAY);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	this.loadFile = new JButton("Load...");
    	this.loadFile.addActionListener(this);
    	this.add(loadFile, BorderLayout.PAGE_START);
    	
    	this.imagePanel = new ImageJPanel();
    	this.imagePanel.setMyImage(this.myImage);
    	this.add(imagePanel, BorderLayout.CENTER);
    	
    	this.actionPanel = new JPanel();
    	this.actionPanel.setLayout(new GridLayout(0,2));
    	this.add(actionPanel, BorderLayout.LINE_START);
    	
    	this.actionSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 127);
    	
    	//TODO: implements changelistener
    	//this.actionSlider.addChangeListener(this);
    	this.actionPanel.add(this.actionSlider);
    	
    	this.actionThreshold = new JButton("Threshold");
    	this.actionThreshold.addActionListener(this);
    	this.actionPanel.add(this.actionThreshold);
    	
    	this.okButton = new JButton("OK");
    	this.okButton.addActionListener(this);
    	this.add(okButton, BorderLayout.PAGE_END);
    	
    	pack();
    	setVisible(true);
    }
    
    JSlider actionSlider;
    JPanel actionPanel;
    JButton actionThreshold;
    ImageJPanel imagePanel;
    JButton loadFile;
    JButton okButton;

}
