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
import javax.swing.border.BevelBorder;

/**
 *
 * @author namrata
 */
public class TotalProduct extends JPanel {

    Object[] totalProductComponents = new Object[10];
    JLabel lab1;
    JTextField textBoxQuantity;
    String prodName;

    public TotalProduct(Double productPrice, String productName, String prID) {

        JPanel panel = new JPanel(new FlowLayout());
        // panel.setBackground(Color.BLUE);
        //  panel.setSize(200, 150);
        //panel.setBounds(0, 0, 200,150);
        panel.setPreferredSize(new Dimension(270, 50));
        //panel.setBorder(new BevelBorder(BevelBorder.RAISED));
        lab1 = new JLabel(productName + ":$" + productPrice);
        panel.add(lab1);
        panel.setBackground(Color.WHITE);
        textBoxQuantity = new JTextField("1");
        textBoxQuantity.setPreferredSize(new Dimension(35, 25));
        this.prodName = productName;
        panel.add(textBoxQuantity);
        // System.out.println("productFrameComponents size : "+productFrameComponents[0].toString());

        add(panel);
        totalProductComponents[0] = textBoxQuantity;
        totalProductComponents[1] = lab1;
        totalProductComponents[2] = prID;
        totalProductComponents[3] = panel;
    }

    public Object[] getTotalProductComponent() {
        return totalProductComponents;
    }

    public String getProductNamePrice() {
        return lab1.getText();
    }

    public Integer getQuantity() {
        return Integer.parseInt(textBoxQuantity.getText().trim());
    }

    public void setQuantity(String s) {
        textBoxQuantity.setText(s);
    }

    public Integer getUpdatedQuantity() {
        return Integer.parseInt(textBoxQuantity.getText().trim());
    }

    public String getProductName() {
        return prodName;
    }

    public String getProductID() {
        return (String) totalProductComponents[2];
    }
}
