package res;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fragment.detail_fragment.detail_info;
import fragment.detail_fragment.detail_reservation;
import fragment.detail_fragment.detail_review;

public class detail_VPAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public detail_VPAdapter(@NonNull FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new detail_info();
            case 1: return new detail_reservation();
            case 2: return new detail_review();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
