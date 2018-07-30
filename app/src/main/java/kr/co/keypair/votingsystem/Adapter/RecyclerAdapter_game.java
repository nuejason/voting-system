package kr.co.keypair.votingsystem.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import kr.co.keypair.votingsystem.fragmentation.frag_betting;
import kr.co.keypair.votingsystem.fragmentation.team_info.*;
import kr.co.keypair.votingsystem.R;

public class RecyclerAdapter_game extends RecyclerView.Adapter<RecyclerAdapter_game.ItemViewHolder> {

    ArrayList<Item> mItems = new ArrayList<>(); ;
    Context context;
    int resources;


    public RecyclerAdapter_game(Context context, int resource) {
        this.context = context;
        this.resources = resource;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final Item items = (Item)getItem(position);

        holder.image1.setImageResource(items.getImage1());
        holder.image2.setImageResource(items.getImage2());
        holder.country1.setText(items.getName1());
        holder.country2.setText(items.getName2());
        holder.times.setText(items.getName5());

        final String frag1 = items.getName3();
        final String frag2 = items.getName4();

        Log.d("abc",frag1);


        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                Fragment country;
                switch(frag1) {
                    case "ARG": country = new ARG(); break;
                    case "BEL": country = new BEL(); break;
                    case "BRA": country = new BRA(); break;
                    case "COL": country = new COL(); break;
                    case "CRO": country = new CRO(); break;
                    case "DEN": country = new DEN(); break;
                    case "ENG": country = new ENG(); break;
                    case "ESP": country = new ESP(); break;
                    case "FRA": country = new FRA(); break;
                    case "JPN": country = new JPN(); break;
                    case "MEX": country = new MEX(); break;
                    case "POR": country = new POR(); break;
                    case "RUS": country = new RUS(); break;
                    case "SUI": country = new SUI(); break;
                    case "SWE": country = new SWE(); break;
                    case "URU": country = new URU(); break;
                    default : country = new ARG(); break;
                }
                transaction.replace(R.id.content, country);
            }
        });
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                Fragment country;
                switch(frag2) {
                    case "ARG": country = new ARG();
                    case "BEL": country = new BEL();
                    case "BRA": country = new BRA();
                    case "COL": country = new COL();
                    case "CRO": country = new CRO();
                    case "DEN": country = new DEN();
                    case "ENG": country = new ENG();
                    case "ESP": country = new ESP();
                    case "FRA": country = new FRA();
                    case "JPN": country = new JPN();
                    case "MEX": country = new MEX();
                    case "POR": country = new POR();
                    case "RUS": country = new RUS();
                    case "SUI": country = new SUI();
                    case "SWE": country = new SWE();
                    case "URU": country = new URU();
                    default : country = new ARG();
                }
                transaction.replace(R.id.content, country);
            }
        });
        holder.betting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment betting_frag = new frag_betting();
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("country1", items.getName1());
                bundle.putString("country2", items.getName2());
                bundle.putInt("image1", items.getImage1());
                bundle.putInt("image2", items.getImage2());
                bundle.putInt("game_id",items.getNum());
                betting_frag.setArguments(bundle);

                transaction.replace(R.id.content, betting_frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Object getItem(int position){
        return mItems.get(position);
    }

    public void add(int num, int image1,int image2, String country1, String country2, String times, String hometeam, String awayteam){
        Item items = new Item();
        items.setNum(num);
        items.setImage1(image1);
        items.setImage2(image2);
        items.setName1(country1);
        items.setName2(country2);
        items.setName5(times);
        items.setName3(hometeam);
        items.setName4(awayteam);
        mItems.add(items);
    }

    public void clearData() {
        mItems.clear();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView country1;
        private TextView country2;
        private ImageView image1;
        private ImageView image2;
        private TextView times;
        private Button betting;

        public ItemViewHolder(View itemView) {
            super(itemView);

            image1=(ImageView) itemView.findViewById(R.id.image1);
            image2=(ImageView)itemView.findViewById(R.id.image2);
            country1 = (TextView) itemView.findViewById(R.id.country1);
            country2 = (TextView) itemView.findViewById(R.id.country2);
            times = (TextView) itemView.findViewById(R.id.times);
            betting = (Button) itemView.findViewById(R.id.betting);
        }
    }

}