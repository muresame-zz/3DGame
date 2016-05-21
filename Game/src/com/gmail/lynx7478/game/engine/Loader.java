package com.gmail.lynx7478.game.engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	
	public Model load(float[] position, int[] indices)
	{
		int id = create();
		bindIndices(indices);
		store(0, position);
		unbind();
		return new Model(id, indices.length);
	}
	
	private void bindIndices(int[] indices)
	{
		int id = GL15.glGenBuffers();
		vbos.add(id);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
		IntBuffer buffer = storeIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private int create()
	{
		int id = GL30.glGenVertexArrays();
		vaos.add(id);
		GL30.glBindVertexArray(id);
		return id;
	}
	
	public void cleanUp()
	{
		for(int i : vaos)
		{
			GL30.glDeleteVertexArrays(i);
		}
		
		for(int i : vbos)
		{
			GL15.glDeleteBuffers(i);
		}
	}
	
	private void store(int att, float[] data)
	{
		int id = GL15.glGenBuffers();
		vbos.add(id);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		FloatBuffer buffer = convert(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(att, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbind()
	{
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer convert(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
