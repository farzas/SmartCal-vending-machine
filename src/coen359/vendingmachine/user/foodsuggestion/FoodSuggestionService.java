/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.foodsuggestion;

//import Product.ProductFrame;
//import Product.ProductList;
import coen359.vendingmachine.product.Product;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class FoodSuggestionService {

    private EntityManager manager;

    public FoodSuggestionService(EntityManager manager) {
        this.manager = manager;
    }

    public List<Object[]> readAll() {
        String queryStr = "SELECT  p.id,p.name ,p.type,p.imageLink,nf.calorie,nf.fat,nf.sugar,p.price,p.quantity "
                + "FROM Product p, "
                + "Nutritional_Info  nf where p.id=nf.id";
        Query query = manager.createQuery(queryStr, Product.class);
        List<Object[]> result = query.getResultList();
        
        return result;
    }
    
    public List<Object[]>readAllwithImage(List<Object[]> res){
        String image ="";
       Iterator<Object[]> itr = res.iterator();

        while (itr.hasNext()) {  
            Object[] objProduct = itr.next();
           
            image = (String) objProduct[3];
           // ImageIcon aboutIcon = new ImageIcon(image);
         ThumbnailIcon aboutIcon=   new ThumbnailIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(image)),80);
            objProduct[3]=aboutIcon ;
            //objProduct[9]=Boolean.FALSE ;
            
        }
        return res;
    }

    public List<Object[]> readSearchedProd(Integer calto, Integer calFrom, Integer fatto, Integer fatfrom, Integer sugarto, Integer sugarFrom, String ptype, String pid, String pname) {
     System.out.println("Calto :"+calto+" cal from "+calFrom +"Fats to :"+fatto+" fats from  "+fatfrom+"sugar to "+sugarto+"sugar from "+sugarFrom+"ptpe "+ptype+"pid "+pid+"pname "+pname);
       String queryStr = "SELECT p.id,p.name ,p.type,p.imageLink,nf.calorie,nf.fat,nf.sugar,p.price,p.quantity \n"
                + "FROM Product p,Nutritional_Info  nf where \n"
                + "nf.calorie between :calto and :calfrom and\n"
                + "nf.fat between :fatTo and :fatFfrom and\n"
                + "nf.sugar between :sugTo and :sugFrom and\n"
                + "p.type like :pType \n"
                 + " and p.id like :pid and p.name like :pname\n"
                + " and p.id=nf.id";
        Query query = manager.createQuery(queryStr, Product.class);
        query.setParameter("calto", calto);
        query.setParameter("calfrom", calFrom);
        query.setParameter("fatTo", fatto);
        query.setParameter("fatFfrom", fatfrom);
        query.setParameter("sugTo", sugarto);
        query.setParameter("sugFrom", sugarFrom);
        if (ptype.equals("All")){
            query.setParameter("pType", "%%");
        }else{
        query.setParameter("pType", "%" + ptype + "%");    
        }
        
       query.setParameter("pid","%"+  pid+"%" );
        query.setParameter("pname","%"+ pname+"%" );

        List<Object[]> result = query.getResultList();

        return result;
    }

    

}
