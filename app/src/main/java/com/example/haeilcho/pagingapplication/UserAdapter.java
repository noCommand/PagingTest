package com.example.haeilcho.pagingapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter<T extends User> extends PagedListAdapter<T,UserAdapter.UserViewHolder> {

    public UserAdapter() {
        super(new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(
                    @NonNull T oldUser, @NonNull T newUser) {
                // User properties may have changed if reloaded from the DB, but ID is fixed
                return oldUser.getId() == newUser.getId();
            }

            @Override
            public boolean areContentsTheSame(
                    @NonNull T oldUser, @NonNull T newUser) {
                // NOTE: if you use equals, your object must properly override Object#equals()
                // Incorrectly returning false here will result in too many animations.
                return oldUser.equals(newUser);
            }
        });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        T user = getItem(position);
        if (user != null) {
            holder.bindTo(user);
        } else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
            holder.clear();
        }
    }

    public  final DiffUtil.ItemCallback<T> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<T>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull T oldUser, @NonNull T newUser) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.getId() == newUser.getId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull T oldUser, @NonNull T newUser) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser.equals(newUser);
                }
            };


    public final static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public UserViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_textview);
        }
        public void bindTo(User user){
            textView.setText(user.getFirstName());

        }
        public void clear(){

        }
    }

}
