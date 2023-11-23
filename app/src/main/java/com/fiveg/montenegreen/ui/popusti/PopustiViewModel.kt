package com.fiveg.montenegreen.ui.popusti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.models.PopustModel

class PopustiViewModel : ViewModel() {
    private val _popusti = MutableLiveData<ArrayList<PopustModel>>()
    val popusti: LiveData<ArrayList<PopustModel>> = _popusti

    fun loadPopusti() {
        _popusti.postValue(
            arrayListOf(
                PopustModel("Popust 1", "location 1", 40, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit tristique euismod. Aenean pharetra, lacus eget vulputate tincidunt, justo massa accumsan nisi, at blandit tellus dui fermentum enim. Donec quis dignissim enim. In porta venenatis dolor at sagittis. Etiam iaculis nisl massa, ac placerat erat tincidunt et. Nulla quis cursus tellus. Nam vel lectus quis arcu luctus auctor.", "http://192.168.1.52:8000/storage/2023/11/23/7c4e67970ae8aa307c99131c7042dd5fc11255fb.jpg"),
                PopustModel("Popust 2", "location 2", 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit tristique euismod. Aenean pharetra, lacus eget vulputate tincidunt, justo massa accumsan nisi, at blandit tellus dui fermentum enim. Donec quis dignissim enim. In porta venenatis dolor at sagittis. Etiam iaculis nisl massa, ac placerat erat tincidunt et. Nulla quis cursus tellus. Nam vel lectus quis arcu luctus auctor.", "http://192.168.1.52:8000/storage/2023/11/23/b517e64b36a5e62dc9db24070ea19f72b99ad578.jpg"),
                PopustModel("Popust 3", "location 3", 70, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit tristique euismod. Aenean pharetra, lacus eget vulputate tincidunt, justo massa accumsan nisi, at blandit tellus dui fermentum enim. Donec quis dignissim enim. In porta venenatis dolor at sagittis. Etiam iaculis nisl massa, ac placerat erat tincidunt et. Nulla quis cursus tellus. Nam vel lectus quis arcu luctus auctor.", "http://192.168.1.52:8000/storage/2023/11/23/65eb6709586fd926da09c85d812d6d60ccc50afb.jpg"),
            )
        )
    }
}