package com.zoesap.borrowclient.register;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/26.
 */

public interface RegisterContract {
    interface Presenter extends BasePresenter {
        void getSmsCode(String phoneNum);

        void stopCountDownTimer();

        void applySubmit(String mobileNumber, String password, String verifyPassword, String getCode, String visitCode);
    }

    interface View extends BaseView<Presenter> {
        void updateCountDownButton(String millis);

        void countDownFinish();

        void clearInput();

        void activityFinish();
    }
}
