package com.shawnfrye.project2;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    EditText editText;
    Button buttonDelete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        editText = itemView.findViewById(R.id.etData);
        buttonDelete = itemView.findViewById(R.id.buttonDelete);
    }
}
