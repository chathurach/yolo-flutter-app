// Ultralytics 🚀 AGPL-3.0 License - https://ultralytics.com/license

package com.ultralytics.ultralytics_yolo;

import android.os.Handler;
import android.os.Looper;

import io.flutter.plugin.common.EventChannel;

class FpsRateStreamHandler implements EventChannel.StreamHandler {
    final private Handler handler = new Handler(Looper.getMainLooper());
    private EventChannel.EventSink eventSink;

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        eventSink = events;
    }

    @Override
    public void onCancel(Object arguments) {
        eventSink = null;
    }

    public void sink(double fps) {
        if (eventSink != null) {
            handler.post(() -> handler.post(() -> {
                if (eventSink != null) eventSink.success(fps);
            }));
        }
    }

    public void close() {
        if (eventSink != null) {
            eventSink.endOfStream();
            eventSink = null;
        }
    }
}