package com.coal.profileapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.coal.profileapp.R
import com.coal.profileapp.dataModel.model.User
import java.text.SimpleDateFormat
import java.util.*


class SwipableAdapter(val mUser: ArrayList<User>, val mContext : Context)
    : RecyclerView.Adapter<SwipableAdapter.ViewHolder>(){

    private val TAG = "SwipableAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipableAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.card_layout,parent,false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int  = mUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(mContext)
            .load(mUser.get(position).picture)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_launcher)
            .into(holder.profileImageView)


        holder.userInfo.setOnClickListener {
            setClickItem(OPERATION.INFO, holder.userInfo, holder,position)
        }

        holder.dateOfBirth.setOnClickListener {
            setClickItem(OPERATION.CALENDER, holder.dateOfBirth, holder,position)
        }

        holder.location.setOnClickListener {
            setClickItem(OPERATION.LOCATION, holder.location, holder,position)
        }

        holder.phone.setOnClickListener {
            setClickItem(OPERATION.PHONE, holder.phone, holder,position)
        }


        holder.security.setOnClickListener {
            setClickItem(OPERATION.LOCK, holder.security, holder,position)
        }

        setClickItem(OPERATION.LOCATION, holder.location, holder, position)

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val userInfo : ImageView = view.findViewById(R.id.info)
        var dateOfBirth: ImageView = view.findViewById(R.id.dateOfBirth)
        var phone: ImageView = view.findViewById(R.id.phone)
        var security: ImageView = view.findViewById(R.id.security)
        var location: ImageView = view.findViewById(R.id.location)
        var profileImageView: ImageView = view.findViewById(R.id.profileImageView)

        var nameAgeTxt: TextView = view.findViewById(R.id.nameAgeTxt)
        var locationNameTxt: TextView = view.findViewById(R.id.locationNameTxt)
    }

    fun setClickItem(
        operation: OPERATION,
        imageView: ImageView,
        holder: ViewHolder,
        position: Int
    ){

        holder.userInfo.setImageResource(R.drawable.ic_info_outline_light_grey)
        holder.dateOfBirth.setImageResource(R.drawable.ic_perm_contact_calendar_light_grey)
        holder.location.setImageResource(R.drawable.ic_map_light_grey)
        holder.phone.setImageResource(R.drawable.ic_phone_light_grey)
        holder.security.setImageResource(R.drawable.ic_lock_outline_grey)

        when(operation){
            OPERATION.INFO -> { imageView.setImageResource(R.drawable.ic_info_outline_light_green)
                doInfoOperation(holder,position)
            }

            OPERATION.CALENDER ->{ imageView.setImageResource(R.drawable.ic_perm_contact_calendar_light_green)
                doCalenderOperation(holder,position)
            }

            OPERATION.LOCATION -> { imageView.setImageResource(R.drawable.ic_map_light_green)
                doLocationOperation(holder,position)
            }

            OPERATION.PHONE -> { imageView.setImageResource(R.drawable.ic_phone_light_green)
                doPhoneOperation(holder,position)
            }

            OPERATION.LOCK -> { imageView.setImageResource(R.drawable.ic_lock_outline_light_green)
                doLockOperation(holder,position)
            }
        }
    }
    fun doLockOperation(holder: ViewHolder,position: Int) {
        holder.nameAgeTxt.setText(mContext.getString(R.string.my_email_info))
        holder.locationNameTxt.setText(mUser.get(position).email)
    }

    fun doPhoneOperation(holder: ViewHolder,position: Int) {
        holder.nameAgeTxt.setText(mContext.getString(R.string.my_contact_info))
        holder.locationNameTxt.setText("${mUser.get(position).cell} - ${mUser.get(position).phone}")}

    fun doLocationOperation(holder: ViewHolder,position: Int) {
        holder.nameAgeTxt.setText(mContext.getString(R.string.my_address_info))
        holder.locationNameTxt.setText("${mUser.get(position).location.street.capitalize()} , ${mUser.get(position).location.city.capitalize()} , ${mUser.get(position).location.state.capitalize() } , ${mUser.get(position).location.zip}") }

    fun doCalenderOperation(holder: ViewHolder,position: Int) {
        holder.nameAgeTxt.setText(mContext.getString(R.string.my_dob_info))
        val date = Date(mUser.get(position).dob)
        val dateFormat = SimpleDateFormat(mContext.getString(R.string.date_format))
        val formattedDate = dateFormat.format(date);
        holder.locationNameTxt.setText(formattedDate.toString())
    }

    fun doInfoOperation(holder: ViewHolder,position: Int) {
        holder.nameAgeTxt.setText(mContext.getString(R.string.my_name))
        holder.locationNameTxt.setText("${mUser.get(position).name.title.capitalize()} ${mUser.get(position).name.first.capitalize()} ${mUser.get(position).name.last.capitalize() }")
    }


    enum class OPERATION{
        INFO,
        CALENDER,
        LOCATION,
        PHONE,
        LOCK
    }
}