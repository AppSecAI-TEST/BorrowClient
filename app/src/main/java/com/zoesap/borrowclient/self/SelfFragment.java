package com.zoesap.borrowclient.self;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.login.LoginActivity;
import com.zoesap.borrowclient.myloan.MyLoanActivity;
import com.zoesap.borrowclient.myrecommendation.MyRecommendationActivity;
import com.zoesap.borrowclient.setting.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pers.maoqi.core.CoreBaseFragment;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class SelfFragment extends CoreBaseFragment implements SelfContract.View {
    SelfContract.Presenter mPresenter;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.rl_my_borrow)
    RelativeLayout rlMyBorrow;
    @BindView(R.id.rl_my_recommend)
    RelativeLayout rlMyRecommend;
    @BindView(R.id.rl_my_income)
    RelativeLayout rlMyIncome;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_self, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresent(@NonNull SelfContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
        NullUtils.checkNotNull(mPresenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_my_borrow, R.id.rl_my_recommend, R.id.rl_my_income, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_my_borrow:
                if (BorrowApplication.getInstance().ismSignIn()) {
                    startActivity(MyLoanActivity.getStartIntent(getActivity()));
                } else {
                    startActivity(LoginActivity.getStartIntent(getActivity()));
                }
                break;
            case R.id.rl_my_recommend:
                if (BorrowApplication.getInstance().ismSignIn()) {
                    startActivity(MyRecommendationActivity.getStartIntent(getActivity()));
                } else {
                    startActivity(LoginActivity.getStartIntent(getActivity()));
                }
                break;
            case R.id.rl_my_income:
                break;
            case R.id.rl_setting:
                startActivity(SettingActivity.getStartIntent(getActivity()));
                break;
        }
    }

    @Override
    public void setAccount(String account) {
        tvNickname.setText(account);
        tvNickname.setOnClickListener(null);
    }

    @Override
    public void setLoginInfo() {
        tvNickname.setText(R.string.login_now);
        tvNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.getStartIntent(getActivity()));
            }
        });
    }
}
