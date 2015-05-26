package com.dashboard.orange.orangedashboard.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dashboard.orange.orangedashboard.Adapters.OLD_SubcategorySelectionAdapter;
import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class OLD_SubcategorySelectionFragment extends ListFragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Toolbar toolbar;
    Button button;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter<Subcategory> mAdapter;

    static Fragment OldFragment;
    private static ArrayList<Subcategory> subcategories;
    private static ArrayList<Subcategory> requiredSubcategories = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OLD_SubcategorySelectionFragment()
    {
    }

    public static OLD_SubcategorySelectionFragment newInstance(Fragment oldFragment, ArrayList<Subcategory> Subcategories)
    {
        OldFragment = oldFragment;
        subcategories = Subcategories;
        OLD_SubcategorySelectionFragment fragment = new OLD_SubcategorySelectionFragment();
        return fragment;

    }

    // TODO: Rename and change types of parameters
    public static OLD_SubcategorySelectionFragment newInstance(String param1, String param2)
    {
        OLD_SubcategorySelectionFragment fragment = new OLD_SubcategorySelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < subcategories.size(); i++)
        {
            if(subcategories.get(i).getTagCategory().getObjectId().equals("IallhZGE9Y"))
            {
                requiredSubcategories.add(subcategories.get(i));
            }
        }
        mAdapter = new OLD_SubcategorySelectionAdapter(getActivity(), requiredSubcategories);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_example, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar_list_fragment);
        toolbar.setTitle("Clothing");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isVisible())
                {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, OldFragment)
                            .commit();
                }
                ((ActionBarActivity)getActivity()).getSupportActionBar().show();

            }
        });
        toolbar.inflateMenu(R.menu.menu_main);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SparseBooleanArray checked = mListView.getCheckedItemPositions();
                ArrayList<Subcategory> selectedItems = new ArrayList<>();
                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter

                    int position = checked.keyAt(i);
                    // Add sport if it is checked i.e.) == TRUE!
                    if (checked.valueAt(i))
                    {
                        Log.d("Selected", "Selected item position is " + position);
                        selectedItems.add((Subcategory) mAdapter.getItem(position));
                    }
            }

                ArrayList<Subcategory> outputStrArr = new ArrayList<>();

                for (int i = 0; i < selectedItems.size(); i++)
                {
                    outputStrArr.add(selectedItems.get(i));
                }
            }
        });

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    ArrayList<Subcategory> selectedItems = new ArrayList<>();

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText)
    {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView)
        {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }



}
