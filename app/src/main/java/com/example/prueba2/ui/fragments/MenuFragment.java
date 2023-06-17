package com.example.prueba2.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prueba2.R;
import com.example.prueba2.ui.adaptadores.RecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mSizes = new ArrayList<>();
    private ArrayList<String> mPrices = new ArrayList<>();

    private ArrayList<String> mImageNamesWine = new ArrayList<>();
    private ArrayList<String> mNamesWine = new ArrayList<>();
    private ArrayList<String> mSizesWine = new ArrayList<>();
    private ArrayList<String> mPricesWine = new ArrayList<>();

    private ArrayList<String> mImageNamesFood = new ArrayList<>();
    private ArrayList<String> mNamesFood = new ArrayList<>();
    private ArrayList<String> mSizesFood = new ArrayList<>();
    private ArrayList<String> mPricesFood = new ArrayList<>();
    private View viewRoot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewRoot = inflater.inflate(R.layout.fragment_menu, container, false);
        createBeerView();
        createWineView();
        createFoodView();

        return viewRoot;
    }

    private void createBeerView() {
        mImageNames.add("https://hebmx.vtexassets.com/arquivos/ids/604983-800-800?v=638218521196070000&width=800&height=800&aspect=true");
        mNames.add("Corona extra");
        mSizes.add("355 ml");
        mPrices.add("$21.00");

        mImageNames.add("https://lapencavinos.com/wp-content/uploads/2019/10/modelo-especial.png");
        mNames.add("Modelo especial");
        mSizes.add("355 ml");
        mPrices.add("$28.00");

        mImageNames.add("https://hebmx.vtexassets.com/arquivos/ids/604983-800-800?v=638218521196070000&width=800&height=800&aspect=true");
        mNames.add("Corona extra");
        mSizes.add("355 ml");
        mPrices.add("$21.00");

        mImageNames.add("https://lapencavinos.com/wp-content/uploads/2019/10/modelo-especial.png");
        mNames.add("Modelo especial");
        mSizes.add("355 ml");
        mPrices.add("$28.00");

        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = viewRoot.findViewById(R.id.recycler_view_beers);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(viewRoot.getContext(), mImageNames, mNames, mSizes, mPrices);
        recyclerView.setAdapter(adapter);
    }

    private void createWineView() {
        mImageNamesWine.add("https://www.lanaval.com.mx/107740-product_default/whisky-black-and-white-700-ml.jpg");
        mNamesWine.add("Black & white");
        mSizesWine.add("700 ml");
        mPricesWine.add("$199.00");

        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewWine = viewRoot.findViewById(R.id.recycler_view_wine);
        recyclerViewWine.setLayoutManager(layoutManager);
        RecyclerAdapter adapterWine = new RecyclerAdapter(viewRoot.getContext(), mImageNamesWine, mNamesWine, mSizesWine, mPricesWine);
        recyclerViewWine.setAdapter(adapterWine);
    }

    private void createFoodView() {
        mImageNamesFood.add("https://static.vecteezy.com/system/resources/previews/021/952/562/original/tasty-hamburger-on-transparent-background-png.png");
        mNamesFood.add("Hamburguesa");
        mSizesFood.add("Clasica");
        mPricesFood.add("$79.00");
        mImageNamesFood.add("https://www.ballparkbrand.com/sites/default/files/Hero_Dog_Full_Crop_0.png");
        mNamesFood.add("Hot dog");
        mSizesFood.add("Clasico con pepinillos");
        mPricesFood.add("$30.00");

        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewFood = viewRoot.findViewById(R.id.recycler_view_food);
        recyclerViewFood.setLayoutManager(layoutManager);
        RecyclerAdapter adapterFood = new RecyclerAdapter(viewRoot.getContext(), mImageNamesFood, mNamesFood, mSizesFood, mPricesFood);
        recyclerViewFood.setAdapter(adapterFood);
    }
}