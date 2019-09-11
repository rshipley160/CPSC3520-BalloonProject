import  org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;

import java.awt.*;

public class Balloon extends GameObject{
    private float radius = width;
    private int stringLength = 150;
    private long win;
    private int key;
    private int count = 0;

    public Balloon(int x, int y, int radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    public Balloon(int x, int y, int radius, Color color, long window)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.win = window;
    }

    public boolean keyPressed(int key)
    {
        return glfwGetKey(win, key) == GLFW_PRESS;
    }

    public void update (float delta)
    {
        count++;
        if(keyPressed(GLFW_KEY_LEFT) && key != GLFW_KEY_LEFT) {
            radius += 5;
            key = GLFW_KEY_LEFT;
            count = 0;
            System.out.println("Hit left");
        }
        else if (keyPressed(GLFW_KEY_RIGHT) && key != GLFW_KEY_RIGHT)
        {
            radius += 5;
            key = GLFW_KEY_RIGHT;
            count = 0;
            System.out.println("Hit right");
        }
        else if (count >= 10)
        {
            radius -= 10 * delta;
        }
    }

    public void draw()
    {
        GL11.glColor3f(r,g,b);


        GL11.glBegin(GL11.GL_POLYGON);
        //How fine the circle is drawn
        int grain = 60;
        for(int i =0; i <= grain; i++)
        {
            double angle =  2 * Math.PI * i / grain;
            double horiz = Math.cos(angle)*radius;
            double vert = Math.sin(angle)*radius;
            GL11.glVertex2d(x+horiz,y+vert);
        }
        GL11.glEnd();

        GL11.glColor3f(1f,1f,1f);

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x, y + radius);
        GL11.glVertex2f(x, y + radius + stringLength);
        GL11.glEnd();

    }
}
