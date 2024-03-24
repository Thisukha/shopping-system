public class user {

    private String User_Name;
    private int Password;


//////////CONSTRUCTOR//////////
    public user (String User_Name , int Password){
        this.User_Name = User_Name;
        this.Password = Password;
    }


//////////GETTERS//////////
    public String setusername(){
        return User_Name;
    }

    public int Setpasword(){
        return Password;
    }



// 
    public void getusername(String i){
        this.User_Name = i;
    }

    public void getpasword(int j){
        this.Password = j;
    }


    
}
