package com.wan.commonsdk.rx;


import androidx.annotation.IntRange;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 倒计时，默认单位秒
 */
public class RxCountDown {

    public static Observable<Integer> countdown(@IntRange(from = 1) int time) {
        return countdown(time, 1, TimeUnit.SECONDS);
    }

    public static Observable<Integer> countdown(@IntRange(from = 1) int time, int period, TimeUnit timeUnit) {
        final int countTime = time;
        return Observable.interval(0, period, timeUnit)
                .map(increaseTime -> countTime - increaseTime.intValue())
                .take(countTime + 1);
    }
}