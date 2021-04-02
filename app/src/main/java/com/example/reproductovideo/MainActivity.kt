package com.example.reproductovideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.MediaController
import android.widget.VideoView
import com.example.reproductovideo.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

var arraylist = ArrayList<String>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arraylist.add("")
        arraylist.add("Noticias Fin de Semana")
        arraylist.add("Belinda")
        val binding = ActivityMainBinding.inflate( layoutInflater )

        setContentView( binding.root );

        /** Media Controller */
        val mediaController = MediaController(this)

        /** TextView */
        val title = binding.textTitle

        /** Buttons */
        val buttonVideo1 = binding.buttonVideo1
        val buttonVideo2 = binding.buttonVideo2
        val buttonVideo3 = binding.buttonVideo3
        val buttonPlay = binding.buttonPlay
        val buttonStop = binding.buttonStop
        var isPlay=false

        /** Video View */
        val videoView = binding.videoView
        //var videoFile1: Uri = Uri.parse("https://www.youtube.com/watch?v=5rSGGz296Hg")
        var videoFile1: Uri = Uri.parse("https://www.videvo.net/videvo_files/converted/2016_01/preview/Forest_15_3b_Videvo.mov47209.webm")
        //val videoFile1: Uri = Uri.parse( "android.resource://$packageName/${ R.raw.video1 }" )
        val videoFile2: Uri = Uri.parse( "android.resource://$packageName/${ R.raw.video2 }" )
        val videoFile3: Uri = Uri.parse( "android.resource://$packageName/${ R.raw.video3 }" )

        videoView.setMediaController( mediaController )

        buttonPlay.setOnClickListener {
            isPlay=true

        }
        buttonStop.setOnClickListener{
            isPlay=false
        }


        buttonVideo1.setOnClickListener  {

            // findViewById(R.id.nombreUrl)
            //EditText editText = (EditText)
            val edittext: EditText =  findViewById(R.id.nombreUrl)
            val message:String=edittext.text.toString()
            videoFile1= Uri.parse(""+message)

            //String obj = binding.nombreUrl.editText.

            //val videoFile1: Uri = Uri.parse(""+edittext)
            binding.textTitle.text=message
            //binding.textTitle.text=arraylist[0]+" Pulsa play"
            //arraylist[0]=message
            buttonPlay.setOnClickListener{

                //videoFile1= Uri.parse(""+message)

                var urlInput: TextInputLayout = findViewById(R.id.txtInputUrl)
                var urlText=urlInput.editText!!.text

                var urlValidado = isUrlValidate(urlText)

                urlInput.error = if(!urlValidado) "Debe ser una url Valida" else null

                if(urlValidado){
                    //val edittext:EditText=  findViewById(R.id.nombreUrl)
                    //val message:String=edittext.text.toString()
                    //videoFile1= Uri.parse(""+message)

                    videoPlayer( videoView, videoFile1, mediaController ,0,binding)
                }



                //videoPlayer2( videoView, videoFile1, mediaController)
            }
            buttonStop.setOnClickListener{
                videoStop(videoView)
            }



        }

        buttonVideo2.setOnClickListener {
            binding.textTitle.text=arraylist[1]+" Pulsa play"
            buttonPlay.setOnClickListener{
                videoPlayer( videoView, videoFile2, mediaController ,1,binding)
            }
            buttonStop.setOnClickListener{
                videoStop(videoView)
            }

        }

        buttonVideo3.setOnClickListener {

            binding.textTitle.text=arraylist[2]+" Pulsa play"
            buttonPlay.setOnClickListener {
                videoPlayer(videoView, videoFile3, mediaController, 2, binding)
            }
            buttonStop.setOnClickListener{
                videoStop(videoView)
            }
        }

    }

    private  fun videoStop(videoView: VideoView){
        if ( videoView.isPlaying )
        { videoView.stopPlayback(); }
    }


    private fun videoPlayer(videoView: VideoView, uri: Uri, mediaController: MediaController, int: Int, binding:ActivityMainBinding) {


        //binding.textTitle.text=arraylist[int]+" Pulsa play"

        //stopPlayback()



        if ( videoView.isPlaying )
        { videoView.pause();
            var playButton: MaterialButton = findViewById(R.id.buttonPlay)
            playButton.setIconResource(R.drawable.ic_not_started_24px)
        }else{
            var playButton: MaterialButton = findViewById(R.id.buttonPlay)
            playButton.setIconResource(R.drawable.ic_baseline_play_arrow_24)
            videoView.setVideoURI( uri )

            binding.textTitle.text=uri.toString()
            mediaController.setAnchorView( videoView )

            videoView.requestFocus()

            videoView.start()


        }


    }

    private fun isUrlValidate(urlText: Editable): Boolean {
        return urlText.isNotEmpty()

    }
}