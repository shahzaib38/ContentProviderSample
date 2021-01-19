package image.crystalapps.contentprovidersample.ui.singlevideo.pager_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import image.crystalapps.contentprovidersample.BR
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.api.VideoApi
import image.crystalapps.contentprovidersample.common.PlaylistManager
import image.crystalapps.contentprovidersample.data.GalleryApp
import image.crystalapps.contentprovidersample.databinding.VideoSelectDataBinding
import image.crystalapps.contentprovidersample.entities.MediaItem
import image.crystalapps.contentprovidersample.entities.Samples
import image.crystalapps.contentprovidersample.entities.Video
import image.crystalapps.contentprovidersample.ui.base.BaseFragment
import image.crystalapps.contentprovidersample.ui.singlevideo.SingleVideoPlayerViewModel


@AndroidEntryPoint
class VideoFragmentSelect  : BaseFragment<SingleVideoPlayerViewModel , VideoSelectDataBinding>() {


//    VideoControlsSeekListener


    val mViewModel by  viewModels<SingleVideoPlayerViewModel>()

    var mVideoSelectDataBinding:VideoSelectDataBinding?=null


    override fun getBindingVariable(): Int =BR.viewModel
    override fun getLayoutId(): Int =R.layout.video_select_layout

    override fun getViewModel(): SingleVideoPlayerViewModel =mViewModel



    private lateinit var videoApi: VideoApi
    private lateinit var playlistManager: PlaylistManager
    private lateinit var captionsButton: AppCompatImageButton
    private val selectedIndex by lazy { requireActivity().intent.extras?.getInt(EXTRA_INDEX, 0) ?: 0 }

