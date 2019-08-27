package com.jonas.tinderclone.services;

public interface IResponseListener {
    void onSuccess();
    void onFailure(String errorMessage);
}
