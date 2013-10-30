package org.cocos2dx.plugin;

import java.util.Hashtable;
import org.cocos2dx.plugin.InterfaceSocial;
import org.cocos2dx.plugin.PluginWrapper;
import android.content.Context;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class ShareSDKPluginX implements InterfaceSocial {
	private static final String PLUGIN_VER = "1.0.0";
	private Context context;

	public ShareSDKPluginX(Context context) {
		this.context = context;
	}
	
	public void configDeveloperInfo(Hashtable<String, String> cpInfo) {
		
	}

	public void share(final Hashtable<String, String> cpInfo) {
		PluginWrapper.runOnMainThread(new Runnable() {
			public void run() {
				ShareSDK.initSDK(context);
				OnekeyShare oks = new OnekeyShare();
				
				if (cpInfo.containsKey("notifyIcon") && cpInfo.containsKey("notifyTitle")) {
					int icon = Integer.parseInt(cpInfo.get("notifyIcon"));
					String title = cpInfo.get("notifyTitle");
					oks.setNotification(icon, title);
				}
				if (cpInfo.containsKey("text")) {
					oks.setText(cpInfo.get("text"));
				}
				if (cpInfo.containsKey("imagePath")) {
					oks.setImagePath(cpInfo.get("imagePath"));
				}
				if (cpInfo.containsKey("filePath")) {
					oks.setFilePath(cpInfo.get("filePath"));
				}
				if (cpInfo.containsKey("title")) {
					oks.setTitle(cpInfo.get("title"));
				}
				if (cpInfo.containsKey("venueName")) {
					oks.setVenueName(cpInfo.get("venueName"));
				}
				if (cpInfo.containsKey("venueDescription")) {
					oks.setVenueDescription(cpInfo.get("venueDescription"));
				}
				if (cpInfo.containsKey("latitude")) {
					oks.setLatitude(Float.parseFloat(cpInfo.get("latitude")));
				}
				if (cpInfo.containsKey("longitude")) {
					oks.setLongitude(Float.parseFloat(cpInfo.get("longitude")));
				}
				if (cpInfo.containsKey("imageUrl")) {
					oks.setImageUrl(cpInfo.get("imageUrl"));
				}
				if (cpInfo.containsKey("comment")) {
					oks.setComment(cpInfo.get("comment"));
				}
				if (cpInfo.containsKey("titleUrl")) {
					oks.setTitleUrl(cpInfo.get("titleUrl"));
				}
				if (cpInfo.containsKey("url")) {
					oks.setUrl(cpInfo.get("url"));
				}
				if (cpInfo.containsKey("address")) {
					oks.setAddress(cpInfo.get("address"));
				}
				if (cpInfo.containsKey("site")) {
					oks.setSite(cpInfo.get("site"));
				}
				if (cpInfo.containsKey("siteUrl")) {
					oks.setSiteUrl(cpInfo.get("siteUrl"));
				}
				if (cpInfo.containsKey("musicUrl")) {
					oks.serMusicUrl(cpInfo.get("musicUrl"));
				}
				if (cpInfo.containsKey("silent")) {
					oks.setSilent(Boolean.parseBoolean(cpInfo.get("silent")));
				}
				if (cpInfo.containsKey("platform")) {
					oks.setPlatform(cpInfo.get("platform"));
				}
				if (cpInfo.containsKey("callback")) {
					try {
						String name = cpInfo.get("callback");
						Class<?> clz = Class.forName(name);
						PlatformActionListener pal = (PlatformActionListener) clz.newInstance();
						oks.setCallback(pal);
					} catch (Throwable t) {
						throw new RuntimeException(t);
					}
				}
				if (cpInfo.containsKey("customize")) {
					try {
						String name = cpInfo.get("customize");
						Class<?> clz = Class.forName(name);
						ShareContentCustomizeCallback ccc = (ShareContentCustomizeCallback) clz.newInstance();
						oks.setShareContentCustomizeCallback(ccc);
					} catch (Throwable t) {
						throw new RuntimeException(t);
					}
				}
				if (cpInfo.containsKey("dialogMode") && "true".equals(cpInfo.get("dialogMode"))) {
					oks.setDialogMode();
				}
				oks.show(context);
			}
		});
	}

	public void setDebugMode(boolean debug) {
		
	}

	public String getSDKVersion() {
		return ShareSDK.getSDKVersionName();
	}

	public String getPluginVersion() {
		return PLUGIN_VER;
	}

}
