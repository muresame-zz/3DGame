package com.gmail.lynx7478.game.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int width = 1600;
	private static final int height = 900;
	
	private static final int fps = 120;
	
	public static void createDisplay()
	{
		
		ContextAttribs att = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat(), att);
			Display.setTitle("Render Engine");
			Display.setFullscreen(true);
			Display.setResizable(false);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, width, height);
	}
	
	public static void updateDisplay()
	{
		Display.sync(fps);
		Display.update();
	}
	
	public static void closeDisplay()
	{
		Display.destroy();
	}

}
