package cn.claycoffee.ClayTech;

import cn.claycoffee.ClayTech.api.ClayTechBranch;
import cn.claycoffee.ClayTech.utils.FileDownloader;
import cn.claycoffee.ClayTech.utils.Lang;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.net.URL;

public class ClayTechUpdater {
    private ClayTech plugin = ClayTech.getInstance();
    private ClayTechBranch branch;

    public ClayTechUpdater() {
        try {
            branch = ClayTechBranch.valueOf(ClayTech.getUpdateBranch());
        } catch (Exception e) {
            Bukkit.getLogger().info("§cInvalid Update Branch.");
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
                                    .equalsIgnoreCase(ClayTechData.currentVersion)) {
                                if (new File(plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"),
                                        ja.get(i).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                                .getAsJsonObject().get("name").getAsString()).exists())
                                    return;

                                downloadURL = ja.get(i).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                        .getAsJsonObject().get("browser_download_url").getAsString();
                                if (!FileDownloader.updateFunc(downloadURL,
                                        ja.get(i).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                                .getAsJsonObject().get("name").getAsString(),
                                        plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"), ja)
                                        .equalsIgnoreCase("")) {
                                    Bukkit.getServer().getLogger().info("§a" + Lang.readGeneralText("Info_1"));
                                    Bukkit.getServer().getLogger()
                                            .info(Lang.readGeneralText("update_done")
                                                    .replaceAll("\\{new_version\\}",
                                                            ja.get(i).getAsJsonObject().get("tag_name").getAsString())
                                                    .replaceAll("\\{old_version\\}",
                                                            ClayTech.getInstance().getPluginVersion()));
                                    Bukkit.getServer().getLogger().info("§a" + Lang.readGeneralText("Info_6"));
                                    ClayTechData.currentVersion = ja.get(i).getAsJsonObject().get("tag_name")
                                            .getAsString();
                                    return;
                                }
                            } else {
                                Bukkit.getServer().getLogger().info(Lang.readGeneralText("LatestVersion"));
                                return;
                            }
                        }
                    } else if (branch == ClayTechBranch.DEVELOPMENT) {
                        if (ja.get(0).getAsJsonObject().get("prerelease").getAsBoolean()) {
                            if (!ja.get(0).getAsJsonObject().get("tag_name").getAsString()
                                    .equalsIgnoreCase(ClayTechData.currentVersion)) {
                                if (new File(plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"),
                                        ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                                .getAsJsonObject().get("name").getAsString()).exists())
                                    return;
                                downloadURL = ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                        .getAsJsonObject().get("browser_download_url").getAsString();
                                // 开始下载
                                if (!FileDownloader.updateFunc(downloadURL,
                                        ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                                .getAsJsonObject().get("name").getAsString(),
                                        plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"), ja)
                                        .equalsIgnoreCase("")) {
                                    Bukkit.getServer().getLogger().info("§a" + Lang.readGeneralText("Info_1"));
                                    Bukkit.getServer().getLogger()
                                            .info(Lang.readGeneralText("update_done")
                                                    .replaceAll("\\{new_version\\}",
                                                            ja.get(0).getAsJsonObject().get("tag_name").getAsString())
                                                    .replaceAll("\\{old_version\\}",
                                                            ClayTech.getInstance().getPluginVersion()));
                                    Bukkit.getServer().getLogger().info("§a" + Lang.readGeneralText("Info_6"));
                                    ClayTechData.currentVersion = ja.get(0).getAsJsonObject().get("tag_name")
                                            .getAsString();
                                    return;
                                }
                            } else {
                                Bukkit.getServer().getLogger().info(Lang.readGeneralText("LatestVersion"));
                                return;
                            }
                        } else
                            return;
                    } else if (branch == ClayTechBranch.ALL) {
                        if (!ja.get(0).getAsJsonObject().get("tag_name").getAsString()
                                .equalsIgnoreCase(ClayTechData.currentVersion)) {
                            if (new File(plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"),
                                    ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                            .getAsJsonObject().get("name").getAsString()).exists())
                                return;
                            downloadURL = ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                    .getAsJsonObject().get("browser_download_url").getAsString();
                            // 开始下载
                            if (!FileDownloader.updateFunc(downloadURL,
                                    ja.get(0).getAsJsonObject().get("assets").getAsJsonArray().get(0)
                                            .getAsJsonObject().get("name").getAsString(),
                                    plugin.getServer().getUpdateFolder().replaceAll("update", "plugins"), ja)
                                    .equalsIgnoreCase("")) {
                                Bukkit.getServer().getLogger().info("§a" + Lang.readGeneralText("Info_1"));
                                Bukkit.getServer().getLogger()
                                        .info(Lang.readGeneralText("update_done")
                                                .replaceAll("\\{new_version\\}",
                                                        ja.get(0).getAsJsonObject().get("tag_name").getAsString())
                                                .replaceAll("\\{old_version\\}",
                                                        ClayTech.getInstance().getPluginVersion()));
                                Bukkit.getServer().getLogger().info("§a" + Lang.readGeneralText("Info_6"));
                                ClayTechData.currentVersion = ja.get(0).getAsJsonObject().get("tag_name")
                                        .getAsString();
                                return;
                            }
                        } else {
                            Bukkit.getServer().getLogger().info(Lang.readGeneralText("LatestVersion"));
                            return;
                        }
                    }

                } catch (IOException e) {
                    Bukkit.getLogger().info("§cCould not perform update. Is the Github down?");
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
