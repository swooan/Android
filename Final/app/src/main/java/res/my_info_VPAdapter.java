package res;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fragment.My_Info_fragment.my_info_payed;
import fragment.My_Info_fragment.my_info_reserved;

public class my_info_VPAdapter extends FragmentStatePagerAdapter {

    int iNumOfTabs;

    public my_info_VPAdapter(@NonNull FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.iNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 : return new my_info_reserved();
            case 1 : return new my_info_payed();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return iNumOfTabs;
    }
}
