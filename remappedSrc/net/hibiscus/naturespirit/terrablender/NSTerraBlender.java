package net.hibiscus.naturespirit.compat.terrablender;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.io.IOException;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSTerraBlender implements TerraBlenderApi {

  @Override
  public void onTerraBlenderInitialized() {
    {
      Regions.register(new TerraFeraxRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_ferax"), NatureSpirit.CONFIG.terra_ferax_weight));
      Regions.register(new TerraSolarisRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_solaris"), NatureSpirit.CONFIG.terra_solaris_weight));
      Regions.register(new TerraFlavaRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_flava"), NatureSpirit.CONFIG.terra_flava_weight));
      Regions.register(new TerraMaterRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_mater"), NatureSpirit.CONFIG.terra_mater_weight));
      Regions.register(new TerraLaetaRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "terra_laeta"), NatureSpirit.CONFIG.terra_laeta_weight));
      SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());
    }
  }
}
