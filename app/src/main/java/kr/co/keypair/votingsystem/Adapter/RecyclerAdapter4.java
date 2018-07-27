package kr.co.keypair.votingsystem.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import kr.co.keypair.votingsystem.R;

public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.ItemViewHolder> {

    ArrayList<Item_player> mItems = new ArrayList<>();;
    Context context;
    int resources;


    public RecyclerAdapter4(Context context, int resource) {
        this.context = context;
        this.resources = resource;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        Item_player items = (Item_player)getItem(position);
        holder.player_name.setText(items.getName());
        holder.player_position.setText(items.getPosition());
        int num = items.getNum();
        holder.player_num.setText(String.valueOf(num));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Object getItem(int position){
        return mItems.get(position);
    }

    public void add(String player_name, String player_position, int player_num){
        Item_player items = new Item_player();

        items.setName(player_name);
        items.setPosition(player_position);
        items.setNum(player_num);
        mItems.add(items);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView player_name;
        private TextView player_num;
        private TextView player_position;

        public ItemViewHolder(View itemView) {
            super(itemView);

            player_name = (TextView) itemView.findViewById(R.id.player_name);
            player_num = (TextView) itemView.findViewById(R.id.player_num);
            player_position = (TextView) itemView.findViewById(R.id.player_position);
        }
    }

}