/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package challenge;

import java.awt.Rectangle;
import javax.vecmath.Vector2f;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author gsta4786
 */
public class Unit {
    float x, y;
    Vector2f t;
    boolean selected;
    
    public Unit(float x, float y) {
        this.x = x;
        this.y = y;
        t = new Vector2f(x, y);
        selected = false;
    }
    
    public void update() {
        if(((x < Cursor.p0.x && x > Cursor.p1.x) || (x > Cursor.p0.x && x < Cursor.p1.x)) && ((y < Cursor.p0.y && y > Cursor.p1.y) || (y > Cursor.p0.y && y < Cursor.p1.y))) {
            selected = true;
        }else{
            selected = false;
        }
        
        if(selected && Mouse.isButtonDown(1)) {
            t = Cursor.toWorld();
        } 
        
        if(Math.sqrt((x-t.x)*(x-t.x)+(y-t.y)*(y-t.y)) > Math.random()+0.25) {
            if(x > t.x)
                x-=0.1f;
            else if(x < t.x)
                x+=0.1f;

            if(y > t.y)
                y-=0.1f;
            else if(y < t.y)
                y+=0.1f;
        }
    }
    
    public void render() {
        glPushMatrix();
        glTranslatef(x, 0, y);
        
        glBegin(GL_QUADS);
        
        if(selected)
            glColor3f(1, 0, 0);
        else
            glColor3f(1, 1, 1);
        glVertex3f(-0.25f, 0, 0);
        glVertex3f(-0.25f, 1, 0);
        glVertex3f(0.25f, 1, 0);
        glVertex3f(0.25f, 0, 0);
        
        glEnd();
        glPopMatrix();
    }
}
