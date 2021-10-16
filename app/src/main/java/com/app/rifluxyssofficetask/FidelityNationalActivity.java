package com.app.rifluxyssofficetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.app.rifluxyssofficetask.databinding.ActivityFidelitynationalBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class FidelityNationalActivity extends AppCompatActivity {

    // Initalize object of the ActivityFidelitynationalBinding
    private ActivityFidelitynationalBinding mActivityFidelitynationalBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*initialization of Databinding in FidelityNationalActivity activity in mvvm*/
        mActivityFidelitynationalBinding = DataBindingUtil.setContentView(FidelityNationalActivity.this, R.layout.activity_fidelitynational);
        /*Databinding using FidelityNationalActivity ViewModels Initialization*/
        mActivityFidelitynationalBinding.setFidelityViewModels(new FidelityViewModels(FidelityNationalActivity.this, mActivityFidelitynationalBinding));
        /*Databinding using FidelityNationalActivity ViewModels executePendingBindings*/
        mActivityFidelitynationalBinding.executePendingBindings();

    }


    /**/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {

            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {

                    new FidelityViewModels(FidelityNationalActivity.this, mActivityFidelitynationalBinding).callPhonePermission();

                } else {

                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                }
            }
        }
    }
}