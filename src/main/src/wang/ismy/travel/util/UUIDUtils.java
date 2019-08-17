package wang.ismy.travel.util;

import java.util.UUID;

public class UUIDUtils {

    public static String get(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
