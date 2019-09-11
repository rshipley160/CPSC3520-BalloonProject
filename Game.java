import org.lwjgl.opengl.GL;

import  org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.system.MemoryUtil.NULL;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.*;
import java.util.List;

public class Game {
    private static int width=600;
    private static int height=600;
    private static String title="Game";
    private List<GameObject> objList;
    private long window;

    public Game()
    {
        window = init();
        objList = new java.util.LinkedList<GameObject>();

        objList.add(new Balloon(width/2, height/2, 30, Color.red, window));
    }

    public boolean keyPressed(int key)
    {
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    // returns window id
    public long init()
    {
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(width, height, title, NULL, NULL);


        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        //set up OpenGL
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);

        // screen clear is white (this could go in drawFrame if you wanted it to change
        glClearColor(0.8f, 0.8f, 0.8f, 0.0f);

        // set projection to dimensions of window
        // set viewport to entire window
        GL11.glViewport(0,0,width,height);

        // set up orthographic projection to map world pixels to screen
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);




        return window;
    }

    public void drawFrame(float delta)
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        for (GameObject go : objList)
        {
            go.update(delta);
        }

        for (GameObject go : objList)
        {
            go.draw();

        }

    }

}
