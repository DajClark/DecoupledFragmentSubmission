package com.example.decoupledfragmentsubmission;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class GameListFragment extends Fragment {

    // Holds the recycler view and adapter objects for use within methods.
    private RecyclerView gameRecyclerView;
    ListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Calls the required onCreate methods from super class.
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the fragment view using an XML layout file.
        View view = inflater.inflate(R.layout.game_list_fragment, container, false);

        // Initialise recycler view element from XML using ID and provide layout manager for child views.
        gameRecyclerView = view.findViewById(R.id.game_recycler_view);
        gameRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) );

        // Updates the view with current array of games.
        updateUI();

        return view;

    }

    @Override
    public void onResume() {

        // Updates the UI with new information during onResume lifecycle after onPause.
        super.onResume();
        updateUI();
    }


    private void updateUI(){

        // Get the current list of games from the GameList model.
        ArrayList gameList;
        GameList gameListModel = GameList.get(getContext());
        gameList = gameListModel.getGames();

        // If list adapter is not null then initialise adapter using list of games and set the adapter in recycler view.
        if (listAdapter == null) {
            listAdapter = new ListAdapter(gameList);
            gameRecyclerView.setAdapter(listAdapter);
        } else {
            // Informs observers data is no longer valid.
            listAdapter.notifyDataSetChanged();
        }

    }

    public class GameHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Holds Game object and view elements.
        private Game gameDetails;
        private TextView textViewTitle;
        private TextView textViewPlatform;
        private TextView textViewDescription;

        // Constructor inflates individual item holders using XML layout file and declares view elements.
        public GameHolder(LayoutInflater inflater, ViewGroup parent) {

            // Inflates the view using the XML layout file for an individual item in the adapter list.
            super(inflater.inflate(R.layout.list_item_holder, parent, false));

            // Sets the listener for a list item being selected.
            itemView.setOnClickListener(this);

            // Sets view elements to hold game details within the holder using XML id's.
            textViewTitle = itemView.findViewById(R.id.game_list_title);
            textViewPlatform = itemView.findViewById(R.id.game_list_platform);
            textViewDescription = itemView.findViewById(R.id.game_list_description);

        }

        @Override
        public void onClick(View view) {

            // Show toast message when view holder is clicked.
            Toast.makeText(
                    getActivity(),
                    gameDetails.getTitle() + " clicked",
                    Toast.LENGTH_SHORT)
                    .show();

        }

        // Bind details for game to view holder elements.
        public void bind(Game game){
            gameDetails = game;
            textViewTitle.setText(game.getTitle());
            textViewPlatform.setText(game.getPlatform());
            textViewDescription.setText(game.getDescription());
        }

    }

    public class ListAdapter extends RecyclerView.Adapter<GameListFragment.GameHolder> {

        // Holds the list of games to be shown in the adapter list.
        private List<Game> adapterList;

        // Constructor to initialise list of games when new instance of ListAdapter class is created.
        public ListAdapter(List<Game> games) {
            adapterList = games;
        }

        // Overrides onCreateViewHolder in superclass to inflate the layout of the holder representing a list item.
        @Override
        public GameListFragment.GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new GameHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(GameHolder holder, int position) {
            // Binds the data to the view at the given position in the list of games.
            Game game = adapterList.get(position);
            holder.bind(game);
        }

        @Override
        public int getItemCount() {
            // Returns size of the list of games.
            return adapterList.size();
        }

    }

}
