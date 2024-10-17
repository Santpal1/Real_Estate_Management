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
public class P_Client {
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
    
    public P_Client(){}
    public P_Client(int ID, String FNAME, String LNAME, String PHONE, String EMAIL, String ADDRESS){
        this.id = ID;
        this.firstName = FNAME;
        this.lastName = LNAME;
        this.phone = PHONE;
        this.email = EMAIL;
        this.address = ADDRESS;
    }
    
    public boolean addNewClient(P_Client client){
        PreparedStatement ps;
        
        String addQuery = "INSERT INTO `property_client`(`fname`, `lname`, `phone`, `email`, `address`) VALUES (?,?,?,?,?)";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(addQuery);
            ps.setString(1, client.getFname());
            ps.setString(2, client.getLname());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getAddress());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    public boolean editClientData(P_Client client){
        PreparedStatement ps;
        
        String editQuery = "UPDATE `property_client` SET `fname`=?,`lname`=?,`phone`=?,`email`=?,`address`=? WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(editQuery);
            ps.setString(1, client.getFname());
            ps.setString(2, client.getLname());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getAddress());
            ps.setInt(6, client.getID());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteClient(int clientId){
        PreparedStatement ps;
        
        String deleteQuery = "DELETE FROM `property_client` WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(deleteQuery);
            ps.setInt(1, clientId);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public ArrayList<P_Client> clientsList(){
        ArrayList<P_Client> list = new ArrayList<>();
        
        Statement st;
        ResultSet rs;
        
        String selectQuery = "SELECT * FROM `property_client`";
        
        try {
            st = THE_Connection.getTheConnection().createStatement();
            rs = st.executeQuery(selectQuery);
            
            P_Client client;
            
            while (rs.next()){
                client = new P_Client(rs.getInt(1),rs.getString(2),
                rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(client);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
