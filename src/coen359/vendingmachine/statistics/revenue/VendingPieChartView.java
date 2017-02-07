package coen359.vendingmachine.statistics.revenue;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class VendingPieChartView extends JPanel
   implements Observer {
	public VendingPieChartView() {
	}

   /**
	 * 
	 */
	private static final long serialVersionUID = -2189851182115739872L;

// Set of observed Vending machines
   private List vendings = new ArrayList();

   // Map of Colors for drawing pie chart wedges
   private Map colors = new HashMap();

   // add Vending machine to pie chart view
   public void addVending(VendingMachineStats stats)
   {
      // do not add null Vending machines
      if ( stats == null )
         throw new NullPointerException();

      // add Vending machine statistics to vendings Vector
      vendings.add(stats);

      // add Color to Hashtable for drawing Vending machine's wedge
      colors.put(stats,getRandomColor());

      // register as Observer to receive Vending machine updates
      stats.addObserver( this );

      // update display with new Vending machine information
      repaint();
   }

   // remove Vending machine from pie chart view
   public void removeVending(VendingMachineStats stats)
   {
      // stop receiving updates from given Vending machine
      stats.deleteObserver( this );

      // remove Vending machine from accounts Vector
      vendings.remove(stats);

      // remove Vending machine's Color from Hashtable
      colors.remove(stats);

      // update display to remove Vending machine information
      repaint();
   }

   // draw Vending machine revenues in a pie chart
   public void paintComponent( Graphics g )
   {
      // ensure proper painting sequence
      super.paintComponent( g );

      // draw pie chart
      drawPieChart( g );

      // draw legend to describe pie chart wedges
      drawLegend( g );
   }

   // draw pie chart on given Graphics context
   private void drawPieChart( Graphics g )
   {
      // get combined Vending machine revenue
      double totalBalance = getTotalRevenue();

      // create temporary variables for pie chart calculations
      double percentage = 0.0;
      int startAngle = 0;
      int arcAngle = 0;

      Iterator vendingIterator = vendings.iterator();
      VendingMachineStats stats = null;

      // draw pie wedge for each Vending machine
      while (vendingIterator.hasNext() ) {

         // get next Vending machine from Iterator
         stats = (VendingMachineStats) vendingIterator.next();

         // draw wedges only for included Vending machines
         if ( !includeVendingInChart(stats) )
            continue;

         // get percentage of total balance held in Vending machine
         percentage = stats.getRevenue() / totalBalance;

         // calculate arc angle for percentage
         arcAngle = ( int ) Math.round( percentage * 360 );

         // set drawing Color for Vending machine pie wedge
         g.setColor( ( Color ) colors.get(stats) );

         // draw Vending machine pie wedge
         //g.fillArc( 5, 5, 100, 100, startAngle, arcAngle );
         g.fillArc( 5, 5, 150, 150, startAngle, arcAngle );

         // calculate startAngle for next pie wedge
         startAngle += arcAngle;
      }
   } // end method drawPieChart

   // draw pie chart legend on given Graphics context
   private void drawLegend( Graphics g )
   {
      Iterator vendingsIterator = vendings.iterator();
      VendingMachineStats stats = null;

      // create Font for Vending name
      Font font = new Font( "SansSerif", Font.BOLD, 22 );
      g.setFont( font );

      // get FontMetrics for calculating offsets and
      // positioning descriptions
      FontMetrics metrics = getFontMetrics( font );
      int ascent = metrics.getMaxAscent();
      int offsetY = ascent + 2;

      // draw description for each Vending machine
      for ( int i = 1; vendingsIterator.hasNext(); i++ ) {

         // get next Vending machine from Iterator
         stats = (VendingMachineStats) vendingsIterator.next();

         // draw Vending machine color swatch at next offset
         g.setColor( ( Color ) colors.get(stats) );
         g.fillRect( 175, offsetY * i, 2*ascent, ascent );

         // draw Vending machine name next to color swatch
         g.setColor( Color.black );
         g.drawString(stats.getVendingID(), 175 + 2*ascent + 10,
            offsetY * i + ascent );
      }
   } // end method drawLegend

   // get combined revenue of all observed Vending machines
   private double getTotalRevenue()
   {
      double sum = 0.0;

      Iterator vendingsIterator = vendings.iterator();
      VendingMachineStats stats = null;

      // calculate total revenue
      while ( vendingsIterator.hasNext() ) {
         stats = (VendingMachineStats) vendingsIterator.next();

         // add only included Vending machines to sum
         if ( includeVendingInChart(stats) )
            sum += stats.getRevenue();
      }

      return sum;
   }

   // return true if given Vending machine should be included in
   // pie chart
   protected boolean includeVendingInChart(VendingMachineStats stats)
   {
      // include only Vending machines with positive revenue 
      return stats.getRevenue() > 0.0;
   }

   // get a random Color for drawing pie wedges
   private Color getRandomColor()
   {
      // calculate random red, green and blue values
      int red = ( int ) ( Math.random() * 256 );
      int green = ( int ) ( Math.random() * 256 );
      int blue = ( int ) ( Math.random() * 256 );

      // return newly created Color
      return new Color( red, green, blue );
   }

   // receive updates from Observable Vending machine
   public void update( Observable observable, Object object )
   {
       repaint();
   }

   // get VendingPieChartView's preferred size
   public Dimension getPreferredSize()
   {
      return new Dimension( 510, 310 );
   }

   // get VendingPieChartView's preferred size
   public Dimension getMinimumSize()
   {
      return getPreferredSize();
   }

   // get VendingPieChartView's preferred size
   public Dimension getMaximumSize()
   {
      return getPreferredSize();
   }
}

