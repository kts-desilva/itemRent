package edu.ijse.cmjd.itemrent.model;
import java.io.*;

public class ClientM implements Serializable{
	
    private String Id;
    private String Name;
    private String NIC;
    private String Type;
    private String Tel;
    private String Address;
    private String Zip;
    private String Since;
    private String packageName;
    private double Fee;
    private String Types;
    private String More;
    private double Charges;  

    public ClientM() {
    }

    public ClientM(String Id, String Name, String NIC, String Type, String Tel, String Address, String Zip, String Since, String packageName, double Fee, String Types, String More, double Charges) {
        this.Id = Id;
        this.Name = Name;
        this.NIC = NIC;
        this.Type = Type;
        this.Tel = Tel;
        this.Address = Address;
        this.Zip = Zip;
        this.Since = Since;
        this.packageName = packageName;
        this.Fee = Fee;
        this.Types = Types;
        this.More = More;
        this.Charges = Charges;
    }
  
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNIC() {
        return NIC;
    }

  
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getType() {
        return Type;
    }


    public void setType(String Type) {
        this.Type = Type;
    }

    public String getTel() {
        return Tel;
    }

 
    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getZip() {
        return Zip;
    }

 
    public void setZip(String Zip) {
        this.Zip = Zip;
    }


    public String getSince() {
        return Since;
    }

    public void setSince(String Since) {
        this.Since = Since;
    }


    public String getPackageName() {
        return packageName;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

  
    public double getFee() {
        return Fee;
    }

    
    public void setFee(double Fee) {
        this.Fee = Fee;
    }

  
    public String getTypes() {
        return Types;
    }


    public void setTypes(String Types) {
        this.Types = Types;
    }


    public String getMore() {
        return More;
    }

    public void setMore(String More) {
        this.More = More;
    }

  
    public double getCharges() {
        return Charges;
    }

    public void setCharges(double Charges) {
        this.Charges = Charges;
    }
 	
	
}
