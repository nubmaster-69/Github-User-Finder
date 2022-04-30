package com.hisu.retrofit.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.hisu.retrofit.R;
import com.hisu.retrofit.databinding.FragmentUserProfileBinding;
import com.hisu.retrofit.model.User;


public class UserProfileFragment extends Fragment {

    private static final String USER_KEY = "github_user";
    private FragmentUserProfileBinding binding;
    private String[] month;

    public UserProfileFragment(User user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_KEY, user);
        setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserProfileBinding.inflate(
                inflater, container, false
        );

        User user = (User) (
                getArguments() != null ? getArguments().getSerializable(USER_KEY) : null
        );

        month = getActivity().getResources().getStringArray(R.array.month);

        setUserData(user);

        return binding.getRoot();
    }

    private void setUserData(User user) {
        Glide.with(binding.userImg).load(user.getImageUrl()).into(binding.userImg);
        binding.userFullName.setText(user.getFullName());
        binding.userUsername.setText(String.valueOf("@" + user.getUserName()));

        binding.userFollower.setText(formatFollow(user.getFollowers()));
        binding.userFollowing.setText(formatFollow(user.getFollowing()));

        binding.userRepo.setText(String.valueOf(user.getRepo()));
        binding.userBio.setText(TextUtils.isEmpty(user.getBio()) ? "No bio" : user.getBio());

        binding.txtJoinDate.setText(joinDateFormat(user.getJoinDate()));

        binding.txtLocation.setText(validateData(user.getLocation(), "No Location"));

        binding.txtCompany.setText(validateData(user.getCompany(), "No Company"));

        binding.txtTwitter.setText(validateData(user.getTwitter(), "No Twitter"));

        binding.txtBlog.setText(validateData(user.getBlog(), "No Blog"));
    }

    private String validateData(String data, String msg) {
        return TextUtils.isEmpty(data) ? msg : data;
    }

    private String formatFollow(int follow) {
        if (follow >= 1000000)
            return (follow / 1000000) + "M";
        return String.valueOf(follow);
    }

    private String joinDateFormat(String joinDate) {
        String year = joinDate.substring(0, 4);
        int monthIndex = Integer.parseInt(joinDate.substring(5, 7));
        return String.format("Joined %s %s", month[monthIndex - 1], year);
    }
}