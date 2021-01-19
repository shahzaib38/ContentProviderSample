package image.crystalapps.contentprovidersample.view

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.cursoradapter.widget.CursorAdapter
import image.crystalapps.contentprovidersample.R
import image.crystalapps.contentprovidersample.data.contentprovider.HistoryContract
import image.crystalapps.contentprovidersample.viewHolder.CursorSearchAdapter


class MaterialSearchView @JvmOverloads constructor(  val mContext :Context , val  attributeSet: AttributeSet?=null ,val defStyleAttributeSet: Int=0)  : FrameLayout(mContext,attributeSet ) {



    companion object{


        private val LOG_TAG =MaterialSearchView::class.java


        //Compile time Initialization
        private const val MAX_RESULTS=1    // initlaze at compile time and only initialize value not method  , create only one copy

        //Compile Time Initialization
        const val  REQUEST_VOICE=42       // initialize at compile time and only initilize value ,create only one copy

        // Can be reassigned
        private var MAX_HISTORY =2
          private  val  EMPTY_STRING=""



        fun setMaxHistory(maxhistory :Int){
            MAX_HISTORY =maxhistory
        }
    }

    // Initialized with Constructor
    init {


        init()

        initStyle(attributeSet)


    }


   private fun initStyle(attributeSet: AttributeSet?){
       AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

       val typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.MaterialSearchView, defStyleAttributeSet, 0)

       if(typedArray.hasValue(R.styleable.MaterialSearchView_searchBackground)){
           background = typedArray.getDrawable(R.styleable.MaterialSearchView_searchBackground) }

       if(typedArray.hasValue(R.styleable.MaterialSearchView_android_textColor)){
             setTextColor(typedArray.getColor(R.styleable.MaterialSearchView_android_textColor,
                 ContextCompat.getColor(mContext, R.color.black))) }


       if (typedArray.hasValue(R.styleable.MaterialSearchView_android_textColorHint)) {
           setHintTextColor(typedArray.getColor(R.styleable.MaterialSearchView_android_textColorHint,
               ContextCompat.getColor(mContext, R.color.gray_50)))
       }
       if (typedArray.hasValue(R.styleable.MaterialSearchView_android_hint)) {
           setHint(typedArray.getString(R.styleable.MaterialSearchView_android_hint).toString())
       }
       if (typedArray.hasValue(R.styleable.MaterialSearchView_searchVoiceIcon)) {
           setVoiceIcon(typedArray.getResourceId(
               R.styleable.MaterialSearchView_searchVoiceIcon,
               R.drawable.ic_action_voice_search)
           )
       }
       if (typedArray.hasValue(R.styleable.MaterialSearchView_searchCloseIcon)) {
           setClearIcon(typedArray.getResourceId(
               R.styleable.MaterialSearchView_searchCloseIcon,
               R.drawable.ic_action_navigation_close)
           )
       }
       if (typedArray.hasValue(R.styleable.MaterialSearchView_searchBackIcon)) {
           setBackIcon(typedArray.getResourceId(
               R.styleable.MaterialSearchView_searchBackIcon,
               R.drawable.ic_action_navigation_arrow_back)
           )
       }
       if (typedArray.hasValue(R.styleable.MaterialSearchView_searchSuggestionBackground)) {
           setSuggestionBackground(typedArray.getResourceId(
               R.styleable.MaterialSearchView_searchSuggestionBackground,
               R.color.search_layover_bg)
           )
       }

       // Searchbar Height
       if (typedArray.hasValue(R.styleable.MaterialSearchView_searchBarHeight)) {
           setSearchBarHeight(typedArray.getDimensionPixelSize(R.styleable.MaterialSearchView_searchBarHeight, appCompatActionBarHeight))
       } else {
           setSearchBarHeight(appCompatActionBarHeight)
       }

       if(typedArray.hasValue(R.styleable.MaterialSearchView_voiceHintPrompt)){

           setVoiceHintPrompt(typedArray.getString(R.styleable.MaterialSearchView_voiceHintPrompt)?: EMPTY_STRING)

       }

