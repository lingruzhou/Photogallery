package com.examples.photogalleryapp.adapter

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.examples.photogalleryapp.R
import com.examples.photogalleryapp.data.model.Image
import com.examples.photogalleryapp.databinding.RecImageItemBinding
import com.examples.photogalleryapp.listener.OnClickListener

class ImagesPageAdapter : PagingDataAdapter<Image, ImagesPageAdapter.ImagesViewHolder>(IMAGES_COMPARATOR) {

    lateinit var clickListener: OnClickListener

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = getItem(position)

        val imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image?.id.toString())
        val inputStream = holder.itemView.context.contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val thumbnail = ThumbnailUtils.extractThumbnail(bitmap, 100, 100)  // 100x100 is an example size for the thumbnail

        // Rotate the thumbnail based on the orientation
        val orientation = image?.orientation ?: 0
        val matrix = Matrix()
        matrix.postRotate(orientation.toFloat())
        val rotatedThumbnail = Bitmap.createBitmap(thumbnail, 0, 0, thumbnail.width, thumbnail.height, matrix, true)

        holder.binding.imageViewImage.setImageBitmap(rotatedThumbnail)

        holder.binding.root.setOnClickListener {
            clickListener.onClickItem(image?.id ?: 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = RecImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    class ImagesViewHolder(val binding: RecImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val IMAGES_COMPARATOR = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }
        }
    }
}
