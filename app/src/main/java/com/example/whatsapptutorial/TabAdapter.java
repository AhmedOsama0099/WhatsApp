package com.example.whatsapptutorial;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                CameraFragment cameraFragment=new CameraFragment();
                return cameraFragment;
            case 1:
                ChatFragment chatFragmenttttt=new ChatFragment();
                return chatFragmenttttt;
            case 2:
                StatusFragment statusFragment=new StatusFragment();
                return statusFragment;
            case 3:
                CallsFragment callsFragment=new CallsFragment();
                return callsFragment;
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 1:
                return "CHATS";
            case 2:
                return "STATUS";
            case 3:
                return "CALLS";
            default:
                return null;

        }
    }
}
