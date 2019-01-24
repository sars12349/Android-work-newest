package tw.tcnr21.m0607;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M0504 extends ExpandableListActivity {

    private static final String ITEM_NAME = "Item Name";
    private static final String ITEM_SUBNAME = "Item Subname";
    private TextView txtAns01;
    private String b_itemname, b_subitemname, b_txtdesc, b_txtsubdesc;
    private ExpandableListAdapter mExpaListAdap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0504);
        setupViewComponent();
    }

    private void setupViewComponent() {
        txtAns01 = (TextView) findViewById(R.id.m0504_t001); // 取得輸出答案的R.ID
        b_itemname = getString(R.string.m0504_titem);// 選項群組
        b_subitemname = getString(R.string.m0504_tsubitem); // 子選項
        b_txtdesc = getString(R.string.m0504_tdesc); // 選項群組說明
        b_txtsubdesc = getString(R.string.m0504_tsubitem); // 子選項說明

        //宣告 list 內容 使用陣列 Map
        List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childList2D = new ArrayList<List<Map<String, String>>>();
        //---第一層------------------
        for (int i = 0; i < 6; i++) {
            Map<String, String> group = new HashMap<String, String>();
            group.put(ITEM_NAME, b_itemname + i);
            group.put(ITEM_SUBNAME, b_txtdesc + i);
            groupList.add(group);
            //---------------第二層-----------------------
            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            for (int j = 0; j < 2; j++) {
                Map<String, String> child = new HashMap<String, String>();
                child.put(ITEM_NAME, b_subitemname + i + j);
                child.put(ITEM_SUBNAME, b_txtsubdesc + i + j);
                childList.add(child);
            }
            childList2D.add(childList);
            //---------第二層 end-----------
        }
        //--第一層 end----------------------

        // 設定 expandablelistview adapter
        mExpaListAdap = new SimpleExpandableListAdapter(this, groupList,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{ITEM_NAME, ITEM_SUBNAME}, new int[]{android.R.id.text1, android.R.id.text2},

                childList2D, android.R.layout.simple_expandable_list_item_2,
                new String[]{ITEM_NAME, ITEM_SUBNAME},
                new int[]{android.R.id.text1, android.R.id.text2});

        setListAdapter(mExpaListAdap);



    }

    //// ======== ExpandableListView按入監聽 ======


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String b_txtans = getString(R.string.m0504_t001); // 選擇結果:
        String ans = b_txtans + getString(R.string.m0504_titem) + groupPosition + ","
                + getString(R.string.m0504_tsubitem) + childPosition + ",ID:" + id;
        txtAns01.setText(ans);

        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }
}

