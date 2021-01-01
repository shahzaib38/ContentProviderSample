package image.crystalapps.contentprovidersample.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel


val TAG = BaseDialogFragment::class.simpleName
abstract class BaseDialogFragment<VM : ViewModel,VDB : ViewDataBinding> : DialogFragment() {

    private var mActivity: BaseActivity<*, *>? = null
    private var mViewDataBinding :VDB?=null
    private var mRootView: View? = null
    private  var mViewModel :VM? =null


    abstract fun getBindingVariable(): Int
    abstract fun getViewModel(): VM?
    @LayoutRes
    abstract fun getLayoutId(): Int

    fun getViewDataBinding(): VDB? {
        return mViewDataBinding
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding=  DataBindingUtil.inflate(inflater,getLayoutId(),container, false)
        mViewDataBinding?.run {
            println("OnViewCreated")

            setVariable(getBindingVariable(), mViewModel)
            lifecycleOwner = this@BaseDialogFragment
            executePendingBindings()

        }
        return mViewDataBinding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel=getViewModel()
        println("OnCreate")
    }




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        //if (context != null) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }


        dialog.setCanceledOnTouchOutside(false)
        return dialog


    }

    fun isInternetAvailable() :Boolean{
        val connectivityManager= requireActivity().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true; }
            }
        }

        return false }









    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is BaseActivity<* ,*>){
            mActivity  =context as BaseActivity<*,*>
        }
    }



    fun  getBaseActivity():BaseActivity<*,*>?{
        return mActivity
    }





    open fun showDialog(fragment : FragmentManager){
        val fragmentTransaction =
            requireFragmentManager().beginTransaction()
        val fragmentByTag = requireFragmentManager().findFragmentByTag(tag)
        if (fragmentByTag != null) {
            fragmentTransaction.remove(fragmentByTag) }
        fragmentTransaction.addToBackStack(null)
        show(fragment ,TAG)
    }


    open fun dismissDialog(tag :String?){ dismiss() }










}

