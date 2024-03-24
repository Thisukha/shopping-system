/**
 * The Clothing class represents a type of product that includes additional attributes
 * such as size and color, extending the base Product class.
 */



public class Clothing extends Product {

// Additional attributes specific to Clothing
    private String Size;
    private String colour;

    


//////////CONSTRUCTOR//////////
    public Clothing (String Product_Id , String Product_Name , int Number_Of_Available_Items , double Price ,String size2 , String Colour ){
        // Call the constructor of the base class (Product)
        super(Product_Id , Product_Name , Number_Of_Available_Items , Price );
        // Initialize Clothing-specific attributes
        this.Size = size2;
        this.colour = Colour;
    }
    

//////////GETTERS//////////

 //Gets the size of the clothing product.

    public String getsize (){
        return Size;
    }

    public String getcolour(){
        return colour;
    }


//////////SETTERS//////////
//Sets the size of the clothing product.
    public void setsize(String Size){
        this.Size = Size;
    }
    
//Sets the color of the clothing product.
    public void setcolour(String Colour){
        this.colour = Colour;
    }


    
}
