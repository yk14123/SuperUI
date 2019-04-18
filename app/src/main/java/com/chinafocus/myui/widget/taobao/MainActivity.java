package com.chinafocus.myui.widget.taobao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bumptech.glide.Glide;
import com.chinafocus.myui.R;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //应用
    String[] ITEM_NAMES = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    int[] IMG_URLS = {R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai, R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center, R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction, R.mipmap.ic_classify};

    //    高颜值商品位
    int[] ITEM_URL = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5};
    int[] GRID_URL = {R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3, R.mipmap.flashsale4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        initView();
//        initTime();
    }

    private void initTime() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalDateTime localDateTimeBefore = LocalDateTime.now().minus(30, ChronoUnit.MINUTES);

            Log.e("between-Time", "localDateTime = " + localDateTime);
            Log.e("between-Time", "localDateTimeBefore = " + localDateTimeBefore);

            long between = ChronoUnit.MINUTES.between(localDateTimeBefore, localDateTime);
            Log.e("between-Time", "between = " + between);


            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            ZonedDateTime zonedDateTimeBefore = ZonedDateTime.now().minus(1, ChronoUnit.DAYS);


            Log.e("between-Time", "zonedDateTime = " + zonedDateTime);
            Log.e("between-Time", "zonedDateTimeBefore = " + zonedDateTimeBefore);

            long betweenZoned = ChronoUnit.MINUTES.between(zonedDateTimeBefore, zonedDateTime);
            Log.e("between-Time", "betweenZoned = " + betweenZoned);
        }

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
//        viewPool.setMaxRecycledViews(1, 10);
//        viewPool.setMaxRecycledViews(2, 1);
//        viewPool.setMaxRecycledViews(3, 1);
//        viewPool.setMaxRecycledViews(4, 4);

        List<DelegateAdapter.Adapter> adapterLists = new LinkedList<>();

        /**
         设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        // 公共属性
//        stickyLayoutHelper.setItemCount(1);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //这个Margin设置了会卡
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量

        BaseDelegateAdapter stickyAdapter = new BaseDelegateAdapter(this,stickyLayoutHelper,R.layout.vlayout_grid,1,10){

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
                if (position == 0){
                    int item = GRID_URL[0];
                    ImageView iv = baseViewHolder.getView(R.id.iv);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };


        /**
         设置可选固定布局
         */

        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,0,0);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y



        // 公共属性
        scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
//        @Override
//        public void setItemCount(int itemCount) {
//            if (itemCount > 0) {
//                super.setItemCount(1);
//            } else {
//                super.setItemCount(0);
//            }
//        }
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);// 设置Item的显示模式

        // scrollFixLayoutAdapter的位置！决定了scroll展示的时机
        // 原理就是，在scrollFixLayoutAdapter的布局位置，插入了一个隐藏的不可见View，当不可见View，滑出去的时候，scroll展示！
        BaseDelegateAdapter scrollFixLayoutAdapter  = new BaseDelegateAdapter(this, scrollFixLayoutHelper,R.layout.vlayout_fixlayout_item, 1,11) {
            // 设置需要展示的数据总数,此处设置是1
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为scrollFix
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                if (position == 0 ){

                    int item = GRID_URL[0];
                    ImageView iv = holder.getView(R.id.Image);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });

                    holder.setText(R.id.Item,"这是scroll滚动的Layout");
                }
            }
        };

        /**
         设置浮动布局
         */
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        // 创建FloatLayoutHelper对象

        // 公共属性
        floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
//        @Override
//        public void setItemCount(int itemCount) {
//            if (itemCount > 0) {
//                super.setItemCount(1);
//            } else {
//                super.setItemCount(0);
//            }
//        }
        floatLayoutHelper.setAlignType(FixLayoutHelper.TOP_RIGHT);
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // floatLayoutHelper特有属性
//        floatLayoutHelper.setDefaultLocation(0, 0);// 设置布局里Item的初始位置

        BaseDelegateAdapter Adapter_FloatLayout = new BaseDelegateAdapter(this, floatLayoutHelper,R.layout.vlayout_floatlayout_item,1,12) {
            // 设置需要展示的数据总数,此处设置是1
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为float
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(500,1000);
//                holder.itemView.setLayoutParams(layoutParams);
//                holder.itemView.setBackgroundColor(Color.RED);
//
//                if (position == 0) {
//                    holder.Text.setText("float");
//                }
                if (position == 0 ){
                    int item = GRID_URL[0];
                    ImageView iv = holder.getView(R.id.iv_float);
                    Glide.with(getApplicationContext()).load(item).into(iv);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });

