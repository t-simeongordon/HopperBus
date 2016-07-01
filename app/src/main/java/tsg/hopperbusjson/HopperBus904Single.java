package tsg.hopperbusjson;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by terrelsimeongordon on 14/05/16.
 */
public class HopperBus904Single extends AppCompatActivity {
    private static String urlString;
    List<Integer> updatedTime = new ArrayList<>();

    List<JSONArray> term_time = new ArrayList<>();

    List<String> highlightedTimeList = new ArrayList<>();

    List<String> extractedCodeList = new ArrayList<>();

    List<String> extractedLocationList = new ArrayList<>();

    List<String> extractedTimeList = new ArrayList<>();


    int pt;
    DateTime dt;
    DateTime cdt;
    ProgressDialog mProgressDialog;
    int timeUpdateLock = 0;
    int highlightedTimeListSize = 0;

    TextView codeTv1, locatiionTv1, timeTv1, codeTv2, locatiionTv2, timeTv2, codeTv3, locatiionTv3, timeTv3, codeTv4, locatiionTv4,
            timeTv4, codeTv5, locatiionTv5, timeTv5, codeTv6, locatiionTv6, timeTv6;

    int count = 0;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hb_904_single_activity);

        codeTv1 = (TextView) findViewById(R.id.hb904_code_tv1);
        locatiionTv1 = (TextView) findViewById(R.id.hb904_location_tv1);
        timeTv1 = (TextView) findViewById(R.id.hb904_time_tv1);

        codeTv2 = (TextView) findViewById(R.id.hb904_code_tv2);
        locatiionTv2 = (TextView) findViewById(R.id.hb904_location_tv2);
        timeTv2 = (TextView) findViewById(R.id.hb904_time_tv2);

        codeTv3 = (TextView) findViewById(R.id.hb904_code_tv3);
        locatiionTv3 = (TextView) findViewById(R.id.hb904_location_tv3);
        timeTv3 = (TextView) findViewById(R.id.hb904_time_tv3);

        codeTv4 = (TextView) findViewById(R.id.hb904_code_tv4);
        locatiionTv4 = (TextView) findViewById(R.id.hb904_location_tv4);
        timeTv4 = (TextView) findViewById(R.id.hb904_time_tv4);

        codeTv5 = (TextView) findViewById(R.id.hb904_code_tv5);
        locatiionTv5 = (TextView) findViewById(R.id.hb904_location_tv5);
        timeTv5 = (TextView) findViewById(R.id.hb904_time_tv5);

        codeTv6 = (TextView) findViewById(R.id.hb904_code_tv6);
        locatiionTv6 = (TextView) findViewById(R.id.hb904_location_tv6);
        timeTv6 = (TextView) findViewById(R.id.hb904_time_tv6);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (updatedTime.equals(null) && term_time.equals(null) && highlightedTimeList.equals(null) && extractedCodeList.equals(null)
                        && extractedLocationList.equals(null) && extractedTimeList.equals(null)) {
                    count = 0;
                    urlString = "https://raw.githubusercontent.com/TosinAF/HopperBus-iOS/master/HopperBus/Resources/Routes/Routes.json";
                    new ProcessJSON().execute(urlString);

                } else {
                    updatedTime.clear();
                    term_time.clear();
                    highlightedTimeList.clear();
                    extractedCodeList.clear();
                    extractedLocationList.clear();
                    extractedTimeList.clear();
                    count = 0;

                    urlString = "https://raw.githubusercontent.com/TosinAF/HopperBus-iOS/master/HopperBus/Resources/Routes/Routes.json";
                    new ProcessJSON().execute(urlString);
                }

//                urlString = "https://raw.githubusercontent.com/TosinAF/HopperBus-iOS/master/HopperBus/Resources/Routes/Routes.json";
//                new ProcessJSON().execute(urlString);


                handler.postDelayed(this, 10000);
                // or   handler.postDelayed( this, 10* 1000 );
