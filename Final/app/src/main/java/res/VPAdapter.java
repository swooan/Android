package res;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import fragment.detail_info;
import fragment.detail_reservation;
import fragment.detail_review;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;

    public VPAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new detail_info());
        items.add(new detail_review());
        items.add(new detail_reservation());
    }

        @Override
        public Fragment getItem ( int position){
            return items.get(position);
        }

        @Override
        public int getCount () {
            return items.size();
        }

}
