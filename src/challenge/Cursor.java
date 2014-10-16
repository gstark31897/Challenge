/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package challenge;

import javax.vecmath.Vector2f;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 *
 * @author gsta4786
 */
public class Cursor {
    static boolean firstPoint;
    static Vector2f p0;
    static Vector2f p1;
    
    static {
        p0 = new Vector2f(0, 0);
        p1 = new Vector2f(0, 0);
    }
    
    public static void update() {
        if(Mouse.isButtonDown(0)) {
            if(!firstPoint) {
                p0 = toWorld();
                firstPoint = true;
            }
            
            p1 = toWorld();
        }else if(Mouse.isButtonDown(1)) {
            p0.x = 0;
            p0.y = 0;
            p1.x = 0;
            p1.y = 0;
        }else{
            firstPoint = false;
        }
    }
    
    public static void render() {
        glDisable(GL_DEPTH_TEST);
        
        glBegin(GL_LINES);
        
        glColor3f(1,0,0);
        glVertex3f(p0.x, 0, p0.y);
        glVertex3f(p0.x, 0, p1.y);
        
        glVertex3f(p0.x, 0, p1.y);
        glVertex3f(p1.x, 0, p1.y);
        
        glVertex3f(p1.x, 0, p1.y);
        glVertex3f(p1.x, 0, p0.y);
        
        glVertex3f(p1.x, 0, p0.y);
        glVertex3f(p0.x, 0, p0.y);
        
        glEnd();
        
        glEnable(GL_DEPTH_TEST);
    }
    
    public static Vector2f toWorld() {
        float x = 0;
        float y = 0;
        if(Main.tacView) {
            x = (Mouse.getX()-Display.getWidth()/2)/Main.z - Main.x;
            y = (Mouse.getY()-Display.getHeight()/2)/Main.z - Main.y;
        }else{
            x = (Mouse.getX()*0.7f - Mouse.getY() -Display.getWidth()/2*0.7f +Display.getHeight()/2*0.7f)/Main.z - Main.x*0.7f + Main.y*1.4f;
            y = (Mouse.getX()*0.7f + Mouse.getY() -Display.getWidth()/2*0.7f -Display.getHeight()/2*0.7f)/Main.z - Main.x*0.7f - Main.y*1.4f;
        }
        
        return new Vector2f(x, y);
    }
}
