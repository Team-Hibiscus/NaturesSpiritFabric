package net.hibiscus.naturespirit.compat.terrablender;

import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import java.util.function.Consumer;

import static net.hibiscus.naturespirit.NatureSpirit.CONFIG;


public class TerraMaterParameters extends OverworldBiomeBuilder {

  private final Climate.Parameter defaultParameter = Climate.Parameter.span(-1.0F, 1.0F);
  private final Climate.Parameter[] temperatureParameters = new Climate.Parameter[]{
      Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F),
      Climate.Parameter.span(0.2F,
          0.55F
      ), Climate.Parameter.span(0.55F, 1.0F)
  };
  private final Climate.Parameter[] humidityParameters = new Climate.Parameter[]{
      Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F),
      Climate.Parameter.span(0.1F,
          0.3F
      ), Climate.Parameter.span(0.3F, 1.0F)
  };
  private final Climate.Parameter[] erosionParameters = new Climate.Parameter[]{
      Climate.Parameter.span(-1.0F, -0.78F),
      Climate.Parameter.span(-0.78F, -0.375F),
      Climate.Parameter.span(-0.375F, -0.2225F),
      Climate.Parameter.span(-0.2225F, 0.05F),
      Climate.Parameter.span(0.05F, 0.45F),
      Climate.Parameter.span(0.45F, 0.55F),
      Climate.Parameter.span(0.55F, 1.0F)
  };
  private final Climate.Parameter frozenTemperature;
  private final Climate.Parameter nonFrozenTemperatureParameters;
  private final Climate.Parameter mushroomFieldsContinentalness;
  private final Climate.Parameter deepOceanContinentalness;
  private final Climate.Parameter oceanContinentalness;
  private final Climate.Parameter coastContinentalness;
  private final Climate.Parameter riverContinentalness;
  private final Climate.Parameter nearInlandContinentalness;
  private final Climate.Parameter midInlandContinentalness;
  private final Climate.Parameter farInlandContinentalness;
  private final ResourceKey<Biome>[][] oceanBiomes;
  private final ResourceKey<Biome>[][] commonBiomes;
  private final ResourceKey<Biome>[][] uncommonBiomes;
  private final ResourceKey<Biome>[][] nearMountainBiomes;
  private final ResourceKey<Biome>[][] specialNearMountainBiomes;
  private final ResourceKey<Biome>[][] windsweptBiomes;
  ResourceKey<Biome> commonBiomeJungleWarm = CONFIG.has_tropical_woods ? NSBiomes.TROPICAL_WOODS : Biomes.JUNGLE;
  ResourceKey<Biome> nearBiomeJungleWarm = CONFIG.has_sparse_tropical_woods ? NSBiomes.SPARSE_TROPICAL_WOODS : Biomes.JUNGLE;
  ResourceKey<Biome> commonBiomeForestWarm = CONFIG.has_sparse_tropical_woods ? NSBiomes.SPARSE_TROPICAL_WOODS : Biomes.FOREST;
  ResourceKey<Biome> uncommonBiomeBambooJungleWarm = CONFIG.has_tropical_woods ? null : Biomes.BAMBOO_JUNGLE;
  ResourceKey<Biome> uncommonBiomeSparseJungleWarm = CONFIG.has_tropical_woods ? null : Biomes.SPARSE_JUNGLE;
  ResourceKey<Biome> uncommonBiomePlainsWarm = CONFIG.has_sparse_tropical_woods ? null : Biomes.PLAINS;
  ResourceKey<Biome> commonBiomeDesertHot = CONFIG.has_arid_savanna ? NSBiomes.ARID_SAVANNA : Biomes.DESERT;
  ResourceKey<Biome> nearBiomeWoodedBadlandsHot = CONFIG.has_arid_savanna ? NSBiomes.ARID_SAVANNA : Biomes.WOODED_BADLANDS;
  ResourceKey<Biome> nearBiomeBadlandsHot = CONFIG.has_arid_savanna ? NSBiomes.ARID_SAVANNA : Biomes.BADLANDS;
  ResourceKey<Biome> commonBiomeDesertHot2 = CONFIG.has_scorched_dunes ? NSBiomes.SCORCHED_DUNES : Biomes.DESERT;
  ResourceKey<Biome> commonBiomeNullHot = CONFIG.has_scorched_dunes ? NSBiomes.SCORCHED_DUNES : null;
  ResourceKey<Biome> commonBiomeFlowerForestTemperate = CONFIG.has_flowering_shrubland ? NSBiomes.FLOWERING_SHRUBLAND : Biomes.FLOWER_FOREST;
  ResourceKey<Biome> uncommonBiomeSunflowerFieldTemperate = CONFIG.has_flowering_shrubland ? null : Biomes.SUNFLOWER_PLAINS;
  ResourceKey<Biome> commonBiomePlainsTemperate = CONFIG.has_shrubland ? NSBiomes.SHRUBLAND : Biomes.PLAINS;
  ResourceKey<Biome> nearBiomeMeadowTemperate = CONFIG.has_flowering_shrubland ? NSBiomes.FLOWERING_SHRUBLAND : Biomes.MEADOW;
  ResourceKey<Biome> nearBiomeMeadowTemperate2 = CONFIG.has_shrubland ? NSBiomes.SHRUBLAND : Biomes.MEADOW;
  ResourceKey<Biome> specialBiomeCherryTemperate = CONFIG.has_flowering_shrubland ? null : Biomes.CHERRY_GROVE;

  public TerraMaterParameters() {
    this.frozenTemperature = this.temperatureParameters[0];
    this.nonFrozenTemperatureParameters = Climate.Parameter.span(this.temperatureParameters[1], this.temperatureParameters[4]);
    this.mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
    this.deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    this.oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    this.coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
    this.riverContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    this.nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    this.midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    this.farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);
    this.oceanBiomes = new ResourceKey[][]{
        {
            Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN
        }, {
        Biomes.FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN
    }
    };
    this.commonBiomes = new ResourceKey[][]{
        {
            Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA
        }, {
        Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA
    }, {
        commonBiomeFlowerForestTemperate, commonBiomePlainsTemperate, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST
    }, {
        Biomes.SAVANNA, Biomes.SAVANNA, commonBiomeForestWarm, commonBiomeJungleWarm, commonBiomeJungleWarm
    }, {
        Biomes.DESERT, Biomes.DESERT, commonBiomeDesertHot2, commonBiomeDesertHot, commonBiomeDesertHot
    }
    };
    this.uncommonBiomes = new ResourceKey[][]{
        {Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null},
        {null, null, null, null, Biomes.OLD_GROWTH_PINE_TAIGA}, {
        uncommonBiomeSunflowerFieldTemperate, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null
    }, {
        null, null, uncommonBiomePlainsWarm, uncommonBiomeSparseJungleWarm, uncommonBiomeBambooJungleWarm
    }, {null, commonBiomeNullHot, null, null, null}
    };
    this.nearMountainBiomes = new ResourceKey[][]{
        {
            Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA
        }, {
        Biomes.MEADOW, Biomes.MEADOW, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA
    }, {
        nearBiomeMeadowTemperate, nearBiomeMeadowTemperate2, nearBiomeMeadowTemperate2, nearBiomeMeadowTemperate2, Biomes.DARK_FOREST
    }, {
        Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, commonBiomeForestWarm, commonBiomeForestWarm, nearBiomeJungleWarm
    }, {
        Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, nearBiomeWoodedBadlandsHot, nearBiomeWoodedBadlandsHot
    }
    };
    this.specialNearMountainBiomes = new ResourceKey[][]{
        {Biomes.ICE_SPIKES, null, null, null, null}, {
        Biomes.CHERRY_GROVE, null, Biomes.MEADOW, Biomes.MEADOW, Biomes.OLD_GROWTH_PINE_TAIGA
    }, {
        specialBiomeCherryTemperate, specialBiomeCherryTemperate, Biomes.FOREST, Biomes.BIRCH_FOREST, null
    }, {null, null, null, null, null}, {
        Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null
    }
    };
    this.windsweptBiomes = new ResourceKey[][]{
        {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
        {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
        {Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
        {null, null, null, null, null},
        {null, null, null, null, null}
    };
  }

  protected void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters) {
    this.addOffCoastBiomes(parameters);
    this.addInlandBiomes(parameters);
    this.addUndergroundBiomes(parameters);
  }


  private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters) {
    this.addSurfaceBiome(parameters,
        this.defaultParameter,
        this.defaultParameter,
        this.mushroomFieldsContinentalness,
        this.defaultParameter,
        this.defaultParameter,
        0.0F,
        Biomes.MUSHROOM_FIELDS
    );

    for (int i = 0; i < this.temperatureParameters.length; ++i) {
      Climate.Parameter parameterRange = this.temperatureParameters[i];
      this.addSurfaceBiome(parameters, parameterRange, this.defaultParameter, this.deepOceanContinentalness, this.defaultParameter, this.defaultParameter, 0.0F,
          this.oceanBiomes[0][i]);
      this.addSurfaceBiome(parameters, parameterRange, this.defaultParameter, this.oceanContinentalness, this.defaultParameter, this.defaultParameter, 0.0F,
          this.oceanBiomes[1][i]);
    }

  }

  private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters) {
    this.addMidSlice(parameters, Climate.Parameter.span(-1.0F, -0.93333334F));
    this.addHighSlice(parameters, Climate.Parameter.span(-0.93333334F, -0.7666667F));
    this.addPeaks(parameters, Climate.Parameter.span(-0.7666667F, -0.56666666F));
    this.addHighSlice(parameters, Climate.Parameter.span(-0.56666666F, -0.4F));
    this.addMidSlice(parameters, Climate.Parameter.span(-0.4F, -0.26666668F));
    this.addLowSlice(parameters, Climate.Parameter.span(-0.26666668F, -0.05F));
    this.addValleys(parameters, Climate.Parameter.span(-0.05F, 0.05F));
    this.addLowSlice(parameters, Climate.Parameter.span(0.05F, 0.26666668F));
    this.addMidSlice(parameters, Climate.Parameter.span(0.26666668F, 0.4F));
    this.addHighSlice(parameters, Climate.Parameter.span(0.4F, 0.56666666F));
    this.addPeaks(parameters, Climate.Parameter.span(0.56666666F, 0.7666667F));
    this.addHighSlice(parameters, Climate.Parameter.span(0.7666667F, 0.93333334F));
    this.addMidSlice(parameters, Climate.Parameter.span(0.93333334F, 1.0F));
  }

  private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness) {
    for (int i = 0; i < this.temperatureParameters.length; ++i) {
      Climate.Parameter parameterRange = this.temperatureParameters[i];

      for (int j = 0; j < this.humidityParameters.length; ++j) {
        Climate.Parameter parameterRange2 = this.humidityParameters[j];
        ResourceKey<Biome> registryKey = this.pickMiddleBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
        ResourceKey<Biome> registryKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
        ResourceKey<Biome> registryKey4 = this.pickPlateauBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey5 = this.pickShatteredBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey6 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, registryKey5);
        ResourceKey<Biome> registryKey7 = this.pickPeakBiome(i, j, weirdness);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
            this.erosionParameters[0],
            weirdness,
            0.0F,
            registryKey7
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
            this.erosionParameters[1],
            weirdness,
            0.0F,
            registryKey3
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[1],
            weirdness,
            0.0F,
            registryKey7
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
            Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[2],
            weirdness,
            0.0F,
            registryKey4
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey2);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey4);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
            this.erosionParameters[4],
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
            this.erosionParameters[5],
            weirdness,
            0.0F,
            registryKey6
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[5],
            weirdness,
            0.0F,
            registryKey5
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
            this.erosionParameters[6],
            weirdness,
            0.0F,
            registryKey
        );
      }
    }

  }

  private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness) {
    for (int i = 0; i < this.temperatureParameters.length; ++i) {
      Climate.Parameter parameterRange = this.temperatureParameters[i];

      for (int j = 0; j < this.humidityParameters.length; ++j) {
        Climate.Parameter parameterRange2 = this.humidityParameters[j];
        ResourceKey<Biome> registryKey = this.pickMiddleBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
        ResourceKey<Biome> registryKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
        ResourceKey<Biome> registryKey4 = this.pickPlateauBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey5 = this.pickShatteredBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey6 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, registryKey);
        ResourceKey<Biome> registryKey7 = this.pickSlopeBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey8 = this.pickPeakBiome(i, j, weirdness);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            this.coastContinentalness,
            Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[0], weirdness, 0.0F, registryKey7);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[0],
            weirdness,
            0.0F,
            registryKey8
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[1], weirdness, 0.0F, registryKey3);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[1],
            weirdness,
            0.0F,
            registryKey7
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
            Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[2],
            weirdness,
            0.0F,
            registryKey4
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey2);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey4);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
            this.erosionParameters[4],
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
            this.erosionParameters[5],
            weirdness,
            0.0F,
            registryKey6
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[5],
            weirdness,
            0.0F,
            registryKey5
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
            this.erosionParameters[6],
            weirdness,
            0.0F,
            registryKey
        );
      }
    }

  }

  private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness) {
    this.addSurfaceBiome(parameters,
        this.defaultParameter,
        this.defaultParameter,
        this.coastContinentalness,
        Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[2]),
        weirdness,
        0.0F,
        Biomes.STONY_SHORE
    );

    for (int i = 0; i < this.temperatureParameters.length; ++i) {
      Climate.Parameter parameterRange = this.temperatureParameters[i];

      for (int j = 0; j < this.humidityParameters.length; ++j) {
        Climate.Parameter parameterRange2 = this.humidityParameters[j];
        ResourceKey<Biome> registryKey = this.pickMiddleBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey2 = this.getBadlandsOrRegularBiome2(i, j, weirdness);
        ResourceKey<Biome> registryKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
        ResourceKey<Biome> registryKey4 = this.pickShatteredBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey5 = this.pickPlateauBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey6 = this.pickBeachBiome(i, j);
        ResourceKey<Biome> registryKey7 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, registryKey);
        ResourceKey<Biome> registryKey8 = this.pickShatteredCoastBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey9 = this.pickSlopeBiome(i, j, weirdness);

        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[6],
            weirdness,
            0.0F,
            this.getWetlandType(i, j, weirdness)
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[0],
            weirdness,
            0.0F,
            registryKey9
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness),
            this.erosionParameters[1],
            weirdness,
            0.0F,
            registryKey3
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[1], weirdness, 0.0F,
            i == 0 ? registryKey9 : registryKey5);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, registryKey);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, registryKey2);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, registryKey5);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness),
            this.erosionParameters[3],
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[3],
            weirdness,
            0.0F,
            registryKey2
        );
        if (weirdness.max() < 0L) {
          this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[4], weirdness, 0.0F, registryKey6);
          this.addSurfaceBiome(parameters,
              parameterRange,
              parameterRange2,
              Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness),
              this.erosionParameters[4],
              weirdness,
              0.0F,
              registryKey
          );
        } else {
          this.addSurfaceBiome(parameters,
              parameterRange,
              parameterRange2,
              Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
              this.erosionParameters[4],
              weirdness,
              0.0F,
              registryKey
          );
        }
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[5],
            weirdness,
            0.0F,
            registryKey4
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey8);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey7);

        if (weirdness.max() < 0L) {
          this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, registryKey6);
        } else {
          this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, registryKey);
        }
      }
    }

  }

  private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness) {
    this.addSurfaceBiome(parameters,
        this.defaultParameter,
        this.defaultParameter,
        this.coastContinentalness,
        Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[2]),
        weirdness,
        0.0F,
        Biomes.STONY_SHORE
    );

    for (int i = 0; i < this.temperatureParameters.length; ++i) {
      Climate.Parameter parameterRange = this.temperatureParameters[i];

      for (int j = 0; j < this.humidityParameters.length; ++j) {
        Climate.Parameter parameterRange2 = this.humidityParameters[j];
        ResourceKey<Biome> registryKey = this.pickMiddleBiome(i, j, weirdness);
        ResourceKey<Biome> registryKey2 = this.getBadlandsOrRegularBiome2(i, j, weirdness);
        ResourceKey<Biome> registryKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
        ResourceKey<Biome> registryKey4 = this.pickBeachBiome(i, j);
        ResourceKey<Biome> registryKey5 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, registryKey);
        ResourceKey<Biome> registryKey6 = this.pickShatteredCoastBiome(i, j, weirdness);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[6],
            weirdness,
            0.0F,
            this.getWetlandType(i, j, weirdness)
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            this.nearInlandContinentalness,
            Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
            weirdness,
            0.0F,
            registryKey2
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
            weirdness,
            0.0F,
            registryKey3
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            this.nearInlandContinentalness,
            Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[3]),
            weirdness,
            0.0F,
            registryKey2
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            this.coastContinentalness,
            Climate.Parameter.span(this.erosionParameters[3], this.erosionParameters[4]),
            weirdness,
            0.0F,
            registryKey4
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[4],
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            this.erosionParameters[5],
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey6);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey5);
        this.addSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, registryKey4);
      }
    }

  }

  private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness) {
    this.addSurfaceBiome(parameters,
        this.frozenTemperature,
        this.defaultParameter,
        this.coastContinentalness,
        Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
        weirdness,
        0.0F,
        weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER
    );
    this.addSurfaceBiome(parameters,
        this.nonFrozenTemperatureParameters,
        this.defaultParameter,
        this.coastContinentalness,
        Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
        weirdness,
        0.0F,
        weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER
    );
    this.addSurfaceBiome(parameters,
        this.frozenTemperature,
        this.defaultParameter,
        this.nearInlandContinentalness,
        Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
        weirdness,
        0.0F,
        Biomes.FROZEN_RIVER
    );
    this.addSurfaceBiome(parameters,
        this.nonFrozenTemperatureParameters,
        this.defaultParameter,
        this.nearInlandContinentalness,
        Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
        weirdness,
        0.0F,
        Biomes.RIVER
    );
    this.addSurfaceBiome(parameters,
        this.frozenTemperature,
        this.defaultParameter,
        Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
        Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[5]),
        weirdness,
        0.0F,
        Biomes.FROZEN_RIVER
    );
    this.addSurfaceBiome(parameters,
        this.nonFrozenTemperatureParameters,
        this.defaultParameter,
        Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
        Climate.Parameter.span(this.erosionParameters[2], this.erosionParameters[4]),
        weirdness,
        0.0F,
        Biomes.RIVER
    );
    this.addSurfaceBiome(parameters, this.frozenTemperature, this.defaultParameter, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F,
        Biomes.FROZEN_RIVER);
    this.addSurfaceBiome(parameters,
        this.frozenTemperature,
        this.defaultParameter,
        Climate.Parameter.span(this.riverContinentalness, this.farInlandContinentalness),
        this.erosionParameters[6],
        weirdness,
        0.0F,
        Biomes.FROZEN_RIVER
    );

    for (int i = 0; i < this.temperatureParameters.length; ++i) {
      Climate.Parameter parameterRange = this.temperatureParameters[i];

      for (int j = 0; j < this.humidityParameters.length; ++j) {
        Climate.Parameter parameterRange2 = this.humidityParameters[j];
        ResourceKey<Biome> registryKey = this.getBadlandsOrRegularBiome2(i, j, weirdness);
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness),
            Climate.Parameter.span(this.erosionParameters[0], this.erosionParameters[1]),
            weirdness,
            0.0F,
            registryKey
        );
        this.addSurfaceBiome(parameters,
            parameterRange,
            parameterRange2,
            Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness),
            this.erosionParameters[6],
            weirdness,
            0.0F,
            this.getWetlandType(i, j, weirdness)
        );
      }
    }

  }

  private ResourceKey<Biome> pickMiddleBiome(int temperature, int humidity, Climate.Parameter weirdness) {
    if (weirdness.max() < 0L) {
      return this.commonBiomes[temperature][humidity];
    } else {
      ResourceKey<Biome> registryKey = this.uncommonBiomes[temperature][humidity];
      return registryKey == null ? this.commonBiomes[temperature][humidity] : registryKey;
    }
  }

  private ResourceKey<Biome> getWetlandType(int temperature, int humidity, Climate.Parameter weirdness) {
    if (temperature == 0) {
      return this.pickMiddleBiome(temperature, humidity, weirdness);
    } else if (temperature == 3 && humidity > 1 && CONFIG.has_tropical_basin) {
      return NSBiomes.TROPICAL_BASIN;
    } else if ((humidity <= 3 || temperature == 4) && CONFIG.has_marsh) {
      return NSBiomes.MARSH;
    } else {
      return Biomes.SWAMP;
    }
  }

  private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int temperature, int humidity, Climate.Parameter weirdness) {
    return temperature == 4 ? this.pickBadlandsBiome(humidity, weirdness) : this.pickMiddleBiome(temperature, humidity, weirdness);
  }

  private ResourceKey<Biome> getBadlandsOrRegularBiome2(int temperature, int humidity, Climate.Parameter weirdness) {
    return temperature == 4 ? this.getBadlandsBiome2(humidity, weirdness) : this.pickMiddleBiome(temperature, humidity, weirdness);
  }

  private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int temperature, int humidity, Climate.Parameter weirdness) {
    return temperature == 0 ? this.pickSlopeBiome(temperature, humidity, weirdness) : this.pickMiddleBiomeOrBadlandsIfHot(temperature, humidity, weirdness);
  }

  private ResourceKey<Biome> maybePickWindsweptSavannaBiome(int temperature, int humidity, Climate.Parameter weirdness, ResourceKey<Biome> biomeKey) {
    if (temperature == 2 && CONFIG.has_sugi_forest) {
      return humidity < 4 && weirdness.max() >= 0L ? NSBiomes.WINDSWEPT_SUGI_FOREST : biomeKey;
    }
    return temperature > 1 && humidity < 4 && weirdness.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : biomeKey;
  }

  private ResourceKey<Biome> pickShatteredCoastBiome(int temperature, int humidity, Climate.Parameter weirdness) {
    ResourceKey<Biome> registryKey = weirdness.max() >= 0L ? this.pickMiddleBiome(temperature, humidity, weirdness) : this.pickBeachBiome(temperature, humidity);
    return this.maybePickWindsweptSavannaBiome(temperature, humidity, weirdness, registryKey);
  }

  private ResourceKey<Biome> pickBeachBiome(int temperature, int humidity) {
    if (temperature == 0) {
      return Biomes.SNOWY_BEACH;
    } else if (temperature == 3 && CONFIG.has_tropical_shores) {
      return NSBiomes.TROPICAL_SHORES;
    } else {
      return temperature == 4 ? Biomes.DESERT : Biomes.BEACH;
    }
  }

  private ResourceKey<Biome> pickBadlandsBiome(int humidity, Climate.Parameter weirdness) {
    if (humidity < 2) {
      return weirdness.max() < 0L ? Biomes.BADLANDS : Biomes.ERODED_BADLANDS;
    } else {
      return humidity < 3 ? Biomes.BADLANDS : nearBiomeWoodedBadlandsHot;
    }
  }

  private ResourceKey<Biome> getBadlandsBiome2(int humidity, Climate.Parameter weirdness) {
    if (humidity < 2) {
      return weirdness.max() < 0L ? nearBiomeBadlandsHot : Biomes.ERODED_BADLANDS;
    } else {
      return humidity < 3 ? nearBiomeBadlandsHot : nearBiomeWoodedBadlandsHot;
    }
  }

  private ResourceKey<Biome> pickPlateauBiome(int temperature, int humidity, Climate.Parameter weirdness) {
    if (weirdness.max() >= 0L) {
      ResourceKey<Biome> registryKey = this.specialNearMountainBiomes[temperature][humidity];
      if (registryKey != null) {
        return registryKey;
      }
    }

    return this.nearMountainBiomes[temperature][humidity];
  }

  private ResourceKey<Biome> pickPeakBiome(int temperature, int humidity, Climate.Parameter weirdness) {
    if (temperature <= 2) {
      return weirdness.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
    } else {
      return temperature == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(humidity, weirdness);
    }
  }

  private ResourceKey<Biome> pickSlopeBiome(int temperature, int humidity, Climate.Parameter weirdness) {
    if (temperature >= 3) {
      return this.pickPlateauBiome(temperature, humidity, weirdness);
    } else {
      return humidity <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
    }
  }

  private ResourceKey<Biome> pickShatteredBiome(int temperature, int humidity, Climate.Parameter weirdness) {
    ResourceKey<Biome> registryKey = this.windsweptBiomes[temperature][humidity];
    return registryKey == null ? this.pickMiddleBiome(temperature, humidity, weirdness) : registryKey;
  }

  private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter temperature,
      Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness,
      float offset, ResourceKey<Biome> biome) {
    parameters.accept(
        Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
    parameters.accept(
        Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
  }
}
