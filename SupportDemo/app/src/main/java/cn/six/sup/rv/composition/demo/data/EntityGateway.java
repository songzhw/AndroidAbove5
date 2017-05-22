package cn.six.sup.rv.composition.demo.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.rv.composition.BaseRow;

public class EntityGateway {

    public List<IEntity> getHomeData(){
        List<IEntity> list = new ArrayList<>();
        try {
            JSONObject raw = new JSONObject(data);
            JSONArray payload = raw.getJSONArray("data");

            int size = payload.length();
            for(int i = 0 ; i < size; i++) {
                JSONObject item = payload.getJSONObject(i);

                int type = item.getInt("type");
                if(BaseRow.TYPE_HEADER == type){
                    list.add(new EntityHeader(item.getString("title"), item.getString("caption")));
                } else if (BaseRow.TYPE_TWO_TEXT == type){
                    list.add(new EntityTwo(item.getString("left"), item.getString("right")));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private String data = "{\n" +
            "  \"retCode\": \"200\",\n" +
            "  \"retDesc\": \"succ\",\n" +
            "  \"data\": [\n" +
            "    {\"type\":4,\"left\":\"holder\", \"right\":\"jian\"},\n" +
            "    {\"type\":3, \"title\":\"fangdong\",\"caption\":\"fangke\"},\n" +
            "    {\"type\":3, \"title\":\"laoshi\",\"caption\":\"xuesheng\"},\n" +
            "    {\"type\":4,\"left\":\"feng\", \"right\":\"cha\"},\n" +
            "    {\"type\":3, \"title\":\"shuangyang\",\"caption\":\"song\"},\n" +
            "    {\"type\":3, \"title\":\"ganlu\",\"caption\":\"sang\"},\n" +
            "    {\"type\":3, \"title\":\"tianshui\",\"caption\":\"sang\"},\n" +
            "    {\"type\":3, \"title\":\"zhamei\",\"caption\":\"song\"}\n" +
            "  ]\n" +
            "}";
}
