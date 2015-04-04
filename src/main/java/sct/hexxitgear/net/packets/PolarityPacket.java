package sct.hexxitgear.net.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.mixinsupport.climbing.IClimbingShoesWearer;
import sct.hexxitgear.net.HexxitGearNetwork;

public class PolarityPacket extends HexxitGearPacketBase {

    private ForgeDirection direction;

    public PolarityPacket(ForgeDirection direction) {
        this.direction = direction;
    }

    public PolarityPacket() {}

    @Override
    public void write(ByteArrayDataOutput out) {
        out.writeInt(direction.ordinal());
    }

    @Override
    public void read(ByteArrayDataInput in) {
        direction = ForgeDirection.VALID_DIRECTIONS[in.readInt()];
    }

    @Override
    public void handleClient(World world, EntityPlayer player) {
        if (Minecraft.getMinecraft().thePlayer != player)
            ((IClimbingShoesWearer)player).setFloor(direction);
    }

    @Override
    public void handleServer(World world, EntityPlayerMP player) {
        ((IClimbingShoesWearer)player).setFloor(direction);
    }
}