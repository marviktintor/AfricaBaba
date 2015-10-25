package com.origicheck.africababa.fragments.authentication.signup;

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

    private OnSignupCallbacks onSignupCallbacks;

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
        onSignupCallbacks = (OnSignupCallbacks) getActivity();
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
        if (v == mBtSignup) {signupUser();}
    }

    private void initChildViews(@NonNull View signupView) {
        mIvAvatar = (CircleImageView) signupView.findViewById(R.id.fragment_signup_imageView_user_avatar);
        setUserAvatar();

        mEtFullname = (EditText) signupView.findViewById(R.id.fragment_signup_editText_fullname);
        mEtEmail = (EditText) signupView.findViewById(R.id.fragment_signup_editText_email);
        mEtPhone = (EditText) signupView.findViewById(R.id.fragment_signup_editText_phone);

        mEtUsername = (EditText) signupView.findViewById(R.id.fragment_signup_editText_username);
        mEtPassword = (EditText) signupView.findViewById(R.id.fragment_signup_editText_password);

        mEtFullname.setText("Marvik Tintor");
        mEtEmail.setText("marviktintor@gmail.com");
        mEtPhone.setText("0718034449");
        mEtUsername.setText("marviktintor");
        mEtPassword.setText("marviktintor");


        mBtSignup = (Button) signupView.findViewById(R.id.fragment_signup_button_signup);
        mBtLogin = (Button) signupView.findViewById(R.id.fragment_signup_button_login);

        mBtSignup.setOnClickListener(this);
        mBtLogin.setOnClickListener(this);
    }

    private void setUserAvatar() {
        try {
            mIvAvatar.setImageDrawable(getUtils().getUserAvatar());
        } catch (Exception e) {
            e.printStackTrace();
            getUtils().toast(e.toString());
        }
    }
    private void loginUser() {
        String username = getUtils().getString(mEtUsername);
        String password = getUtils().getString(mEtPassword);
        onSignupCallbacks.onClickLoginButton(null, username, password);
    }

    private void signupUser() {

        getUtils().getPrefsManager().clearPreferences();

        if (!getUtils().isEmpty(new EditText[]{mEtFullname, mEtEmail, mEtPhone, mEtUsername, mEtPassword})) {

            String fullname = getUtils().getString(mEtFullname);
            String email = getUtils().getString(mEtEmail);
            String phonenumber = getUtils().getString(mEtPhone);
            String username = getUtils().getString(mEtUsername);
            String password = getUtils().getString(mEtPassword);

            getSyncExecutorThread().signupUsers(fullname, email, phonenumber, username, password);

        }
    }

    public interface OnSignupCallbacks {
        void onClickLoginButton(String userAvatar, String username, String password);

        void onSignup();
    }


}
