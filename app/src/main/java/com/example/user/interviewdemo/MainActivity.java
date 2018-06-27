package com.example.sudhakarsinha.HamaraGaon.Adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sudhakarsinha.HamaraGaon.Models.VillageModel;
import com.example.sudhakarsinha.HamaraGaon.R;

import java.util.List;

import static com.example.sudhakarsinha.HamaraGaon.GlobalClass.mDB;

/**
 * Created by user on 2/20/2018.
 */

public class VillageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VillageModel> villageList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 2;
    public  int villageID = 0;
    public  int numOFBASIC = 0;
    public  int numOFVolunt = 0;
    public  int numOFSCHOOL= 0;
    public  int numTestingMela= 0;
    public MyAdapterListener onClickListener;
    public interface MyAdapterListener {
        void iconBasicInfoOnClick(View v, int position);
        void iconSchoolInfoViewOnClick(View v, int position);
        void iconTesting1ViewOnClick(View v, int position);
        void volunterInfo1ViewOnClick(View v, int position);
        void testingMelaViewOnClick(View v, int position);
    }

    public VillageAdapter(List<VillageModel> villageList) {
        this.villageList = villageList;
    }
    public VillageAdapter(List<VillageModel> villageList, MyAdapterListener listener) {
        this.villageList = villageList;
        onClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  View itemView = LayoutInflater.from(parent.getContext())
        //        .inflate(R.layout.student_list_row, parent, false);
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.village_list_row, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            //Inflating header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.village_list_header, parent, false);
            return new HeaderViewHolder(itemView);
        }
        else {
            // return new MyViewHolder(itemView);
            return  null;
        }




    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) { HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            VillageModel     villageModel = villageList.get(position-1);
            itemViewHolder.vname.setText(villageModel.getVill_Name());
            itemViewHolder.imgbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VillageModel villageModel =  villageList.get(position-1);
                    onClickListener.iconBasicInfoOnClick(v, Integer.valueOf(villageModel.getVill_id()));
                }
            });
            itemViewHolder.imgbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VillageModel villageModel = villageList.get(position-1);
                    onClickListener.iconSchoolInfoViewOnClick(v, Integer.valueOf(villageModel.getVill_id()));
                }
            });
            itemViewHolder.imgbtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VillageModel villageModel =  villageList.get(position-1);
                    onClickListener.iconTesting1ViewOnClick(v, Integer.valueOf(villageModel.getVill_id()));
                }
            });

            itemViewHolder.imgbtn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VillageModel villageModel =  villageList.get(position-1);
                    onClickListener.volunterInfo1ViewOnClick(v, Integer.valueOf(villageModel.getVill_id()));
                }
            });

        }
        /////////////////////////////Fetching Detail/////////////////////////////

        VillageModel     villageModel = villageList.get(position-1);
        villageID = Integer.valueOf(villageModel.getVill_id());
        Cursor packcur_basic = mDB.rawQuery("Select * from hg_basicinfo WHERE Vid=" + villageID , null);
        numOFBASIC = packcur_basic.getCount();  packcur_basic.close();

        Cursor packcur_volnt = mDB.rawQuery("Select * from hg_volunteerInfo WHERE Vid=" + villageID , null);
        numOFVolunt = packcur_volnt.getCount();   packcur_volnt.close();

        Cursor packcur_school = mDB.rawQuery("Select * from hg_schoolinfo WHERE Vid=" + villageID , null);
        numOFSCHOOL = packcur_school.getCount();  packcur_school.close();

        Cursor packcur_test = mDB.rawQuery("Select * from hg_testingmelaInfo WHERE Vid=" + villageID , null);
        numTestingMela = packcur_test.getCount();  packcur_test.close();

        Log.d("setServeyStatus1--","numOFBASIC"+numOFBASIC+"numOFVolunt"+numOFVolunt+"numOFSCHOOL"+numOFSCHOOL+"numTestingMela"+numTestingMela);
        setServeyStatus( (ItemViewHolder) holder,numOFBASIC,numOFVolunt,numOFSCHOOL,numTestingMela);

        /////////////////////////////Fetching Detail/////////////////////////////

    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {


        public HeaderViewHolder(View view) {
            super(view);

        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView vid,vname;
        public TextView imgbtn1,imgbtn2,imgbtn3,imgbtn4;

        public ItemViewHolder(View itemView) {
            super(itemView);

            vname = (TextView) itemView.findViewById(R.id.vill_name);
            imgbtn1 = (TextView) itemView.findViewById(R.id.imageButton2);
            imgbtn2 = (TextView) itemView.findViewById(R.id.imageButton);
            imgbtn3 = (TextView) itemView.findViewById(R.id.imageButton3);
            imgbtn4 = (TextView) itemView.findViewById(R.id.imageButton4);

        }
    }

    public void setServeyStatus( ItemViewHolder itemViewHolder,int numofBasic,int numofVolunt,int numofSchool,int numofTestingmela){
        Log.d("setServeyStatus2--","numofBasic"+numofBasic+"numofVolunt"+numofVolunt+"numofSchool"+numofSchool+"numofTestingmela"+numofTestingmela);


        if(numofBasic == 0){itemViewHolder.imgbtn1.setText("+");}else if(numofBasic >0){itemViewHolder.imgbtn1.setText("√");}
        if(numofVolunt == 0){itemViewHolder.imgbtn4.setText("+");}else if(numofVolunt >0){itemViewHolder.imgbtn4.setText("√");}
        if(numofSchool == 0){itemViewHolder.imgbtn2.setText("+");}else if(numofSchool >0){itemViewHolder.imgbtn2.setText("√");}
        if(numofTestingmela == 0){itemViewHolder.imgbtn3.setText("+");}else if(numofTestingmela >0){itemViewHolder.imgbtn3.setText("√");}






    }

    @Override
    public int getItemCount() {
        return villageList.size()+1;
    }


}