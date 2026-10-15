package net.hibiscus.naturespirit.compat.terrablender;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSTerraBlender implements TerraBlenderApi {

  @Override
  public void onTerraBlenderInitialized() {
    {
      Regions.register(new TerraFeraxRegion(Identifier.of(MOD_ID, "terra_ferax"), NatureSpirit.CONFIG.terra_ferax_weight));
      Regions.register(new TerraSolarisRegion(Identifier.of(MOD_ID, "terra_solaris"), NatureSpirit.CONFIG.terra_solaris_weight));
      Regions.register(new TerraFlavaRegion(Identifier.of(MOD_ID, "terra_flava"), NatureSpirit.CONFIG.terra_flava_weight));
      Regions.register(new TerraMaterRegion(Identifier.of(MOD_ID, "terra_mater"), NatureSpirit.CONFIG.terra_mater_weight));
      Regions.register(new TerraLaetaRegion(Identifier.of(MOD_ID, "terra_laeta"), NatureSpirit.CONFIG.terra_laeta_weight));
      SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());
    }
  }
}
