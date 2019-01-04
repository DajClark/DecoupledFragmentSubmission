package com.example.decoupledfragmentsubmission;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.Date;

public class GameEditFragment extends Fragment {

    // Key to pass game type using bundle.
    final static String EXTRA_GAME = "game";

    Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Calls onCreate methods from super class.
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            game = (Game) args.getSerializable(EXTRA_GAME);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the fragment view using the XML layout file.
        View view = inflater.inflate(R.layout.game_edit_fragment, container, false);

        // Initialises view elements using the id's within the XML layout file.
        final EditText gameTitle = view.findViewById(R.id.gameTitle);
        final EditText gamePlatform = view.findViewById(R.id.gamePlatform);
        final EditText gameDescription = view.findViewById(R.id.gameDescription);
        final Button dateButton = view.findViewById(R.id.completedDate);
        final CheckBox isComplete = view.findViewById(R.id.gameComplete);

        // Sets the edit box text to the games details.
        gameTitle.setText(game.getTitle());
        gamePlatform.setText(game.getPlatform());
        gameDescription.setText(game.getDescription());

        if (game.isComplete()) {
            dateButton.setText(game.getDateComplete().toString());
        }

        isComplete.setChecked(game.isComplete());

        gameTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        gamePlatform.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game.setPlatform(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        gameDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        // Initialise listener for checkbox on checked change.
        isComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Declares new date and updates view based on checkbox status.
                if (isChecked) {
                    Date date = new Date();
                    dateButton.setText(date.toString());

                    game.setComplete(true);
                    game.setDateComplete(date);
                } else {
                    dateButton.setText("");
                    game.setComplete(false);
                }
            }
        });

        return view;
    }


}

