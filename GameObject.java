import org.lwjgl.opengl.GL11;

public  class GameObject {

    protected float r;
    protected float g;
    protected float b;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    void update(float delta) { }

    void draw() {

        GL11.glColor3f(r,g,b);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x-width/2, y-height/2);
        GL11.glVertex2f(x+width/2, y-height/2);
        GL11.glVertex2f(x+width/2, y+height/2);
        GL11.glVertex2f(x-width/2, y+height/2);
        GL11.glEnd();
    }

}
