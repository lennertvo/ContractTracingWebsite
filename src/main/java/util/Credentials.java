package util;

import java.util.Properties;

public abstract class Credentials {
    public static void setPass(Properties dbProperties) {
        dbProperties.setProperty("sername", "je studentnr");
        dbProperties.setProperty("Password", "dagadegijniebepalen");
    }
}
