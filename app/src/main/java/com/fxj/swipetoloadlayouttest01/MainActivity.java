package com.fxj.swipetoloadlayouttest01;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeToLoadLayout;
    private ArrayList<Integer> mProductInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();


        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load_layout);

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("fxj0110","正在刷新中……");
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeToLoadLayout.setRefreshing(false);
//                        swipeToLoadLayout.setRefreshEnabled(false);
                    }
                },5000);
            }
        });


        CustomRefreshHeader header = (CustomRefreshHeader) findViewById(R.id.swipe_refresh_header);
        swipeToLoadLayout.setRefreshHeaderView(header);

        recyclerView = (RecyclerView) findViewById(R.id.swipe_target);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.common_margin_left)));
        CustomAdatper adatper=new CustomAdatper(this,mProductInfos);
        recyclerView.setAdapter(adatper);
    }

    public class CustomAdatper extends RecyclerView.Adapter<CustomAdatper.CustomViewHolder>{
        private Context mContext;
        private ArrayList<Integer> resIds;
        public class CustomViewHolder extends RecyclerView.ViewHolder{
            ImageView itemImage;
            public CustomViewHolder(View itemView) {
                super(itemView);
                itemImage= (ImageView) itemView.findViewById(R.id.item_img);

            }
        }

        public CustomAdatper(Context mContext, ArrayList<Integer> resIds) {
            this.mContext = mContext;
            this.resIds = resIds;
        }

        @Override
        public int getItemCount() {
            return resIds.size();
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false);
            CustomViewHolder viewHolder=new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            ImageSize imageSize=getImageSize(mContext,resIds.get(position));
            ViewGroup.LayoutParams params =holder.itemImage.getLayoutParams();
            params.width= (int) (mContext.getResources().getDisplayMetrics().widthPixels*0.7);
            params.height=params.width*imageSize.imageHeight/imageSize.imageWidth;
            holder.itemImage.setLayoutParams(params);
            holder.itemImage.setImageResource(resIds.get(position));
        }


    }
    public ImageSize getImageSize(Context context, int resId){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(context.getResources(),resId,options);
        return new ImageSize(options.outWidth,options.outHeight);
    }
    class ImageSize {
        protected int imageWidth;
        protected int imageHeight;

        public ImageSize(int imageWidth, int imageHeight) {
            this.imageWidth = imageWidth;
            this.imageHeight = imageHeight;
        }



    }
    void initData(){
        mProductInfos = new ArrayList<>();

        mProductInfos.add(R.drawable.ic_girls_0);
        mProductInfos.add(R.drawable.ic_girls_1);
        mProductInfos.add(R.drawable.ic_girls_2);
        mProductInfos.add(R.drawable.ic_girls_3);
        mProductInfos.add(R.drawable.ic_girls_4);
        mProductInfos.add(R.drawable.ic_girls_5);
        mProductInfos.add(R.drawable.ic_girls_6);
        mProductInfos.add(R.drawable.ic_girls_7);
        mProductInfos.add(R.drawable.ic_girls_8);
        mProductInfos.add(R.drawable.ic_girls_9);
        mProductInfos.add(R.drawable.ic_girls_10);
        mProductInfos.add(R.drawable.ic_view_0);
        mProductInfos.add(R.drawable.ic_view_1);
        mProductInfos.add(R.drawable.ic_view_2);
        mProductInfos.add(R.drawable.ic_view_3);
        mProductInfos.add(R.drawable.ic_view_4);
        mProductInfos.add(R.drawable.ic_view_5);
        mProductInfos.add(R.drawable.ic_view_6);
        mProductInfos.add(R.drawable.ic_view_7);
        mProductInfos.add(R.drawable.ic_view_8);
        mProductInfos.add(R.drawable.ic_view_9);
        mProductInfos.add(R.drawable.ic_view_10);
        mProductInfos.add(R.drawable.ic_view_11);
        mProductInfos.add(R.drawable.ic_view_12);
        mProductInfos.add(R.drawable.ic_view_13);
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;
        private StaggeredGridLayoutManager.LayoutParams lp;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);

            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (position == 0) {
                outRect.top = 0;
            } else {
                lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                // 左item的对右间隔设为0，保证item间隔一致
                if (lp.getSpanIndex() == 0) {
                    outRect.right = 0;
                }
            }

        }
    }
}
