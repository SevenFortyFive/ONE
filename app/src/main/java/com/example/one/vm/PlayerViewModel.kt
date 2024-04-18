import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.one.data.playerdata.AudioData
import com.example.one.data.playerdata.PlayerState
import com.example.one.data.playerdata.getAudioList
import com.example.one.player.ExoPlayerManager
import kotlinx.coroutines.launch


class PlayerViewModel(): ViewModel() {
    private var player: ExoPlayer = ExoPlayerManager.getExoPlayer()!!

    private var datalist = getAudioList()

    private var _currentAudioData: MutableLiveData<AudioData> = MutableLiveData(datalist[0])
    val currentAudioData: LiveData<AudioData>
        get() = _currentAudioData

    private var _currentState: MutableLiveData<PlayerState> = MutableLiveData(PlayerState.STOP)

    val currentState: LiveData<PlayerState>
        get() = _currentState

    // 上一首歌的名字
    private var _preName:MutableLiveData<String> = MutableLiveData(datalist[getPreIdx(player.currentMediaItemIndex)].name)
    val preName: LiveData<String>
        get() = _preName

    // 下一首歌的名字
    private var _nextName:MutableLiveData<String> = MutableLiveData(datalist[getNextIdx(player.currentMediaItemIndex)].name)
    val nextName: LiveData<String>
        get() = _nextName

    init {
        _currentState.postValue(PlayerState.LOADING)
        viewModelScope.launch {
            datalist.forEach {
                val mediaItem = MediaItem.fromUri(Uri.parse(it.uri))
                player.addMediaItem(mediaItem)
            }
        }
        player.repeatMode
        _currentState.postValue(PlayerState.STOP)
    }

    fun start() {
        viewModelScope.launch {
            Log.d("player", player.currentMediaItemIndex.toString())
            _currentState.postValue(PlayerState.PLAYING)
            player.prepare()
            player.play()
        }
    }

    fun pause() {
        _currentState.postValue(PlayerState.STOP)
        player.pause()
    }

    fun nextAudio() {
        this.player.seekToDefaultPosition(getNextIdx(player.currentMediaItemIndex))
        this.onDataChanged()
        this.start()
    }

    fun preAudio() {
        this.player.seekToDefaultPosition(getPreIdx(player.currentMediaItemIndex))
        this.onDataChanged()
        this.start()
    }

    private fun onDataChanged(){
        _preName.postValue(datalist[getPreIdx(player.currentMediaItemIndex)].name)
        _nextName.postValue(datalist[getNextIdx(player.currentMediaItemIndex)].name)
        _currentAudioData.postValue(datalist[player.currentMediaItemIndex])
    }

    /**
     * 从当前索引得到前一个索引
     */
    private fun getPreIdx(currentIdx: Int): Int {
        return if (currentIdx == 0)
            this.datalist.size - 1
        else {
            player.currentMediaItemIndex - 1
        }
    }

    /**
     * 从当前索引得到下一个索引
     */
    private fun getNextIdx(currentIdx: Int): Int {
        return if (currentIdx == datalist.size - 1) {
            0
        } else {
            player.currentMediaItemIndex + 1
        }
    }
}