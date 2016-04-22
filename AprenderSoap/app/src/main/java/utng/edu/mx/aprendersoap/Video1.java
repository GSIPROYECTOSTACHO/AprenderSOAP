package utng.edu.mx.aprendersoap;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video1);

        VideoView videoView =(VideoView)findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri= Uri.parse("rtsp://r5---sn-vgqs7nez.googlevideo.com/Cj0LENy73wIaNAkv94HCR8uGOxMYDSANFC1UTwdXMOCoAUIASARgremL8eDM1P1WigELeDB4dHBLX3puWE0M/872444B57D1F116B33A8F888ECCAEBB710E0A822.A8D5D7D92A2116778FB99757349F853D2AE252BE/yt6/1/video.3gp");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}
