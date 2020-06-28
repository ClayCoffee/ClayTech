package club.claycoffee.ClayTech.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;

import club.claycoffee.ClayTech.ClayTech;

public class FileDownloader {

	public static String updateFunc(String urlp, String fileName, String savePath, JsonArray ja) {
		try {
			URL url = new URL(urlp);
			HttpURLConnection conne = (HttpURLConnection) url.openConnection();
			conne.setConnectTimeout(5 * 1000);
			conne.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			InputStream input = conne.getInputStream();
			byte[] getdata = readInputStream(input);

			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			FileOutputStream fos = new FileOutputStream(ClayTech.getInstance().getFile());
			fos.write(getdata);
			if (fos != null) {
				fos.close();
			}
			if (input != null) {
				input.close();
			}
			return saveDir + File.separator + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static String downloadFile(String urlp, String fileName, String savePath) {
		try {
			URL url = new URL(urlp);
			HttpURLConnection conne = (HttpURLConnection) url.openConnection();
			conne.setConnectTimeout(5 * 1000);
			conne.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			InputStream input = conne.getInputStream();
			byte[] getdata = readInputStream(input);

			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + File.separator + fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(getdata);
			if (fos != null) {
				fos.close();
			}
			if (input != null) {
				input.close();
			}
			return saveDir + File.separator + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int leng = 0;
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		while ((leng = inputStream.read(buffer)) != -1) {
			b.write(buffer, 0, leng);
		}
		b.close();
		return b.toByteArray();
	}
}
