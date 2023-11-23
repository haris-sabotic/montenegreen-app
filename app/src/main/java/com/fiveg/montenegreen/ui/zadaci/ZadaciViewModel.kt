package com.fiveg.montenegreen.ui.zadaci

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.models.ZadatakModel

class ZadaciViewModel : ViewModel() {
    private val _zadaci = MutableLiveData<ArrayList<ZadatakModel>>()
    val zadaci: LiveData<ArrayList<ZadatakModel>> = _zadaci

    fun loadZadaci() {
        _zadaci.postValue(
            arrayListOf(
                ZadatakModel("Zadatak 1", "location 1", 50, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit tristique euismod. Aenean pharetra, lacus eget vulputate tincidunt, justo massa accumsan nisi, at blandit tellus dui fermentum enim. Donec quis dignissim enim. In porta venenatis dolor at sagittis. Etiam iaculis nisl massa, ac placerat erat tincidunt et. Nulla quis cursus tellus. Nam vel lectus quis arcu luctus auctor.", "http://192.168.1.52:8000/storage/2023/11/22/0a02d5562e3540501a9d9ffe813f409f7faa84b9.jpg"),
                ZadatakModel("Zadatak 2", "location 2", 45, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit tristique euismod. Aenean pharetra, lacus eget vulputate tincidunt, justo massa accumsan nisi, at blandit tellus dui fermentum enim. Donec quis dignissim enim. In porta venenatis dolor at sagittis. Etiam iaculis nisl massa, ac placerat erat tincidunt et. Nulla quis cursus tellus. Nam vel lectus quis arcu luctus auctor.", "http://192.168.1.52:8000/storage/2023/11/23/cc04833c122785df25e432392999fd499094b350.jpg"),
                ZadatakModel("Zadatak 3", "location 3", 80, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit tristique euismod. Aenean pharetra, lacus eget vulputate tincidunt, justo massa accumsan nisi, at blandit tellus dui fermentum enim. Donec quis dignissim enim. In porta venenatis dolor at sagittis. Etiam iaculis nisl massa, ac placerat erat tincidunt et. Nulla quis cursus tellus. Nam vel lectus quis arcu luctus auctor.", "http://192.168.1.52:8000/storage/2023/11/23/8914383333567183ebc3440f04ed49f81c28650c.jpg"),
            )
        )
    }
}