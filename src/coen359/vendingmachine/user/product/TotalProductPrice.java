/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.product;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TotalProductPrice extends JPanel {

    JPanel panel;
    public JTextField textBoxQuantity;
    JLabel lab1;

    public TotalProductPrice(Double productPrice, String productName) {

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        lab1 = new JLabel(productName + "   :   " + productPrice);

        panel.add(lab1);

        textBoxQuantity = new JTextField();

        panel.add(textBoxQuantity);

        add(panel);

    }

}
