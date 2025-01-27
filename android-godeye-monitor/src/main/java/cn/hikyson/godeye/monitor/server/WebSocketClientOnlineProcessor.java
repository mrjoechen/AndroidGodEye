package cn.hikyson.godeye.monitor.server;

import com.koushikdutta.async.http.WebSocket;

import org.json.JSONObject;

import cn.hikyson.godeye.core.GodEye;
import cn.hikyson.godeye.core.GodEyeConfig;
import cn.hikyson.godeye.core.exceptions.UninstallException;
import cn.hikyson.godeye.core.internal.modules.sm.Sm;
import cn.hikyson.godeye.core.utils.L;

public class WebSocketClientOnlineProcessor implements WebSocketProcessor {

    @Override
    public void process(WebSocket webSocket, JSONObject msgJSONObject) throws Exception {
        try {
            Sm sm = GodEye.instance().getModule(GodEye.ModuleName.SM);
            webSocket.send(new ServerMessage("reinstallBlock", GodEyeConfig.SmConfig.Factory.convert(sm.getSmContext())).toString());
        } catch (UninstallException e) {
            L.e(String.valueOf(e));
        }
    }
}
