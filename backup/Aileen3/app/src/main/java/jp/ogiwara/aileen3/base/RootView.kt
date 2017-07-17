package jp.ogiwara.aileen3.base

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import jp.ogiwara.aileen3.R
import jp.ogiwara.aileen3.topchart.TopChartFragment
import org.jetbrains.anko.find
import trikita.anvil.DSL.xml
import trikita.anvil.RenderableView

//状態管理はAndroid側
//サイクル間は結合度が高くてもよい
class RootView(val activity: AppCompatActivity) : RenderableView(activity){

    //TODO xml使わないでやってみる -> 後回し! -> ランタイム側がついてこれてない
    override fun view() {
        xml(R.layout.activity_main){

            val toolBar = find<Toolbar>(jp.ogiwara.aileen3.R.id.toolbar)
            val tabLayout = find<TabLayout>(jp.ogiwara.aileen3.R.id.tab_layout)
            val viewpager = find<ViewPager>(jp.ogiwara.aileen3.R.id.view_pager)

            viewpager.adapter = ViewPagerAdapter(activity.supportFragmentManager)
            viewpager.offscreenPageLimit = 4//キャッシュ

            tabLayout.setupWithViewPager(viewpager)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab) {
                    //TODO TabTitleの変更
                    toolBar.title = tab.toString()
                }
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            })
            activity.setSupportActionBar(toolBar)
        }
    }

    //region non-xml
    /*override fun view(){
        var viewPager: ViewPager? = null //Dirty...
        var toolBar: Toolbar? = null
        var tabLayout: TabLayout? = null

        coordinatorLayout {
            id = jp.ogiwara.aileen3.R.id.coordinator
            size(FILL,FILL)
            appBarLayout {
                id = jp.ogiwara.aileen3.R.id.app_bar

                size(FILL,WRAP)
                toolbar {
                    id = jp.ogiwara.aileen3.R.id.toolbar

                    toolBar = Anvil.currentView<Toolbar>()

                    size(FILL,android.R.attr.actionBarSize)
                    elevation = dip(5f)
                }

                tabLayout {
                    id = jp.ogiwara.aileen3.R.id.tab_layout

                    tabLayout = Anvil.currentView<TabLayout>()

                    tabGravity(FILL)
                    elevation = dip(5f)
                    size(FILL, WRAP)

                    onTabSelected(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            toolBar?.title = "WWW"
                        }

                        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                    })
                }
            }

            viewPager {
                id = jp.ogiwara.aileen3.R.id.view_pager

                viewPager = Anvil.currentView<ViewPager>()

                size(FILL,WRAP)
            }
        }

        viewPager?.adapter = ViewPagerAdapter(activity.supportFragmentManager)
        viewPager?.offscreenPageLimit = 4

        tabLayout?.setupWithViewPager(viewPager)
        activity.setSupportActionBar(toolBar)
    }*/
    //endregion

    private class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> TopChartFragment()
                else -> TopChartFragment()
            }
            //TODO
        }

        override fun getCount() = 4
    }
}