       typedArray.recycle()

    }


    var isOpen =false
    private  set

    private var mShouldAnimate =true

    private var mShouldCLosedOntintClick = false

    private var mClearingFocus=false

    private lateinit var mHintPrompt:String


    var isVoiceEnabled =true


    private lateinit var mTintView: View


    private lateinit var mRoot:FrameLayout

    private lateinit var mSearchBar :LinearLayout

    private lateinit var mSearchEditText:EditText

    private lateinit var mBack :ImageButton

    private lateinit var mVoice:ImageButton

    private  lateinit var mClear :ImageButton

    private  lateinit var mSuggestionListView:ListView


    // adapter for displaying adapter
    lateinit var adapter :CursorAdapter

    private lateinit var mOldQuery :CharSequence

    private  lateinit var mCurrentQuery:CharSequence

    private  var mOnQueryTextListener :OnQueryTextListener?=null

    private  var mSearchViewListener :SearchViewListener?=null

    private var mVoiceClickedListener :OnVoiceClickedListener?=null
    private lateinit var mSuggestionsListView: ListView

    private var mShouldKeppHistory :Boolean =true


    // Listeners

    interface OnQueryTextListener{

        fun onQueryTextSubmit(query :String):Boolean

        fun onQueryTextChange(newText :String):Boolean
    }


    interface SearchViewListener{

        fun onSearchViewOpened()

        fun onSearchViewClosed() }



     fun setOnQueryListener(mOnQueryTextListener: OnQueryTextListener){
         this.mOnQueryTextListener=mOnQueryTextListener

     }


   interface OnVoiceClickedListener{
       fun onVoiceClicked() }



    //initiliazting setter and getter


    fun setOnVoiceClickedListener(listener :OnVoiceClickedListener){
        mVoiceClickedListener =listener }


    fun setOnVoiceClickedListener(listner :()->Unit){
        mVoiceClickedListener =object : OnVoiceClickedListener{
            override fun onVoiceClicked() {
                listner.invoke()
            }


        } }




    fun setOnItemClickListener(listener : AdapterView.OnItemClickListener){
        mSuggestionsListView.onItemClickListener =listener }



    fun setOnItemLongClickListener(listener  : AdapterView.OnItemLongClickListener){
        mSuggestionsListView.onItemLongClickListener =listener }

    fun getSuggestionAtPosition(position :Int):String {
        return if (position < 0 || position >= adapter.count) {
            EMPTY_STRING
        } else {
            adapter.getItem(position).toString()
        }

    }

     fun setBackIcon(resourceId: Int) {
         mBack.setImageResource(resourceId)
     }


    fun setVoiceHintPrompt(hintPrompt :String){
        mHintPrompt =if(!TextUtils.isEmpty(hintPrompt)){
            hintPrompt
        }else{
            mContext.getString(R.string.hint_prompt
            )
        }



    }




    fun setTextColor(color:Int){
        mSearchEditText.setTextColor(color) }

    fun setHintTextColor(color:Int){
     mSearchEditText.setHintTextColor(color)

    }

    fun setClearIcon(resourceId: Int){
        mClear.setImageResource(resourceId) }


    fun setVoiceIcon(resourceId :Int){

        mVoice.setImageResource(resourceId)

    }



    fun setSearchBarColor(color:Int){

        mSearchEditText.setBackgroundColor(color)

    }

    fun setHint(hint :CharSequence){
        mSearchEditText.hint=hint
    }



