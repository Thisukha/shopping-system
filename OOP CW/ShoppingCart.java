import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> Product_List; //This list will include elements of the type "product."


////////// CONSTRUCTOR //////////
    public ShoppingCart(){
        this.Product_List = new ArrayList<>();
    }


////////// Method //////////
    public void addProduct(Product k){
        Product_List.add(k);
    }

    public void removeProduct(Product k){
        Product_List.add(k);
    }

    public double calculate_Total_coast(){
        double totalcost = 0;
        for (Product k : Product_List){
            totalcost += k.getprice();
        }
        return totalcost;
    }


}
