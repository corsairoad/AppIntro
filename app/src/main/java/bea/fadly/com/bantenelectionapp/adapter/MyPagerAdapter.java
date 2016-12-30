package bea.fadly.com.bantenelectionapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIGIKOM-EX4 on 11/22/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    List<String> titleFragments = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titleFragments.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
