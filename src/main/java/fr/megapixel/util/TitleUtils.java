package fr.megapixel.util;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleUtils {
		   
	    public static void sendTitle(Player player, String title, String subtitle, int ticks){
	        IChatBaseComponent basetitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
	        IChatBaseComponent basesubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
	        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, basetitle);
	        PacketPlayOutTitle subtitlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, basesubtitle);
	        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
	        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlepacket);
	        sendTime(player, ticks);
	    }
	   
	    private static void sendTime(Player player, int ticks){
	        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, 20, ticks, 20);
	        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
	    }
	   
	    public static void sendActionBar(Player player, String message){
	        IChatBaseComponent basetitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
	        PacketPlayOutChat packet = new PacketPlayOutChat(basetitle, (byte)2);
	        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	    }
}
