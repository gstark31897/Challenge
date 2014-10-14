/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package challenge;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author gsta4786
 */
public class TileRenderer {
    static Texture textures;
    static int[][] map;
    static int[][] heights;
    
    static {
        try {
            textures = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Tiles.png"), GL_NEAREST);
            map = new int[][]{{3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 0, 1, 1, 1, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 0, 1, 1, 1, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1},
                              {3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
            
            heights = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        } catch (IOException ex) {
            Logger.getLogger(TileRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void render() {
        glBegin(GL_QUADS);
        
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[0].length; x++){ 
                int id = map[y][x];
                int height = heights[y][x];
                float tex = id*0.25f;
                glColor3f(1, 1, 1);
                glTexCoord2f(tex, 0.5f);         glVertex3f(x,   height, y);
                glTexCoord2f(tex, 0);            glVertex3f(x,   height, y+1);
                glTexCoord2f(tex + 0.25f, 0);    glVertex3f(x+1, height, y+1);
                glTexCoord2f(tex + 0.25f, 0.5f); glVertex3f(x+1, height, y);
                
                for(int i = 0; i < height; i++) {
                    glColor3f(0.5f, 0.5f, 0.5f);
                    glTexCoord2f(tex,       1f);   glVertex3f(x,   i,   y);
                    glTexCoord2f(tex,       0.5f); glVertex3f(x,   i+1, y);
                    glTexCoord2f(tex+0.25f, 0.5f); glVertex3f(x+1, i+1, y);
                    glTexCoord2f(tex+0.25f, 1f);   glVertex3f(x+1, i,   y);
                    
                    glColor3f(0.9f, 0.9f, 0.9f);
                    glTexCoord2f(tex,       1f);   glVertex3f(x+1, i,   y);
                    glTexCoord2f(tex,       0.5f); glVertex3f(x+1, i+1, y);
                    glTexCoord2f(tex+0.25f, 0.5f); glVertex3f(x+1, i+1, y+1);
                    glTexCoord2f(tex+0.25f, 1f);   glVertex3f(x+1, i,   y+1);
                }
            }
        }
        
        glEnd();
    }
}
