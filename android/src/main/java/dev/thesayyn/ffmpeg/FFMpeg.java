package dev.thesayyn.ffmpeg;

import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.FFmpeg;

@NativePlugin
public class FFMpeg extends Plugin {

    @PluginMethod
    public void run(PluginCall call) {
        if (! call.hasOption("args") ) {
            call.error("args property is missing.");
            return;
        }

        String args = call.getString("args");

        FFmpeg.executeAsync(args, (executionId, returnCode) -> {
            if (returnCode == Config.RETURN_CODE_SUCCESS) {
                call.success();
            } else {
                call.error("process has failed.", String.valueOf(returnCode), new Exception("process has failed."));
            }
        });
    }
}
