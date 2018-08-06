package kr.co.keypair.votingsystem.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import kr.co.keypair.votingsystem.R;
import kr.co.keypair.votingsystem.fragmentation.setting.*;

public class RecyclerAdapter_betting2 extends RecyclerView.Adapter<RecyclerAdapter_betting2.ItemViewHolder> {

    ArrayList<Item> mItems = new ArrayList<>();
    Context context;
    View view;
    int resources;


    public RecyclerAdapter_betting2(Context context, int resource) {
        this.context = context;
        this.resources = resource;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_betting2,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final ItemViewHolder holder1 = holder ;

        Item items = (Item)getItem(position);
        holder.image1.setImageResource(items.getImage1());
        holder.image2.setImageResource(items.getImage2());
        holder.country1.setText(items.getName1());
        holder.country2.setText(items.getName2());
        holder.date.setText(items.getName3());
        holder.time.setText(items.getName4());
        holder.name1.append(items.getName5());
        holder.name2.append(items.getName6());
        holder.name3.append(items.getName7());
        holder.name4.append(items.getName8());
        holder.name5.append(items.getName9());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Object getItem(int position){
        return mItems.get(position);
    }

    public void add(int image1, int image2, String country1, String country2, String date, String time,
                    String name1, String name2, String name3, String name4, String name5){
        Item items = new Item();

        items.setImage1(image1);
        items.setImage2(image2);
        items.setName1(country1);
        items.setName2(country2);
        items.setName3(date);
        items.setName4(time);
        items.setName5(name1);
        items.setName6(name2);
        items.setName7(name3);
        items.setName8(name4);
        items.setName9(name5);


        mItems.add(items);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView country1, country2, date,time, name1, name2, name3,name4, name5;
        private ImageView image1,image2;

        public ItemViewHolder(View itemView) {
            super(itemView);

            image1 = (ImageView) itemView.findViewById(R.id.image1);
            image2 = (ImageView) itemView.findViewById(R.id.image2);
            country1 = (TextView) itemView.findViewById(R.id.country1);
            country2 = (TextView) itemView.findViewById(R.id.country2);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            name1 =(TextView)itemView.findViewById(R.id.betting_country);
            name2 = (TextView) itemView.findViewById(R.id.total_money);
            name3 = (TextView) itemView.findViewById(R.id.my_money);
            name4 = (TextView) itemView.findViewById(R.id.result);
            name5 = (TextView) itemView.findViewById(R.id.result_money);

        }
    }

}