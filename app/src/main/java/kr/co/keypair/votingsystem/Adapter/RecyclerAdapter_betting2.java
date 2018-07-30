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
        holder.name1.append(items.getName1());
        holder.name2.append(items.getName2());
        holder.name3.append(items.getName3());
        holder.name4.append(items.getName4());
        holder.name5.append(items.getName5());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Object getItem(int position){
        return mItems.get(position);
    }

    public void add(String name1, String name2, String name3){
        Item items = new Item();
        items.setName1(name1);
        items.setName2(name2);
        items.setName3(name3);

        mItems.add(items);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView name1, name2, name3, name4, name5;

        public ItemViewHolder(View itemView) {
            super(itemView);

            name1=(TextView)itemView.findViewById(R.id.betting_country);
            name2 = (TextView) itemView.findViewById(R.id.total_money);
            name3 = (TextView) itemView.findViewById(R.id.my_money);
            name4 = (TextView) itemView.findViewById(R.id.result);
            name5 = (TextView) itemView.findViewById(R.id.result_money);
        }
    }

}