package net.hibiscus.naturespirit.compat.lithostitched;


import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.hibiscus.naturespirit.world.paramaters.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LithostitchedEventHandlers {
    private static final RegistryKey<Region> TERRA_FERAX = RegistryKey.of(LithostitchedRegistries.REGION, Identifier.of(NatureSpirit.MOD_ID, "terra_ferax"));
    private static final RegistryKey<Region> TERRA_FLAVA = RegistryKey.of(LithostitchedRegistries.REGION, Identifier.of(NatureSpirit.MOD_ID, "terra_flava"));
    private static final RegistryKey<Region> TERRA_LAETA = RegistryKey.of(LithostitchedRegistries.REGION, Identifier.of(NatureSpirit.MOD_ID, "terra_laeta"));
    private static final RegistryKey<Region> TERRA_MATER = RegistryKey.of(LithostitchedRegistries.REGION, Identifier.of(NatureSpirit.MOD_ID, "terra_mater"));
    private static final RegistryKey<Region> TERRA_SOLARIS = RegistryKey.of(LithostitchedRegistries.REGION, Identifier.of(NatureSpirit.MOD_ID, "terra_solaris"));

    public static void handleAddRegionsEvent() {
        AddRegionsEvent.EVENT.register((registries, consumer) -> {
            RegistryEntryList<Biome> isOverworld = registries.createRegistryLookup().getOrThrow(RegistryKeys.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);
            consumer.accept(TERRA_FERAX, World.OVERWORLD, isOverworld, NatureSpirit.CONFIG.terra_ferax_weight);
            consumer.accept(TERRA_FLAVA, World.OVERWORLD, isOverworld, NatureSpirit.CONFIG.terra_flava_weight);
            consumer.accept(TERRA_LAETA, World.OVERWORLD, isOverworld, NatureSpirit.CONFIG.terra_laeta_weight);
            consumer.accept(TERRA_MATER, World.OVERWORLD, isOverworld, NatureSpirit.CONFIG.terra_mater_weight);
            consumer.accept(TERRA_SOLARIS, World.OVERWORLD, isOverworld, NatureSpirit.CONFIG.terra_solaris_weight);
        });
    }

    public static void handleAddBiomeInjectorsEvent() {
        AddBiomeInjectorsEvent.EVENT.register((registries, consumer) -> {
            addBiomeInjector(registries, consumer, "terra_ferax", new TerraFeraxParameters()::writeOverworldBiomeParameters, TERRA_FERAX);
            addBiomeInjector(registries, consumer, "terra_flava", new TerraFlavaParameters()::writeOverworldBiomeParameters, TERRA_FLAVA);
            addBiomeInjector(registries, consumer, "terra_laeta", new TerraLaetaParameters()::writeOverworldBiomeParameters, TERRA_LAETA);
            addBiomeInjector(registries, consumer, "terra_mater", new TerraMaterParameters()::writeOverworldBiomeParameters, TERRA_MATER);
            addBiomeInjector(registries, consumer, "terra_solaris", new TerraSolarisParameters()::writeOverworldBiomeParameters, TERRA_SOLARIS);

            RegistryEntryLookup<Biome> biomeRegistry = registries.createRegistryLookup().getOrThrow(RegistryKeys.BIOME);

            if(NatureSpirit.CONFIG.has_marsh) {
                consumer.accept(
                        Identifier.of(NatureSpirit.MOD_ID, "marsh_humidity"),
                        BiomeInjector.builder(World.OVERWORLD).replacePartially(
                                biomeRegistry.getOrThrow(BiomeKeys.SWAMP),
                                biomeRegistry.getOrThrow(NSBiomes.MARSH),
                                ParameterBuilder.create().climateMax(BiomeInjector.ClimateParameter.HUMIDITY, .1F)
                        )
                );
                consumer.accept(
                        Identifier.of(NatureSpirit.MOD_ID, "marsh_temperature"),
                        BiomeInjector.builder(World.OVERWORLD).replacePartially(
                                biomeRegistry.getOrThrow(BiomeKeys.SWAMP),
                                biomeRegistry.getOrThrow(NSBiomes.MARSH),
                                ParameterBuilder.create().climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, .55F, 1.0F)
                        )
                );
            }

        });
    }

    // Only used for surface rules
    public static void handleAddWorldgenModifiersEvent() {
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                    Identifier.of(NatureSpirit.MOD_ID, "surface_rules"),
                    WorldgenModifier.builder()
                            .priority(3000)  // default is 1000; we set it at 3000 to let datapacks override our surface rules by default
                            .addSurfaceRule(World.OVERWORLD, InjectionType.PREPEND, NSSurfaceRules.makeRules())
            );
        });
    }

    private static void addBiomeInjector(DynamicRegistryManager registries, BiConsumer<Identifier, BiomeInjector> consumer, String name,
                                         BiomeParameterProvider parameterProvider, RegistryKey<Region> region) {

        List<Pair<MultiNoiseUtil.NoiseHypercube, RegistryEntry<Biome>>> regionPoints = new ArrayList<>();
        parameterProvider.writeOverworldBiomeParameters(point -> {
            RegistryEntry<Biome> holder = registries.createRegistryLookup().getOrThrow(RegistryKeys.BIOME).getOrThrow(point.getSecond());
            regionPoints.add(Pair.of(point.getFirst(), holder));
        });

        MultiNoiseUtil.Entries<RegistryEntry<Biome>> regionLayout = new MultiNoiseUtil.Entries<>(regionPoints);
        consumer.accept(
                Identifier.of(NatureSpirit.MOD_ID, name),
                BiomeInjector.builder(World.OVERWORLD).priority(1).dispatchAlternateLayout(
                        ParameterBuilder.create().region(region),
                        regionLayout)
        );
    }

    // This is feels really hacky honestly
    @FunctionalInterface
    private interface BiomeParameterProvider {
        void writeOverworldBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters);
    }
}
