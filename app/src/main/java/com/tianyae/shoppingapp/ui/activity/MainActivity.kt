package com.tianyae.shoppingapp.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.tianyae.baselibrary.common.AppManager
import com.tianyae.baselibrary.ui.activity.BaseActivity
import com.tianyae.shoppingapp.R
import com.tianyae.shoppingapp.ui.fragment.HomeFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.text.FieldPosition
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    private var pressTime: Long = 0

    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mBottomNavBar.checkMsgBadge(false)
//        mBottomNavBar.checkCartBag(0)
//
//        Observable.timer(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    mBottomNavBar.checkMsgBadge(true)
//                })
//
//        Observable.timer(5, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    mBottomNavBar.checkCartBag(2)
//                })

        initFragment()

//        initBottomNav()


    }


    /*
      初始化Fragment栈管理
   */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer, mHomeFragment)
        manager.commit()

//        mStack.add(mHomeFragment)
    }

    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {

            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
    }

    /*
        切换Tab，切换对应的Fragment
     */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }


    /*
       重写Back事件，双击退出
    */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }


}
