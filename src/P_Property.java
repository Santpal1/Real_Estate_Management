
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalra
 */
public class P_Property {
    private int id;
    private int type;
    private int size;
    private int ownerId;
    private String price;
    private String address;
    private int bedrooms;
    private int bathrooms;
    private int age;
    private boolean balcony;
    private boolean pool;
    private boolean backyard;
    private boolean garage;
    private String desc;
    
    public P_Property(){}
    
    public P_Property(int ID, int TYPE, int SIZE, int OWNER_ID, String Price,
                      String ADDRESS, int BEDROOMS, int BATHROOMS, int AGE,
                      boolean BALCONY, boolean POOL, boolean BACKYARD, boolean GARAGE,
                      String DESC){
        this.id = ID;
        this.type = TYPE;
        this.size = SIZE;
        this.ownerId = OWNER_ID;
        this.price = Price;
        this.address = ADDRESS;
        this.age = AGE;
        this.bathrooms = BATHROOMS;
        this.bedrooms = BEDROOMS;
        this.desc = DESC;
        this.balcony = BALCONY;
        this.backyard = BACKYARD;
        this.garage = GARAGE;
        this.pool = POOL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isBackyard() {
        return backyard;
    }

    public void setBackyard(boolean backyard) {
        this.backyard = backyard;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public boolean addNewProperty(P_Property property){
        PreparedStatement ps;
        
        String addQuery = "INSERT INTO `the_property`(`type`, `square_feet`, `ownerId`, `price`, `address`, `bedrooms`, `bathrooms`, `age`, `balcony`, `pool`, `backyard`, `garage`, `description`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(addQuery);
            ps.setInt(1, property.getType());
            ps.setInt(2, property.getSize());
            ps.setInt(3, property.getOwnerId());
            ps.setString(4, property.getPrice());
            ps.setString(5, property.getAddress());
            ps.setInt(6, property.getBedrooms());
            ps.setInt(7, property.getBathrooms());
            ps.setInt(8, property.getAge());
            ps.setBoolean(9, property.isBalcony());
            ps.setBoolean(10, property.isPool());
            ps.setBoolean(11, property.isBackyard());
            ps.setBoolean(12, property.isGarage());
            ps.setString(13, property.getDesc());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean editProperty(P_Property property){
        
        PreparedStatement ps;
        
        String editQuery = "UPDATE `the_property` SET `type`=?,`square_feet`=?,`ownerId`=?,`price`=?,`address`=?,`bedrooms`=?,`bathrooms`=?,`age`=?,`balcony`=?,`pool`=?,`backyard`=?,`garage`=?,`description`=? WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(editQuery);
            ps.setInt(1, property.getType());
            ps.setInt(2, property.getSize());
            ps.setInt(3, property.getOwnerId());
            ps.setString(4, property.getPrice());
            ps.setString(5, property.getAddress());
            ps.setInt(6, property.getBedrooms());
            ps.setInt(7, property.getBathrooms());
            ps.setInt(8, property.getAge());
            ps.setBoolean(9, property.isBalcony());
            ps.setBoolean(10, property.isPool());
            ps.setBoolean(11, property.isBackyard());
            ps.setBoolean(12, property.isGarage());
            ps.setString(13, property.getDesc());
            ps.setInt(14, property.getId());
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    public boolean removeProperty(int propertyId){
        PreparedStatement ps;
        
        String deleteQuery = "DELETE FROM `the_property` WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(deleteQuery);
            ps.setInt(1, propertyId);
            
            return (ps.executeUpdate() > 0);
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public P_Property findProperty(int propertyId){
        PreparedStatement ps;
        ResultSet rs;
        P_Property property = null;
        String searchQuery = "SELECT * FROM `the_property` WHERE `id` = ?";
        try {
            
            ps = THE_Connection.getTheConnection().prepareStatement(searchQuery);
            ps.setInt(1, propertyId);
            rs = ps.executeQuery();
            
            if(rs.next()){
                property = new P_Property(rs.getInt("id"), 
                                          rs.getInt("type"), 
                                          rs.getInt("square_feet"),
                                          rs.getInt("ownerId"), 
                                          rs.getString("price"), 
                                          rs.getString("address"),
                                          rs.getInt("bedrooms"), 
                                          rs.getInt("bathrooms"),
                                          rs.getInt("age"),
                                          rs.getBoolean("balcony"), 
                                          rs.getBoolean("pool"), 
                                          rs.getBoolean("backyard"), 
                                          rs.getBoolean("garage"),
                                          rs.getString("description"));
            }
            return property;
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return property;
    }
    
    public ArrayList<P_Property> proprtiesList(){
        ArrayList<P_Property> list = new ArrayList<>();
        Statement st;
        ResultSet rs;
        
        String selectQuery = "SELECT * FROM `the_property`";
        
        try {
            st = THE_Connection.getTheConnection().createStatement();
            rs = st.executeQuery(selectQuery);
            
            P_Property property;
            
            while (rs.next()){
                property = new P_Property(rs.getInt("id"), 
                                          rs.getInt("type"), 
                                          rs.getInt("square_feet"),
                                          rs.getInt("ownerId"), 
                                          rs.getString("price"), 
                                          rs.getString("address"),
                                          rs.getInt("bedrooms"), 
                                          rs.getInt("bathrooms"),
                                          rs.getInt("age"),
                                          rs.getBoolean("balcony"), 
                                          rs.getBoolean("pool"), 
                                          rs.getBoolean("backyard"), 
                                          rs.getBoolean("garage"),
                                          rs.getString("description"));
                list.add(property);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    //for p_image listing
    public ArrayList<P_Property> proprtiesListByType(int propertyTypeID){
        ArrayList<P_Property> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        
        String selectQuery = "SELECT * FROM `the_property` WHERE `type`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(selectQuery);
            ps.setInt(1, propertyTypeID);
            rs = ps.executeQuery();
            
            P_Property property;
            
            while (rs.next()){
                property = new P_Property(rs.getInt("id"), 
                                          rs.getInt("type"), 
                                          rs.getInt("square_feet"),
                                          rs.getInt("ownerId"), 
                                          rs.getString("price"), 
                                          rs.getString("address"),
                                          rs.getInt("bedrooms"), 
                                          rs.getInt("bathrooms"),
                                          rs.getInt("age"),
                                          rs.getBoolean("balcony"), 
                                          rs.getBoolean("pool"), 
                                          rs.getBoolean("backyard"), 
                                          rs.getBoolean("garage"),
                                          rs.getString("description"));
                list.add(property);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    
    public boolean addImage(int propertyID, String the_image_path){
        PreparedStatement ps;
        
        String addQuery = "INSERT INTO `property_images`(`property_id`, `the_image`) VALUES (?,?)";
        
        try {
            try {
                
                FileInputStream propertyImage = new FileInputStream(new File(the_image_path));
                ps = THE_Connection.getTheConnection().prepareStatement(addQuery);
                ps.setInt(1, propertyID);
                ps.setBinaryStream(2, propertyImage);
                
                return (ps.executeUpdate() > 0);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public HashMap<byte[], Integer> proprtyImagesList(int propertyID){
        HashMap<byte[], Integer> list = new HashMap<>();
        PreparedStatement ps;
        ResultSet rs;
        
        String selectQuery = "SELECT * FROM `property_images` WHERE `property_id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(selectQuery);
            ps.setInt(1, propertyID);
            rs = ps.executeQuery();

            
            while (rs.next()){
                list.put(rs.getBytes("the_image"), rs.getInt("id"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    public byte[] getImageById(int imageId){
        
        PreparedStatement ps;
        ResultSet rs;
        
        String selectQuery = "SELECT `the_image` FROM `property_images` WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(selectQuery);
            ps.setInt(1, imageId);
            rs = ps.executeQuery();

            
            if (rs.next()){
                return rs.getBytes("the_image");
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public boolean removeImage(int imageId){
        
        PreparedStatement ps;
        ResultSet rs;
        
        String deleteQuery = "DELETE FROM `property_images` WHERE `id`=?";
        
        try {
            ps = THE_Connection.getTheConnection().prepareStatement(deleteQuery);
            ps.setInt(1, imageId);
            return(ps.executeUpdate() > 0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(P_Property.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
}
