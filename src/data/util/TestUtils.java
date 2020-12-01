package data.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestUtils {

	//分类一下方法
	
	public static String getCurrentTime() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	    Date date = new Date(System.currentTimeMillis());
	    String currentTime = formatter.format(date);
	    return currentTime;
	}
	
	public static void setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }
	
	public static boolean getRememberPasswordFlag() {
		Properties info  = new Properties();
		try {
			info.load(new FileInputStream("profiles/data/sql.properties"));
			boolean remember_password_flag = Boolean.valueOf(info.getProperty("remember_password"));
			return remember_password_flag;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static Rectangle getResolution() {
		Rectangle r = new Rectangle();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize =  kit. getScreenSize();
		int screenWidth = screensize. width;
		int screenHeight = screensize. height;
		
		r.setSize(screenWidth,screenHeight);
		
		return r;
	}
}
