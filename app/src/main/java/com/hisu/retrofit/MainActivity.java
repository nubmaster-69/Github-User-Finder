package com.hisu.retrofit;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hisu.retrofit.api.GithubApi;
import com.hisu.retrofit.databinding.ActivityMainBinding;
import com.hisu.retrofit.fragment.UserProfileFragment;
import com.hisu.retrofit.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mainBinding.getRoot());

        mainBinding.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                mainBinding.pbLoading.setVisibility(View.VISIBLE);

                String searchUsername = mainBinding.edtSearch.getText().toString().trim();

                GithubApi.githubApiService.getUser(searchUsername)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.body() != null)
                                    setFragment(new UserProfileFragment(response.body()));
                                else
                                    showAlert("Couldn't find this user :<");
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                showAlert("Error! Please check your network connection!");
                            }
                        });

                return false;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(mainBinding.userProfileContainer.getId(), fragment)
                .commit();
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_alert)
                .setTitle("Something went wrong!")
                .setMessage(msg)
                .setPositiveButton("OK", null).show();
    }

    public void closePB() {
        mainBinding.pbLoading.setVisibility(View.GONE);
    }
}