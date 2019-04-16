package hehut.scse.kaoyanbang.TabFragment.Practice.Util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class DownLoadFile {

	public static String getFile(String fileurl, String outPath) {
		// ���ϳ������Ĺ��ͼƬ��ŵ�ַĿ¼
//		String urlDownload = fileurl;
		// ���ͼƬ����sd�����Ŀ¼
//		String dirName = Environment.getExternalStorageDirectory().toString()+ "/Dxsbang/";
		// �жϱ��ش�Ź��ͼƬ��Ŀ¼�Ƿ�棬���������½��ļ���
		File f = new File(outPath);
		if (!f.exists()) {
			f.mkdir();
		}
		// �������Ϲ��ͼƬ����·��������������
		String urlDownloadAll = fileurl;
		// //��ȡ���ͼƬ�����֣���ʵʹ��advert.getUrl()Ҳ��
		String newFilename = urlDownloadAll.substring(urlDownloadAll
				.lastIndexOf("/") + 1);
		// ���ز��Ź��ͼƬ������·��
		newFilename = outPath +"/"+ newFilename;
//		Log.w("down", "url is " + fileurl);

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
