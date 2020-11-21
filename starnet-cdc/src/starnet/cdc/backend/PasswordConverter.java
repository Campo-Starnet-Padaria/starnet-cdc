package starnet.cdc.backend;

public class PasswordConverter {
    public String passwordConverter(char[] password){
        String pass = "";
        for(int cha = 0; cha < password.length; cha++){
            pass = pass + password[cha];
        }
        System.out.println(pass);
        return pass;
    }
}
