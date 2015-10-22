package com.origicheck.africababa.fragments.authentication.signup;

import android.os.Bundle;
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
public class FragmentSignup extends FragmentWrapper implements View.OnClickListener {

    private View mSignupView;

    private CircleImageView mIvAvatar;

    private EditText mEtFullname;
    private EditText mEtEmail;
    private EditText mEtPhone;

    private EditText mEtUsername;
    private EditText mEtPassword;

    private Button mBtSignup;
    private Button mBtLogin;

    private Bundle arguments;

    private OnClickLogin onClickLogin;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public String getActivityTitle() {
        return getString(R.string.title_fragment_signup);
    }

    @Override
    public void receiveBundle() {

        arguments = getArguments();
    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSignupView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mSignupView);
        getContainer().addView(mSignupView);
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
        onClickLogin = (OnClickLogin) getActivity();
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
        return R.layout.fragment_signup;
    }

    @Override
    public void onClick(View v) {
        if (v == mBtLogin) {loginUser();}
    }

    private void loginUser() {
        String username = getUtils().getString(mEtUsername);
        String password = getUtils().getString(mEtPassword);
        onClickLogin.onClickLoginButton(null, username, password);
    }

    private void initChildViews(View signupView) {
        mIvAvatar = (CircleImageView) signupView.findViewById(R.id.fragment_signup_imageView_user_avatar);

        mEtFullname = (EditText) signupView.findViewById(R.id.fragment_signup_editText_fullname);
        mEtEmail = (EditText) signupView.findViewById(R.id.fragment_signup_editText_email);
        mEtPhone = (EditText) signupView.findViewById(R.id.fragment_signup_editText_phone);

        mEtUsername = (EditText) signupView.findViewById(R.id.fragment_signup_editText_username);
        mEtPassword = (EditText) signupView.findViewById(R.id.fragment_signup_editText_password);

        mBtSignup = (Button) signupView.findViewById(R.id.fragment_signup_button_signup);
        mBtLogin = (Button) signupView.findViewById(R.id.fragment_signup_button_login);

        mBtSignup.setOnClickListener(this);
        mBtLogin.setOnClickListener(this);
    }

    public interface OnClickLogin {
        void onClickLoginButton(String userAvatar, String username, String password);
    }

}