//count for 10 secs outside the main ui thread

            }
        }, 1000);
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
//            mProgressDialog = new ProgressDialog(HopperBus904.this);
//            // Set progressdialog title
//            mProgressDialog.setTitle("Json Array");
//            // Set progressdialog message
//            mProgressDialog.setMessage("Loading...");
//            mProgressDialog.setIndeterminate(false);
//            // Show progressdialog
//            mProgressDialog.show();
        }


        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream) {
            Log.e("enter ", "******* " + stream);

            if (stream != null) {
                String data = "";
                res = getResources();

                Log.e("enter1 ", "******* ");
                try {

                    //get current time format into date type
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date ttime = new Date();
                    String temp = sdf.format(ttime);
                    Date currentTime = new SimpleDateFormat("HH:mm").parse(temp);

//value to cross reference
                    //set time versions displayed in list
                    //for testing only
                    //json data

                    String hb904TimeCheckerUPED_1Start = "07:50";
                    String hb904TimeCheckerUPED_1End = "09:40";
                    String hb904TimeCheckerUPWE_1Start = "10:05";
                    String hb904TimeCheckerUPWE_1End = "11:50";
                    String hb904TimeCheckerRDHC_1Start = "12:05";
                    String hb904TimeCheckerRDHC_1End = "13:50";
                    String hb904TimeCheckerRDHC_2Start = "14:15";
                    String hb904TimeCheckerRDHC_2End = "16:00";
                    String hb904TimeCheckerUPWE_2Start = "16:15";
                    String hb904TimeCheckerUPWE_2End = "18:00";

                    Date hbS1Start = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerUPED_1Start);
                    Date hbS1End = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerUPED_1End);
                    Date hbS2Start = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerUPWE_1Start);
                    Date hbS2End = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerUPWE_1End);
                    Date hbS3Start = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerRDHC_1Start);
                    Date hbS3End = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerRDHC_1End);
                    Date hbS4Start = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerRDHC_2Start);
                    Date hbS4End = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerRDHC_2End);
                    Date UPWE_2Start = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerUPWE_2Start);
                    Date UPWE_2End = new SimpleDateFormat("HH:mm").parse(hb904TimeCheckerUPWE_2End);


                    Log.e("currentTime ", "******* " + currentTime);

                    Log.e("enter2 ", "******* ");
                    JSONObject jsonObj = new JSONObject(stream);
                    Log.e("check json ", "stream ******* " + jsonObj);

                    JSONObject objJson = jsonObj.getJSONObject("route904");
                    Log.e("check json ", "route901 ******* " + objJson);


                    JSONArray term_time_schedule = objJson.getJSONArray("term_time_schedule");
                    Log.e("check json ", "term_time_schedule ******* " + term_time_schedule);

                    JSONArray term_time_schedule2 = null;


//get time sessions
                    if (currentTime.after(hbS1Start) && currentTime.before(hbS1End)) {
                        System.out.println("^^^^^");
                        System.out.println("Date1 is after Date2");

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(0);

                        Log.e("check json ", "term_time_schedule2 ******* " + term_time_schedule2);
                    } else if (currentTime.after(hbS2Start) && currentTime.before(hbS2End)) {
                        term_time_schedule2 = (JSONArray) term_time_schedule.get(1);

                    } else if (currentTime.after(hbS3Start) && currentTime.before(hbS3End)) {

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(2);
                    } else if (currentTime.after(hbS4Start) && currentTime.before(hbS4End)) {

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(3);

                    }
                    //make sure to change
                    //testing only
//                    else if(currentTime.after(UPWE_2Start) && currentTime.before(UPWE_2End)){
//
//                        term_time_schedule2 = (JSONArray) term_time_schedule.get(4);
//
//                    }
                    else {

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(4);

                    }