fun init(){

    LayoutInflater.from(mContext).inflate(R.layout.search_view ,this,true)

    mRoot=findViewById(R.id.search_layout)
    mTintView =mRoot.findViewById(R.id.transparent_view)
    mSearchBar =mRoot.findViewById(R.id.search_bar)
    mVoice =mRoot.findViewById(R.id.action_voice)
    mClear =mRoot.findViewById(R.id.action_clear)
    mSearchEditText = mRoot.findViewById(R.id.et_search)
    mSuggestionListView =mRoot.findViewById(R.id.suggestion_list)
    mBack = mRoot.findViewById(R.id.action_back)
    mSuggestionsListView = mRoot.findViewById(R.id.suggestion_list)
    mBack.setOnClickListener {  }
    mVoice.setOnClickListener { onVoiceClicked() }
    mClear.setOnClickListener {  mSearchEditText.setText(EMPTY_STRING) }
    mTintView.setOnClickListener {

        if(mShouldCLosedOntintClick){
            closeSearch()

        }


    }



initSearchView()


    adapter =CursorSearchAdapter(mContext,historyCursor,0)


    adapter.setFilterQueryProvider {constraint->
     val filter=   constraint.toString()

        if(filter.isEmpty()){
            historyCursor

        }else{

            mContext.contentResolver.query(
                HistoryContract.HistoryEntry.CONTENT_URI,
                null,
                HistoryContract.HistoryEntry.COLUMN_QUERY + " LIKE ?", arrayOf("%$filter%"),
                HistoryContract.HistoryEntry.COLUMN_IS_HISTORY + " DESC, " +
                        HistoryContract.HistoryEntry.COLUMN_QUERY
            )


        }

    }

    mSuggestionsListView.adapter = adapter
    mSuggestionsListView.isTextFilterEnabled = true
}






    fun closeSearch(){

        if(!isOpen){
            return }

        mSearchEditText.setText(EMPTY_STRING)




    }





    fun setQuery(query :CharSequence? , submit :Boolean){
        mSearchEditText.setText(query)

        if(query!=null){
            
            mSearchEditText.setSelection(mSearchEditText.length())

        }

        if(submit  &&  !TextUtils.isEmpty(query)){
            onSubmitQuery()
        }





    }




  private   fun initSearchView(){

      mSearchEditText.setOnEditorActionListener{v,actionId,event->

          onSubmitQuery()
          true
      }


      mSearchEditText.addTextChangedListener(object:TextWatcher{
          override fun afterTextChanged(s: Editable?) {

          }

          override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

              if(s!=null) {
                  adapter.filter.filter(s.toString())
                  adapter.notifyDataSetChanged()
                  this@MaterialSearchView.onTextChanged(s)
              }
              }

          override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


          }


      })

      mSearchEditText.setOnFocusChangeListener { v, hasFocus ->

          if(hasFocus){

          }


      }


     }





     private fun onTextChanged(newText: CharSequence){

     mCurrentQuery =    mSearchEditText.text

         if(!TextUtils.isEmpty(mCurrentQuery)){

             displayVoiceButton(false)
             displayClearButton(true)


         }else{
             displayVoiceButton(true)
             displayClearButton(false)
         }

         mOnQueryTextListener?.onQueryTextChange(newText.toString())
     }


     private fun onSubmitQuery(){

      val query :CharSequence  = mSearchEditText.text


         if(query!=null && TextUtils.getTrimmedLength(query)>0){


             if(mOnQueryTextListener?.onQueryTextSubmit(query.toString())==false){

                 if(mShouldKeppHistory) {
                     saveQueryToDb(query.toString(), System.currentTimeMillis()) }

                 closeSearch()

             }
         }



     }



    fun setSuggestionBackground(resourceId: Int){
        if(resourceId>0){
            mSuggestionsListView.setBackgroundColor(resourceId) }

    }

    private fun dismissSuggestions(){

       // mSuggestionsListView.visibility=


    }

    @Synchronized
    fun saveQueryToDb(query: String?, ms: Long) {
        if (!TextUtils.isEmpty(query) && ms > 0) {
            val values = ContentValues()
            values.put(HistoryContract.HistoryEntry.COLUMN_QUERY, query)
            values.put(HistoryContract.HistoryEntry.COLUMN_INSERT_DATE, ms)
            values.put(HistoryContract.HistoryEntry.COLUMN_IS_HISTORY, 1) // Saving as history.
            mContext.contentResolver.insert(HistoryContract.HistoryEntry.CONTENT_URI, values)
        }
    }

    override fun setBackgroundColor(color: Int) {
        setTintColor(color)
    }

    fun setTintColor(color:Int){
        mTintView.setBackgroundColor(color) }



    // change Height of SearchBar

    fun setSearchBarHeight(height :Int){
        mSearchBar.minimumHeight=height
        mSearchBar.layoutParams.height =height
    }


    private    fun onVoiceClicked(){

        if(mVoiceClickedListener!=null) {

            mVoiceClickedListener!!.onVoiceClicked()
        }else{

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, mHintPrompt)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULTS) // Quantity of results we want to receive
            if (mContext is Activity) {
                mContext.startActivityForResult(intent, REQUEST_VOICE)
            }


        }

    }


     private fun displayVoiceButton(display: Boolean){

         if(display && isVoiceEnabled && isVoiceAvailable  ){

             mVoice.visibility= View.VISIBLE
         }else{
             mVoice.visibility=View.GONE

         }


     }

     private val appCompatActionBarHeight: Int
         get() {
             val tv = TypedValue()
             context.theme.resolveAttribute(R.attr.actionBarSize, tv, true)
             return resources.getDimensionPixelSize(tv.resourceId)
         }

     private fun displayClearButton(display:Boolean){
         mClear.visibility=if(display) View.VISIBLE else View.GONE }


    private fun hideKeyboard(view:View){
        val inputMethodManager =view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }


     private val isHardKeyboardAvailable : Boolean get() = mContext.resources.configuration.keyboard!=Configuration.KEYBOARD_NOKEYS


     private val isVoiceAvailable :Boolean get(){

         val packageManager = mContext.packageManager

         val activities =packageManager.queryIntentActivities(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),0)

         return activities.size>0


     }


  fun  setTintAlpha(alpha :Int){
        if(alpha < 0  || alpha>255) {
            throw IllegalArgumentException("Int must be greater than 0 and less then 255")
        }


      val d =mTintView.background
      if(d is ColorDrawable){

          val color =d.color
          val newColor = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))
          setTintColor(newColor)

      }


    }


    fun setShouldKeepHistory(keepHistory :Boolean){
        mShouldKeppHistory =keepHistory }

    private val historyCursor: Cursor?
        get() = mContext.contentResolver.query(
            HistoryContract.HistoryEntry.CONTENT_URI,
            null,
            HistoryContract.HistoryEntry.COLUMN_IS_HISTORY + " = ?", arrayOf("1"),
            HistoryContract.HistoryEntry.COLUMN_INSERT_DATE + " DESC LIMIT " + MAX_HISTORY
        )


 }