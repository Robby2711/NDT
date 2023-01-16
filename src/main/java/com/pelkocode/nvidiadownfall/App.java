package com.pelkocode.nvidiadownfall;


import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */

    public void print(String phrase) {
        System.out.println(phrase);
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");


        System.out.println("running...");
		Document document4090;
        Document document4080;
        Document document4070;
	    /********************test for NEWEGG*********************************/
        try {

            //Get 4090
			document4090 = Jsoup.connect("https://www.newegg.com/p/pl?N=4814%20601408872%20100007709&d=4090&Order=1").get();
            Elements entries4090 = document4090.select("li.price-current strong");
            List<String> prices4090 = entries4090.eachText();

			//Get 4080
			document4080 = Jsoup.connect("https://www.newegg.com/p/pl?N=4814%20601408872%20100007709&d=4080&Order=1").get();
            Elements entries4080 = document4080.select("li.price-current strong");
            List<String> prices4080 = entries4080.eachText();

            //Get 4080
			document4070 = Jsoup.connect("https://www.newegg.com/p/pl?N=601408872%20100007709&d=4070&Order=1").get();
            Elements entries4070 = document4070.select("li.price-current strong");
            List<String> prices4070 = entries4070.eachText();






            int averagePrice4070 = 0;
            int lowPrice4070 = 0;
            int highPrice4070 = 0;
            for (int i=0;i< prices4070.size(); i++) {
                averagePrice4070 += Integer.parseInt(prices4070.get(i).replace(",", ""));
            }
            averagePrice4070 = averagePrice4070/prices4070.size();
            lowPrice4070 = Integer.parseInt(prices4070.get(0).replace(",", ""));
            highPrice4070 = Integer.parseInt(prices4070.get(prices4070.size()-1).replace(",", ""));
            System.out.println("4070 AVERAGE LOW HIGH ---- " + averagePrice4070 + " " + lowPrice4070 + " " + highPrice4070);

            
            int averagePrice4080 = 0;
            int lowPrice4080 = 0;
            int highPrice4080 = 0;
            for (int i=0;i< prices4080.size(); i++) {
                averagePrice4080 += Integer.parseInt(prices4080.get(i).replace(",", ""));
            }
            averagePrice4080 = averagePrice4080/prices4080.size();
            lowPrice4080 = Integer.parseInt(prices4080.get(0).replace(",", ""));
            highPrice4080 = Integer.parseInt(prices4080.get(prices4080.size()-1).replace(",", ""));
            System.out.println("4080 AVERAGE LOW HIGH ---- " + averagePrice4080 + " " + lowPrice4080 + " " + highPrice4080);
		
            
            int averagePrice4090 = 0;
            int lowPrice4090 = 0;
            int highPrice4090 = 0;
            for (int i=0;i< prices4090.size(); i++) {
                averagePrice4090 += Integer.parseInt(prices4090.get(i).replace(",", ""));
            }
            averagePrice4090 = averagePrice4090/prices4090.size();
            lowPrice4090 = Integer.parseInt(prices4090.get(0).replace(",", ""));
            highPrice4090 = Integer.parseInt(prices4090.get(prices4090.size()-1).replace(",", ""));
            System.out.println("4090 AVERAGE LOW HIGH ---- " + averagePrice4090 + " " + lowPrice4090 + " " + highPrice4090);



            try { 
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                 } catch (Exception e) {
                    System.out.println(e);
              }
    
    
                String url = "jdbc:mysql://localhost:3306/webscrapetester"; 
                Connection conn = DriverManager.getConnection(url,"root",""); 
                Statement st = conn.createStatement(); 
                
                st.executeUpdate("INSERT INTO prices (GPU_ID, High, Low, Average) " + 
                    "VALUES (1, " + highPrice4070 + ", " +  lowPrice4070 + ", " + averagePrice4070 + ")");
                st.executeUpdate("INSERT INTO prices (GPU_ID, High, Low, Average)" + 
                    "VALUES (2, " + highPrice4080 + ", " +  lowPrice4080 + ", " + averagePrice4080 + ")");
                st.executeUpdate("INSERT INTO prices (GPU_ID, High, Low, Average)" + 
                    "VALUES (3, " + highPrice4090 + ", " +  lowPrice4090 + ", " + averagePrice4090 + ")");
    
                conn.close(); 
            } catch (Exception e) { 
                System.err.println("Got an MYSQL exception! "); 
                System.err.println(e.getMessage()); 
            }



		} catch (Exception e) {
			e.printStackTrace();
		}



        //INSERT DATA INTO THE DATABASE
        
        // try { 
        //     try {
        //         Class.forName("com.mysql.cj.jdbc.Driver");
        //      } catch (Exception e) {
        //         System.out.println(e);
        //   }


        //     String url = "jdbc:mysql://localhost:3306/webscrapetester"; 
        //     Connection conn = DriverManager.getConnection(url,"root",""); 
        //     Statement st = conn.createStatement(); 
        //     st.executeUpdate("INSERT INTO gpu (name) " + 
        //         "VALUES (4070)");
        //     st.executeUpdate("INSERT INTO gpu (name)" + 
        //         "VALUES (4080)");
        //     st.executeUpdate("INSERT INTO gpu (name)" + 
        //         "VALUES (4090)");

        //     conn.close(); 
        // } catch (Exception e) { 
        //     System.err.println("Got an exception! "); 
        //     System.err.println(e.getMessage()); 
        // } 

		System.out.println("done");
    }
}
