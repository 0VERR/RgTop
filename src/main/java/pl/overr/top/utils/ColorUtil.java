package pl.overr.top.utils;

import org.bukkit.ChatColor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ColorUtil {

    public static String colorFix(String arg){
        return ChatColor.translateAlternateColorCodes('&', arg);
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("HHHH");

    public static String timeFix(long time) {
        Date date = new Date(time);
        return sdf.format(date);
    }
}
