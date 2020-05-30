package club.claycoffee.ClayTech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import club.claycoffee.ClayTech.api.ClayTechBranch;
import club.claycoffee.ClayTech.utils.FileDownloader;

public class ClayTechUpdater {
	private ClayTech plugin = ClayTech.getInstance();
	private ClayTechBranch branch;

	public ClayTechUpdater() {
		if (plugin.getDescription().getVersion().toUpperCase().contains("DEV")) {
			branch = ClayTechBranch.DEVELOPMENT;
		} else {
			branch = ClayTechBranch.STABLE;
		}
	}

	public void tryUpdate() {
		new BukkitRunnable() {

			@Override
			public void run() {
				try {
					URL url = new URL("https://api.github.com/repos/ClayCoffee/ClayTech/releases");
					InputStream in = url.openStream();
					BufferedReader bf = new BufferedReader(new InputStreamReader(in));
					JsonArray ja = new JsonParser().parse(bf.readLine()).getAsJsonArray();
					String downloadURL = null;
					if (branch == ClayTechBranch.STABLE) {
						for (int i = 0; i < ja.size(); i++) {
							if (ja.get(i).getAsJsonObject().get("prerelease").getAsBoolean()) {
								continue;
							} else if (!ja.get(i).getAsJsonObject().get("tag_name").getAsString()
									.equalsIgnoreCase(plugin.getPluginVersion())) {
								downloadURL = ja.get(i).getAsJsonObject().get("assets").getAsJsonArray().get(0)
										.getAsJsonObject().get("browser_download_url").getAsString();
								FileDownloader.updateFunc(downloadURL,
										ja.get(i).getAsJsonObject().get("assets").getAsJsonArray().get(0).getAsJsonObject()
												.get("name").getAsString(),
										plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"), ja);
							}
						}
					} else {
						if (ja.get(0).getAsJsonObject().get("prerelease").getAsBoolean()) {
							if (!ja.get(0).getAsJsonObject().get("tag_name").getAsString()
									.equalsIgnoreCase(plugin.getPluginVersion())) {
								downloadURL = ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
										.getAsJsonObject().get("browser_download_url").getAsString();
								// 开始下载
								FileDownloader.updateFunc(downloadURL,
										ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0).getAsJsonObject()
												.get("name").getAsString(),
										plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"), ja);
							}
						} else
							return;
					}
					// 服务器关闭时删除原文件
					plugin.getFile().deleteOnExit();

				} catch (IOException e) {
					plugin.getLogger().log(Level.SEVERE, "Could not perform update. Is the Github down?");
					e.printStackTrace();
					return;
				}
			}
			
		}.runTaskAsynchronously(ClayTech.getInstance());
	}

	public ClayTechBranch getBranch() {
		return branch;
	}
}
