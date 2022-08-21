package fuzs.airhop.network;

import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.network.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class S2CSyncAirHopsMessage implements Message<S2CSyncAirHopsMessage> {
    private int airHops;

    public S2CSyncAirHopsMessage() {

    }

    public S2CSyncAirHopsMessage(int airHops) {
        this.airHops = airHops;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeByte(this.airHops);
    }

    @Override
    public void read(FriendlyByteBuf buf) {
        this.airHops = buf.readByte();
    }

    @Override
    public MessageHandler<S2CSyncAirHopsMessage> makeHandler() {
        return new MessageHandler<>() {

            @Override
            public void handle(S2CSyncAirHopsMessage packet, Player player, Object gameInstance) {
                ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player).ifPresent(capability -> capability.setAirHops(packet.airHops));
            }
        };
    }
}