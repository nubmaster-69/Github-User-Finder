package com.hisu.retrofit.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @SerializedName("login")
    private String userName;
    @SerializedName("name")
    private String fullName;
    @SerializedName("avatar_url")
    private String imageUrl;
    private int followers;
    private int following;
    @SerializedName("public_repos")
    private int repo;
    @SerializedName("created_at")
    private String joinDate;
    private String bio;
    private String company;
    private String location;
    @SerializedName("twitter_username")
    private String twitter;
    private String blog;

    public String getTwitter() {
        return TextUtils.isEmpty(twitter) ? "" : "@" + twitter;
    }
}