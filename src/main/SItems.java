package main;

import com.google.common.collect.Multimap;
import net.minecraft.server.v1_16_R2.Slot;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SItems {

    public ItemStack 푸른_빛의_검()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.NETHERITE_SWORD);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "푸른 빛의 검 " + ChatColor.GRAY + "[" + ChatColor.DARK_BLUE + "B" + ChatColor.GRAY + "]");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "푸른 빛이 감도는 검이다.");
        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setCustomModelData(10000);
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack 붉은_빛의_검()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.NETHERITE_SWORD);
        lore = new ArrayList<>();
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "붉은 빛의 검 " + ChatColor.GRAY + "[" + ChatColor.DARK_BLUE + "B" + ChatColor.GRAY + "]");
        lore.add(ChatColor.GRAY + "붉은 빛이 감도는 검이다.");
        itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setCustomModelData(10001);
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack 황금_빛의_검()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.NETHERITE_SWORD);
        lore = new ArrayList<>();
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "황금 빛의 검 " + ChatColor.GRAY + "[" + ChatColor.DARK_BLUE + "B" + ChatColor.GRAY + "]");
        lore.add(ChatColor.GRAY + "황금 빛이 감도는 검이다.");
        itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_MOVEMENT_SPEED", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setCustomModelData(10002);
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack 검은_오러의_검()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.NETHERITE_SWORD);
        lore = new ArrayList<>();
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "검은 오러의 검 " + ChatColor.GRAY + "[" + ChatColor.DARK_BLUE + "B" + ChatColor.GRAY + "]");
        lore.add(ChatColor.GRAY + "검은 오러가 감도는 검이다.");
        itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setCustomModelData(10003);
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack 신성한_왕의_검()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.GOLDEN_SWORD);
        lore = new ArrayList<>();
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "신성한 왕의 검 " + ChatColor.GRAY + "[" + ChatColor.WHITE + "S+" + ChatColor.GRAY + "]");
        lore.add(ChatColor.DARK_GRAY + "[조화로운 빛이 감도는 검이다.]");
        lore.add(ChatColor.GRAY + "오른쪽 클릭: " + ChatColor.GOLD + "신성한 빛");
        lore.add(ChatColor.GRAY + "주변 5블럭 이내의 모든 아군들의 체력을");
        lore.add(ChatColor.GRAY + "5초동안 5칸 회복시킨 후 모든 적에게");
        lore.add(ChatColor.GRAY + "5초동안 20의 데미지를 입힙니다.");
        itemMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "GENERIC_KNOCKBACK_RESISTANCE", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "GENERIC_MOVEMENT_SPEED", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "GENERIC_MAX_HEALTH", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setCustomModelData(10004);
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack 피해망상증()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.BLACK_DYE);
        lore = new ArrayList<>();
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "피해망상증 " + ChatColor.GRAY + "[" + ChatColor.WHITE + "S" + ChatColor.GRAY + "]");
        lore.add(ChatColor.DARK_GRAY + "[어디선가 시선이 느껴진다.]");
        lore.add(ChatColor.GRAY + "오른쪽 클릭: " + ChatColor.BLUE + "피해망상증");
        lore.add(ChatColor.GRAY + "10 블럭 이내의 자신을 제외한 모든 플래이어에게");
        lore.add(ChatColor.GRAY + "5초동안 실명 효과를 부여하고");
        lore.add(ChatColor.GRAY + "자신의 이동 속도가 증가합니다.");
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.setCustomModelData(10005);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack 소드_마스터의_검()
    {
        ItemStack itemStack;
        ItemMeta itemMeta;
        List<String> lore;

        itemStack = new ItemStack(Material.IRON_SWORD);
        lore = new ArrayList<>();
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "소드 마스터의 검 " + ChatColor.GRAY + "[" + ChatColor.WHITE + "S+" + ChatColor.GRAY + "]");
        lore.add(ChatColor.DARK_GRAY + "[엄청난 힘이 느껴진다.]");
        lore.add(ChatColor.GRAY + "오른쪽 클릭: " + ChatColor.WHITE + "일섬");
        lore.add(ChatColor.GRAY + "10 블럭 이내의 자신의 팀을 제외한 모든 엔티티를");
        lore.add(ChatColor.GRAY + "1초동안 멈추고 즉사시킵니다.");
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "GENERIC_ATTACK_DAMAGE", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.setCustomModelData(10006);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
