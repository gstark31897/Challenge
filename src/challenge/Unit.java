/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package challenge;

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
    
    public Unit(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void render() {
        glPushMatrix();
        glTranslatef(x, 0, y);
        
        glBegin(GL_QUADS);
        
        glColor3f(1, 1, 1);
        glVertex3f(-0.25f, 0, 0);
        glVertex3f(-0.25f, 1, 0);
        glVertex3f(0.25f, 1, 0);
        glVertex3f(0.25f, 0, 0);
        
        glEnd();
        glPopMatrix();
    }
}
