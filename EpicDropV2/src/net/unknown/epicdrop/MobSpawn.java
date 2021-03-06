package net.unknown.epicdrop;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.entity.LivingEntity;

public class MobSpawn {

	/** Checks where mob is spawned 
	 * @param LV = LivingEntity
	 * @param Mob = Mobs Name
	 * @param Env = Environment where mob spawned*/
	public static void checkMob(LivingEntity LV, String Mob, Environment Env){
		double Max = 0.0;
		
		//Checks to see if mob was spawned in normal or nether world
		if(Env.equals(Environment.NORMAL)){
			//Checks to see if Custom Health is enabled in the Normal World
			if(!Globals.CustomHealthNormal){return;}
			
			Max = getCustomHealthNormal(Mob); //Sets variable max to new health.
			
		}else if(Env.equals(Environment.NETHER)){
			//Checks to see if Custom Health is enabled in the Nether World
			if(!Globals.CustomHealthNether){return;}
			
			Max = getCustomHealthNether(Mob); //Sets variable max to new health.
		}else if(Env.equals(Environment.THE_END)){
			//Checks to see if Custom Health is enabled in the Nether World
			if(!Globals.CustomHealthEnd){return;}
			
			Max = getCustomHealthEnd(Mob); //Sets variable max to new health.
		}
		
		/*Shows MobName, World, and Health */
		if(Globals.Debug){
			Bukkit.getLogger().info(Globals.Debug("MobName: " + Mob + "\nHealth: " + (Max/2) + "\nWorldName: " + LV.getWorld().getName()));
		}
		
		if(Max == 0){
			Core.plugin.getLogger().info("[ERROR]: Mob, " + Mob + " doesn't have a set health in world: " + LV.getWorld().getName());
			return;
		}
		
		LV.setMaxHealth(Max); //Sets new MAX health
		LV.setHealth(Max/2); //Sets new CURRENT health
		
	}
	
	/**Method by which Nether healths are retrieved from Config file */
	public static double getCustomHealthNether(String mob){
		double CustomHealthNether = 0;
		CustomHealthNether = Core.plugin.getConfig().getDouble("eDrop.Mobs." + mob + ".CustomHealth.NETHER");
		
		return CustomHealthNether;
	}
	
	//Method by which Normal healths are retrieved from Config file
	public static double getCustomHealthNormal(String mob){
		double CustomHealthNormal = 0;
		CustomHealthNormal = Core.plugin.getConfig().getDouble("eDrop.Mobs." + mob + ".CustomHealth.NORMAL");
		
		return CustomHealthNormal;
	}
	
	//Method by which The End healths are retrieved from Config file
		public static double getCustomHealthEnd(String mob){
			double CustomHealthEnd = 0;
			CustomHealthEnd = Core.plugin.getConfig().getDouble("eDrop.Mobs." + mob + ".CustomHealth.THE_END");
			
			return CustomHealthEnd;
		}
}
