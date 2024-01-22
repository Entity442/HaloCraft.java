package com.harby.halocraft.HaloItems;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.harby.halocraft.HaloItems.interfaces.IHaloArmorItem;
import com.harby.halocraft.HaloItems.interfaces.IHaloMaterials;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.ForgeMod;

import java.util.EnumMap;
import java.util.UUID;

public abstract class HaloBaseArmor extends ItemBase implements Equipable, IHaloArmorItem {
    private static final EnumMap<HaloBaseArmor.Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(HaloBaseArmor.Type.class), (value) -> {
        value.put(HaloBaseArmor.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        value.put(HaloBaseArmor.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        value.put(HaloBaseArmor.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        value.put(HaloBaseArmor.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    private final EquipmentSlot slot;
    private final HaloBaseArmor.Type type;
    private final boolean isShielded;
    private int energyTicks;
    private final int defense;
    private final double health;
    private final double speed;
    private final double swim;
    private final double damage;
    private final double knockRes;
    private final double toughness;

    public HaloBaseArmor(IHaloMaterials materials, HaloBaseArmor.Type type,boolean shield) {
        super(new Item.Properties().stacksTo(1).durability(materials.getDurability(type)));
        this.slot = type.slot;
        this.type = type;
        this.isShielded = shield;
        this.defense = materials.getDefense(type);
        this.health = materials.getHealthModifier(type);
        this.speed = materials.getSpeedModifier(type);
        this.swim = materials.getSpeedModifier(type);
        this.damage = materials.getDamageModifier(type);
        this.knockRes = materials.getKnockResistance(type);
        this.toughness = materials.getToughnessModifier(type);
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.defense, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.toughness, AttributeModifier.Operation.ADDITION));
        if (materials.getHealthModifier(type) != 0){
            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Health modifier", this.health, AttributeModifier.Operation.ADDITION));
        }if (materials.getSpeedModifier(type) != 0){
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Movement modifier", this.speed, AttributeModifier.Operation.ADDITION));
        }if (materials.getSwimModifier(type) != 0){
            builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "Swim modifier", this.swim, AttributeModifier.Operation.ADDITION));
        }if (materials.getDamageModifier(type) != 0){
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Damage modifier", this.damage, AttributeModifier.Operation.ADDITION));
        }if (materials.getKnockResistance(type) > 0){
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "KnockBack modifier", this.knockRes/10, AttributeModifier.Operation.ADDITION));
        }
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
        this.defaultModifiers = builder.build();
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return this.swapWithEquipmentSlot(this, pLevel, pPlayer, pHand);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return this.slot;
    }

    public enum Type {
        HELMET(EquipmentSlot.HEAD, "helmet"),
        CHESTPLATE(EquipmentSlot.CHEST, "chestplate"),
        LEGGINGS(EquipmentSlot.LEGS, "leggings"),
        BOOTS(EquipmentSlot.FEET, "boots");

        private final EquipmentSlot slot;
        private final String name;

        Type(EquipmentSlot pSlot, String pName) {
            this.slot = pSlot;
            this.name = pName;
        }

        public EquipmentSlot getSlot() {
            return this.slot;
        }

        public String getName() {
            return this.name;
        }
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public double getSpeed() {
        return speed;
    }

    public double getKnockRes() {
        return knockRes;
    }

    public double getSwim() {
        return swim;
    }

    public double getToughness() {
        return toughness;
    }

    public int getDefense() {
        return defense;
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_CHAIN;
    }

    public boolean isShielded(){
       return this.isShielded;
    }

    public int getShieldTicks(){
        return this.energyTicks;
    }

    public void setShieldTicks(int i){
        this.energyTicks = i;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (player.isShiftKeyDown()){
            this.setShieldTicks(40);
        }
        if (this.getShieldTicks() > 0){
            this.energyTicks--;
        }
    }

}
