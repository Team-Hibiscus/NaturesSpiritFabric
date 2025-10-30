package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class NSConfiguredFeatures {

  public static final RegistryKey<ConfiguredFeature<?, ?>> RED_MOSS_PATCH_BONEMEAL = registerKey("red_moss_patch_bonemeal");
  public static final RegistryKey<ConfiguredFeature<?, ?>> WISTERIA_DELTA = registerKey("water_delta");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_DELTA = registerKey("swamp_delta");
  public static final RegistryKey<ConfiguredFeature<?, ?>> MARSH_DELTA = registerKey("marsh_delta");
  public static final RegistryKey<ConfiguredFeature<?, ?>> RIVER_DELTA = registerKey("river_delta");

  public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_REDWOOD_TREE_SPAWN = registerKey("large_redwood_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> REDWOOD_TREE_SPAWN = registerKey("redwood_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_FROSTY_REDWOOD_TREE = registerKey("large_frosty_redwood_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FROSTY_REDWOOD_TREE = registerKey("frosty_redwood_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_FROSTY_REDWOOD_TREE_SPAWN = registerKey("large_frosty_redwood_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FROSTY_REDWOOD_TREE_SPAWN = registerKey("frosty_redwood_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> WILLOW_TREE = registerKey("willow_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> WILLOW_TREE_SPAWN = registerKey("willow_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ASPEN_TREE = registerKey("aspen_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ASPEN_TREE_BEES = registerKey("aspen_tree_bees");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ASPEN_TREE_SPAWN = registerKey("aspen_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN_TREE = registerKey("yellow_aspen_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN_TREE_BEES = registerKey("yellow_aspen_tree_bees");
  public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_ASPEN_TREE_SPAWN = registerKey("yellow_aspen_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> RED_MAPLE_TREE = registerKey("red_maple_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ORANGE_MAPLE_TREE = registerKey("orange_maple_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_MAPLE_TREE = registerKey("yellow_maple_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> MAPLE_SPAWN = registerKey("maple_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_TREE = registerKey("fir_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_TREE_SPAWN = registerKey("fir_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARCH_TREE = registerKey("larch_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARCH_TREE_SPAWN = registerKey("larch_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> WISTERIA_SPAWN = registerKey("wisteria_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SUGI_TREE = registerKey("sugi_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SUGI_TREE = registerKey("large_sugi_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SUGI_SPAWN = registerKey("sugi_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SUGI_SPAWN = registerKey("large_sugi_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> CYPRESS_TREE = registerKey("cypress_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> CYPRESS_TREE_SPAWN = registerKey("cypress_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> OLIVE_TREE = registerKey("olive_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> OLIVE_TREE_SPAWN = registerKey("olive_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> GHAF_TREE = registerKey("ghaf_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> GHAF_TREE_SPAWN = registerKey("ghaf_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PALO_VERDE_TREE = registerKey("palo_verde_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PALO_VERDE_TREE_SPAWN = registerKey("palo_verde_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE = registerKey("mahogany_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE_SPAWN = registerKey("mahogany_tree_spawn");
  //   public static final RegistryKey <ConfiguredFeature <?, ?>> BANYAN_TREE = registerKey("banyan_tree");
//   public static final RegistryKey <ConfiguredFeature <?, ?>> BANYAN_TREE_SPAWN = registerKey("banyan_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SAXAUL_TREE = registerKey("saxaul_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SAXAUL_TREE_SPAWN = registerKey("saxaul_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> JOSHUA_TREE = registerKey("joshua_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> JOSHUA_TREE_SPAWN = registerKey("joshua_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ALLUAUDIA = registerKey("alluaudia");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ALLUAUDIA_SPAWN = registerKey("alluaudia_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> COCONUT_TREE = registerKey("coconut_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> COCONUT_TREE_SPAWN = registerKey("coconut_tree_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> CEDAR_TREE = registerKey("cedar_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> CEDAR_TREE_SPAWN = registerKey("cedar_tree_spawn");

  public static final RegistryKey<ConfiguredFeature<?, ?>> OAK_BUSH = registerKey("oak_bush");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_BUSH = registerKey("spruce_bush");
  public static final RegistryKey<ConfiguredFeature<?, ?>> OAK_BUSH_SPAWN = registerKey("oak_bush_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_BUSH_SPAWN = registerKey("spruce_bush_spawn");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_OAK_TREE_SPAWN = registerKey("custom_fancy_oak_tree_spawn");


  public static final RegistryKey<ConfiguredFeature<?, ?>> PUMPKIN_PATCH_FEATURE = registerKey("pumpkin_patch_feature");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PUMPKIN_FEATURE = registerKey("pumpkin_feature");

  public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_WISTERIA_FOREST = registerKey("flower_wisteria_forest");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_SUGI_FOREST = registerKey("flower_sugi_forest");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_ERODED_RIVER = registerKey("flower_eroded_river");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_GOLDEN_WILDS = registerKey("flower_golden_wilds");
  public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_SHIITAKE_MUSHROOM = registerKey("huge_shiitake_mushroom");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_CYPRESS_FIELDS = registerKey("flower_cypress_fields");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SCORCHED_GRASS = registerKey("patch_scorched_grass");
  public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_TALL_SCORCHED_GRASS = registerKey("patch_tall_scorched_grass");
  public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_STRATIFIED_DESERT = registerKey("flower_stratified_desert");
  public static final RegistryKey<ConfiguredFeature<?, ?>> CATTAILS = registerKey("cattails");
  public static final RegistryKey<ConfiguredFeature<?, ?>> LOTUS_PLANT = registerKey("lotus_plant");
  public static final RegistryKey<ConfiguredFeature<?, ?>> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip");
  public static final RegistryKey<ConfiguredFeature<?, ?>> GRAY_POLYPORE = registerKey("gray_polypore");

  public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
    return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(NatureSpirit.MOD_ID, name));
  }
}


