package com.example.spandananakkireddy.hw2_group32;

import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Spandana Nakkireddy on 2/1/2018.
 */

public class DateComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        try {
           /*
            int indexOfSlash = s1date.indexOf('/');
            int months = Integer.parseInt(s1date.substring(0,indexOfSlash));
            int indexOfSlash2 = s1date.substring(indexOfSlash+1).indexOf('/');
            int days = Integer.parseInt(s1date.substring(indexOfSlash+1,indexOfSlash2));
            int years = Integer.parseInt(s1date.substring(indexOfSlash2+1));
            String s2date = o2.getDate();
            indexOfSlash = s2date.indexOf('/');
            int months2 = Integer.parseInt(s2date.substring(0,indexOfSlash));
            indexOfSlash2 = s2date.substring(indexOfSlash+1).indexOf('/');
            int days2 = Integer.parseInt(s2date.substring(indexOfSlash+1,indexOfSlash2));
            int years2 = Integer.parseInt(s2date.substring(indexOfSlash2+1));

            if(years>years2)
                return 1;
            else if(years2>years)
                return -1;
            else{
                if((months>months2) )
                return 1
            }
*/
//            Date date1 = new SimpleDateFormat("MM/dd/YYYY").parse(s1date);
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MM/dd/yyyy");
            Date date1 = simpleDateFormat.parse(o1.getDate());
            Date date2 = simpleDateFormat.parse(o2.getDate());


            Log.d("date1",o1.getDate().toString());
            Log.d("date2",o2.getDate().toString());

            return simpleDateFormat.format(date1).compareTo(simpleDateFormat.format(date2));
        } catch (ParseException e) {
            Log.d("Exception", e.toString());
        }
        return 0;
    }

}
