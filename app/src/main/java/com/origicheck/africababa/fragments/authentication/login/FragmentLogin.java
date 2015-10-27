package com.origicheck.africababa.fragments.authentication.login;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.sync.worker.SyncExecutorThread;
import com.origicheck.africababa.views.circleimageview.CircleImageView;

/**
 * Created by victor on 10/22/2015.
 */
public class FragmentLogin extends FragmentWrapper implements View.OnClickListener {

    private View mLoginView;

    private CircleImageView mIvAvatar;
    private EditText mEtUsername;
    private EditText mEtPassword;

    private Button mBtLogin;
    private Button mBtSignup;

    private OnClickSignup onClickSignup;

    private Bundle arguments;
    private Receiver receiver;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        receiver = new Receiver();
    }

    @Nullable
    @Override
    public String getActivityTitle() {
        return null;
    }

    @Override
    public void receiveBundle() {
        arguments = getArguments();
    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoginView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mLoginView);
        getContainer().addView(mLoginView);
    }


    @Override
    public void consumeBundle() {
        if (arguments != null) {
            String username = arguments.getString(Intents.EXTRA_USERNAME, "");
            String password = arguments.getString(Intents.EXTRA_PASSWORD, "");

            mEtUsername.setText(username);
            mEtPassword.setText(password);
        }
    }

    @Override
    public void onAttachFragment() {
        onClickSignup = (OnClickSignup) getActivity();
    }

    @Override
    public void onResumeFragment() {
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_LOGIN_FAILED));
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_LOGIN_SUCCESSFUL));
    }

    @Override
    public void performPartialSync() {
        if (getUtils().getUserAccountsManager().isExistsUserAccount()) {
            if (getUtils().getPrefsManager().isLoggedIn()) {
                //FORCE A SYNC
                SyncExecutorThread syncExecutor = new SyncExecutorThread(getActivity(), getUtils());
                syncExecutor.start();
            }
        }
    }

    @Override
    public void onPerformPartialSync() {

    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        if (v == mBtLogin) {
            loginUser();
        } if (v == mBtSignup) {
            signupUser();
        }
    }

    private void initChildViews(@NonNull View loginView) {
        mIvAvatar = (CircleImageView) loginView.findViewById(R.id.fragment_login_imageView_user_avatar);
        setUserAvatar();

        mEtUsername = (EditText) loginView.findViewById(R.id.fragment_login_editText_username);
        mEtPassword = (EditText) loginView.findViewById(R.id.fragment_login_editText_password);

        if (false) {
            mEtUsername.setText("marviktintor");
            mEtPassword.setText("marviktintor");
        }

        mBtLogin = (Button) loginView.findViewById(R.id.fragment_login_button_login);
        mBtSignup = (Button) loginView.findViewById(R.id.fragment_login_button_signup);

        mBtSignup.setOnClickListener(this);
        mBtLogin.setOnClickListener(this);
    }

    private void signupUser() {
        String username = getUtils().getString(mEtUsername);
        String password = getUtils().getString(mEtPassword);
        onClickSignup.onClickSignupButton(null, username, password);
    }

    private void loginUser() {
        if (!getUtils().isEmpty(new EditText[]{mEtUsername, mEtPassword})) {

            String username = getUtils().getString(mEtUsername);
            String password = getUtils().getString(mEtPassword);

            getSyncExecutorThread().loginUser(username, password);

            mProgressDialog = getUtils().showCustomProgressDialog("Logging in", "Please wait. We are connecting to your account", true);

        }
    }

    private void setUserAvatar() {
        try {
            mIvAvatar.setImageDrawable(getUtils().getUserAvatar());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnClickSignup {
        void onClickSignupButton(String userAvatar, String username, String password);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intents.ACTION_LOGIN_SUCCESSFUL)) {
                if (mProgressDialog != null) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.cancel();
                    }
                }
            }
            if (intent.getAction().equals(Intents.ACTION_LOGIN_FAILED)) {
                String error = intent.getExtras().getString(Intents.EXTRA_SERVER_ERROR, Intents.EXTRA_UNKNOWN_SERVER_ERROR);
                getUtils().showInfoDialog("Login failed!", "Could not login.\n Error : " + error, Color.RED, "Close", new Intent(Intents.ACTION_AFRICABABA_EMPTY_INTENT));
            }
        }
    }
}
