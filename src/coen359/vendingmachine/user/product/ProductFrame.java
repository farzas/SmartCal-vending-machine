/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.product;

/**
 *
 * @author DELL
 */
import coen359.vendingmachine.user.foodsuggestion.ThumbnailIcon;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coen359.vendingmachine.user.management.VendingMachineController;
import java.awt.Color;
import java.awt.Toolkit;

public class ProductFrame extends JPanel implements ActionListener {

    Object[] productFrameComponents ;
    VendingMachineController prCont;
    JCheckBox check;
    ProductFrame theProduct;
    JLabel lab1;
    JLabel lab2;

    public ProductFrame(String image, String ProductName, String ProductPrice, String productID, Integer quant) {
        super();
        productFrameComponents = new Object[10];
        prCont = new VendingMachineController(this);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(150, 150));

        check = new JCheckBox(ProductName +  "              ");// for tracing adding product I to it 

        panel.add(check);
        ThumbnailIcon warnIcon = new ThumbnailIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(image)), 180);
        //Icon warnIcon = new ImageIcon(getClass().getClassLoader().getResource(image));
        JButton button = new JButton(warnIcon);
        button.setPreferredSize(new Dimension(100, 100));
        panel.add(button);
        panel.setBackground(Color.WHITE);

        lab1 = new JLabel("Price : $" + ProductPrice + "");
        lab1.setSize(125, 15);
        lab1.setBackground(Color.WHITE);
        lab2 = new JLabel("Quantity : " + quant + "");
        lab2.setSize(125, 15);
        lab2.setBackground(Color.WHITE);
        panel.add(lab1);
        panel.add(lab2);
        productFrameComponents[0] = button;
        productFrameComponents[1] = check;
        productFrameComponents[2] = lab1;
        productFrameComponents[3] = panel;
        productFrameComponents[4] = productID;
        // System.out.println("productFrameComponents size : "+productFrameComponents[0].toString());
        check.addActionListener(this);
        add(panel);

        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("your have clicked mouse ");

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    public Object[] getProductObject() {
        return productFrameComponents;
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        System.out.println(productFrameComponents[4].toString());
        //}
        prCont.getNutrionalInfo(productFrameComponents[4].toString(), (JCheckBox) productFrameComponents[1], (JLabel) productFrameComponents[2]);

        System.out.println("Panel Clicked");
    }

    public JCheckBox getSelectedJCheck() {
        if (check.isSelected()) {
            return check;
        }
        return null;
    }

}
