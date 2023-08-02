package com.example.example.AdminServlet;

import com.example.example.DataBase.District;
import com.example.example.DataBase.School;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import jakarta.servlet.annotation.WebServlet;


@WebServlet(name = "OkulUpdate", value = "/adminpanel/OkulUpdate")
public class OkulUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
   	 	int okulid = Integer.parseInt(request.getParameter("okulid"));
    	String Durum = request.getParameter("Durum");
    	String Okul = request.getParameter("Okul");
    	String OkulSect = request.getParameter("OkulSect");
    	String OkulName = request.getParameter("OkulName");


    
try {
    // Hibernate session'ı yapılandırması
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
           
    School school = session.get(School.class,okulid);
    school.setDurum(Durum);
    school.setOkul(Okul);
    school.setOkulTipi(OkulSect);
    school.setOkulName(OkulName);
    
    session.merge(school);
    transaction.commit();

    session.close();
    sessionFactory.close();

   

    response.sendRedirect("./okullist.jsp");
} catch (Exception e) {
    System.err.println("Hata oluştu: " + e);
    e.printStackTrace();
}
}
}

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    


