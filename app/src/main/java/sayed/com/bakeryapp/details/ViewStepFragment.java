package sayed.com.bakeryapp.details;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.model.Step;

/**
 * Created by Sayed on 10/18/2017.
 */

public class ViewStepFragment extends Fragment {
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    Bundle stepBundle;
    Step step;
    TextView stepDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_step_fragment, container, false);
        exoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exo_player_view);
        stepDescription = (TextView) rootView.findViewById(R.id.step_description);
        stepBundle = getArguments();
        step = (Step) stepBundle.getSerializable("step");
        setViews();

        return rootView;
    }

    private void setViews() {
        stepDescription.setText("");
        if (!step.getVideoURL().equals("")) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), "BakeryApp"), bandwidthMeter);
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);

            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(step.getVideoURL()),
                    dataSourceFactory, extractorsFactory, null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        } else {
            Toast.makeText(getContext(), "Video Is Not Avaliable ", Toast.LENGTH_LONG).show();
        }
        stepDescription.setText(step.getDescription());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      //  unbinder.unbind();
    }
}
