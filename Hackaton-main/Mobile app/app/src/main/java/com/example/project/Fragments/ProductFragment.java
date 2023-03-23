//package com.example.project.Fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.navigation.Navigation;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.project.Adapters.RecyclerViewAdapterProduct;
//import com.example.project.R;
//import com.example.project.networking.APIs;
//import com.example.project.networking.Product;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.progressindicator.CircularProgressIndicator;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ProductFragment extends Fragment {
//
//    FloatingActionButton addButton;
//    private RecyclerView productRV;
//    private ProgressBar circularProgress;
//    private ArrayList<Product> recyclerDataArrayList;
//    private RecyclerViewAdapterProduct recyclerViewAdapterProduct;
//
//    private ProgressBar progressBar;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_product, container, false);
//    }
//
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        requireView().findViewById(R.id.back_iamgeView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.action_productFragment_to_restaurantsFragment);
//            }
//        });
//
//        addButton = requireView().findViewById(R.id.productAddButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.action_productFragment_to_addProductFragment);
//            }
//        });
//
//        productRV = requireView().findViewById(R.id.productRecycleView);
//        circularProgress = requireView().findViewById(R.id.idPBLoading);
//        recyclerDataArrayList = new ArrayList<>();
//        getAllProducts(1);
//
//    }
//
//    private void getAllProducts(Integer prodId) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://172.20.10.6:8080/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIs apIs = retrofit.create(APIs.class);
//
//        Call<ArrayList<Product>> call = apIs.getAllProducts(1);
//
//        call.enqueue(new Callback<ArrayList<Product>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
//
//                if (response.isSuccessful()) {
//                    circularProgress.setVisibility(View.GONE);
//                    recyclerDataArrayList = response.body();
//
//                    for (int i = 0; i < recyclerDataArrayList.size(); i++) {
//                        recyclerViewAdapterProduct = new RecyclerViewAdapterProduct(recyclerDataArrayList, getActivity());
//
//                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//
//                        productRV.setLayoutManager(manager);
//
//                        productRV.setAdapter(recyclerViewAdapterProduct);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Fail to get data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}