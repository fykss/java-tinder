package utils;

import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class DescribeTime {


    public String describeTimeDif(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate timeLastin = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = LocalDate.now();
        Period period = Period.between(timeLastin, now);
        String result = "";
        if(period.getDays() == 0){
            result = "today";
        }else {
            result = period.getDays() + " days ago";
        }
        return result;
    }
}
