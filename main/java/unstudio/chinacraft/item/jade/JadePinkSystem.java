package unstudio.chinacraft.item.jade;

import unstudio.chinacraft.event.jade.JadePinkCDReduceEvent;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by trych on 2016/1/9.
 */
public class JadePinkSystem {
    public static HashMap<UUID,Integer> time = new HashMap<UUID, Integer>();

    public int getPlayerCD(UUID uuid){
        if (time.containsKey(uuid)) return time.get(uuid);
        time.put(uuid,0);
        return 0;
    }

    public void init(){
        new Timer().schedule(new ReduceTimeCDTask(),100,100);
    }

    public boolean hasCD(UUID uuid){
        if (time.containsKey(uuid)) return true;
        return false;
    }

    class ReduceTimeCDTask extends TimerTask{
        @Override
        public void run() {
            for (UUID uuid: JadePinkSystem.time.keySet()){
                if (JadePinkSystem.time.get(uuid) > 0){
                    JadePinkCDReduceEvent e = new JadePinkCDReduceEvent(uuid, JadePinkSystem.time.get(uuid));
                    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(e)) continue;
                    JadePinkSystem.time.put(uuid, JadePinkSystem.time.get(uuid)-1);
                } else {
                    JadePinkCDReduceEvent e = new JadePinkCDReduceEvent(uuid,0);
                    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(e)) continue;
                    JadePinkSystem.time.remove(uuid);
                }
            }
        }
    }
}
