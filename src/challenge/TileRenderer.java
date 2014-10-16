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
    static float[][] colors;
    
    static {
        try {
            textures = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Tiles.png"), GL_NEAREST);
            map = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 1, 1},
                              {1, 1, 1, 1, 1, 1, 0, 1},
                              {1, 1, 1, 1, 1, 0, 0, 0},
                              {1, 1, 1, 1, 0, 0, 0, 0},
                              {1, 1, 1, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0}};
            
            heights = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 1, 1},
                                  {1, 1, 1, 1, 1, 1, 0, 1},
                                  {1, 1, 1, 1, 1, 0, 0, 0},
                                  {1, 1, 1, 1, 0, 0, 0, 0},
                                  {1, 1, 1, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0}};
            
            colors = new float[][]{{0.0f,0.5f,1.0f},
                                   {0.0f,1.0f,0.2f},
                                   {1.0f,0.8f,0.0f}};
        } catch (IOException ex) {
            Logger.getLogger(TileRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void render() {
        glBindTexture(GL_TEXTURE_2D, textures.getTextureID());
        
        glBegin(GL_QUADS);
        
        for(int y = 0; y < map.length-1; y++){
            for(int x = 0; x < map[0].length-1; x++){ 
                
                glColor3f(colors[map[y][x]][0], colors[map[y][x]][1], colors[map[y][x]][2]); glVertex3f(x, heights[y][x], y);
                glColor3f(colors[map[y+1][x]][0], colors[map[y+1][x]][1], colors[map[y+1][x]][2]); glVertex3f(x, heights[y+1][x], y+1);
                glColor3f(colors[map[y+1][x+1]][0], colors[map[y+1][x+1]][1], colors[map[y+1][x+1]][2]); glVertex3f(x+1, heights[y+1][x+1], y+1);
                glColor3f(colors[map[y][x+1]][0], colors[map[y][x+1]][1], colors[map[y][x+1]][2]); glVertex3f(x+1, heights[y][x+1], y);
            }
        }
        
        glEnd();
        
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
