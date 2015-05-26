package com.dashboard.orange.orangedashboard.Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dashboard.orange.orangedashboard.Adapters.Info_CategoryAdapter;
import com.dashboard.orange.orangedashboard.AppManager;
import com.dashboard.orange.orangedashboard.CustomLayout.ExpandableHeightGridView;
import com.dashboard.orange.orangedashboard.ParseObjects.Category;
import com.dashboard.orange.orangedashboard.ParseObjects.Locality;
import com.dashboard.orange.orangedashboard.ParseObjects.Subcategory;
import com.dashboard.orange.orangedashboard.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final ArrayList<String> LOCALITIES = new ArrayList<>();
    AppManager manager;
    public static int scrollX = 0;
    public static int scrollY = -1;
    ScrollView scrollView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static ArrayList<Category> CATEGORIES = new ArrayList<>();
    private static ArrayList<Subcategory> SUBCATEGORIES = new ArrayList<>();


    //private OnFragmentInteractionListener mListener;

    public InfoFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2)
    {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static InfoFragment newInstance()
    {
        InfoFragment fragment = new InfoFragment();
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
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        manager = (AppManager) getActivity().getApplication();
        ArrayList<Locality> localityList = manager.getLocalityList();
        for (int i = 0; i < localityList.size(); i++)
        {
            LOCALITIES.add(localityList.get(i).getLocalityName());
        }

        CATEGORIES = manager.getCategoryList();
        SUBCATEGORIES = manager.getSubcategoryList();

        for (int i = 0; i < CATEGORIES.size(); i++)
        {
            Log.d("Cat", "Name : " + CATEGORIES.get(i).getCategoryName());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        scrollView = (ScrollView) view.findViewById(R.id.scrollview_info);
        AutoCompleteTextView editTextLocality = (AutoCompleteTextView) view.findViewById(R.id.editText_locality);
        final EditText editTextOpenTime = (EditText) view.findViewById(R.id.editText_start_time);
        final EditText editTextCloseTime = (EditText) view.findViewById(R.id.editText_end_time);
        RadioGroup radioGroupPaymentType = (RadioGroup) view.findViewById(R.id.radiogroup_payment_type);
        editTextOpenTime.setShowSoftInputOnFocus(false);
        editTextCloseTime.setShowSoftInputOnFocus(false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, LOCALITIES);
        editTextLocality.setAdapter(adapter);
        editTextLocality.setValidator(new LocalityValidator());
        editTextLocality.setOnFocusChangeListener(new FocusListener());
        editTextOpenTime.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (hasFocus)
                {
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
                    {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                        {
                            String colon;
                            if (selectedMinute < 10)
                            {
                                colon = ":0";
                            }
                            else
                            {
                                colon = ":";
                            }
                            if (selectedHour > 12)
                            {
                                editTextOpenTime.setText("" + (selectedHour - 12) + colon + selectedMinute + " pm");
                            }
                            else
                            {
                                editTextOpenTime.setText("" + selectedHour + colon + selectedMinute + "am");
                            }
                        }
                    }, 10, 00, false);
                    mTimePicker.setTitle("Select Opening Time");
                    mTimePicker.show();
                }
            }
        });

        editTextCloseTime.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (hasFocus)
                {
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener()
                    {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                        {
                            String colon;
                            if (selectedMinute < 10)
                            {
                                colon = ":0";
                            }
                            else
                            {
                                colon = ":";
                            }
                            if (selectedHour > 12)
                            {
                                editTextCloseTime.setText("" + (selectedHour - 12) + colon + selectedMinute + " pm");
                            }
                            else
                            {
                                editTextCloseTime.setText("" + selectedHour + colon + selectedMinute + "am");
                            }
                        }
                    }, 21, 30, false);
                    mTimePicker.setTitle("Select Opening Time");
                    //mTimePicker.show();
                    ((ActionBarActivity) getActivity()).getSupportActionBar().hide();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, Info_SubcategorySelectionFragment.newInstance(InfoFragment.this, SUBCATEGORIES))
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack("InfoFragment")
                            .commit();
                }
            }
        });

        final ExpandableHeightGridView gridview = (ExpandableHeightGridView) view.findViewById(R.id.gridview);
        gridview.setExpanded(true);
        gridview.setAdapter(new Info_CategoryAdapter(getActivity(), CATEGORIES));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id)
            {
                TextView currentLetter = (TextView) v.findViewById(R.id.tvTitle);
                int defaultTextColor = currentLetter.getTextColors().getDefaultColor();
                currentLetter.setTextColor(getResources().getColor(R.color.orange_accent));
                ImageView currentImage = (ImageView) v.findViewById(R.id.ivIcon);
                currentImage.getDrawable().setColorFilter(0xFFFF5722, PorterDuff.Mode.SRC_ATOP);

                for(int i = 0; i < gridview.getChildCount(); i++)
                {
                    if(i != position)
                    {
                        View v1 = (View) gridview.getChildAt(i);
                        ImageView currentImage1 = (ImageView) v1.findViewById(R.id.ivIcon);
                        currentImage1.getDrawable().clearColorFilter();
                        TextView currentLetter1 = (TextView) v1.findViewById(R.id.tvTitle);
                        currentLetter1.setTextColor(getResources().getColor(android.R.color.secondary_text_light_nodisable));
                    }
                }

            }
        });

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
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
    /*public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/


    class LocalityValidator implements AutoCompleteTextView.Validator
    {

        @Override
        public boolean isValid(CharSequence text)
        {
            Log.v("Test", "Checking if valid: " + text);
            Collections.sort(LOCALITIES);
            if (Collections.binarySearch(LOCALITIES, text.toString()) > 0)
            {
                return true;
            }

            return false;
        }

        @Override
        public CharSequence fixText(CharSequence invalidText)
        {
            Log.v("Test", "Returning fixed text");

            /* I'm just returning an empty string here, so the field will be blanked,
             * but you could put any kind of action here, like popping up a dialog?
             *
             * Whatever value you return here must be in the list of valid words.
             */
            return "";
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        scrollX = scrollView.getScrollX();
        scrollY = scrollView.getScrollY();
    }

    @Override
    public void onResume()
    {
        super.onResume();
//this is important. scrollTo doesn't work in main thread.
        scrollView.post(new Runnable()
        {
            @Override
            public void run()
            {
                scrollView.scrollTo(scrollX, scrollY);
            }
        });    }

    class FocusListener implements View.OnFocusChangeListener
    {

        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            if (v.getId() == R.id.editText_locality && hasFocus)
            {
                Log.v("Test", "Performing validation");
                ((AutoCompleteTextView) v).performValidation();
            }
        }
    }

}
