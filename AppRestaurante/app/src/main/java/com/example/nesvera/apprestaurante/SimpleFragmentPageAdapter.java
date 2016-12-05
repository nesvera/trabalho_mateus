package com.example.nesvera.apprestaurante;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Nesvera on 03/12/2016.
 */

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {
    private String[] tabTitle = new String[]{"Home","Xis","Hotdog"};
    Context context;
    private int pageCount = 3;


    public SimpleFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {

        if( position == 0 ) {
            HomeFragment homeFragment = new HomeFragment();

            return homeFragment;

        }else{
            ListaFragment listaFragment = new ListaFragment();

            return listaFragment;

        }

    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
