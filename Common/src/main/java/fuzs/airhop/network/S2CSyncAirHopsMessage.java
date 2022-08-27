package fuzs.airhop.network;

import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.network.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;

public class S2CSyncAirHopsMessage implements Message<S2CSyncAirHopsMessage> {
    private CompoundTag tag;

    public S2CSyncAirHopsMessage() {

    }

    public S2CSyncAirHopsMessage(CompoundTag tag) {
        this.tag = tag;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeNbt(this.tag);
    }

    @Override
    public void read(FriendlyByteBuf buf) {
        this.tag = buf.readNbt();
    }

    @Override
    public MessageHandler<S2CSyncAirHopsMessage> makeHandler() {
        return new MessageHandler<>() {

            @Override
            public void handle(S2CSyncAirHopsMessage packet, Player player, Object gameInstance) {
                LocalPlayer player1 = ((Minecraft) gameInstance).player;
                ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player1).ifPresent(capability -> capability.read(packet.tag));
            }
        };
    }
}