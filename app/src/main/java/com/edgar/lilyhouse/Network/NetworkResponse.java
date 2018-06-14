package com.edgar.lilyhouse.Network;

public interface NetworkResponse {

    void onFailed(int resultCode);
    void onSucceed();

}
