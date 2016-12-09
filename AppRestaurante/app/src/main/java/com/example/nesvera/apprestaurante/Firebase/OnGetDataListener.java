package com.example.nesvera.apprestaurante.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Nesvera on 09/12/2016.
 */

public interface OnGetDataListener {
     public void onStart();

     public void OnSuccess(DataSnapshot data);

     public void onFailed(DatabaseError databaseError);


}
