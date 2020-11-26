/**
 Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
 @author falaf
 Created on 20/11/2020
 */
package starnet.cdc.backend;

public class PasswordConverter {
    public String passwordConverter(char[] password){
        String pass = "";
        for(int cha = 0; cha < password.length; cha++) {
            pass = pass + password[cha];
        }
        return pass;
    }
}
