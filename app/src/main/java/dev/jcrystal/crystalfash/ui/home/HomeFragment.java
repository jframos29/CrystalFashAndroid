package dev.jcrystal.crystalfash.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.jcrystal.crystalfash.R;
import dev.jcrystal.crystalfash.models.Product;
import jcrystal.mobile.entities.ProductNormal;
import jcrystal.mobile.net.controllers.ManagerProduct;
import jcrystal.mobile.net.utils.On1SuccessListener;
import jcrystal.mobile.net.utils.OnErrorListener;
import jcrystal.mobile.net.utils.RequestError;

public class HomeFragment extends Fragment implements On1SuccessListener {

    private List<Product> lstProducts = new ArrayList<>();

    private List<String> categories = new ArrayList<>();

    private CategoryAdapter categoryAdapter;

    private ProductAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        myAdapter = new ProductAdapter(getActivity(), lstProducts);
        ManagerProduct.getProducts(this, this, (OnErrorListener) this.getActivity());
        /*lstProducts = new ArrayList<>();
        for(int i=0;i<30;i++){
            lstProducts.add(new Product("Sweater 1", "MEN", "soft sweater", "https://imgur.com/OqgIsqf.jpg", 13, 28));
        }*/


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerview_id);
        myrv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        myrv.setAdapter(myAdapter);

        ManagerProduct.getCategories(this,this, (OnErrorListener) this.getActivity() );


        /*categories.add("WOMEN");
        categories.add("MEN");
        categories.add("KIDS");
        */

        RecyclerView rv_category = (RecyclerView) root.findViewById(R.id.recyclerview_category_id);
        categoryAdapter = new CategoryAdapter(getActivity(), categories, this);
        rv_category.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_category.setAdapter(categoryAdapter);

        return root;
    }

    @Override
    public void onSuccess(Object o) {
        ArrayList ob = (ArrayList) o;
        if (ob.size() == 0) {

        }
        else {
            if (ob.get(0) instanceof ProductNormal) {

                    for (ProductNormal product : (List<ProductNormal>)ob) {
                        lstProducts.add(new Product(product.name(), product.category().getName(), product.description(), product.image(), product.price(), product.oldPrice()));
                    }

                    myAdapter.notifyDataSetChanged();
            }
            else{
                categories=ob;
                categoryAdapter.notifyDataSetChanged();
            }
        }
    }
}