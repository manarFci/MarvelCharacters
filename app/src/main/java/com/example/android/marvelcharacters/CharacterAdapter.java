package com.example.android.marvelcharacters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by macbook on 7/14/19.
 */


public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private Context mContext;
    private ArrayList<CharacterInfo> mCharList;

    public CharacterAdapter(Context context, ArrayList<CharacterInfo> charList) {
        mContext = context;
        mCharList = charList;
    }
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.character_details, parent, false);
        return new CharacterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CharacterAdapter.CharacterViewHolder holder, int position) {

        CharacterInfo currentItem = mCharList.get(position);

        String imageUrl = currentItem.getmImageURL();
        String charName = currentItem.getmCharacterName();
        String charDesc = currentItem.getmCharacterName();

        holder.mTextViewCharacter.setText(charName);
        holder.mTextViewDesc.setText(charDesc);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCharList.size();
    }

public class CharacterViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCharacter;
        public TextView mTextViewDesc;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCharacter = itemView.findViewById(R.id.text_view_character);
            mTextViewDesc = itemView.findViewById(R.id.text_view_description);
        }
}
}
