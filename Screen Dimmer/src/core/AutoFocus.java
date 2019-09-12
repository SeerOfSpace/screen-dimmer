package core;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class AutoFocus extends Thread {
	
	private static boolean running;
	private HWND hwnd1;
	
	public AutoFocus(HWND hwnd) {
		hwnd1 = hwnd;
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void run() {
		running = true;
		while(running) {
			HWND hwnd2 = User32.INSTANCE.GetForegroundWindow();
			if(!hwnd1.equals(hwnd2)) {
				User32.INSTANCE.SetForegroundWindow(hwnd1);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void stopThread() {
		running = false;
	}
	
	public interface User32 extends StdCallLibrary {
	      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
	      HWND GetForegroundWindow();
	      boolean SetForegroundWindow(HWND hWnd);
	}
	
}
