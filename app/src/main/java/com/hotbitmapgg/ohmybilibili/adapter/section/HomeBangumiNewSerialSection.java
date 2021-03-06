package com.hotbitmapgg.ohmybilibili.adapter.section;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.ohmybilibili.R;
import com.hotbitmapgg.ohmybilibili.entity.bangumi.MiddlewareBangumi;
import com.hotbitmapgg.ohmybilibili.entity.bangumi.NewBangumiSerial;
import com.hotbitmapgg.ohmybilibili.module.home.bangumi.BangumiDetailsActivity;
import com.hotbitmapgg.ohmybilibili.module.home.bangumi.NewBangumiSerialActivity;
import com.hotbitmapgg.ohmybilibili.utils.NumberUtil;
import com.hotbitmapgg.ohmybilibili.widget.sectioned.StatelessSection;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hcc on 2016/10/14 19:29
 * 100332338@qq.com
 * <p>
 * 首页番剧新番连载Section
 */

public class HomeBangumiNewSerialSection extends StatelessSection
{

    private Context mContext;

    private List<NewBangumiSerial.ListBean> newBangumiSerials;


    public HomeBangumiNewSerialSection(Context context, List<NewBangumiSerial.ListBean> newBangumiSerials)
    {

        super(R.layout.layout_home_bangumi_new_serial_head, R.layout.layout_home_bangumi_new_serial_body);
        this.mContext = context;
        this.newBangumiSerials = newBangumiSerials;
    }

    @Override
    public int getContentItemsTotal()
    {

        return 6;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view)
    {

        return new ItemViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        NewBangumiSerial.ListBean listBean = newBangumiSerials.get(position);

        Glide.with(mContext)
                .load(listBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(itemViewHolder.mImage);

        itemViewHolder.mTitle.setText(listBean.getTitle());
        itemViewHolder.mPlay.setText(NumberUtil.converString(listBean.getPlay_count()) + "人在看");
        itemViewHolder.mUpdate.setText("更新至第" + listBean.getBgmcount() + "话");

        itemViewHolder.mCardView.setOnClickListener(v -> {
            NewBangumiSerial.ListBean listBean1 = newBangumiSerials.get(position);
            MiddlewareBangumi middlewareBangumi = new MiddlewareBangumi();
            middlewareBangumi.setPic(listBean1.getCover());
            middlewareBangumi.setTitle(listBean1.getTitle());
            middlewareBangumi.setSpid(listBean1.getSpid());
            middlewareBangumi.setSeason_id(listBean1.getSeason_id());
            middlewareBangumi.setFavorites(listBean1.getFavorites());
            middlewareBangumi.setPlay(listBean1.getPlay_count());
            middlewareBangumi.setWeekday(listBean1.getWeekday());
            middlewareBangumi.setCreate(listBean1.getLastupdate_at());
            middlewareBangumi.setCount(Integer.valueOf(listBean1.getBgmcount()));
            BangumiDetailsActivity.launch((Activity) mContext, middlewareBangumi);
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view)
    {

        return new HomeBangumiNewSerialSection.HeaderViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder)
    {

        HomeBangumiNewSerialSection.HeaderViewHolder headerViewHolder = (HomeBangumiNewSerialSection.HeaderViewHolder) holder;
        headerViewHolder.mAllSerial.setOnClickListener(v -> mContext.startActivity(
                new Intent(mContext, NewBangumiSerialActivity.class)));
    }


    static class HeaderViewHolder extends RecyclerView.ViewHolder
    {

        @Bind(R.id.tv_all_serial)
        TextView mAllSerial;


        HeaderViewHolder(View itemView)
        {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder
    {

        @Bind(R.id.card_view)
        LinearLayout mCardView;

        @Bind(R.id.item_img)
        ImageView mImage;

        @Bind(R.id.item_title)
        TextView mTitle;

        @Bind(R.id.item_play)
        TextView mPlay;

        @Bind(R.id.item_update)
        TextView mUpdate;


        public ItemViewHolder(View itemView)
        {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
