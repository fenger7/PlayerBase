/*
 * Copyright 2017 jiajunhui<junhui_jia@163.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.kk.taurus.playerbase.player;

import android.content.Context;
import android.os.Bundle;

import com.kk.taurus.playerbase.event.OnErrorEventListener;
import com.kk.taurus.playerbase.event.OnPlayerEventListener;

/**
 * Created by Taurus on 2018/3/17.
 *
 * The base class of the decoding scheme,
 * the decoder you access needs to inherit from this class.
 *
 */

public abstract class BaseInternalPlayer implements IPlayer {

    private int mCurrentState = STATE_IDLE;

    private OnPlayerEventListener mOnPlayerEventListener;
    private OnErrorEventListener mOnErrorEventListener;

    public BaseInternalPlayer(Context context){

    }

    @Override
    public void setOnPlayerEventListener(OnPlayerEventListener onPlayerEventListener) {
        this.mOnPlayerEventListener = onPlayerEventListener;
    }

    @Override
    public void setOnErrorEventListener(OnErrorEventListener onErrorEventListener) {
        this.mOnErrorEventListener = onErrorEventListener;
    }

    protected final void submitPlayerEvent(int eventCode, Bundle bundle){
        if(mOnPlayerEventListener!=null)
            mOnPlayerEventListener.onPlayerEvent(eventCode, bundle);
    }

    protected final void submitErrorEvent(int eventCode, Bundle bundle){
        if(mOnErrorEventListener!=null)
            mOnErrorEventListener.onErrorEvent(eventCode, bundle);
    }

    protected final void updateStatus(int status){
        this.mCurrentState = status;
    }

    @Override
    public int getState() {
        return mCurrentState;
    }
}
