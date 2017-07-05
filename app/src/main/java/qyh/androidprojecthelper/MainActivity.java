package qyh.androidprojecthelper;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

import qyh.androidprojecthelper.base.BaseActivity;
import qyh.androidprojecthelper.bean.TabEntity;
import qyh.androidprojecthelper.fragment.FirstTabFragment;

import qyh.androidprojecthelper.fragment.SecondTabFragment;
import qyh.androidprojecthelper.fragment.ThirdTabFragment;

/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **                       / \\\|||  :  |||// \\                        **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                         **
 * ************************************************************************
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles = {"页面1", "页面2", "页面3", "页面4"};
    private int[] mIconUnselectIds = {R.mipmap.n_shouye, R.mipmap.n_faxian, R.mipmap.n_geren, R.mipmap.n_geren};
    private int[] mIconSelectIds = {R.mipmap.y_shouye, R.mipmap.y_faxian, R.mipmap.y_geren, R.mipmap.y_geren};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private FirstTabFragment firstTabFragment;
    private SecondTabFragment secondTabFragment;
    private ThirdTabFragment thirdTabFragment;
    private ThirdTabFragment fourTabFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        initTab();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    //初始化碎片
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (null == savedInstanceState) {
            firstTabFragment = new FirstTabFragment();
            secondTabFragment = new SecondTabFragment();
            thirdTabFragment = new ThirdTabFragment();
            fourTabFragment = new ThirdTabFragment();

            fragmentTransaction.add(R.id.fl_body, firstTabFragment, "firstTabFragment");
            fragmentTransaction.add(R.id.fl_body, secondTabFragment, "secondTabFragment");
            fragmentTransaction.add(R.id.fl_body, thirdTabFragment, "thirdTabFragment");
            fragmentTransaction.add(R.id.fl_body, fourTabFragment, "fourTabFragment");
        } else {
            firstTabFragment = (FirstTabFragment) getSupportFragmentManager().findFragmentByTag("firstTabFragment");
            secondTabFragment = (SecondTabFragment) getSupportFragmentManager().findFragmentByTag("secondTabFragment");
            thirdTabFragment = (ThirdTabFragment) getSupportFragmentManager().findFragmentByTag("thirdTabFragment");
            thirdTabFragment = (ThirdTabFragment) getSupportFragmentManager().findFragmentByTag("fourTabFragment");
        }
        fragmentTransaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                transaction.show(firstTabFragment);
                transaction.hide(secondTabFragment);
                transaction.hide(thirdTabFragment);
                transaction.hide(fourTabFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(secondTabFragment);
                transaction.hide(firstTabFragment);
                transaction.hide(thirdTabFragment);
                transaction.hide(fourTabFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.show(thirdTabFragment);
                transaction.hide(secondTabFragment);
                transaction.hide(firstTabFragment);
                transaction.hide(fourTabFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.show(fourTabFragment);
                transaction.hide(thirdTabFragment);
                transaction.hide(secondTabFragment);
                transaction.hide(firstTabFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //1 显示tabLayout的角标 显示未读数
        tabLayout.showMsg(1, 88);
        tabLayout.setMsgMargin(4, 0, 10);
        //隐藏消息
        //tabLayout.hideMsg(1);
    }
}
