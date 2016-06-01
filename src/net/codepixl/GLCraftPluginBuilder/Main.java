package net.codepixl.GLCraftPluginBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		MainWindow w = new MainWindow();
		w.setVisible(true);
		w.setLocationRelativeTo(null);
	}
	
	public static void build(BuildOptions o){
		try {
			File outFolder = new File(o.outLoc+"/"+o.name);
			outFolder.mkdirs();
			JSONObject j = new JSONObject();
			j.put("pluginName", o.name);
			j.put("pluginVersion", o.ver);
			j.put("pluginDescription", o.desc);
			j.put("mainClass", o.main);
			FileWriter out = new FileWriter(new File(outFolder.getAbsolutePath()+"/plugin.json"));
			j.write(out);
			out.flush();
			out.close();
			FileUtils.copyDirectory(new File(o.binLoc), outFolder);
			ZipFile zip = new ZipFile(new File(outFolder.getParent(),outFolder.getName()+".glcp"));
			ZipParameters params = new ZipParameters();
			params.setIncludeRootFolder(false);
			zip.addFolder(outFolder, params);
			deleteDirectory(outFolder);
			JOptionPane.showMessageDialog(null, "Successfully saved as "+o.name+".glcp","GLCraft Plugin Builder", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An IOException occurred.","GLCraft Plugin Builder", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "A ZipException occurred.","GLCraft Plugin Builder", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
}
