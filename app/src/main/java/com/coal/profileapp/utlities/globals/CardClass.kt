package com.coal.profileapp.utlities.globals
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.alexzh.circleimageview.CircleImageView
import com.alexzh.circleimageview.ItemSelectedListener
import com.bumptech.glide.Glide
import com.coal.profileapp.R
import com.coal.profileapp.dataModel.local.entity.UserEntity
import com.coal.profileapp.dataModel.model.User
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.NonReusable
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

@NonReusable
@Layout(R.layout.card_layout)
class CardClass(
    val mContext: Context,
    val mUser: User,
    val mSwipeView: SwipePlaceHolderView,
    val liveData: MutableLiveData<String>,
    val swipeRightLiveData: MutableLiveData<UserEntity>
) {

    val TAG  = "CardClass"

    @View(R.id.profileImageView)
    lateinit var  profileImageView: CircleImageView

    @View(R.id.nameAgeTxt)
    lateinit var nameAgeTxt: TextView

    @View(R.id.locationNameTxt)
    lateinit var locationNameTxt: TextView


    @View(R.id.info)
    lateinit var userInfo: ImageView

    @View(R.id.dateOfBirth)
    lateinit var dateOfBirth: ImageView

    @View(R.id.location)
    lateinit var location: ImageView

    @View(R.id.phone)
    lateinit var phone: ImageView

    @View(R.id.security)
    lateinit var security: ImageView


    lateinit var  mBitMap : Bitmap

    @Resolve
    fun onResolved() {

        try{
            if(mUser.isOffline){

//Meme cache issue  hence commenting glide and using setImageBitMap

/*                Glide.with(mContext)
                    .asBitmap()
                    .thumbnail(
                        Glide.with(mContext)
                            .asBitmap()
                            .load(R.drawable.ic_launcher)
                            .thumbnail(0.5f)
                    )
                    .load(mUser.bitmap)
                    .into(profileImageView)*/

                profileImageView.setImageBitmap(mUser.bitmap)

            }else{
                Glide.with(mContext)
                    .load(mUser.picture)
                    .into(profileImageView)
            }

            setClickItem(OPERATION.LOCATION,location)

            userInfo.setOnClickListener {
                setClickItem(OPERATION.INFO,userInfo)
            }

            dateOfBirth.setOnClickListener {
                setClickItem(OPERATION.CALENDER,dateOfBirth)
            }

            location.setOnClickListener {
                setClickItem(OPERATION.LOCATION,location)
            }

            phone.setOnClickListener {
                setClickItem(OPERATION.PHONE,phone)
            }


            security.setOnClickListener {
                setClickItem(OPERATION.LOCK,security)
            }

            profileImageView.setOnItemSelectedClickListener(object : ItemSelectedListener{
                override fun onSelected(view: android.view.View?) {
                    AppLogger.e(TAG,"Do nothing")

                }

                override fun onUnselected(view: android.view.View?) {
                    AppLogger.e(TAG,"Do nothing")
                }
            })

        }catch (e: RuntimeException){

        }
    }

    @SwipeOut
    fun onSwipedOut() {
        AppLogger.d("EVENT", "onSwipedOut")
        mSwipeView.addView(this)
        liveData.postValue("out")
    }

    @SwipeCancelState
     fun onSwipeCancelState() {
        AppLogger.d("EVENT", "onSwipeCancelState")
        liveData.postValue("cancel")

    }

    @SwipeIn
     fun onSwipeIn() {
        AppLogger.d("EVENT", "onSwipedIn")

        if (NetworkConnectivity(context = mContext).isNetworkConnected())
        {
            try {
                if(profileImageView.getDrawable() == null){
                    mBitMap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_launcher)
                }else{
                    mBitMap  = (profileImageView.getDrawable() as BitmapDrawable).bitmap
                }

                val baos = ByteArrayOutputStream()
                mBitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

                val userEntity = UserEntity(
                    gender = mUser.gender,
                    title = mUser.name.title,
                    first = mUser.name.first,
                    last = mUser.name.last,
                    street = mUser.location.street,
                    city = mUser.location.city,
                    state = mUser.location.state,
                    zip = mUser.location.zip,
                    email = mUser.email,
                    username = "",
                    dob =mUser.dob,
                    phone = mUser.phone,
                    cell = mUser.phone,
                    image =   baos.toByteArray()
                )
                swipeRightLiveData.postValue(userEntity)
            }catch (e : RuntimeException){
            }
        }
        liveData.postValue("in")
    }
    @SwipeInState
     fun onSwipeInState() {
        AppLogger.d("EVENT", "onSwipeInState")
        liveData.postValue("inStat")

    }

    @SwipeOutState
     fun onSwipeOutState() {
        AppLogger.d("EVENT", "onSwipeOutState")
    }

    fun setClickItem(operation: OPERATION, imageView: ImageView){

        userInfo.setImageResource(R.drawable.ic_info_outline_light_grey)
        dateOfBirth.setImageResource(R.drawable.ic_perm_contact_calendar_light_grey)
        location.setImageResource(R.drawable.ic_map_light_grey)
        phone.setImageResource(R.drawable.ic_phone_light_grey)
        security.setImageResource(R.drawable.ic_lock_outline_grey)

        when(operation){
            OPERATION.INFO -> { imageView.setImageResource(R.drawable.ic_info_outline_light_green)
                doInfoOperation()
            }

            OPERATION.CALENDER ->{ imageView.setImageResource(R.drawable.ic_perm_contact_calendar_light_green)
            doCalenderOperation()
            }

            OPERATION.LOCATION -> { imageView.setImageResource(R.drawable.ic_map_light_green)
            doLocationOperation()
            }

            OPERATION.PHONE -> { imageView.setImageResource(R.drawable.ic_phone_light_green)
            doPhoneOperation()
            }

            OPERATION.LOCK -> { imageView.setImageResource(R.drawable.ic_lock_outline_light_green)
            doLockOperation()
            }
        }
    }


     fun doLockOperation() {
        nameAgeTxt!!.setText(mContext.getString(R.string.my_email_info))
        locationNameTxt!!.setText(mUser.email)
    }

     fun doPhoneOperation() {
        nameAgeTxt!!.setText(mContext.getString(R.string.my_contact_info))
        locationNameTxt!!.setText("${mUser.cell} - ${mUser.phone}")}

     fun doLocationOperation() {
        nameAgeTxt!!.setText(mContext.getString(R.string.my_address_info))
        locationNameTxt!!.setText("${mUser.location.street.capitalize()} , ${mUser.location.city.capitalize()} , ${mUser.location.state.capitalize() } , ${mUser.location.zip}") }

     fun doCalenderOperation() {
        nameAgeTxt!!.setText(mContext.getString(R.string.my_dob_info))
        val date = Date(mUser.dob)
        val dateFormat = SimpleDateFormat(mContext.getString(R.string.date_format))
        val formattedDate = dateFormat!!.format(date);
        locationNameTxt!!.setText(formattedDate.toString())
    }

    fun doInfoOperation(){
        nameAgeTxt!!.setText(mContext.getString(R.string.my_name))
        locationNameTxt!!.setText("${mUser.name.title.capitalize()} ${mUser.name.first.capitalize()} ${mUser.name.last.capitalize() }")
    }

    enum class OPERATION{
        INFO,
        CALENDER,
        LOCATION,
        PHONE,
        LOCK
    }

}