package com.frontanilla.tictactoe;

import com.frontanilla.firebase.FirebaseInterface;
import com.frontanilla.firebase.SetterManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Luis on 03/06/2017.
 */

class FirebaseConnection implements FirebaseInterface {

    private DatabaseReference turnReference;
    private DatabaseReference boardReference;

    FirebaseConnection() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        turnReference = database.getReference("PlayerX");
        boardReference = database.getReference("Board");

        boardReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String board = dataSnapshot.getValue(String.class);
                SetterManager.getInstance().asyncBoardUpdateFromDB(board);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        turnReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean turn = dataSnapshot.getValue(Boolean.class);
                SetterManager.getInstance().asyncTurnUpdateFromDB(turn);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setBoard(String board) {
        boardReference.setValue(board);
        turnReference.setValue(!SetterManager.getInstance().getGame().isPlayerX());
        confirmBoardUpdate();
        confirmTurnUpdate();
    }

    @Override
    public void getTurnToCheck() {
        turnReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean turn = dataSnapshot.getValue(Boolean.class);
                SetterManager.getInstance().gotTurnFromCheck(turn);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getBoardFromEnteringApp() {
        boardReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String board = dataSnapshot.getValue(String.class);
                SetterManager.getInstance().gotBoardFromEnteringApp(board);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void getTurnFromEnteringApp() {
        turnReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean turn = dataSnapshot.getValue(Boolean.class);
                SetterManager.getInstance().gotTurnFromEnteringApp(turn);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void confirmBoardUpdate() {
        boardReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String board = dataSnapshot.getValue(String.class);
                SetterManager.getInstance().gotBoardFromUpdateConfirmed(board);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void confirmTurnUpdate() {
        turnReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean turn = dataSnapshot.getValue(Boolean.class);
                SetterManager.getInstance().gotTurnFromUpdateConfirmed(turn);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
