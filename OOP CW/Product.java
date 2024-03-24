public class Product {

    private String Product_Id;
    private String Product_Name;
    private int Number_Of_Available_Items;
    private double Price;
    public Object get;


//////////CONSTRUCTOR//////////
    public Product (String Product_Id , String Product_Name , int Number_Of_Available_Items , double Price){
        this.Product_Id = Product_Id;
        this.Product_Name = Product_Name;
        this.Number_Of_Available_Items = Number_Of_Available_Items;
        this.Price = Price;
    }


//////////GETTERS//////////
    public String getProductId(){
        return Product_Id;
    }

    public String getproductName(){
        return Product_Name;
    }

    public int getnumberofawalabailitems(){
        return Number_Of_Available_Items;
    }

    public double getprice(){
        return Price;
    }

//////////SETTERS//////////
    public void setProductId(String Product_Id){
        this.Product_Id = Product_Id;
    }

    public void setproductName(String Product_Name){
        this.Product_Name = Product_Name;
    }

    public void setnumberofawalabailitems(int Number_Of_Available_Items){
        this.Number_Of_Available_Items = Number_Of_Available_Items;
    }

    public void setprice(double Price){
        this.Price = Price;
    }


    public void displayDetails() {
    }

    public String getDetails() {
        return "Product ID: " + Product_Id + ", Product Name: " + Product_Name + ", Price: " + Price + ",Number of AvailableItems: " + Number_Of_Available_Items;
}


    public String getAvailableItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableItems'");
    }


   



}