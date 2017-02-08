package tsg.hopperbusjson;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
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

//  http://stackoverflow.com/questions/24572562/android-how-to-iterate-multiple-json-object-arrays-in-the-same-file

/**
 * Created by terrelsimeongordon on 28/03/16.
 */
public class HopperBus904 extends AppCompatActivity{
    private static String urlString;
    // Declare Variables
    ListView listview;
    ProgressDialog mProgressDialog;

    String s1;
List<JSONArray> term_time= null;
    List<HopperBus904Folder> hb904ArrayList= null;
    List<String> extractedTimeList= null;

    List<Integer> updatedTime= null;

    HopperBusAdapter adapter;

    DateTime dt;
    DateTime cdt;
    int colourCount=0;
    int extractedTimeListCount= 0;

    int timeUpdateLock=0;
    int pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hb_904_activity);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

//        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflator.inflate(R.layout.nav_bar_title, null);

//        actionBar.setCustomView(v);

        ColorDrawable cd = new ColorDrawable(0xff0000);
        actionBar.setBackgroundDrawable(cd);
//        Intent i = getIntent();
        // Get the result of title


//        s1 = i.getStringExtra("s1");


//        final TextView tv = (TextView) findViewById(R.id.tv);
//        Button btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tv.setText("");


        final Handler handler = new Handler();
        handler.postDelayed( new Runnable() {

            @Override
            public void run() {


        urlString = "https://raw.githubusercontent.com/TosinAF/HopperBus-iOS/master/HopperBus/Resources/Routes/Routes.json";
        new ProcessJSON().execute(urlString);



                handler.postDelayed( this, 10000 );
                // or   handler.postDelayed( this, 10* 1000 );
//count for 10 secs outside the main ui thread

            }
        },  1000 );
        git status
//        if(s1!=null) {
//            urlString = "https://zymb.eu/api/v1/employee/skill/"+s1;
//
//            new ProcessJSON().execute(urlString);
//        }


