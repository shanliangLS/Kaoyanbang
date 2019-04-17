package hehut.scse.kaoyanbang.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

//    /**
//     * 解析和处理服务器返回的省级数据
//     */
//    public static boolean handleProvinceResponse(String response) {
//        if (!TextUtils.isEmpty(response)) {
//            try {
//                JSONArray allProvinces = new JSONArray(response);
//                for (int i = 0; i < allProvinces.length(); i++) {
//                    JSONObject provinceObject = allProvinces.getJSONObject(i);
//                    Province province = new Province();
//                    province.setProvinceName(provinceObject.getString("name"));
//                    province.setProvinceCode(provinceObject.getInt("id"));
//                    province.save();
//                }
//                return true;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }


}
