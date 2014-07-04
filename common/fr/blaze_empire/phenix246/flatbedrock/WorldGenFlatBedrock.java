 /*******************************************************************************
 * Copyright (c) 2014, Phenix246
 *
 * This work is made available under the terms of the Creative Commons Attribution License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.en
 * Contact the author for use the source
 * 
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.fr
 * Contacter l'auteur pour utiliser les sources
 ******************************************************************************/

package fr.blaze_empire.phenix246.flatbedrock;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenFlatBedrock implements IWorldGenerator
{
	// Original idea from powercrystals (github.com/powercrystals)
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
		boolean isNether = b.biomeName.toLowerCase().equals("hell");

		for(int blockX = 0; blockX < 16; blockX++)
		{
			for(int blockZ = 0; blockZ < 16; blockZ++)
			{
				if(isNether)
				{
					for(int blockY = 126; blockY > 121; blockY--)
					{
						if(world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock)
							world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, Blocks.netherrack, 0, 2);
					}
				}

				for(int blockY = 5; blockY > 0; blockY--)
				{
					if(world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock)
					{
						if(isNether)
							world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, Blocks.netherrack, 0, 2);
						else
							world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, Blocks.stone, 0, 2);
					}
				}
			}
		}		
	}
}