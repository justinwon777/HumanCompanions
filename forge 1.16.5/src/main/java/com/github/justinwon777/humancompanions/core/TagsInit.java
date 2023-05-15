package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TagsInit {
	public static final class Items {
		public static final Tags.IOptionalNamedTag<Item> AXES = ItemTags.createOptional(new ResourceLocation(HumanCompanions.MOD_ID,"axes"));
		public static final Tags.IOptionalNamedTag<Item> SWORDS = ItemTags.createOptional(new ResourceLocation(HumanCompanions.MOD_ID, "swords"));
	}
}
