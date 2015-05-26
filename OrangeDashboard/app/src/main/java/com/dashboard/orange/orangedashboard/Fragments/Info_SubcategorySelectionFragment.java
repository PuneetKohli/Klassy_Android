package com.dashboard.orange.orangedashboard.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.dashboard.orange.orangedashboard.Adapters.Info_SubcategorySelectionAdapter;
import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class Info_SubcategorySelectionFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static Fragment OldFragment;
    private static ArrayList<Subcategory> subcategories;
    private static ArrayList<Subcategory> requiredSubcategories;
    Context context = null;
    Info_SubcategorySelectionAdapter objAdapter;
    ListView lv = null;
    EditText edtSearch = null;
    LinearLayout llContainer = null;
    Button btnOK = null;
    private Toolbar toolbar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    /**
     * The fragment's ListView/GridView.
     */

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Info_SubcategorySelectionFragment()
    {
    }

    public static Info_SubcategorySelectionFragment newInstance(Fragment oldFragment, ArrayList<Subcategory> Subcategories)
    {
        OldFragment = oldFragment;
        subcategories = Subcategories;
        Info_SubcategorySelectionFragment fragment = new Info_SubcategorySelectionFragment();
        return fragment;

    }

    // TODO: Rename and change types of parameters
    public static Info_SubcategorySelectionFragment newInstance(String param1, String param2)
    {
        Info_SubcategorySelectionFragment fragment = new Info_SubcategorySelectionFragment();
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
        requiredSubcategories = new ArrayList<>();
        for (int i = 0; i < subcategories.size(); i++)
        {
            if (subcategories.get(i).getTagCategory().getObjectId().equals("IallhZGE9Y"))
            {
                requiredSubcategories.add(subcategories.get(i));
            }
        }

        context = getActivity();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_info_subcategory, container, false);
        edtSearch = (EditText) view.findViewById(R.id.input_search);
        llContainer = (LinearLayout) view.findViewById(R.id.data_container);
        btnOK = (Button) view.findViewById(R.id.ok_button);
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
                   goBackToPreviousFragment();
                }
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);

        edtSearch.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3)
            {
                // When user changed the Text
                String text = edtSearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                objAdapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable arg0)
            {
                // TODO Auto-generated method stub
            }
        });

        objAdapter = new Info_SubcategorySelectionAdapter(context,
                requiredSubcategories);

        lv = new ListView(context);
        llContainer.addView(lv);
        lv.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        lv.setAdapter(objAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id)
            {
                Log.d("Count", "Count is " + objAdapter.getCount());
                CheckBox chk = (CheckBox) view
                        .findViewById(R.id.contactcheck);
                Subcategory bean = subcategories
                        .get(position);
                if (bean.isSelected())
                {
                    bean.setSelected(false);
                    chk.setChecked(false);
                }
                else
                {
                    bean.setSelected(true);
                    chk.setChecked(true);
                }

            }
        });

        return view;
    }

    private void getSelectedContacts()
    {
        // TODO Auto-generated method stub

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < requiredSubcategories.size(); i ++)
        {
            Log.d("Trial", "Bean names " + requiredSubcategories.get(i).getTagName());
            if (requiredSubcategories.get(i).isSelected())
            {
                sb.append(requiredSubcategories.get(i).getTagName());
                sb.append(",");
            }
        }

        String s = sb.toString().trim();

        if (TextUtils.isEmpty(s))
        {
            Toast.makeText(context, "Select atleast one Contact",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            s = s.substring(0, s.length() - 1);
            Toast.makeText(context, "Selected Contacts : " + s,
                    Toast.LENGTH_SHORT).show();

        }

    }

    public void goBackToPreviousFragment()
    {
        edtSearch.setText("");
        getFragmentManager().beginTransaction()
                .replace(R.id.container, OldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        toolbar.setVisibility(View.INVISIBLE);
        ((ActionBarActivity) getActivity()).getSupportActionBar().show();
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