//                    holder.setText(R.id.Item,"这是float滚动的Layout");
                }
            }
        };

        // type = 0 banner
        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(this
                , new LinearLayoutHelper(), R.layout.vlayout_banner, 1,1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int i) {
                ArrayList<String> arrayList = new ArrayList<>();
//                arrayList.add("http://dn.dengpaoedu.com/examples/glide/1.jpg");
//                arrayList.add("http://dn.dengpaoedu.com/examples/glide/2.jpg");
//                arrayList.add("http://dn.dengpaoedu.com/examples/glide/3.jpg");
//                arrayList.add("http://dn.dengpaoedu.com/examples/glide/4.jpg");
//                arrayList.add("http://dn.dengpaoedu.com/examples/glide/5.jpg");
//                arrayList.add("http://dn.dengpaoedu.com/examples/glide/6.jpg");
                arrayList.add("https://img.alicdn.com/tps/i4/TB1GxpuQPDpK1RjSZFrSuu78VXa.jpg");
                arrayList.add("https://img.alicdn.com/imgextra/i3/141/O1CN01bz8pq51CucMODBK3X_!!141-0-lubanu.jpg_q100.jpg_.webp");
                arrayList.add("https://aecpm.alicdn.com/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg");
                arrayList.add("https://img.alicdn.com/simba/img/TB13DpuO7voK1RjSZFDSutY3pXa.jpg");
                arrayList.add("https://img.alicdn.com/imgextra/i4/198/O1CN01gNGnym1DKiw7Nechy_!!198-0-luban.jpg_q100.jpg_.webp");
                // 绑定数据
                Banner mBanner = holder.getView(R.id.banner);
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                mBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                mBanner.setImages(arrayList);
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                //        mBanner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                mBanner.isAutoPlay(true);
                //设置轮播时间
                mBanner.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                mBanner.start();

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });

                super.onBindViewHolder(holder, i);
            }
        };

        // type = 1 分类itemLog
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setPadding(0, 16, 0, 0);
        gridLayoutHelper.setVGap(10);
        gridLayoutHelper.setHGap(0);//// 控制子元素之间的水平间距
        gridLayoutHelper.setSpanCount(4);
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {

            /**
             *
             * @param position 是从recyclerView整体组件内的item，开始计算！，比如该用例，前面有个banner（item=1），有个FixLayout（item=1），所以此处所有pos需+2
             * @return 注意是占位权重！！
             *  比如是4列。1-4个位置的元素，水平排列占满宽度
             *
             */
            @Override
            public int getSpanSize(int position) {
                if (position < 6) {
                    return 1;
                } else if (position < 10){
                    return 2;
                }else {
                    return 4;
                }
            }
        });

        BaseDelegateAdapter menuAdapter = new BaseDelegateAdapter(this, gridLayoutHelper, R.layout.vlayout_menu, 10,2) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                holder.setText(R.id.tv_menu_title_home, ITEM_NAMES[position] + "");
                holder.setImageResource(R.id.iv_menu_home, IMG_URLS[position]);
                holder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), ITEM_NAMES[position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        // type = 2 头条跑马灯
        BaseDelegateAdapter newsAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(),
                R.layout.vlayout_news, 1,3) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int i) {
                MarqueeView marqueeView1 = holder.getView(R.id.marqueeView1);
                MarqueeView marqueeView2 = holder.getView(R.id.marqueeView2);

                List<String> info1 = new ArrayList<>();
                info1.add("天猫超市最近发大活动啦，快来抢");
                info1.add("没有最便宜，只有更便宜！");

                List<String> info2 = new ArrayList<>();
                info2.add("这个是用来搞笑的，不要在意这写小细节！");
                info2.add("啦啦啦啦，我就是来搞笑的！");

                marqueeView1.startWithList(info1);
                marqueeView2.startWithList(info2);
                // 在代码里设置自己的动画
                marqueeView1.startWithList(info1, R.anim.anim_bottom_in, R.anim.anim_top_out);
                marqueeView2.startWithList(info2, R.anim.anim_bottom_in, R.anim.anim_top_out);

                marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                marqueeView2.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        // SingleLayout布局，内容只有1个，超出部分被抛弃
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        HorizontalAdapter singleAdapter = new HorizontalAdapter(this, singleLayoutHelper) {

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                if (holder.itemView instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) holder.itemView;
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(new HotItemAdapter(getApplicationContext(), IMG_URLS, ITEM_NAMES));
                }
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, false);

