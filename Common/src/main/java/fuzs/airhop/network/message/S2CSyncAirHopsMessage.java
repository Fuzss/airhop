package fuzs.airhop.network.message;

import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.network.message.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class S2CSyncAirHopsMessage implements Message {
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
    public SyncAirHopsHandler makeHandler() {
        return new SyncAirHopsHandler();
    }

    private static class SyncAirHopsHandler extends PacketHandler<S2CSyncAirHopsMessage> {

        @Override
        public void handle(S2CSyncAirHopsMessage packet, Player player, Object gameInstance) {
            ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player).ifPresent(capability -> capability.setAirHops(packet.airHops));
        }
    }
}