package com.svalero.vintedandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svalero.vintedandroid.beans.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailProfileFragment extends Fragment {

    private TextView name;
    private TextView lastName;
    private TextView email;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USER = "user";

    // TODO: Rename and change types of parameters
    private User mUser;


    public DetailProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment DetailProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailProfileFragment newInstance(User user) {
        DetailProfileFragment fragment = new DetailProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_profile, container, false);

        name = view.findViewById(R.id.fragment_detail_profile_name);
        lastName = view.findViewById(R.id.fragment_detail_profile_lastName);
        email = view.findViewById(R.id.fragment_detail_profile_email);

        showDetail();

        return view;
    }

    private void showDetail(){
        name.setText(mUser.getName());
        lastName.setText(mUser.getLastName());
        email.setText(mUser.getEmail());
    }
}