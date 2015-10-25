package com.origicheck.africababa.fragments.authentication.login;

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

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

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

    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {

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

        mEtUsername.setText("marviktintor");
        mEtPassword.setText("marviktintor");

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

        }
    }

    private void setUserAvatar() {
        try {
            mIvAvatar.setImageDrawable(getUtils().getUserAvatar());
        } catch (Exception e) {
            e.printStackTrace();
            getUtils().toast(e.toString());
        }
    }
    public interface OnClickSignup {
        void onClickSignupButton(String userAvatar, String username, String password);
    }

}
