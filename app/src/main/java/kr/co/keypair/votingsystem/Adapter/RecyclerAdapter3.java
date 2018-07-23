package kr.co.keypair.votingsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

import kr.co.keypair.votingsystem.BettingActivity;
import kr.co.keypair.votingsystem.R;

public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.ItemViewHolder> {

    ArrayList<Item_game> mItems ;
    Context context;


    public RecyclerAdapter3(Context context, ArrayList<Item_game> mItems) {
        this.context = context;
        this.mItems = mItems;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {


        holder.image1.setImageResource(mItems.get(position).getImage1());
        holder.image2.setImageResource(mItems.get(position).getImage2());
        holder.country1.setText(mItems.get(position).getName1());
        holder.country2.setText(mItems.get(position).getName2());
        holder.times.setText(mItems.get(position).getTimes());

        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //country1에 맞는 fragment로 옮겨져야함
            }
        });
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //country2에 맞는 fragment로 옮겨져야함
            }
        });
        holder.betting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BettingActivity.class);
                //intent.putExtra("name", name);
                // db에서 받아서 game id(고유번호)를 넘겨줘야함
                //그 고유번호로 배팅화면 할때 바뀔수 있도록
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView country1;
        private TextView country2;
        private ImageButton image1;
        private ImageButton image2;
        private TextView times;
        private Button betting;

        public ItemViewHolder(View itemView) {
            super(itemView);

            image1=(ImageButton) itemView.findViewById(R.id.image1);
            image2=(ImageButton)itemView.findViewById(R.id.image2);
            country1 = (TextView) itemView.findViewById(R.id.country1);
            country2 = (TextView) itemView.findViewById(R.id.country2);
            times = (TextView) itemView.findViewById(R.id.times);
            betting = (Button) itemView.findViewById(R.id.betting);
        }
    }

}