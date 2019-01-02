package com.example.decoupledfragmentsubmission;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.Date;

public class GameEditFragment extends Fragment {

    // Key to pass game type using bundle.
    final static String EXTRA_GAME = "game";

    // Variable to store completion date upon checkbox check.
    private Date newCompleteDate = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Calls onCreate methods from super class.
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the fragment view using the XML layout file.
        View view = inflater.inflate(R.layout.game_edit_fragment, container, false);

        // Initialises view elements using corresponding ID's from the XML layout file.
        final Button dateButton = view.findViewById(R.id.completedDate);
        final CheckBox isComplete = view.findViewById(R.id.gameComplete);

        // Initialise listener for checkbox on checked change.
        isComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                // Declares new date and updates view based on checkbox status.
                if (isChecked) {
                    newCompleteDate = new Date();
                    dateButton.setText(newCompleteDate.toString());
                } else {
                    dateButton.setText("");
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        // Calls onStart required super methods.
        super.onStart();

        // Retrieves the game object from the bundle passed by the activity.
        Bundle args = getArguments();
        if (args != null) {
            updateGameView((Game) args.getSerializable(EXTRA_GAME));
        }
    }

    public void updateGameView(Game game) {

        // Initialises view elements using the id's within the XML layout file.
        final EditText gameTitle = getActivity().findViewById(R.id.gameTitle);
        final EditText gamePlatform = getActivity().findViewById(R.id.gamePlatform);
        final EditText gameDescription = getActivity().findViewById(R.id.gameDescription);
        final Button dateButton = getActivity().findViewById(R.id.completedDate);
        final CheckBox isComplete = getActivity().findViewById(R.id.gameComplete);

        // Sets the edit box text to the games details.
        gameTitle.setText(game.getTitle());
        gamePlatform.setText(game.getPlatform());
        gameDescription.setText(game.getDescription());
        dateButton.setText(game.getDateComplete().toString());
        isComplete.setChecked(game.isComplete());

    }
}

