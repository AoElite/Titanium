package me.jaden.titanium.check.impl.spam;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import java.util.Map;
import me.jaden.titanium.check.PacketCheck;
import me.jaden.titanium.data.PlayerData;
import me.jaden.titanium.settings.TitaniumConfig;

public class SpamC implements PacketCheck {
    private final Map<PacketTypeCommon, Double> multiplierMap = TitaniumConfig.getInstance().getMultipliedPackets();

    @Override
    public void handle(PacketReceiveEvent event, PlayerData data) {
        double multiplier = multiplierMap.getOrDefault(event.getPacketType(), 1.0D);
        if (data.incrementPacketCount(multiplier) > data.getPacketAllowance()) {
            flag(event);
        } else {
            data.decrementPacketAllowance();
        }
    }
}
