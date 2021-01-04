package image.crystalapps.contentprovidersample.ui.mainactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.databinding.MainActivityDataBinding
import image.crystalapps.contentprovidersample.extensions.setupWithNavController
import image.crystalapps.contentprovidersample.listerner.OnItemClickListener
import image.crystalapps.contentprovidersample.ui.singleimage.SingleImageActivity
import image.crystalapps.contentprovidersample.ui.base.BaseActivity
import image.crystalapps.contentprovidersample.ui.settings.SettingsActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, MainActivityDataBinding>() ,OnItemClickListener<Uri> {


    private val mViewModel by viewModels<MainViewModel>()
    private var currentNavController: LiveData<NavController>? = null
    private  var mainActivityDataBinding:MainActivityDataBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityDataBinding = getViewDataBinding()
//        val toolbar  =findViewById<Toolbar>(R.id.toolbar)
//        toolbar.title = resources.getString(R.string.images)
//        setSupportActionBar(toolbar)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setupBottomNavigationBar() }

    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mViewModel

    override fun clickItem(view: View, item: Uri) {
        val activityOption = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair(
                view.findViewById<ImageView>(R.id.singleImageView),
                SingleImageActivity.IMAGE_HEADER))
        val intent = Intent(this, SingleImageActivity::class.java)
        intent.putExtra("parcel", item.toString())
        ActivityCompat.startActivity(this, intent, activityOption.toBundle())
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        val navGraphIds = listOf(R.navigation.photo_nav, R.navigation.albums_nav ,R.navigation.video_nav)
        val navGraphIds = listOf(R.navigation.photo_nav ,R.navigation.video_nav)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        currentNavController = controller }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false }



//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//
//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        return super.onPrepareOptionsMenu(menu) }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean =when(item.itemId) {
//
//        R.id.settings->{
//            startActivity(Intent(this ,SettingsActivity::class.java))
//
//            true }
//
//
//     else->   super.onOptionsItemSelected(item)
//    }
}






