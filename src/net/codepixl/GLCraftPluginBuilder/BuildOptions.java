package net.codepixl.GLCraftPluginBuilder;

public class BuildOptions {
	String outLoc,binLoc,name,ver,desc,main;
	public BuildOptions(String outLoc, String binLoc, String name, String ver, String desc, String main){
		this.binLoc = binLoc;
		this.name = name;
		this.ver = ver;
		this.desc = desc;
		this.main = main;
		this.outLoc = outLoc;
	}
}
