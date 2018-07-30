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

import kr.co.keypair.votingsystem.fragmentation.team_info.*;
import kr.co.keypair.votingsystem.R;


public class RecyclerAdapter_country extends RecyclerView.Adapter<RecyclerAdapter_country.ItemViewHolder2> {

    ArrayList<Item> mItems = new ArrayList<>();
    Context context;
    View view;
    int resources;

    public RecyclerAdapter_country(Context context, int resource) {
        this.context = context;
        this.resources = resource;
    }

    @Override
    public ItemViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team,parent,false);
        return new ItemViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_country.ItemViewHolder2 holder, int position) {
        final ItemViewHolder2 holder1 = holder ;
        Item items = (Item)getItem(position);
        holder.image.setImageResource(items.getImage1());
        holder.name.setText(items.getName1());
        view.findViewById(R.id.layout_team).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                switch (holder1.getAdapterPosition()) {
                    case 0:
                        Fragment frag_DEN = new DEN();
                        transaction.replace(R.id.content, frag_DEN);
                        break;
                    case 1:
                        Fragment frag_RUS = new RUS();
                        transaction.replace(R.id.content, frag_RUS);
                        break;
                    case 2:
                        Fragment frag_MEX = new MEX();
                        transaction.replace(R.id.content, frag_MEX);
                        break;
                    case 3 :
                        Fragment frag_BEL = new BEL();
                        transaction.replace(R.id.content, frag_BEL);
                        break;
                    case 4 :
                        Fragment frag_BRA = new BRA();
                        transaction.replace(R.id.content, frag_BRA);
                        break;
                    case 5 :
                        Fragment frag_SUI = new SUI();
                        transaction.replace(R.id.content, frag_SUI);
                        break;
                    case 6 :
                        Fragment frag_SWE = new SWE();
                        transaction.replace(R.id.content, frag_SWE);
                        break;
                    case 7 :
                        Fragment frag_ESP = new ESP();
                        transaction.replace(R.id.content, frag_ESP);
                        break;
                    case 8 :
                        Fragment frag_ARG = new ARG();
                        transaction.replace(R.id.content, frag_ARG);
                        break;
                    case 9 :
                        Fragment frag_URU = new URU();
                        transaction.replace(R.id.content, frag_URU);
                        break;
                    case 10 :
                        Fragment frag_JPN = new JPN();
                        transaction.replace(R.id.content, frag_JPN);
                        break;
                    case 11 :
                        Fragment frag_ENG = new ENG();
                        transaction.replace(R.id.content, frag_ENG);
                        break;
                    case 12 :
                        Fragment frag_COL = new COL();
                        transaction.replace(R.id.content, frag_COL);
                        break;
                    case 13 :
                        Fragment frag_CRO = new CRO();
                        transaction.replace(R.id.content, frag_CRO);
                        break;
                    case 14 :
                        Fragment frag_POR = new POR();
                        transaction.replace(R.id.content, frag_POR);
                        break;
                    case 15 :
                        Fragment frag_FRA = new FRA();
                        transaction.replace(R.id.content, frag_FRA);
                        break;
                }
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void add(int image, String name){
        Item items = new Item();
        items.setImage1(image);
        items.setName1(name);

        mItems.add(items);
    }

    private Object getItem(int position){
        return mItems.get(position);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder2 extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public ItemViewHolder2(View itemView) {
            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.flag);
            name = (TextView) itemView.findViewById(R.id.country_name);
        }
    }


}