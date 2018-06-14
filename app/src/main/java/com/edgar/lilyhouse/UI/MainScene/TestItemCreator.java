package com.edgar.lilyhouse.UI.MainScene;

import com.edgar.lilyhouse.Data.MainSceneData.MangaItem;

import java.util.ArrayList;
import java.util.List;

public class TestItemCreator {
//    "id": 42435,
//    "name": "利维兹家的家庭教师",
//    "zone": "日本",
//    "status": "连载中",
//    "last_update_chapter_name": "第07话下",
//    "last_update_chapter_id": 73597,
//    "last_updatetime": 1517220131,
//    "hidden": 0,
//    "cover": "webpic/13/liweizijiadejiatingjiaoshi.jpg",
//    "first_letter": "l",
//    "comic_py": "liweicijiadejiatingjiaoshi",
//    "authors": "山座",
//    "types": "冒险/魔法/百合",
//    "readergroup": "青年漫画",
//    "copyright": 0,
//    "hot_hits": 2419184,
//    "app_click_count": 2420238,
//    "num": "4839422"

    public List<MangaItem> getCreatedItems(int maxNum) {

        ArrayList<MangaItem> listItems = new ArrayList<>();

        for (int i = 0; i < maxNum; i++) {

            int id = i;
            String name = "利维兹家的家庭教师" + i;
            String zone = "日本";
            String status = "连载中";
            String last_update_chapter_name = "第07话下";
            int last_update_chapter_id = 73597;
            long last_updatetime = 1517220131;
            int hidden = 0;
            String cover = "webpic/13/liweizijiadejiatingjiaoshi.jpg";
            String first_letter = "l";
            String comic_py = "liweicijiadejiatingjiaoshi";
            String authors = "山座";
            String types = "冒险/魔法/百合";
            String readergroup = "青年漫画";
            int copyright = 0;
            int hot_hits = 2419184;
            int app_click_count = 2420238;
            String num = "4839422";

            MangaItem listItem = new MangaItem(id, name, zone, status, last_update_chapter_name, last_update_chapter_id,
                    last_updatetime, hidden, cover, first_letter, comic_py, authors, types, readergroup, copyright,
                    hot_hits, app_click_count, num, 0);
            listItems.add(listItem);


        }
        return listItems;


    }

}
