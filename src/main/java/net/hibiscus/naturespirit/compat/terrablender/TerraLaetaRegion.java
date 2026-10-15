package net.hibiscus.naturespirit.compat.terrablender;

import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.world.paramaters.TerraLaetaParameters;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class TerraLaetaRegion extends Region {

  public TerraLaetaRegion(Identifier name, int weight) {
    super(name, RegionType.OVERWORLD, weight);
  }

  @Override
  public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {

    (new TerraLaetaParameters()).writeOverworldBiomeParameters(mapper);
  }

}
