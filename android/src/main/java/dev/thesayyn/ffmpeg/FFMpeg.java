package dev.thesayyn.ffmpeg;

import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.FFmpeg;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

@NativePlugin
public class FFMpeg extends Plugin {

    public FFMpeg() {
        Config.enableStatisticsCallback(statistics -> {
            JSObject stats = new JSObject();
            stats.put("execution_id", statistics.getExecutionId());
            stats.put("bitrate", statistics.getBitrate());
            stats.put("size", statistics.getSize());
            stats.put("speed", statistics.getSpeed());
            stats.put("time", statistics.getTime());
            stats.put("video_fps", statistics.getVideoFps());
            stats.put("video_frame_number", statistics.getVideoFrameNumber());
            stats.put("video_quality", statistics.getVideoQuality());
            notifyListeners("statistic", stats);
        });
        Config.enableLogCallback(message -> {
            JSObject entry = new JSObject();
            entry.put("execution_id", message.getExecutionId());
            entry.put("level", message.getLevel());
            entry.put("text", message.getText());
            notifyListeners("message", entry);
        });
    }

    @PluginMethod
    public void run(PluginCall call) {
        if (! call.hasOption("args") ) {
            call.error("args property is missing.");
            return;
        }

        String args = call.getString("args");

        FFmpeg.executeAsync(args, (executionId, returnCode) -> {
            if (returnCode == Config.RETURN_CODE_SUCCESS) {
                JSObject result = new JSObject();
                result.put("execution_id", executionId);
                call.success(result);
            } else {
                call.error("process has failed.", String.valueOf(returnCode), new Exception("process has failed."));
            }
        });

    }
}
