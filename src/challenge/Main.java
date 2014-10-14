/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package challenge;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author gavinstark
 */
public class Main {
    static DisplayMode[] displayModes;
    static int displayMode = 4;
    static int i = 0;
    static double sy = 0;
    static float x, y, z;
    static boolean tacView = false;
    static boolean idle = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LWJGLException {
        System.out.println(-1.0019908f * 0.851594f);
        displayModes = Display.getAvailableDisplayModes();
        Display.setDisplayMode(displayModes[displayMode]);
        Display.setFullscreen(false);
        Display.setVSyncEnabled(true);
        Display.create();
        
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, displayModes[displayMode].getWidth(), 0, displayModes[displayMode].getHeight(), 600, -600);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 0);
        
        glEnable(GL_TEXTURE_2D);
        
        glEnable(GL_DEPTH_TEST);
        System.out.println("Running");
        
        x = 0;
        y = 0;
        z = 20;
        
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            
            if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                y-=2/z;
            }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                y+=2/z;
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                x-=2/z;
            }else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                x+=2/z;
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_EQUALS)) {
                z+=1;
            }else if(Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
                z-=1;
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_T) && idle) {
                tacView = !tacView;
                idle = false;
            }else if(!Keyboard.isKeyDown(Keyboard.KEY_T)) {
                idle = true;
            }
            
            glPushMatrix();
            
            if(!tacView) {
                glRotatef(45, -1, 0, 0);

                glTranslatef(displayModes[displayMode].getWidth()/2, 0, displayModes[displayMode].getHeight()/2);
                glScalef(z, z, z);
                glTranslatef(x, y, y);

                glRotatef(45, 0, 1, 0);
            }else{
                glRotatef(90, -1, 0, 0);
                
                glTranslatef(displayModes[displayMode].getWidth()/2, 0, displayModes[displayMode].getHeight()/2);
                glScalef(z, z, z);
                glTranslatef(x, 0, y);
            }
            
            TileRenderer.render();
            
            Cursor.update();
            Cursor.render();
            
            glPopMatrix();
            
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
    }
    
}
