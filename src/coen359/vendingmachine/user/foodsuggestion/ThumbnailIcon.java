/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coen359.vendingmachine.user.foodsuggestion;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;

/**
 *
 * @author DELL
 */
public class ThumbnailIcon implements Icon {
int width, height;
  Image image;
   
  public ThumbnailIcon(Image image, int width) {
    this.image = image;
    this.width = width;
    this.height = (width * image.getHeight(null)) / image.getWidth(null);
  }
  
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
         g.drawImage(image, x, y, width, height, c);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
         return height;
    }
    
}
