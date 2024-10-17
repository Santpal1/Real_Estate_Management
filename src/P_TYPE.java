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
import java.util.HashMap;
import java.sql.ResultSet;
public class P_TYPE {
    private int id;
    private String name;
    private String description;
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer ID){
        this.id = ID;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String NAME){
        this.name = NAME;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String DESC){
        this.description = DESC;
    }
    
    public P_TYPE(){}
    
    public P_TYPE(Integer ID, String NAME, String DESC){
            this.id = ID;
            this.name = NAME;
            this.description = DESC;
    }
    
    public boolean execTypeQuery(String queryType, P_TYPE type){
        
        PreparedStatement ps;
        
        if(queryType.equals("add")){
            try {
                ps = THE_Connection.getTheConnection().prepareStatement("INSERT INTO `property_type`(`name`, `description`) VALUES (?,?)");
                ps.setString(1, type.getName());
                ps.setString(2, type.getDescription());
                
                return (ps.executeUpdate() > 0);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        else if(queryType.equals("edit")){
            try {
                ps = THE_Connection.getTheConnection().prepareStatement("UPDATE `property_type` SET `name`=?,`description`=? WHERE `id`=?");
                ps.setString(1, type.getName());
                ps.setString(2, type.getDescription());
                ps.setInt(3, type.getId());
                
                return (ps.executeUpdate() > 0);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        else if(queryType.equals("remove")){
            try {
                ps = THE_Connection.getTheConnection().prepareStatement("DELETE FROM `property_type` WHERE `id`=?");
                ps.setInt(1, type.getId());
                
                return (ps.executeUpdate() > 0);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Enter The Correct Query( add,edit,remove )", "Invalid Operation", 2);
            return false;
        }
    }
    
    public HashMap<String, Integer> getTypeMap(){
        HashMap<String, Integer> map = new HashMap<>();
        
        Statement st;
        ResultSet rs;
        
        try {
            
            st = THE_Connection.getTheConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM `property_type`");
            
            P_TYPE type;
            
            while(rs.next()){
                type = new P_TYPE(rs.getInt(1), rs.getString(2), rs.getString(3));
                
                map.put(type.getName(), type.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
    }    
    
    public P_TYPE getTypeById(Integer Id){
        PreparedStatement ps;
        ResultSet rs;
        P_TYPE type = new P_TYPE();
        
        try {
            
            ps = THE_Connection.getTheConnection().prepareStatement("SELECT * FROM `property_type` WHERE `id`=?");
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                type.setId(Id);
                type.setName(rs.getString(2));
                type.setDescription(rs.getString(3));
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }
    
}
