package com.gmail.lynx7478.game;

import org.lwjgl.opengl.Display;

import com.gmail.lynx7478.game.engine.DisplayManager;
import com.gmail.lynx7478.game.engine.Loader;
import com.gmail.lynx7478.game.engine.Model;
import com.gmail.lynx7478.game.engine.Renderer;
import com.gmail.lynx7478.game.shaders.StaticShader;

public class Main {

	public static void main(String[] args) 
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				  -0.5f, 1.0f, 0,
				  -0.5f, -0.5f, 0,
				};
				  
				int[] indices = {
				  0,1,3,
				};
		
		Model model = loader.load(vertices, indices);
		
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
			System.out.println("Redering...");
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
