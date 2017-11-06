package ahmed.sabih.com.astroassignment.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sabih on 03-Nov-17.
 */

public class SampleObject {

    String header1;
    String header2;
    String header3;
    String header4;
    String header5;
    String header6;
    String header7;
    String header8;
    String header9;

    public SampleObject(String header1, String header2, String header3,
                        String header4, String header5, String header6,
                        String header7, String header8, String header9){

        this.header1 = header1;
        this.header2 = header2;
        this.header3 = header3;
        this.header4 = header4;
        this.header5 = header5;
        this.header6 = header6;
        this.header7 = header7;
        this.header8 = header8;
        this.header9 = header9;

    }

    public static ArrayList<String> getTimeSlots(){

        ArrayList<String> timeSlotList = new ArrayList<>();

        timeSlotList.add("12AM");
        timeSlotList.add("1AM");
        timeSlotList.add("2AM");
        timeSlotList.add("3AM");
        timeSlotList.add("4AM");
        timeSlotList.add("5AM");
        timeSlotList.add("6AM");
        timeSlotList.add("7AM");
        timeSlotList.add("8AM");
        timeSlotList.add("9AM");
        timeSlotList.add("10AM");
        timeSlotList.add("11AM");
        timeSlotList.add("12PM");
        timeSlotList.add("1PM");
        timeSlotList.add("2PM");
        timeSlotList.add("3PM");
        timeSlotList.add("4PM");
        timeSlotList.add("5PM");
        timeSlotList.add("6PM");
        timeSlotList.add("7PM");
        timeSlotList.add("8PM");
        timeSlotList.add("9PM");
        timeSlotList.add("10PM");
        timeSlotList.add("11PM");
        timeSlotList.add("12AM");

        return timeSlotList;




    }

    // this is just the sample data
    static List<SampleObject> getSampleObjects(){

        List<SampleObject> sampleObjects = new ArrayList<SampleObject>();

        for(int x=1; x<=1; x++){

            SampleObject sampleObject = new SampleObject(
                    "Col 1, Row " + x,
                    "Col 2, Row " + x + " - multi-lines",
                    "Col 3, Row " + x,
                    "Col 4, Row " + x,
                    "Col 5, Row " + x,
                    "Col 6, Row " + x,
                    "Col 7, Row " + x,
                    "Col 8, Row " + x,
                    "Col 9, Row " + x
            );

            sampleObjects.add(sampleObject);
        }

        return sampleObjects;

    }
}
