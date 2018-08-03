package com.htcompany.education.studentforgansu.mainpart.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.mainpart.entity.RewardsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 奖励记录适配器
 * Created by wrb on 2016/10/25.
 */
public class RewardsFragmentAdapter extends RecyclerView.Adapter{
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> postionlist;
    private List<RewardsEntity> datas;
    public RewardsFragmentAdapter(Context context,List<RewardsEntity> datas){
        this.context = context;
        this.datas =datas;
        inflater = LayoutInflater.from(context);
        postionlist = new ArrayList<Integer>();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler myViewHodler = new MyViewHodler(inflater.inflate(R.layout.rewards_fragment_item,parent,false));
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHodler viewHolder = (MyViewHodler) holder;
        viewHolder.huodexiangqing.setTag(new Integer(position));//设置tag 否则划回来时选中消失
        //checkbox  复用问题
        ((MyViewHodler) holder).rewards_name_tv.setText(datas.get(position).getName());
        ((MyViewHodler) holder).rewards_time_tv.setText(datas.get(position).getReward_time());
        ((MyViewHodler) holder).rewards_jb_tv.setText(datas.get(position).getReward_class());
        ((MyViewHodler) holder).rewards_dj_tv.setText(datas.get(position).getReward_level());
        ((MyViewHodler) holder). rewards_jlje_tv.setText(datas.get(position).getAmount()+"元");
        ((MyViewHodler) holder).rewards_jlwh_tv.setText(datas.get(position).getReason());
        ((MyViewHodler) holder).rewards_bjdw_tv.setText(datas.get(position).getReward_uint());
        int viewIndex = position% BaseUtils.getRwardsIconImage().size();
        ((MyViewHodler) holder).rewards_item_img.setImageResource(BaseUtils.getRwardsIconImage().get(viewIndex));
        if (postionlist != null) {
            ((MyViewHodler) holder).huodexiangqing.setChecked((postionlist.contains(new Integer(position)) ? true : false));
            if(postionlist.contains(new Integer(position))){
                viewHolder.bottom_view_item.setVisibility(View.VISIBLE);
            }else{
                viewHolder.bottom_view_item.setVisibility(View.GONE);
            }
        } else {
            ((MyViewHodler) holder).huodexiangqing.setChecked(false);
            viewHolder.bottom_view_item.setVisibility(View.GONE);
        }
       onchecked(viewHolder,position);
    }
    private void onchecked(final MyViewHodler viewHolder, final int position) {

        viewHolder.huodexiangqing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Log.e("fcccc 1", viewHolder.checkBox.getTag() + "");
                    if (!postionlist.contains(viewHolder.huodexiangqing.getTag())) {
                        postionlist.add(new Integer(position));
                        viewHolder.bottom_view_item.setVisibility(View.VISIBLE);
                    }
                } else {
                    //Log.e("fcccc 2", viewHolder.checkBox.getTag() + "");
                    if (postionlist.contains(viewHolder.huodexiangqing.getTag())) {
                        postionlist.remove(new Integer(position));
                        viewHolder.bottom_view_item.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    public class MyViewHodler extends RecyclerView.ViewHolder{
        ImageView rewards_item_img;
        CheckBox huodexiangqing;
        RelativeLayout bottom_view_item;
        TextView rewards_name_tv,rewards_time_tv,rewards_jb_tv,rewards_dj_tv,rewards_jlje_tv,rewards_jlwh_tv,rewards_bjdw_tv;
        public MyViewHodler(View itemView) {
            super(itemView);
            rewards_item_img=(ImageView)itemView.findViewById(R.id.rewards_item_img);
            huodexiangqing =(CheckBox)itemView.findViewById(R.id.huodexiangqing);
            bottom_view_item = (RelativeLayout)itemView.findViewById(R.id.bottom_view_item);
            rewards_name_tv=(TextView)itemView.findViewById(R.id.rewards_name_tv);
            rewards_time_tv=(TextView)itemView.findViewById(R.id.rewards_time_tv);
            rewards_jb_tv=(TextView)itemView.findViewById(R.id.rewards_jb_tv);
            rewards_dj_tv=(TextView)itemView.findViewById(R.id.rewards_dj_tv);
            rewards_jlje_tv=(TextView)itemView.findViewById(R.id.rewards_jlje_tv);
            rewards_jlwh_tv=(TextView)itemView.findViewById(R.id.rewards_jlwh_tv);
            rewards_bjdw_tv=(TextView)itemView.findViewById(R.id.rewards_bjdw_tv);
        }
    }
}
