package core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;

import java.awt.Color;

public class Window {

	private JFrame frame;
	private int monitor = 0;

	public Window() {
		initialize();
		setTransparent();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setLocation(0, 0);
		frame.setOpacity(0.5f);
		frame.setVisible(true);
		frame.pack();
	}
	
	private void setTransparent() {
	    WinDef.HWND hwnd = getHWnd();
	    int wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
	    wl = wl | WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT;
	    User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl);
	}
	
	public HWND getHWnd() {
		HWND hwnd = new HWND();
	    hwnd.setPointer(Native.getComponentPointer(frame));
	    return hwnd;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setTransparency(int t) {
		float t2 = (float) t/100;
		frame.setOpacity(t2);
	}
	
	public void showOnScreen( int screen) {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    if( screen > -1 && screen < gd.length ) {
	        frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().getLocation());
	        frame.setSize(gd[screen].getDefaultConfiguration().getBounds().getSize());
	    } else if( gd.length > 0 ) {
	        frame.setLocation(gd[0].getDefaultConfiguration().getBounds().getLocation());
	        frame.setSize(gd[screen].getDefaultConfiguration().getBounds().getSize());
	    } else {
	        throw new RuntimeException( "No Screens Found" );
	    }
	}
	
	public void switchMonitor() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    if(monitor+1 >= gd.length) {
	    	monitor = 0;
	    } else {
	    	monitor ++;
	    }
	    showOnScreen(monitor);
	}
	
}
