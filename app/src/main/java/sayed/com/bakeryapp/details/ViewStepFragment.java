package sayed.com.bakeryapp.details;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.listener.StepItemClickListener;
import sayed.com.bakeryapp.model.Step;

/**
 * Created by Sayed on 10/18/2017.
 */

public class ViewStepFragment extends Fragment  {
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    Bundle stepBundle;
    Step step;
    TextView stepDescription, errorText;
    ImageView thumbImage;
    Uri videoUri;
    DataSource.Factory dataSourceFactory;
    long position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_step_fragment, container, false);
        exoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exo_player_view);
        stepDescription = (TextView) rootView.findViewById(R.id.step_description);
        errorText = (TextView) rootView.findViewById(R.id.error_text_message);
        thumbImage = (ImageView) rootView.findViewById(R.id.thumb_id);
        stepBundle = getArguments();
        step = (Step) stepBundle.getSerializable("step");
        videoUri = Uri.parse(step.getVideoURL());

        position = C.TIME_UNSET;
        if (savedInstanceState != null) {
            position = savedInstanceState.getLong("position", C.TIME_UNSET);

        }
        setViews();
        if (!step.getVideoURL().equals("")) {
            initializePlayer(videoUri);

        } else {
            Toast.makeText(getContext(), "Video Is Not Avaliable ", Toast.LENGTH_LONG).show();
        }
        exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    private void setViews() {

        if (!step.getThumbnailURL().equals("")) {
            errorText.setVisibility(View.GONE);
            Picasso.with(getContext()).load(step.getThumbnailURL()).into(thumbImage);

        }
        stepDescription.setText(step.getDescription());
    }

    private void initializePlayer(Uri videoUri) {
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "BakeryApp"), bandwidthMeter);
        exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
        exoPlayerView.setPlayer(exoPlayer);

        MediaSource mediaSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);
        if (position != C.TIME_UNSET) exoPlayer.seekTo(position);

        exoPlayer.prepare(mediaSource);
    }







    @Override
    public void onPause() {

        if (exoPlayer != null) {
            position = exoPlayer.getCurrentPosition();
            exoPlayer.stop();
            exoPlayer.release();
           exoPlayer = null;
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            position = exoPlayer.getCurrentPosition();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("position", position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  unbinder.unbind();
    }



}