//            }
//        });



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



        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){
//            organisationProList = new ArrayList<_OrganisationProFolder>();

//            TextView tv = (TextView) findViewById(R.id.tv);
            //tv.setText(stream);

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

            //..........Process JSON DATA................
            Log.e("enter ", "******* " + stream);

            if(stream !=null){
                String data = "";

                Log.e("enter1 ","******* ");
//                try{
//                    Log.e("enter2 ","******* ");
//
//                    // Get the full HTTP Data as JSONObject
//                    JSONObject reader= new JSONObject(stream);
////                    JSONObject reader = (JSONObject) new JSONTokener(stream).nextValue();
//                    Log.e("check json reader ","******* "+reader);
//
//                    // Get the JSONObject "coord"...........................
////                    JSONArray coord = reader.optJSONArray("movies");
////                    JSONObject hopper_bus = reader.optJSONObject("api_codes");
////                    JSONObject route903 = reader.optJSONObject("route903");
////                    JSONObject term_time_schedule1 = reader.optJSONObject("term_time_schedule");
//                    JSONArray term_time_schedule = new JSONArray(stream);
////                    String time1 = term_time_schedule.get(0).toString();
//
//                    Log.e("check json array ","******* "+reader);
////                    Log.e("check json hopper_bus ","******* "+hopper_bus);
////                    Log.e("check json route903 ", "******* " + route903);
////                    Log.e("check json ", "term_time_schedule ******* " + term_time_schedule1);
//                    Log.e("check json ", "term_time_schedule2 ******* " + term_time_schedule);
////                    Log.e("check json ", "time1 ******* " + time1);
//
//                    mProgressDialog.dismiss();
//
////                    for(int i=0; i < term_time_schedule.length(); i++){
//////                        _OrganisationProFolder map = new _OrganisationProFolder();
////
////                        JSONObject jsonObject = term_time_schedule.getJSONObject(i);
////                        Log.e("check json jsonObject ","******* "+jsonObject);
////
//////                        int year1 = Integer.parseInt(jsonObject.optString("year").toString());
//////                        String name = jsonObject.optString("movie").toString();
//////                        int year1 = Integer.parseInt(jsonObject.optString("id").toString());
//////                        String name = jsonObject.optString("first_name").toString();
//////
//////
//////                        String name = jsonObject.optString("movie").toString();
////
////
////
//////                        Log.e("what happened ", "DoggieRecipeslist " + organisationProList);
//////
//////                        Log.e("what happened ", "DoggieRecipeslist size" + organisationProList.size());
////
//////                        data += "year= "+ year1 +" \n Name= "+ name +" \n ";
////                    }
////                    listview = (ListView) findViewById(R.id.listview);
////                    // Pass the results into _ListViewAdapter.java
////                    adapter = new _MainListViewAdapter(SkillResult.this,
////                            organisationProList);
////                    // Binds the Adapter to the ListView
////                    listview.setAdapter(adapter);
//////                    tv.setText(data);
//
//                }catch(JSONException e){
//                    Log.e("check json ", "error ******* " + e.toString());
//
//
//                }
//                String term_time_schedule = null;
                try {
                    updatedTime = new ArrayList<>();
//for(int i=0;i<=6;i++){
//
//    updatedTime.add(colourCount);
//}
                //TODO (1) implement navigation here
                    //get current time format into date type
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date ttime = new Date();
                    String temp = sdf.format(ttime);
                    Date currentTime = new SimpleDateFormat("HH:mm").parse(temp);

//value to cross reference
                    //set time versions displayed in list
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


                    Log.e("currentTime ", "******* "+currentTime );

                    Log.e("enter2 ","******* ");
                    JSONObject jsonObj = new JSONObject(stream);
                    Log.e("check json ", "stream ******* " + jsonObj);

                    JSONObject objJson = jsonObj.getJSONObject("route904");
                    Log.e("check json ", "route901 ******* " + objJson);


                    JSONArray term_time_schedule = objJson.getJSONArray("term_time_schedule");
                    Log.e("check json ", "term_time_schedule ******* " + term_time_schedule);

                    JSONArray term_time_schedule2=null;


//get time sessions
                    if(currentTime.after(hbS1Start) && currentTime.before(hbS1End)) {
                    System.out.println("^^^^^");
                    System.out.println("Date1 is after Date2");

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(0);

                        Log.e("check json ", "term_time_schedule2 ******* " + term_time_schedule2);
                    }else if(currentTime.after(hbS2Start) && currentTime.before(hbS2End))  {
                        term_time_schedule2 = (JSONArray) term_time_schedule.get(1);

                    }else if(currentTime.after(hbS3Start) && currentTime.before(hbS3End)){

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(2);
                    }else if(currentTime.after(hbS4Start) && currentTime.before(hbS4End)){

                        term_time_schedule2 = (JSONArray) term_time_schedule.get(3);

                    }
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

                 if(   term_time_schedule2!=null) {
                     term_time = new ArrayList<>();
                     hb904ArrayList = new ArrayList<>();
                     extractedTimeList = new ArrayList<>();

                     JSONArray term_time_schedule_main = null;
                     String term_time_schedule_main2_0 = null;
                     String term_time_schedule_main2_1 = null;
                     String term_time_schedule_main2_2 = null;


                     for (int i = 0; i < term_time_schedule2.length(); i++) {
                         term_time_schedule_main = (JSONArray) term_time_schedule2.get(i);
                         Log.e("check json ", "term_time ******* " + term_time_schedule_main);
                         term_time.add(term_time_schedule_main);
                         Log.e("check json ", "term_time content ******* " + term_time);
                         colourCount++;

                     }

                     for (JSONArray timeFromJson : term_time) {
                         Date t1 = new SimpleDateFormat("HH:mm").parse(timeFromJson.getString(2));
                         Log.e("show_me_p "," p ");
// must change this to after ##
                         if (t1.after(currentTime)) {
                             extractedTimeList.add(timeFromJson.getString(2));
                             extractedTimeListCount++;
                             Log.e("show_me_p "," p0 ");

//                             if(timeUpdateLock==0){
//                                 timeUpdateLock =1;
                                 Log.e("show_me_p "," p1 ");

                                 pt =  timeUpdate();
                                 Log.e("show_me_p "," p2 "+pt);
//                             }
                             // may need a list count
                         }
                     }

int count=0;

//                        for (int j = 0; j < term_time.size(); j++) {
                     for (JSONArray hb904Array : term_time) {
                         int colourCountMain = colourCount-extractedTimeListCount;

                         if(colourCountMain==count) {

updatedTime.add(1);
                         }else {
                             updatedTime.add(0);

                         }


                         HopperBus904Folder map = new HopperBus904Folder();


                         map.setBusCode((String) hb904Array.get(0));
                         map.setBusLocation((String) hb904Array.get(1));
                         String i = hb904Array.getString(2);

                         Log.e("check json ", "check date ******* " + i);
                       map.setUpdateColour((int) updatedTime.get(count));
                         if(colourCountMain==count) {


                             map.setBusTime(String.valueOf(pt));
                         }else {
                             map.setBusTime((String) hb904Array.get(2));

                         }
//                        map.setPhoto(image.getUrl());
                         hb904ArrayList.add(map);
//
                         count++;
//                            term_time_schedule_main2_0 = (String) term_time_schedule_main.get(0);
//                            term_time_schedule_main2_1 = (String) term_time_schedule_main.get(1);
//                            term_time_schedule_main2_2 = (String) term_time_schedule_main.get(2);

                         Log.e("check json ", "term_time2 content ******* " + term_time);
                         Log.e("check json ", "term_time2 size ******* " + term_time.size());
                         Log.e("check json ", "get count update ******* " + count);
                    Log.e("check json ", "get count ******* " + updatedTime);

                         Log.e("check 904 list ", "  ******* " + hb904ArrayList);
                         Log.e("check 904 list ", " size ******* " + hb904ArrayList.size());

//                            Log.e("check json ", "term_time2 ******* " + term_time_schedule_main2_0);
//                            Log.e("check json ", "term_time2 ******* " + term_time_schedule_main2_1);
//                            Log.e("check json ", "term_time2 ******* " + term_time_schedule_main2_2);

                     }

                     Log.e("check list ", "final ******* " + term_time.size());
                     Log.e("check list ", "final ******* " + term_time);

//                     mProgressDialog.dismiss();
                     listview = (ListView) findViewById(R.id.listView);
                     // Pass the results into _ListViewAdapter.java
                     adapter = new HopperBusAdapter(HopperBus904.this,
                             hb904ArrayList);
                     // Binds the Adapter to the ListView
                     listview.setAdapter(adapter);
//                    tv.setText(data);

                     /**  using lists
                      *

                      term_time = new ArrayList<>();
                      term_time2 = new ArrayList<>();

                      JSONArray   term_time_schedule_select;
                      JSONArray term_time_schedule_select2=null;
                      for (int i = 0; i < term_time.size(); i++) {

                      term_time_schedule_select = (JSONArray) term_time.get(i);
                      Log.e("check json ", "term_time select ******* " + term_time_schedule_select);
                      //                        term_time2.add(String.valueOf(term_time_schedule_select));
                      term_time_schedule_select2 = (JSONArray) term_time_schedule_select.get(0);

                      }



                      Log.e("check LIST ", "term_time2 ******* " + term_time_schedule_select2);
                      */
                 }
                 //TO
                 else {
                     mProgressDialog.dismiss();

                     Toast.makeText(getApplicationContext(),"no times available ",Toast.LENGTH_LONG).show();
                 }
                }catch(JSONException e){
                    Log.e("check json ", "error ******* " + e.toString());


                } catch (ParseException e) {
                    Log.e("check parse ", "error ******* " + e.toString());
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end

    public int timeUpdate(){

        if(!extractedTimeList.isEmpty()) {
            Log.e("enter list ", "highlightedTimeList" );
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date ttime = new Date();
            String temp = sdf.format(ttime);

            String t1 = extractedTimeList.get(0);
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
            return  displayWaitingTime;
        }

        return 0;
    }

} // Activity end