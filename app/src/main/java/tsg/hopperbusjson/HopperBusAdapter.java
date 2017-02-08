package tsg.hopperbusjson;

/**
 * Created by terrelsimeongordon on 04/04/16.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HopperBusAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    //    ImageLoader imageLoader;
    private List<HopperBus904Folder> organisationProList = null;

    private ArrayList<HopperBus904Folder> arraylist;

    Resources res;

    public HopperBusAdapter(Context context,
                            List<HopperBus904Folder> worldpopulationlist) {
        this.context = context;
         res = context.getResources();

        this.organisationProList = worldpopulationlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<HopperBus904Folder>();
        this.arraylist.addAll(worldpopulationlist);
//        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        TextView busCode;
        TextView busLocation;
        TextView busTime;

        ImageView photo;
    }

    @Override
    public int getCount() {
        return organisationProList.size();
    }

    @Override
    public Object getItem(int position) {
        return organisationProList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.hb_904_custom_view, null);
            // Locate the TextViews in listview_item.xml
            holder.busCode = (TextView) view.findViewById(R.id.hb_904_bus_code);
            holder.busLocation = (TextView) view.findViewById(R.id.hb_904_bus_location);
            holder.busTime = (TextView) view.findViewById(R.id.hb_904_bus_time);
//            holder.userID.setVisibility(View.INVISIBLE);

            // Locate the ImageView in listview_item.xml
            /**
             holder.photo = (ImageView) view.findViewById(R.id.doggie_recipe_img);
             **/
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews

        holder.busCode.setText(organisationProList.get(position).getBusCode());
        holder.busLocation.setText(organisationProList.get(position).getBusLocation());

        if(organisationProList.get(position).getUpdateColour()==1  && organisationProList.get(position).getBusLocation()!=null) {
            holder.busTime.setText(organisationProList.get(position).getBusTime()+"mins left");
            holder.busTime.setTextColor(res.getColor(R.color.colorUpdate));
            holder.busTime.setTypeface(null, Typeface.BOLD);
        }else {
            holder.busTime.setText(organisationProList.get(position).getBusTime());

        }
        // Set the results into ImageView
        /**
         imageLoader.DisplayImage(_DoggieRecipesList.get(position).getPhoto(),
         holder.photo);
         */
//         Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                // Send single item click data to SingleItemView Class
//                Intent intent = new Intent(context, SingleCustomLayout.class);
//                // Pass all data title
////                intent.putExtra("skill",
////                        (_DoggieRecipesList.get(position).getSkill()));
////                // Pass all data name
////                intent.putExtra("name",
////                        (_DoggieRecipesList.get(position).getName()));
//
//                intent.putExtra("userID",
//                        (organisationProList.get(position).getUserId()));
////                // Pass all data photo
////                intent.putExtra("photo",
////                        (_DoggieRecipesList.get(position).getPhoto()));
//
//                // Start SingleItemView Class
//                context.startActivity(intent);
            }
        });
        return view;
    }




}