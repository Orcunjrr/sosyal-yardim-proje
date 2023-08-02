package com.example.example.AdminServlet;

import com.example.example.DataBase.DisabledSub;
import com.example.example.DataBase.Disabled;

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


//... (import statements)

@WebServlet(name = "DisabledSubType", value = "/adminpanel/disabledSubType")
public class DisabledSubType extends HttpServlet {
 private static final long serialVersionUID = 1L;

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     String disabledSubName = request.getParameter("disabledSunName");
     int disabledID = Integer.parseInt(request.getParameter("disableID")); 

     try {
         // Hibernate session'ı yapılandırması
         SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
         Session session = sessionFactory.openSession();
         Transaction transaction = session.beginTransaction();

         DisabledSub disabledSub = new DisabledSub();
         disabledSub.setDisabledSubName(disabledSubName);

         // Retrieve the Disabled object based on disabledID
         Disabled disabled = session.get(Disabled.class, disabledID);
         disabledSub.setDisabled(disabled);

         // Personel örneğini kaydet
         session.persist(disabledSub);
         transaction.commit();

         session.close();
         sessionFactory.close();

         response.sendRedirect("./engellialttip.jsp");
     } catch (Exception e) {
         System.err.println("Hata oluştu: " + e);
         e.printStackTrace();
     }
 }
}

