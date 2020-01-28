package dev.jcrystal.crystalfash.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.jcrystal.crystalfash.R;
import dev.jcrystal.crystalfash.models.Product;

public class HomeFragment extends Fragment {

    private List<Product> lstProducts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //TODO list of products from ws
        lstProducts = new ArrayList<>();
        for(int i=0;i<30;i++){
            lstProducts.add(new Product("Sweater 1", "MEN", "soft sweater", "https://imgur.com/OqgIsqf.jpg", 13, 28));
        }


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerview_id);
        ProductAdapter myAdapter = new ProductAdapter(getContext(),lstProducts);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),2));
        myrv.setAdapter(myAdapter);

        // TODO get list of categories
        List<String> categories = new ArrayList<>();

        categories.add("WOMEN");
        categories.add("MEN");
        categories.add("KIDS");

        RecyclerView rv_category = (RecyclerView) root.findViewById(R.id.recyclerview_category_id);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),categories);
        rv_category.setLayoutManager(new GridLayoutManager(getContext(),3));
        rv_category.setAdapter(categoryAdapter);

        return root;
    }
}