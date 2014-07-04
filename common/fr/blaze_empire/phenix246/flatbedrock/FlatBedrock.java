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

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = FlatBedrock.MODID, name = FlatBedrock.MOD_NAME, version = FlatBedrock.MOD_VERSION)
public class FlatBedrock
{
	public static final String MODID = "flat_bedrock";
	public static final String MOD_NAME = "Flat Bedrock";
	public static final String MOD_VERSION = "@VERSION@";
	
	public static boolean activeFlatBedrockGeneration;

	/** Configuration **/
	private static Configuration config;
	protected static File configFolder;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		configFolder = new File(event.getModConfigurationDirectory(), "Phenix246");
		config = new Configuration(new File(configFolder, "FlatBedrock.cfg"));
		try
		{
			config.load();
			activeFlatBedrockGeneration = getBoolean("Generation", "Active flat bedrock generation", true, "Flat Bedrock Generation", event);
		}
		catch(Exception ex)
		{
			(event.getModLog()).error("Failed to load configuration");
		}
		finally
		{
			if(config.hasChanged())
				config.save();
		}
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		 if(activeFlatBedrockGeneration)
			 GameRegistry.registerWorldGenerator(new WorldGenFlatBedrock(), 0);
	}
	
	public static boolean getBoolean(String category, String key, boolean defaultValue, String message, FMLPreInitializationEvent event)
	{
		boolean b = false;
		try
		{
			b = config.get(category, key, defaultValue).getBoolean(defaultValue);
		}
		catch(Exception ex)
		{
			(event.getModLog()).error("Failed to load configuration");
			(event.getModLog()).error("Failed to get " + message + " value");
		}
		return b;
	}
}
