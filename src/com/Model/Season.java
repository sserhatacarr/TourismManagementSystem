package com.Model;

import java.util.Date;
import java.util.Calendar;

import com.Helper.Helper;

public class Season {
    public static boolean isSummerSeason(Date entranceDate) {
        // This method is used to check if the selected dates are in the summer.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entranceDate);

        int month = calendar.get(Calendar.MONTH);
        int summerSeason = (month > 2 && month < 9) ? 1 : 0;

        return summerSeason == 1;
    }

}
