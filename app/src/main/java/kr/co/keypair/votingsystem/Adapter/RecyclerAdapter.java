package kr.co.keypair.votingsystem.Adapter;

import android.content.Context;
import android.content.Intent;
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

import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;
import kr.co.keypair.votingsystem.fragmentation.setting.*;
import kr.co.keypair.votingsystem.fragmentation.team_info.ARG;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    ArrayList<Item> mItems ;
    Context context;


    public RecyclerAdapter(Context context, ArrayList<Item> mItems) {
        this.context = context;
        this.mItems = mItems;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final ItemViewHolder holder1 = holder ;

        holder.image.setImageResource(mItems.get(position).getImage());
        holder.name.setText(mItems.get(position).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();

                switch (holder1.getAdapterPosition()) {
                    case 0:
                        Fragment frag_notice = new setting_notice();
                        transaction.replace(R.id.content, frag_notice);
                        break;
                    case 1:
                        Fragment frag_version = new setting_version();
                        transaction.replace(R.id.content, frag_version);
                        break;
                    case 2:
                        Fragment frag_team = new setting_team();
                        transaction.replace(R.id.content, frag_team);
                        break;
                }
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public ItemViewHolder(View itemView) {
            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

}