//        delegateAdapter.addAdapter(bannerAdapter);
//
//        delegateAdapter.addAdapter(menuAdapter);
//
//        delegateAdapter.addAdapter(newsAdapter);
//
//        delegateAdapter.addAdapter(singleAdapter);

        adapterLists.add(bannerAdapter);
        adapterLists.add(menuAdapter);
        adapterLists.add(newsAdapter);
        adapterLists.add(scrollFixLayoutAdapter);
        adapterLists.add(Adapter_FloatLayout);
        adapterLists.add(singleAdapter);
        adapterLists.add(stickyAdapter);
        for (int i = 0; i < ITEM_URL.length; i++) {
            final int finalI = i;
            // type = 3
            BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(this,
                    new LinearLayoutHelper(), R.layout.vlayout_title, 1,4) {
                @Override
                public void onBindViewHolder(BaseViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setImageResource(R.id.iv, ITEM_URL[finalI]);
                }
            };

            // type = 4
            GridLayoutHelper gridHelper = new GridLayoutHelper(2);
            BaseDelegateAdapter gridAdapter = new BaseDelegateAdapter(this, gridHelper,
                    R.layout.vlayout_grid, 4,5) {

                @Override
                public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                    int item = GRID_URL[position];
                    ImageView iv = holder.getView(R.id.iv);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
//            delegateAdapter.addAdapter(titleAdapter);
//            delegateAdapter.addAdapter(gridAdapter);
            adapterLists.add(titleAdapter);
            adapterLists.add(gridAdapter);
        }
//        delegateAdapter.addAdapter(stickyAdapter);



        // 纵列布局
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        BaseDelegateAdapter columnGridAdapter = new BaseDelegateAdapter(this, columnLayoutHelper,
                R.layout.vlayout_grid, 5,6) {

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                int item = GRID_URL[position % 4];
                ImageView iv = holder.getView(R.id.iv);
                Glide.with(getApplicationContext()).load(item).into(iv);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
//        delegateAdapter.addAdapter(columnGridAdapter);
        adapterLists.add(columnGridAdapter);
        // 一拖N布局
        // itemCount：为右半边展示item数量
        // 注意：一拖N布局，展示的内容，以实际传入的count为准，构造方法OnePlusNLayoutHelper（itemCount无效）
        // count只能大于1小于等于5！6和7虽然不会报错。但是无法显示。大于7直接报错
        // ps:要注意的是setItemCount()方法设置的Item数量如果与Adapter的getItemCount()方法返回的数量不同，会取决于后者。
        // setDividerHeight()设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(4);
        BaseDelegateAdapter onePlusNAdapter = new BaseDelegateAdapter(this, onePlusNLayoutHelper,
                R.layout.vlayout_grid, 5,7) {

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
                int item = GRID_URL[position % 4];
                ImageView iv = holder.getView(R.id.iv);
                Glide.with(getApplicationContext()).load(item).into(iv);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
//        delegateAdapter.addAdapter(onePlusNAdapter);
        adapterLists.add(onePlusNAdapter);

        // FloatLayoutHelper 以window为布局参数的滑动小窗口
        // StickyLayoutHelper 吸附效果


//         FixLayout
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT,30,50);
        BaseDelegateAdapter fixAdapter = new BaseDelegateAdapter(this, fixLayoutHelper,
                R.layout.vlayout_fixlayout_item, 1,8) {

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
//                int item = GRID_URL[position % 4];
                if (position == 0 ){

                    int item = GRID_URL[0];
                    ImageView iv = holder.getView(R.id.Image);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });

                    holder.setText(R.id.Item,"这是fix固定不动的Layout");
                }
            }
        };
//        delegateAdapter.addAdapter(fixAdapter);
        adapterLists.add(1,fixAdapter);
