package core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Slider extends JPanel{

	private static final long serialVersionUID = 1L;
	private JSlider slider;
	private Window window;
	private int monitor;
	
	public Slider() {
		super();
		monitor = 0;
		initialize();
	}
	
	public Slider(int monitor) {
		super();
		this.monitor = monitor;
		initialize();
	}
	
	private void initialize() {
		this.setBounds(100, 100, 450, 35);
		this.setBackground(Color.DARK_GRAY);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{44, 57, 0};
		gbl_panel.rowHeights = new int[]{26, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel);
		
		final JCheckBox chckbxEnable = new JCheckBox("Enable");
		chckbxEnable.setForeground(Color.LIGHT_GRAY);
		chckbxEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxEnable.isSelected()) {
					window = new Window();
					window.showOnScreen(monitor);
					window.setTransparency(slider.getValue());
					slider.setEnabled(true);
				} else {
					window.getFrame().dispose();
					slider.setEnabled(false);
				}
			}
		});
		GridBagConstraints gbc_chckbxEnable = new GridBagConstraints();
		gbc_chckbxEnable.anchor = GridBagConstraints.WEST;
		gbc_chckbxEnable.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxEnable.gridx = 0;
		gbc_chckbxEnable.gridy = 0;
		this.add(chckbxEnable, gbc_chckbxEnable);
		
		slider = new JSlider();
		slider.setBackground(Color.DARK_GRAY);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				window.setTransparency(slider.getValue());
			}
		});
		slider.setEnabled(false);
		slider.setPreferredSize(new Dimension(300,26));
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		this.add(slider, gbc_slider);
	}
	
	public Window getWindow() {
		return window;
	}

}
