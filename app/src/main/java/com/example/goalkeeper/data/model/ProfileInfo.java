package com.example.goalkeeper.data.model;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileInfo {
    EditText editUserName, editEmail, editDOB;
    //ProgressBar progressBar;
    TextView textViewProfile;

    String username, first, last;
    String email;

    public ProfileInfo() {}

    public ProfileInfo(String first, String last, String username) {
        super();
        this.first = username;
        this.last =  last;
        this.username = username;

    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getUsername() {
        return username;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void getLast(String last) {
        this.last = last;
    }

    public void getUserName(String username) {
        this.username = username;
    }
}
