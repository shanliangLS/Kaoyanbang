package hehut.scse.kaoyanbang.TabFragment.Player.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hehut.scse.kaoyanbang.R;
import hehut.scse.kaoyanbang.TabFragment.Player.PlayerDetail;

public class PlayerFragment1 extends Fragment {
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment1, container, false);

        button = (Button) view.findViewById(R.id.play_button);
        Button.OnClickListener buttonListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.play_button:
                        startActivity(new Intent(getActivity(), PlayerDetail.class));
                        return;
                }
            }
        };
        button.setOnClickListener(buttonListener);

        return view;
    }
}