//                    JSONArray term_time_schedule3 = (JSONArray) term_time_schedule2.get(0);
//                    Log.e("check json ", "term_time_schedule3 ******* " + term_time_schedule3);

                    if (term_time_schedule2 != null) {

                        JSONArray term_time_schedule_main = null;
                        String term_time_schedule_main2_0 = null;
                        String term_time_schedule_main2_1 = null;
                        String term_time_schedule_main2_2 = null;


                        for (int i = 0; i < term_time_schedule2.length(); i++) {
                            term_time_schedule_main = (JSONArray) term_time_schedule2.get(i);
                            Log.e("check json ", "term_time ******* " + term_time_schedule_main);
                            term_time.add(term_time_schedule_main);
                            Log.e("check json ", "term_time content ******* " + term_time);

                        }

                        for (JSONArray timeFromJson : term_time) {
                            Date t1 = new SimpleDateFormat("HH:mm").parse(timeFromJson.getString(2));
                            Date currentTime1 = new SimpleDateFormat("HH:mm").parse("16:17");

                            count += 1;
                            Log.e("show_me_p ", " p ");
                            Log.e("show_me_p ", " p " + timeFromJson);
                            Log.e("show_me_count ", " p " + count);

                            extractedCodeList.add(timeFromJson.getString(0));
                            extractedLocationList.add(timeFromJson.getString(1));
                            extractedTimeList.add(timeFromJson.getString(2));

                            switch (count) {
                                case 1:
                                    codeTv1.setText(extractedCodeList.get(0));
                                    locatiionTv1.setText(extractedLocationList.get(0));
                                    timeTv1.setText(extractedTimeList.get(0));
                                    if (t1.after(currentTime)) {
                                        highlightedTimeList.add(timeFromJson.getString(2));
                                        Log.e("show_me_p0 ", " p0 " + highlightedTimeList);

                                    }
                                    break;
                                case 2:
                                    codeTv2.setText(extractedCodeList.get(1));
                                    locatiionTv2.setText(extractedLocationList.get(1));
                                    timeTv2.setText(extractedTimeList.get(1));
                                    if (t1.after(currentTime)) {
                                        highlightedTimeList.add(timeFromJson.getString(2));
                                        Log.e("show_me_p0 ", " p0 " + highlightedTimeList);

                                    }
                                    break;
                                case 3:
                                    codeTv3.setText(extractedCodeList.get(2));
                                    locatiionTv3.setText(extractedLocationList.get(2));
                                    timeTv3.setText(extractedTimeList.get(2));
                                    if (t1.after(currentTime)) {
                                        highlightedTimeList.add(timeFromJson.getString(2));
                                        Log.e("show_me_p0 ", " p0 " + highlightedTimeList);
                                    }
                                    break;
                                case 4:
                                    codeTv4.setText(extractedCodeList.get(3));
                                    locatiionTv4.setText(extractedLocationList.get(3));
                                    timeTv4.setText(extractedTimeList.get(3));
                                    if (t1.after(currentTime)) {
                                        highlightedTimeList.add(timeFromJson.getString(2));
                                        Log.e("show_me_p0 ", " p0 " + highlightedTimeList);

                                    }
                                    break;
                                case 5:
                                    codeTv5.setText(extractedCodeList.get(4));
                                    locatiionTv5.setText(extractedLocationList.get(4));
                                    timeTv5.setText(extractedTimeList.get(4));
                                    if (t1.after(currentTime)) {
                                        highlightedTimeList.add(timeFromJson.getString(2));
                                        Log.e("show_me_p0 ", " p0 " + highlightedTimeList);
                                    }
                                    break;
                                case 6:
                                    codeTv6.setText(extractedCodeList.get(5));
                                    locatiionTv6.setText(extractedLocationList.get(5));
                                    timeTv6.setText(extractedTimeList.get(5));
                                    if (t1.after(currentTime)) {
                                        highlightedTimeList.add(timeFromJson.getString(2));
                                        Log.e("show_me_p0 ", " p0  " + highlightedTimeList);
                                        Log.e("show_me_p0 ", "highlightedTimeList total  " + highlightedTimeList.size());
                                        highlightedTimeListSize = 7 - highlightedTimeList.size();

                                        highlightResults(highlightedTimeListSize);
                                    }
                                    break;
                                default:
                                    Log.e("check json error ", "******* ");

                            }
                            Log.e("show_me_count ", " p1 " + count);


// must change this to after ##
                            //change current time 1 to current time if after and if before leave as current time 1
//                            if (t1.after(currentTime)) {
//                                highlightedTimeList.add(timeFromJson.getString(2));
//                                Log.e("show_me_p0 ", " p0 " + highlightedTimeList);
//
//                                highlightedTimeListSize = 6-highlightedTimeList.size();
//                                Log.e("show_me_timelock ", " p1 "+timeUpdateLock);
//                                Log.e("show_me_timeSize ", " p1 "+highlightedTimeListSize);
//
//
////                                if (timeUpdateLock == 0) {
////                                    timeUpdateLock = 1;
//                                    Log.e("show_me_timelock ", " p2 "+timeUpdateLock);
//                                    Log.e("show_me_timeSize ", " p2 "+highlightedTimeListSize);
//
//                                    Log.e("show_me_p ", " p1 ");
//
//                                pt = timeUpdate();
//                                Log.e("show_me_p ", " p2 " + pt);
//                                Log.e("show_me_count ", " p2 " + count);

//                                switch (count) {
//                                    case 1:
//                                        Log.e("came_through ", " p1 " );
////change if condtion to meet 5 if current time is before and 0 if after
//                                        if(highlightedTimeListSize==0) {
//                                            Log.e("came_through ", " p2 " );
//
//                                            timeTv1.setTextColor(res.getColor(R.color.colorUpdate));
//                                            timeTv1.setTypeface(null, Typeface.BOLD);
//
//                                            codeTv1.setText(extractedCodeList.get(0));
//                                            locatiionTv1.setText(extractedLocationList.get(0));
//                                            timeTv1.setText(""+pt);
//                                        }else{
//                                            Log.e("came_through ", " p3 " );
//
//                                            codeTv1.setText(extractedCodeList.get(0));
//                                            locatiionTv1.setText(extractedLocationList.get(0));
//                                            timeTv1.setText(extractedTimeList.get(0));
//                                        }
//                                        break;
//                                    case 2:
//                                        if(highlightedTimeListSize==1) {
//                                            timeTv2.setTextColor(res.getColor(R.color.colorUpdate));
//                                            timeTv2.setTypeface(null, Typeface.BOLD);
//
//                                            codeTv2.setText(extractedCodeList.get(0));
//                                            locatiionTv2.setText(extractedLocationList.get(0));
//                                            timeTv2.setText(""+pt);
//                                        }else{
//                                            codeTv2.setText(extractedCodeList.get(0));
//                                            locatiionTv2.setText(extractedLocationList.get(0));
//                                            timeTv2.setText(extractedTimeList.get(0));
//                                        }
//                                        break;
//                                    case 3:
//                                        if(highlightedTimeListSize==2) {
//                                            timeTv3.setTextColor(res.getColor(R.color.colorUpdate));
//                                            timeTv3.setTypeface(null, Typeface.BOLD);
//
//                                            codeTv3.setText(extractedCodeList.get(0));
//                                            locatiionTv3.setText(extractedLocationList.get(0));
//                                            timeTv3.setText(""+pt);
//                                        }else{
//                                            codeTv3.setText(extractedCodeList.get(0));
//                                            locatiionTv3.setText(extractedLocationList.get(0));
//                                            timeTv3.setText(extractedTimeList.get(0));
//                                        }
//                                        break;
//                                    case 4:
//                                        if(highlightedTimeListSize==3) {
//                                            timeTv4.setTextColor(res.getColor(R.color.colorUpdate));
//                                            timeTv4.setTypeface(null, Typeface.BOLD);
//
//                                            codeTv4.setText(extractedCodeList.get(0));
//                                            locatiionTv4.setText(extractedLocationList.get(0));
//                                            timeTv4.setText(""+pt);
//                                        }else{
//                                            codeTv4.setText(extractedCodeList.get(0));
//                                            locatiionTv4.setText(extractedLocationList.get(0));
//                                            timeTv4.setText(extractedTimeList.get(0));
//                                        }
//                                        break;
//                                    case 5:
//                                        if(highlightedTimeListSize==4) {
//                                            timeTv5.setTextColor(res.getColor(R.color.colorUpdate));
//                                            timeTv5.setTypeface(null, Typeface.BOLD);
//
//                                            codeTv5.setText(extractedCodeList.get(0));
//                                            locatiionTv5.setText(extractedLocationList.get(0));
//                                            timeTv5.setText(""+pt);
//                                        }else{
//                                            codeTv5.setText(extractedCodeList.get(0));
//                                            locatiionTv5.setText(extractedLocationList.get(0));
//                                            timeTv5.setText(extractedTimeList.get(0));
//                                        }
//                                       break;
//                                    case 6:
//                                        if(highlightedTimeListSize==5) {
//                                            timeTv6.setTextColor(res.getColor(R.color.colorUpdate));
//                                            timeTv6.setTypeface(null, Typeface.BOLD);
//
//                                            codeTv6.setText(extractedCodeList.get(0));
//                                            locatiionTv6.setText(extractedLocationList.get(0));
//                                            timeTv6.setText(""+pt);
//                                        }else{
//                                            codeTv6.setText(extractedCodeList.get(0));
//                                            locatiionTv6.setText(extractedLocationList.get(0));
//                                            timeTv6.setText(extractedTimeList.get(0));
//                                        }
//                                        break;
//                                    default:
//                                        Log.e("check json error ", "******* ");
//
//                                }

//                                }
                            // may need a list count

//                            }
                        }


                    } else {
                        mProgressDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "no times available ", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.e("check json ", "error ******* " + e.toString());


                } catch (ParseException e) {
                    Log.e("check parse ", "error ******* " + e.toString());
                }

            }

        }
    }

    public int timeUpdate() {

        if (!highlightedTimeList.isEmpty()) {
            Log.e("enter list ", "highlightedTimeList");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date ttime = new Date();
            String temp = sdf.format(ttime);

            String t1 = highlightedTimeList.get(0);
            DateTime hbS1Start_1 = DateTimeFormat.forPattern("HH:mm").parseDateTime(t1);
            DateTime jodaCurrentTime = DateTimeFormat.forPattern("HH:mm").parseDateTime(temp);


            dt = new DateTime(hbS1Start_1);
            cdt = new DateTime(jodaCurrentTime);

// test1
            int jsonHour = dt.getHourOfDay();
            int jsonMin = dt.getMinuteOfHour();
            int currentHour = cdt.getHourOfDay();
            int currentMin = cdt.getMinuteOfHour();


            int jsonMilliHour = jsonHour * 3600000;
            int jsonMilliMinutes = jsonMin * 60000;
            int currentMilliHour = currentHour * 3600000;
            int currentMilliMinutes = currentMin * 60000;

            int jsonCurrentMilliTime = jsonMilliHour + jsonMilliMinutes;
            int currentMilliTime = currentMilliHour + currentMilliMinutes;

            // make sure to change  ## json time - current time

            int finalWaitingTime = jsonCurrentMilliTime - currentMilliTime;

            int displayWaitingTime = finalWaitingTime / 60000;

            Log.e("get_dt_cdt ", "wait time 0 " + displayWaitingTime);
            return displayWaitingTime;
        }

        return 0;
    }

    public void highlightResults(int highlightPoisition) {
        pt = timeUpdate();

        switch (highlightPoisition) {
            case 1:
                Log.e("came_through ", " p1 ");
//change if condtion to meet 5 if current time is before and 0 if after
//                if (highlightPoisition == 1) {
                    Log.e("came_through ", " p2 ");

                    timeTv1.setTextColor(res.getColor(R.color.colorUpdate));
                    timeTv1.setTypeface(null, Typeface.BOLD);

                    codeTv1.setText(extractedCodeList.get(0));
                    locatiionTv1.setText(extractedLocationList.get(0));
                    timeTv1.setText("" + pt);
//                } else {
//                    Log.e("came_through ", " p3 ");
//
//                    codeTv1.setText(extractedCodeList.get(0));
//                    locatiionTv1.setText(extractedLocationList.get(0));
//                    timeTv1.setText(extractedTimeList.get(0));
//                }
                break;
            case 2:
//                if (highlightPoisition == 2) {
                    timeTv2.setTextColor(res.getColor(R.color.colorUpdate));
                    timeTv2.setTypeface(null, Typeface.BOLD);

                    codeTv2.setText(extractedCodeList.get(0));
                    locatiionTv2.setText(extractedLocationList.get(0));
                    timeTv2.setText("" + pt);
//                } else {
//                    codeTv2.setText(extractedCodeList.get(0));
//                    locatiionTv2.setText(extractedLocationList.get(0));
//                    timeTv2.setText(extractedTimeList.get(0));
//                }
                break;
            case 3:
//                if (highlightPoisition == 3) {
                    timeTv3.setTextColor(res.getColor(R.color.colorUpdate));
                    timeTv3.setTypeface(null, Typeface.BOLD);

                    codeTv3.setText(extractedCodeList.get(0));
                    locatiionTv3.setText(extractedLocationList.get(0));
                    timeTv3.setText("" + pt);
//                } else {
//                    codeTv3.setText(extractedCodeList.get(0));
//                    locatiionTv3.setText(extractedLocationList.get(0));
//                    timeTv3.setText(extractedTimeList.get(0));
//                }
                break;
            case 4:
//                if (highlightPoisition == 4) {
                    timeTv4.setTextColor(res.getColor(R.color.colorUpdate));
                    timeTv4.setTypeface(null, Typeface.BOLD);

                    codeTv4.setText(extractedCodeList.get(0));
                    locatiionTv4.setText(extractedLocationList.get(0));
                    timeTv4.setText("" + pt);
//                } else {
//                    codeTv4.setText(extractedCodeList.get(0));
//                    locatiionTv4.setText(extractedLocationList.get(0));
//                    timeTv4.setText(extractedTimeList.get(0));
//                }
                break;
            case 5:
//                if (highlightPoisition == 5) {
                    timeTv5.setTextColor(res.getColor(R.color.colorUpdate));
                    timeTv5.setTypeface(null, Typeface.BOLD);

                    codeTv5.setText(extractedCodeList.get(0));
                    locatiionTv5.setText(extractedLocationList.get(0));
                    timeTv5.setText("" + pt);
//                } else {
//                    codeTv5.setText(extractedCodeList.get(0));
//                    locatiionTv5.setText(extractedLocationList.get(0));
//                    timeTv5.setText(extractedTimeList.get(0));
//                }
                break;
            case 6:
//                if (highlightPoisition == 6) {
                    timeTv6.setTextColor(res.getColor(R.color.colorUpdate));
                    timeTv6.setTypeface(null, Typeface.BOLD);

                    codeTv6.setText(extractedCodeList.get(0));
                    locatiionTv6.setText(extractedLocationList.get(0));
                    timeTv6.setText("" + pt);
//                } else {
//                    codeTv6.setText(extractedCodeList.get(0));
//                    locatiionTv6.setText(extractedLocationList.get(0));
//                    timeTv6.setText(extractedTimeList.get(0));
//                }
                break;
            default:
                Log.e("check json error ", "******* ");

        }

    }


}
