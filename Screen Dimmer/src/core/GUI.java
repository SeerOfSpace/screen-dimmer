package core;

import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

	private String version = "Screen Dimmer v2.3";
	private JFrame frmScreenDimmer;
	private int monitors;
	private Slider[] sliders;
	private JCheckBox checkBox;
	
	public GUI() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
		monitors = gd.length;
		initialize();
		frmScreenDimmer.pack();
	}

	private void initialize() {
		frmScreenDimmer = new JFrame();
		ArrayList<Image> list = new ArrayList<Image>();
		list.add(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icons/Light-bulb16.png")));
		list.add(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icons/Light-bulb32.png")));
		list.add(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icons/Light-bulb48.png")));
		list.add(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icons/Light-bulb256.png")));
		frmScreenDimmer.setIconImages(list);
		frmScreenDimmer.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {}
			public void windowLostFocus(WindowEvent arg0) {
				if(checkBox.isSelected()) {
					for(int i = 0; i < sliders.length; i++) {
						try {
							sliders[i].getWindow().getFrame().toFront();
						} catch(NullPointerException e1) {
							//e1.printStackTrace();
						}
					}
				}
			}
		});
		frmScreenDimmer.setTitle(version);
		frmScreenDimmer.getContentPane().setBackground(Color.DARK_GRAY);
		frmScreenDimmer.setBounds(100, 100, 450, 135);
		frmScreenDimmer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 372, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frmScreenDimmer.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		frmScreenDimmer.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0};
		gbl_panel_1.rowHeights = new int[] {0, 0};
		gbl_panel_1.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		sliders = new Slider[monitors];
	    for(int i = 0; i < monitors; i++) {
	    	sliders[i] = new Slider(i);
	    	GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 0, 5, 0);
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = i;
	    	panel_1.add(sliders[i], gbc);
	    }
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 1;
		frmScreenDimmer.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridx = 2;
		gbc_horizontalStrut.gridy = 1;
		frmScreenDimmer.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		frmScreenDimmer.getContentPane().add(panel, gbc_panel);
		
		JButton buttonF = new JButton("Focus");
		buttonF.setBackground(Color.GRAY);
		buttonF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < sliders.length; i++) {
					try {
						sliders[i].getWindow().getFrame().toFront();
					} catch(NullPointerException e1) {
						//e1.printStackTrace();
					}
				}
			}
		});
		panel.add(buttonF);
		
		checkBox = new JCheckBox("Always On Top");
		checkBox.setToolTipText("Experimental Feature");
		checkBox.setForeground(Color.LIGHT_GRAY);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected()) {
					//new AutoFocus(Main.window.getHWnd()).start();
					frmScreenDimmer.setAlwaysOnTop(true);
				} else {
					//AutoFocus.stopThread();
					frmScreenDimmer.setAlwaysOnTop(false);
				}
			}
		});
		panel.add(checkBox);
	}
	
	public JFrame getFrame() {
		return frmScreenDimmer;
	}

}