    companion object {
                const val EXTRA_INDEX = "EXTRA_INDEX"
        const val PLAYLIST_ID = 6 //Arbitrary, for the example (different from audio)

        private const val CC_GROUP_INDEX_MOD = 1000
        private const val CC_DISABLED = -1001
        private const val CC_DEFAULT = -1000
        private const val VIDEO = "video"

        fun newInstance(video: Video?): VideoFragmentSelect {
            val fragment = VideoFragmentSelect()
            val args = Bundle()
            args.putParcelable(VIDEO, video)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       mVideoSelectDataBinding= getViewDataBinding()





        val video=      arguments?.let { it.getParcelable<Video>(VIDEO) }


        if(video!=null){


          val thumbnail =  video.uriImage

            Glide.with(view).load(Uri.parse(thumbnail)).into(
                view.findViewById<ImageView>(R.id.imageView2))




            view.findViewById<ImageView>(R.id.imageView2).setOnClickListener {

                val viewIntent = Intent(Intent.ACTION_VIEW)
                viewIntent.setDataAndType(Uri.parse(thumbnail), "video/*")
                startActivity(Intent.createChooser(viewIntent, null))
            }

        }



//        init()

    }





    private fun init() {
//        setupPlaylistManager()
//
//        videoView.setHandleAudioFocus(false)
//        videoView.setAnalyticsListener(EventLogger(null))

//        setupClosedCaptions()

//        videoApi = VideoApi(videoView)
//        playlistManager.addVideoApi(videoApi)
//        playlistManager.play(0, false)

    }

//    private fun showCaptionsMenu() {
//        val availableTracks = videoView.availableTracks ?: return
//        val trackGroupArray = availableTracks[ExoMedia.RendererType.CLOSED_CAPTION]
//        if (trackGroupArray == null || trackGroupArray.isEmpty) {
//            return
//        }

//        val popupMenu = PopupMenu(requireContext(), captionsButton)
//        val menu = popupMenu.menu
//
//        // Add Menu Items
//        val disabledItem = menu.add(0, CC_DISABLED, 0, getString(R.string.disable)).apply {
//            isCheckable = true
//        }
//
//        val defaultItem = menu.add(0, CC_DEFAULT, 0, getString(R.string.auto)).apply {
//            isCheckable = true
//        }
//
//        var selected = false
//        for (groupIndex in 0 until trackGroupArray.length) {
//            val selectedIndex = videoView.getSelectedTrackIndex(ExoMedia.RendererType.CLOSED_CAPTION, groupIndex)
//            Log.d("Captions", "Selected Caption Track: $groupIndex | $selectedIndex")
//            val trackGroup = trackGroupArray.get(groupIndex)
//            for (index in 0 until trackGroup.length) {
//                val format = trackGroup.getFormat(index)
//
//                // Skip over non text formats.
//                if (!format.sampleMimeType!!.startsWith("text")) {
//                    continue
//                }
//
//                val title = format.label ?: format.language ?: "${groupIndex.toShort()}:$index"
//                val itemId = groupIndex * CC_GROUP_INDEX_MOD + index
//                val item = menu.add(0, itemId, 0, title)
//
//                item.isCheckable = true
//                if (index == selectedIndex) {
//                    item.isChecked = true
//                    selected = true
//                }
//            }
//        }
//
//        if (!selected) {
//            if (videoView.isRendererEnabled(ExoMedia.RendererType.CLOSED_CAPTION)) {
//                defaultItem.isChecked = true
//            } else {
//                disabledItem.isChecked = true
//            }
//        }
//
//        menu.setGroupCheckable(0, true, true)
//        popupMenu.setOnMenuItemClickListener { menuItem -> onTrackSelected(menuItem) }
//        popupMenu.show()
//    }
//
//
//
//    private fun setupClosedCaptions() {
//        captionsButton = AppCompatImageButton(requireContext()).apply {
//            setBackgroundResource(android.R.color.transparent)
//            setImageResource(R.drawable.ic_launcher_foreground)
//            setOnClickListener { showCaptionsMenu()
//
//            }
//        }
//
//        (videoView.videoControlsCore as? VideoControls)?.let {
//            it.setSeekListener(this)
//            if (videoView.trackSelectionAvailable()) {
//                it.addExtraView(captionsButton)
//            }
//        }
//
//        videoView.setOnVideoSizedChangedListener { intrinsicWidth, intrinsicHeight, pixelWidthHeightRatio ->
//            val videoAspectRatio: Float = if (intrinsicWidth == 0 || intrinsicHeight == 0) {
//                1f
//            } else {
//                intrinsicWidth * pixelWidthHeightRatio / intrinsicHeight
//            }
//
//            subtitleFrameLayout.setAspectRatio(videoAspectRatio)
//        }
//
//        videoView.setCaptionListener(subtitleView)
//    }

    /**
     * Retrieves the playlist instance and performs any generation
     * of content if it hasn't already been performed.
     */
    private fun setupPlaylistManager() {
        playlistManager = (requireActivity().applicationContext as GalleryApp).playlistManager

        val mediaItems = Samples.getVideoSamples().map {

            println("media Url ${it.mediaUrl}")
            MediaItem(it, false)
        }

        playlistManager.setParameters(mediaItems, selectedIndex)
        playlistManager.id = PLAYLIST_ID.toLong()
    }


    override fun onStop() {
        super.onStop()
//        playlistManager.removeVideoApi(videoApi)
//        playlistManager.invokeStop()
    }

    override fun onDestroy() {
        super.onDestroy()
//        playlistManager.invokeStop()
    }






//
//    private fun onTrackSelected(menuItem: MenuItem): Boolean {
//        menuItem.isChecked = true
//        val itemId = menuItem.itemId
//
//        when (itemId) {
//            CC_DEFAULT -> videoView.clearSelectedTracks(ExoMedia.RendererType.CLOSED_CAPTION)
//            CC_DISABLED -> videoView.setRendererEnabled(ExoMedia.RendererType.CLOSED_CAPTION, false)
//            else -> {
//                val trackIndex = itemId % CC_GROUP_INDEX_MOD
//                val groupIndex = itemId / CC_GROUP_INDEX_MOD
//                videoView.setTrack(ExoMedia.RendererType.CLOSED_CAPTION, groupIndex, trackIndex)
//            }
//        }
//
//        return true
//    }

//    override fun onSeekStarted(): Boolean {
//        playlistManager.invokeSeekStarted()
//        return true
//    }
//
//    override fun onSeekEnded(seekTime: Long): Boolean {
//        playlistManager.invokeSeekEnded(seekTime)
//        return true
//    }
//



}