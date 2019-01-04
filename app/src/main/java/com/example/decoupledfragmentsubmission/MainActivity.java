package com.example.decoupledfragmentsubmission;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.decoupledfragmentsubmission.GameEditFragment.EXTRA_GAME;

public class MainActivity extends AppCompatActivity
implements GameListFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new Fragment to be placed in the activity layout.
        GameListFragment firstFragment = new GameListFragment();

        // Add the fragment to the 'fragment_container' FrameLayout.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();


    }

    /*  MainActivity class implements interface within the list fragment
        to communicate and pass data between them. The activity listens
        for the interface method call in the list fragment and replaces
        it with the edit fragment.*/
    @Override
    public void onGameSelected(Game game) {

            GameEditFragment newFragment = new GameEditFragment();

            /* Create bundle and passes reference to the game object as serializable
               from the list in the model.*/
            Bundle args = new Bundle();
            args.putSerializable(EXTRA_GAME,GameList.get(this).getGame(game.getGameID()));

            // Set fragment arguments using bundle.
            newFragment.setArguments(args);

            // Get fragment manager and begin fragment transactions.
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            // Replace the current fragment within the activity.
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
 //       }


    }
}
