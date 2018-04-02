package zarf18.zhcet.msaqib.com.zarf18;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by MSaqib on 29-03-2018.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position%3) {
            case 0:
                return new FeedsFragment();
            case 1:
                return new SponsorsFragment();
            case 2:
                return new EventsFragment();
        }

        return new FeedsFragment();
    }

    @Override
    public int getCount() { return 3; }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position%3) {
            case 0:
                return "FEEDS";
            case 1:
                return "GALLERY";
            case 2:
                return "EVENTS";
        }

        return "FEEDS";

    }
}