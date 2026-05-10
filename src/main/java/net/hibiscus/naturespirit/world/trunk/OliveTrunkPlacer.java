//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.blocks.BranchingTrunkBlock;
import net.hibiscus.naturespirit.registration.NSWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer.TreeNode;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class OliveTrunkPlacer extends TrunkPlacer {
   public static final Codec <OliveTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return fillTrunkPlacerFields(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps").forGetter((trunkPlacer) -> {
         return trunkPlacer.extraBranchSteps;
      }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
         return trunkPlacer.placeBranchPerLogProbability;
      }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
         return trunkPlacer.extraBranchLength;
      }), RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
         return trunkPlacer.canGrowThrough;
      }))).apply(instance, OliveTrunkPlacer::new);
   });
   private final IntProvider extraBranchSteps;
   private final float placeBranchPerLogProbability;
   private final IntProvider extraBranchLength;
   private final RegistryEntryList <Block> canGrowThrough;

   public OliveTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability, IntProvider extraBranchLength, RegistryEntryList <Block> canGrowThrough) {
      super(baseHeight, firstRandomHeight, secondRandomHeight);
      this.extraBranchSteps = extraBranchSteps;
      this.placeBranchPerLogProbability = placeBranchPerLogProbability;
      this.extraBranchLength = extraBranchLength;
      this.canGrowThrough = canGrowThrough;
   }

   protected TrunkPlacerType <?> getType() {
      return NSWorldGen.OLIVE_TRUNK_PLACER;
   }

   public List <TreeNode> generate(TestableWorld world, BiConsumer <BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
      List <TreeNode> list = Lists.newArrayList();
      Set <BlockPos> generatedTrunkPositions = new HashSet<>();
      Mutable mutable = new Mutable();
      Mutable mutable2 = new Mutable();
      boolean bl = random.nextBoolean();
      boolean bl2 = random.nextBoolean();
      boolean bl3 = random.nextBoolean();
      Direction trunkOffset = Type.HORIZONTAL.random(random);
      Direction trunkOffset2 = Type.HORIZONTAL.random(random);
      int randomInt = random.nextBetween(0, 1);
      int randomInt2 = random.nextBetween(-1, 1);

      for(int i = 0; i < height; ++i) {
         int j = startPos.getY() + i;
         Direction direction = Type.HORIZONTAL.random(random);
         Direction direction2 = Type.HORIZONTAL.random(random);
         Direction direction3 = Type.HORIZONTAL.random(random);

         Mutable mutablePos1 = mutable.set(startPos.getX(), j, startPos.getZ());
         BlockPos pos2 = mutablePos1.offset(trunkOffset);
         BlockPos pos3 = pos2.offset(trunkOffset2);
         BlockPos blockPos = bl && i >= 2 ? (bl2 && trunkOffset != trunkOffset2 ? pos3 : pos2) : mutablePos1;

         if(bl && i == 2 && !bl3) {
            this.getAndSetTrunkState(world, replacer, random, mutablePos1, config, generatedTrunkPositions);
         }
         if(this.getAndSetTrunkState(world, replacer, random, blockPos, config, generatedTrunkPositions) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
            int k = this.extraBranchLength.get(random);
            int l = Math.max(0, k - this.extraBranchLength.get(random) - 1);
            int m = height + i - 7;
            if(i > 2) {
               this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction, l, m, i, generatedTrunkPositions);

               if(random.nextFloat() < this.placeBranchPerLogProbability - 0.65) {
                  this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction2, l, Math.round(m / 2), i, generatedTrunkPositions);
               }
               if(random.nextFloat() < this.placeBranchPerLogProbability - 0.85) {
                  this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction3, l, Math.round(m / 2), i, generatedTrunkPositions);
               }
            }
         }
         if(i == height - 1) {
            list.add(new TreeNode(blockPos.up(1), 0, false));
         }
      }
      if(bl3 && bl) {
         for(int i = 0; i < height - randomInt; ++i) {
            int j = startPos.getY() + i;
            Direction direction = Type.HORIZONTAL.random(random);
            Direction direction2 = Type.HORIZONTAL.random(random);
            Direction direction3 = Type.HORIZONTAL.random(random);

            Mutable mutablePos1 = mutable2.set(startPos.getX(), j, startPos.getZ());
            BlockPos pos2 = mutablePos1.offset(trunkOffset.getOpposite()).offset(randomInt2 < 0 ? Direction.UP : Direction.DOWN, randomInt2);
            BlockPos pos3 = pos2.offset(trunkOffset2.getOpposite());
            BlockPos blockPos = i >= 2 ? bl2 && trunkOffset.getOpposite() != trunkOffset2.getOpposite() ? pos3 : pos2 : mutablePos1.offset(randomInt2 < 0 ? Direction.UP : Direction.DOWN, randomInt2);

            if(i == 2) {
               this.getAndSetTrunkState(world, replacer, random, mutablePos1.offset(randomInt2 < 0 ? Direction.UP : Direction.DOWN, randomInt2), config, generatedTrunkPositions);
            }
            if(this.getAndSetTrunkState(world, replacer, random, blockPos, config, generatedTrunkPositions) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
               int k = this.extraBranchLength.get(random);
               int l = Math.max(0, k - this.extraBranchLength.get(random) - 1);
               int m = height + i - 7;
               if(i > 2) {
                  this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction, l, m, i, generatedTrunkPositions);

                  if(random.nextFloat() < this.placeBranchPerLogProbability - 0.65) {
                     this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction2, l, Math.round(m / 2), i, generatedTrunkPositions);
                  }
                  if(random.nextFloat() < this.placeBranchPerLogProbability - 0.85) {
                     this.generateExtraBranch(world, replacer, random, height, config, list, blockPos, j, direction3, l, Math.round(m / 2), i, generatedTrunkPositions);
                  }
               }
            }
            if(i == ((height - 1) - randomInt2) - randomInt) {
               list.add(new TreeNode(blockPos.up(1), 0, false));
            }
         }
      }

      this.updateGeneratedConnections(replacer, random, config, generatedTrunkPositions);
      return list;
   }

   private void generateExtraBranch(TestableWorld world, BiConsumer <BlockPos, BlockState> replacer, Random random, int height, TreeFeatureConfig config, List <TreeNode> nodes, BlockPos pos, int yOffset, Direction direction, int length, int steps, int b, Set <BlockPos> generatedTrunkPositions) {
      int i = yOffset + length;
      int j = pos.getX();
      int k = pos.getZ();
      Mutable mutable = new Mutable();
      boolean bl = random.nextFloat() < .5F;
      Direction direction2 = random.nextFloat() < .5F ? direction.rotateYClockwise() : direction.rotateYCounterclockwise();

      for(int l = length; l < height && steps > 0 && (steps < (bl ? 7 : 10)); --steps) {
         if(l >= 1) {
            int m = Math.round(yOffset + l / 5);
            j += direction.getOffsetX();
            if(bl) {
               k += Math.max(0, direction2.getOffsetZ() - random.nextInt(2));
            }
            else {
               k += direction.getOffsetZ();
            }
            i = m;
            if(this.getAndSetTrunkState(world, replacer, random, mutable.set(j, m, k), config, blockState -> {
               return blockState.withIfExists(PillarBlock.AXIS, direction.getAxis());
            }, generatedTrunkPositions)) {
               i = m + 1;
            }
         }
         ++l;
      }

      if(i - yOffset >= 1) {
         BlockPos blockPos = new BlockPos(j, i, k);
         nodes.add(new TreeNode(blockPos, 0, false));
      }

   }

   private boolean getAndSetTrunkState(TestableWorld world, BiConsumer <BlockPos, BlockState> replacer, Random random, BlockPos pos, TreeFeatureConfig config, Set <BlockPos> generatedTrunkPositions) {
      boolean placed = this.getAndSetState(world, replacer, random, pos, config);
      if(placed) {
         generatedTrunkPositions.add(pos.toImmutable());
      }
      return placed;
   }

   private boolean getAndSetTrunkState(TestableWorld world, BiConsumer <BlockPos, BlockState> replacer, Random random, BlockPos pos, TreeFeatureConfig config, Function <BlockState, BlockState> trunkBlockStateDecorator, Set <BlockPos> generatedTrunkPositions) {
      boolean placed = this.getAndSetState(world, replacer, random, pos, config, trunkBlockStateDecorator);
      if(placed) {
         generatedTrunkPositions.add(pos.toImmutable());
      }
      return placed;
   }

   private void updateGeneratedConnections(BiConsumer <BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, Set <BlockPos> generatedTrunkPositions) {
      for(BlockPos pos : generatedTrunkPositions) {
         BlockState state = config.trunkProvider.get(random, pos);
         if(state.getBlock() instanceof BranchingTrunkBlock) {
            replacer.accept(pos, state
                    .withIfExists(BranchingTrunkBlock.DOWN, generatedTrunkPositions.contains(pos.down()))
                    .withIfExists(BranchingTrunkBlock.UP, generatedTrunkPositions.contains(pos.up()))
                    .withIfExists(BranchingTrunkBlock.NORTH, generatedTrunkPositions.contains(pos.north()))
                    .withIfExists(BranchingTrunkBlock.EAST, generatedTrunkPositions.contains(pos.east()))
                    .withIfExists(BranchingTrunkBlock.SOUTH, generatedTrunkPositions.contains(pos.south()))
                    .withIfExists(BranchingTrunkBlock.WEST, generatedTrunkPositions.contains(pos.west()))
            );
         }
      }
   }

   protected boolean canReplace(TestableWorld world, BlockPos pos) {
      return super.canReplace(world, pos) || world.testBlockState(pos, (state) -> {
         return state.isIn(this.canGrowThrough);
      });
   }
}
