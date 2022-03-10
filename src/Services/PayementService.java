/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDB;
import entities.payement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PayementService {
    
    Connection cnx;

    public PayementService() {
        cnx = MyDB.getInstance().getCnx();

    }

    public void ajouterAdmin(payement p) {
        
        try {
            String requete = "INSERT INTO payement(montant,date_pay)"
                    + "VALUES(?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setFloat(1, p.getMontant());
            pst.setDate(2, (Date) p.getDate_pay());
            pst.executeUpdate();
            
            System.out.println("Payement enregistré !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<payement> afficherAdmins() {
        
        List<payement> p_list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM payement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                payement p = new payement();
                p.setId_pay(rs.getInt("id_pay"));
                p.setMontant(rs.getFloat("montant"));
                p.setDate_pay(rs.getDate("date_pay"));
                p_list.add(p);
                System.out.println(p.toString());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return p_list;
    }

    public void supprimerAdmin(payement p) {

        try {
            String requete = "DELETE FROM payement WHERE"
                    + " id_pay=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getId_pay());
            pst.executeUpdate();
            
            System.out.println("Payement supprimé avec succés !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    public void modifierAdmin(payement p,float montant, Date date){
        
         try {
            String requete = " UPDATE payement SET montant=?, date_pay=?" 
                    +"WHERE id_pay=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setFloat(1,montant );
            pst.setDate(2,date );
            pst.setInt(3, p.getId_pay());
            pst.executeUpdate();
            System.out.println("Payement modifié avec succés !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
