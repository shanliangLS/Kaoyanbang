package hehut.scse.kaoyanbang.TabFragment.Practice.Util;

import android.app.ProgressDialog;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpDownFile {

	public static String getPrintFile(String fileurl, ProgressDialog pd) {
		// ���ϳ������Ĺ��ͼƬ��ŵ�ַĿ¼
		String urlDownload = fileurl;
		// ���ͼƬ����sd�����Ŀ¼
		String dirName = Environment.getExternalStorageDirectory().toString()+ "/Dxsbang";
		// �жϱ��ش�Ź��ͼƬ��Ŀ¼�Ƿ�棬���������½��ļ���
		File f = new File(dirName);
		if (!f.exists()) {
			f.mkdir();
		}
		// �������Ϲ��ͼƬ����·��������������
		String urlDownloadAll = urlDownload;
		// //��ȡ���ͼƬ�����֣���ʵʹ��advert.getUrl()Ҳ��
		String newFilename = urlDownloadAll.substring(urlDownloadAll
				.lastIndexOf("/") + 1);
		// ���ز��Ź��ͼƬ������·��
		newFilename = dirName +"/"+ newFilename;
		Log.w("down", "url is " + urlDownload);

		File file = new File(newFilename);
		// ���Ŀ���ļ��Ѿ����ڣ���ɾ�����������Ǿ��ļ���Ч�����˴��Ժ������չΪ�Ѿ�����ͼƬ�����������ع��ܣ�
		if (file.exists()) {
			return "EXIST";
		}
		try {
			String furl = new URI(fileurl).toASCIIString();
			URL url = new URL(furl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			// ��ȡ���ļ��Ĵ�С
			pd.setMax(con.getContentLength() / 1024);
			pd.setCanceledOnTouchOutside(false);
			InputStream is = con.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			// 1K�����ݻ���
			byte[] bs = new byte[1024];
			// ��ȡ�������ݳ���
			int len;
			int total = 0;
			// ��ʼ��ȡ
			while ((len = bis.read(bs)) != -1) {
				fos.write(bs, 0, len);
				total += len;
				pd.setProgress(total / 1024);
				pd.setProgressNumberFormat("%1d kb/%2d kb");
				// pd.dismiss();
			}
			// ��ϣ��ر���������
			fos.close();
			is.close();
			return newFilename;
		} catch (Exception e) {
			e.printStackTrace();

		}
		// pd.dismiss();
		return "FAIL";
	}
}

/*private void getPrintFile(String fileurl,ProgressDialog pd) {
	// ���ϳ������Ĺ��ͼƬ��ŵ�ַĿ¼
	String urlDownload = fileurl;
	// ���ͼƬ����sd�����Ŀ¼
	String dirName = Environment.getExternalStorageDirectory().toString()
			+ "/Dxsbang/";// "/sdcard/Dxsbang/";
	// �жϱ��ش�Ź��ͼƬ��Ŀ¼�Ƿ�棬���������½��ļ���
	File f = new File(dirName);
	if (!f.exists()) {
		f.mkdir();
	}
	// �������Ϲ��ͼƬ����·��������������
	String urlDownloadAll = urlDownload;
	// //��ȡ���ͼƬ�����֣���ʵʹ��advert.getUrl()Ҳ��
	String newFilename = urlDownloadAll.substring(urlDownloadAll
			.lastIndexOf("/") + 1);
	// ���ز��Ź��ͼƬ������·��
	newFilename = dirName + newFilename;
	Log.d("ppt", "The local url is" + newFilename);

	File file = new File(newFilename);
	// ���Ŀ���ļ��Ѿ����ڣ���ɾ�����������Ǿ��ļ���Ч�����˴��Ժ������չΪ�Ѿ�����ͼƬ�����������ع��ܣ�
	if (file.exists()) {
		file.delete();
	}
	try {
		// ����URL
		URL url = new URL(urlDownloadAll);
		// ������
		URLConnection con = url.openConnection();
		// ����ļ��ĳ���
		int contentLength = con.getContentLength();
		pd.setMax(contentLength);
		System.out.println("���� :" + contentLength);
		// ������
		InputStream is = con.getInputStream();
		// 1K�����ݻ���
		byte[] bs = new byte[1024];
		// ��ȡ�������ݳ���
		int len;
		int total=0;
		// ������ļ���
		OutputStream fos = new FileOutputStream(newFilename);
		// ��ʼ��ȡ
		while ((len = is.read(bs)) != -1) {
			fos.write(bs, 0, len);
			total+=len;
			pd.setProgress(total);
		}
		// ��ϣ��ر���������
		fos.close();
		is.close();

	} catch (Exception e) {
		e.printStackTrace();

	}
	}*/

/*//�Դ�����������
	private void download(String url2) {
		String url = url2;
		DownloadManager.Request request = new DownloadManager.Request(
				Uri.parse(url));
		request.setDescription("Some descrition");
		request.setTitle("Some title");
		// ���Ϊ���������У��������android3.2�������Ӧ�ó���
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			request.allowScanningByMediaScanner();
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		}
		request.setDestinationInExternalPublicDir(
				Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");
		// ������ط���Ͷ����ļ�
		DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		manager.enqueue(request);
	}

	public static boolean isDownloadManagerAvailable(Context context) {
		try {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
				return false;
			}
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setClassName("com.android.providers.downloads.ui",
					"com.android.providers.downloads.ui.DownloadList");
			List<ResolveInfo> list = context.getPackageManager()
					.queryIntentActivities(intent,
							PackageManager.MATCH_DEFAULT_ONLY);
			return list.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}*/