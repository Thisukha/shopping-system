public class Electronics extends Product {

    // Additional attributes specific to Electronics
    private String brand;
    private int warranty_period;

    


//////////CONSTRUCTOR//////////

    public Electronics(String Product_Id , String Product_Name , int Number_Of_Available_Items , double Price , String brand , double warranty_period){
        // Call the constructor of the base class (Product)
        super(Product_Id , Product_Name , Number_Of_Available_Items , Price );
        // Initialize Electronics-specific attributes
        this.brand = brand;
        this.warranty_period = (int) warranty_period;
    } 

//////////GETTERS//////////

//Gets the brand of the electronics product.

    public String getbrand (){
        return brand;
    }

    //Gets the warranty period of the electronics product in months

    public int getwaranty(){
        return warranty_period;
    }

    
//////////SETTERS//////////

//Sets the brand of the electronics product.
    public void setbrand(String brand){
        this.brand = brand;
    }

//Sets the warranty period of the electronics product..
    public void setwaranty(int warranty_period){
        this.warranty_period = warranty_period;
    }

//Gets the warranty period of the electronics product in months.
    public String getWarrantyPeriod() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWarrantyPeriod'");
    }


        

        
        
    
    

    
}