//         ScrollFixLayout
        //ScrollFixLayoutHelper——固定布局
        //
        //　　这个也是固定布局，而且使继承自FixLayoutHelper的，特性都继承了上面的，比上面多出来的功能就是可以通过设置showType来决定这个布局的Item是否显示，可以用来做一些返回顶部之类的按钮：
        //- SHOW_ALWAYS：与FixLayoutHelper的行为一致，固定在某个位置；
        //- SHOW_ON_ENTER：默认不显示视图，当页面滚动到这个视图的位置的时候，才显示；
        //- SHOW_ON_LEAVE：默认不显示视图，当页面滚出这个视图的位置的时候显示；
//        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(200, 500);

        //设置固定布局
//        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,0,0);
//        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);

//        BaseDelegateAdapter scrollFixAdapter = new BaseDelegateAdapter(this, scrollFixLayoutHelper,
//                R.layout.vlayout_grid, 5) {
//
//            @Override
//            public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
//                int item = GRID_URL[position % 4];
//                ImageView iv = holder.getView(R.id.iv);
//                Glide.with(getApplicationContext()).load(item).into(iv);
//
//                iv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        };
//        delegateAdapter.addAdapter(scrollFixAdapter);

        //StaggeredGrid 瀑布流布局  lanes：表示是3列
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(3);
        BaseDelegateAdapter staggeredGridAdapter = new BaseDelegateAdapter(this, staggeredGridLayoutHelper, R.layout.vlayout_menu, 20,9) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                layoutParams.height = 260 + position % 7 * 20;
                holder.itemView.setLayoutParams(layoutParams);

                holder.setText(R.id.tv_menu_title_home, ITEM_NAMES[position % 9] + "");
                holder.setImageResource(R.id.iv_menu_home, IMG_URLS[position % 9]);
                holder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), ITEM_NAMES[position % 9] + " -- " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        adapterLists.add(staggeredGridAdapter);

        delegateAdapter.addAdapters(adapterLists);
        mRecyclerView.setAdapter(delegateAdapter);
    }

//    class BannerAdapter   extends DelegateAdapter.Adapter<BaseViewHolder>{
//
//
//        private Context mContext;
//
//        public BannerAdapter(Context context) {
//            mContext = context;
//        }
//        @Override
//        public LayoutHelper onCreateLayoutHelper() {
//            return new LinearLayoutHelper();
//        }
//
//        @NonNull
//        @Override
//        public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            return  new BaseViewHolder(LayoutInflater.from(mContext).inflate( R.layout.vlayout_banner, viewGroup, false));
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
//            ArrayList<String> arrayList = new ArrayList<>();
//            arrayList.add("http://dn.dengpaoedu.com/examples/glide/1.jpg");
//            arrayList.add("http://dn.dengpaoedu.com/examples/glide/2.jpg");
//            arrayList.add("http://dn.dengpaoedu.com/examples/glide/3.jpg");
//            arrayList.add("http://dn.dengpaoedu.com/examples/glide/4.jpg");
//            arrayList.add("http://dn.dengpaoedu.com/examples/glide/5.jpg");
//            arrayList.add("http://dn.dengpaoedu.com/examples/glide/6.jpg");
//            // 绑定数据
//            Banner mBanner = baseViewHolder.getView(R.id.banner);
//            //设置banner样式
//            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//            //设置图片集合
//            mBanner.setImages(arrayList);
//            //设置banner动画效果
//            mBanner.setBannerAnimation(Transformer.DepthPage);
//            //设置标题集合（当banner样式有显示title时）
//            //        mBanner.setBannerTitles(titles);
//            //设置自动轮播，默认为true
//            mBanner.isAutoPlay(true);
//            mBanner.setImageLoader(new GlideImageLoader());
//            //设置轮播时间
//            mBanner.setDelayTime(3000);
//            //设置指示器位置（当banner模式中有指示器时）
//            mBanner.setIndicatorGravity(BannerConfig.CENTER);
//            //banner设置方法全部调用完毕时最后调用
//            mBanner.start();
//
//            mBanner.setOnBannerListener(new OnBannerListener() {
//                @Override
//                public void OnBannerClick(int position) {
//                    Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        @Override
//        public int getItemCount() {
//            return 1;
//        }
//    }
}
