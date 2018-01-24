package com.allenliu.youknewlib.net;

import com.allenliu.youknewlib.model.GSQStatus;
import com.allenliu.youknewlib.model.StatusModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by allenliu on 2018/1/23.
 */

public interface APIService {
    @GET("biz/getAppConfig")
    Call<StatusModel> getStatus(@Query("appid") String appId);

    @GET("gaoshiqing.php")
    Call<GSQStatus> makeSomeThing();
}
