package kr.co.keypair.votingsystem.fragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import kr.co.keypair.votingsystem.R;
import kr.co.keypair.votingsystem.fragmentation.my_betting.betting1;
import kr.co.keypair.votingsystem.fragmentation.my_betting.betting2;

public class frag_betting extends Fragment {

    View v;
    private RadioButton country1_name, country2_name;
    private ImageView country1_image, country2_image;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("배팅하기");
        v = inflater.inflate(R.layout.fragment_frag_betting, container, false);

        Bundle extra = getArguments();
        int image1 = extra.getInt("image1");
        int image2 = extra.getInt("image2");
        String country1 = extra.getString("country1");
        String country2 = extra.getString("country2");

        country1_image = (ImageView)v.findViewById(R.id.image1);
        country2_image = (ImageView)v.findViewById(R.id.image2);

        country1_image.setImageResource(image1);
        country2_image.setImageResource(image2);

        country1_name = (RadioButton) v.findViewById(R.id.country1);
        country2_name = (RadioButton) v.findViewById(R.id.country2);

        country1_name.setText(country1);
        country2_name.setText(country2);




        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
