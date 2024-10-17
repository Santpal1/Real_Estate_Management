/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalra
 */
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
public class P_Owner {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    
    public int getID(){
        return this.id;
    }
    
    public void setId(int ID){
        this.id = ID;
    }
    
    public String getFname(){
        return this.firstName;
    }
    
    public void setFName(String FNAME){
        this.firstName = FNAME;
    }
    public String getLname(){
        return this.lastName;
    }
    
    public void setLName(String LNAME){
        this.lastName = LNAME;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
    public void setPhone(String PHONE){
        this.phone = PHONE;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String EMAIL){
        this.email = EMAIL;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String ADDRESS){
        this.address = ADDRESS;
    }
    
    public P_Owner(){}
    public P_Owner(int ID, String FNAME, String LNAME, String PHONE, String EMAIL, String ADDRESS){
        this.id = ID;
        this.firstName = FNAME;
        this.lastName = LNAME;
        this.phone = PHONE;
        this.email = EMAIL;
        this.address = ADDRESS;
    }
    
    public boolean addNewOwner(P_Owner owner){
        PreparedStatement ps;
        
        String addQuery = "INSERT INTO `property_owner`(`fname`, `lname`, `phone`, `email`, `address`) VALUES (?,?,?,?,?)";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(addQuery);
            ps.setString(1, owner.getFname());
            ps.setString(2, owner.getLname());
            ps.setString(3, owner.getPhone());
            ps.setString(4, owner.getEmail());
            ps.setString(5, owner.getAddress());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Owner.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    public boolean editOwnerData(P_Owner owner){
        PreparedStatement ps;
        
        String editQuery = "UPDATE `property_owner` SET `fname`=?,`lname`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(editQuery);
            ps.setString(1, owner.getFname());
            ps.setString(2, owner.getLname());
            ps.setString(3, owner.getPhone());
            ps.setString(4, owner.getEmail());
            ps.setString(5, owner.getAddress());
            ps.setInt(6, owner.getID());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Owner.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteOwner(int ownerId){
        PreparedStatement ps;
        
        String deleteQuery = "DELETE FROM `property_owner` WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(deleteQuery);
            ps.setInt(1, ownerId);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Owner.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public ArrayList<P_Owner> ownersList(){
        ArrayList<P_Owner> list = new ArrayList<>();
        
        Statement st;
        ResultSet rs;
        
        String selectQuery = "SELECT * FROM `property_owner`";
        
        try {
            st = THE_Connection.getTheConnection().createStatement();
            rs = st.executeQuery(selectQuery);
            
            P_Owner owner;
            
            while (rs.next()){
                owner = new P_Owner(rs.getInt(1),rs.getString(2),
                rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(owner);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Owner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
