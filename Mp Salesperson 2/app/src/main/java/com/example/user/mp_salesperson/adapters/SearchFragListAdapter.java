package com.example.user.mp_salesperson.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Amitlibs.net.HttpUrlConnectionJSONParser;
import com.Amitlibs.utils.ComplexPreferences;
import com.Amitlibs.utils.TextUtils;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.user.mp_salesperson.Constant;
import com.example.user.mp_salesperson.HomeActivity;
import com.example.user.mp_salesperson.R;
import com.example.user.mp_salesperson.bean.CartItemBean;
import com.example.user.mp_salesperson.bean.CartItemInfo;
import com.example.user.mp_salesperson.bean.basecat_subcat_cat_bean_package.ItemList;
import com.example.user.mp_salesperson.customClasses.Utility;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Krishna on 03-01-2017.
 */

public class SearchFragListAdapter extends RecyclerView.Adapter<SearchFragListAdapter.ViewHolder> {
    private ArrayList<ItemList> itemListArrayList;
    private Context context;
    private int ivWidth;
    private int ivHeight;

    AsyncTask<String, Void, JSONObject> getItemOffer;
    private TextView tvTotalItemPrice;
    private TextView tvTotalItemQty;
    private TextView tvTotalDp;
    private TextView show_popup;
    private double deliveryCharges = 10;
    String language;
    ArrayAdapter<ItemList> myAdapter;
    private ArrayList<ItemList> itemListAllvalue;
    AlertDialog customAlertDialog;
    //int  count=0;
    public SearchFragListAdapter(Context context, ArrayList<ItemList> itemListArrayList, int ivWidth, int ivHeight, TextView tvTotalItemPrice, TextView tvTotalItemQty, TextView tvTotalDp,TextView show_popup,ArrayList<ItemList> itemListAllvalue ) {



        this.itemListArrayList = itemListArrayList;
        this.context = context;
        this.ivWidth = ivWidth;
        this.ivHeight = ivHeight;
        this.tvTotalItemPrice = tvTotalItemPrice;
        this.tvTotalItemQty = tvTotalItemQty;
        this.tvTotalDp= tvTotalDp;
        this.show_popup= show_popup;
        this.itemListAllvalue = itemListAllvalue;


    }

    @Override
    public SearchFragListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_frag_moq_item, viewGroup, false);
        return new SearchFragListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchFragListAdapter.ViewHolder viewHolder, final int i) {


        final ArrayList<ItemList> moqPojoArrayList = new ArrayList<ItemList>();
        for (int jj = 0; jj < itemListAllvalue.size(); jj++) {
            if (itemListAllvalue.get(jj).getItemNumber().equalsIgnoreCase(itemListArrayList.get(i).getItemNumber())) {
                String ItemId = itemListAllvalue.get(jj).getItemId();
                String Categoryid = itemListAllvalue.get(jj).getCategoryid();
                String SubCategoryId = itemListAllvalue.get(jj).getSubCategoryId();
                String SubsubCategoryid = itemListAllvalue.get(jj).getSubsubCategoryid();
                String itemname = itemListAllvalue.get(jj).getItemname();
                String price = itemListAllvalue.get(jj).getPrice();
                String SellingUnitName = itemListAllvalue.get(jj).getSellingUnitName();
                String SellingSku = itemListAllvalue.get(jj).getSellingSku();
                String UnitPrice = itemListAllvalue.get(jj).getUnitPrice();
                String VATTax = itemListAllvalue.get(jj).getVATTax();
                String LogoUrl = itemListAllvalue.get(jj).getLogoUrl();
                String MinOrderQty = itemListAllvalue.get(jj).getMinOrderQty();
                String Discount = itemListAllvalue.get(jj).getDiscount();
                String TotalTaxPercentage = itemListAllvalue.get(jj).getTotalTaxPercentage();
                String HindiName = itemListAllvalue.get(jj).getItemHindiname();
                String DpPoint = itemListAllvalue.get(jj).getDreamPoint();
                String PromoPoint = itemListAllvalue.get(jj).getDreamPoint();
                String ItemNumber = itemListAllvalue.get(jj).getItemNumber();
                String MarginPoint = itemListAllvalue.get(jj).getMarginPoint();
                String WarehouseId = itemListAllvalue.get(jj).getWarehouseId();
                String CompanyId = itemListAllvalue.get(jj).getCompanyId();
                String IsOffer = itemListAllvalue.get(jj).getIsOffer();

                moqPojoArrayList.add(new ItemList(ItemId, "UnitId", Categoryid, SubCategoryId, SubsubCategoryid, itemname, "UnitName", "PurchaseUnitName", price, SellingUnitName, SellingSku, UnitPrice, VATTax, LogoUrl, MinOrderQty, Discount, TotalTaxPercentage, HindiName, DpPoint, PromoPoint, MarginPoint, WarehouseId, CompanyId, ItemNumber,IsOffer));


                // System.out.println("moqPojoArrayList12:::"+moqPojoArrayList);
            } else {
                //   System.out.println("Moq5555:::"+itemListArrayList.get(i).getMinOrderQty());
            }


        }

        //  arrayAdapterItemName = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, moq);

        if (moqPojoArrayList.size() == 0) {
            //  System.out.println("Run1");
            String ItemId = itemListArrayList.get(i).getItemId();
            String Categoryid = itemListArrayList.get(i).getCategoryid();
            String SubCategoryId = itemListArrayList.get(i).getSubCategoryId();
            String SubsubCategoryid = itemListArrayList.get(i).getSubsubCategoryid();
            String itemname = itemListArrayList.get(i).getItemname();
            String price = itemListArrayList.get(i).getPrice();
            String SellingUnitName = itemListArrayList.get(i).getSellingUnitName();
            String SellingSku = itemListArrayList.get(i).getSellingSku();
            String UnitPrice = itemListArrayList.get(i).getUnitPrice();
            String VATTax = itemListArrayList.get(i).getVATTax();
            String LogoUrl = itemListArrayList.get(i).getLogoUrl();
            String MinOrderQty = itemListArrayList.get(i).getMinOrderQty();
            String Discount = itemListArrayList.get(i).getDiscount();
            String TotalTaxPercentage = itemListArrayList.get(i).getTotalTaxPercentage();
            String HindiName = itemListArrayList.get(i).getItemHindiname();
            String DpPoint = itemListArrayList.get(i).getDreamPoint();
            String PromoPoint = itemListArrayList.get(i).getPromoPoint();
            String ItemNumber = itemListArrayList.get(i).getItemNumber();
            String MarginPoint = itemListArrayList.get(i).getMarginPoint();
            String WarehouseId = itemListArrayList.get(i).getWarehouseId();
            String CompanyId = itemListArrayList.get(i).getCompanyId();
            String IsOffer = itemListArrayList.get(i).getIsOffer();
            //Collections.sort(moqPojoArrayList, new ComparatorOfNumericString());
            moqPojoArrayList.add(new ItemList(ItemId, "UnitId", Categoryid, SubCategoryId, SubsubCategoryid, itemname, "UnitName", "PurchaseUnitName", price, SellingUnitName, SellingSku, UnitPrice, VATTax, LogoUrl, MinOrderQty, Discount, TotalTaxPercentage, HindiName, DpPoint, PromoPoint, MarginPoint, WarehouseId, CompanyId, ItemNumber,IsOffer));


            // myAdapter = new ArrayAdapter<ItemList>(context, android.R.layout.simple_spinner_item, moqPojoArrayList);

        } else {
            // myAdapter = new ArrayAdapter<ItemList>(context, android.R.layout.simple_spinner_item, moqPojoArrayList);
        }
      //  myAdapter = new ArrayAdapter<ItemList>(context, android.R.layout.simple_spinner_item, moqPojoArrayList);
        if(moqPojoArrayList.size()>2)
        {
            viewHolder.ivOfferImage.setVisibility(View.VISIBLE);


        }
        if(moqPojoArrayList.size()==1)
        {
            viewHolder.tvSingleMoq.setVisibility(View.VISIBLE);
            viewHolder.tvMoqPrice.setVisibility(View.GONE);
            viewHolder.tvSingleMoq.setText("MOQ: "+moqPojoArrayList.get(0).getMinOrderQty());
        }

        try {
            if(moqPojoArrayList.size()==0) {

                context.startActivity(new Intent(context,HomeActivity.class));
            }else{
                //ii;
                // String item = arg0.getItemAtPosition(ii).toString();


                viewHolder.tvMoqPrice.setText(moqPojoArrayList.get(0).getMinOrderQty());
                viewHolder.tvItemName.setText(itemListArrayList.get(i).getItemname());

                //  viewHolder.tvselectedItemQuantity.setText();


                Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Kruti_Dev_010.ttf");

                language = Utility.getStringSharedPreferences(context, "MultiLaguage");
                if (language.equals("m")) {

                    viewHolder.tvItemHindiName.setTypeface(tf);
                    viewHolder.tvItemHindiName.setVisibility(View.VISIBLE);
                }

                if (language.equals("s")) {

                    viewHolder.tvItemHindiName.setVisibility(View.GONE);
                }


                viewHolder.tvItemHindiName.setText(itemListArrayList.get(i).getItemHindiname());


                if (moqPojoArrayList.get(0).getDreamPoint().isEmpty()) {

                    viewHolder.tvDpPoint.setVisibility(View.GONE);

                }
                if (moqPojoArrayList.get(0).getIsOffer().equalsIgnoreCase("true"))
                {
                    viewHolder.ivSpecialOfer.setVisibility(View.VISIBLE);
                    viewHolder.ivSpecialOfer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            getItemOffer = new GetCallItemOffer().execute(moqPojoArrayList.get(0).getItemId(),moqPojoArrayList.get(0).getCompanyId());

                        }
                    });
                }

                viewHolder.tvDpPoint.setText("Dream Point " + moqPojoArrayList.get(0).getDreamPoint());

                ComplexPreferences mCartItemArraylistPref = ComplexPreferences.getComplexPreferences(context, Constant.CART_ITEM_ARRAYLIST_PREF, context.MODE_PRIVATE);
                CartItemBean mCartItemBean = mCartItemArraylistPref.getObject(Constant.CART_ITEM_ARRAYLIST_PREF_OBJ, CartItemBean.class);
                ArrayList<CartItemInfo> mCartItemInfos = mCartItemBean != null ? mCartItemBean.getmCartItemInfos() : new ArrayList<CartItemInfo>();
                if (mCartItemInfos == null) {
                    mCartItemInfos = new ArrayList<>();
                }

                boolean isItemFound = false;
                if (!moqPojoArrayList.isEmpty()) {
                    for (int j = 0; j < mCartItemInfos.size(); j++) {
                        if (moqPojoArrayList.get(0).getItemId().equalsIgnoreCase(mCartItemInfos.get(j).getItemId())) {
                            isItemFound = true;
                            int itemQuantity = mCartItemInfos.get(j).getQty();
                            if (itemQuantity > 0) {
                                if (itemQuantity > 0)
                                    viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                                else
                                    viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                                String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice())));
                                viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));
                                if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                                    deliveryCharges = 10;
                                } else {
                                    deliveryCharges = 0;
                                }
                                ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());
                                tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                                tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());
                            }
                            break;
                        } else {
                            isItemFound = false;
                        }
                    }
                }


                if (!isItemFound) {
                    int itemQuantity = 0;
                    viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                    String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice())));
                    viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));
                    if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                        deliveryCharges = 10;
                    } else {
                        deliveryCharges = 0;
                    }
                    ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());
                    tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                    tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());
                }


              //  System.out.println("ImageUrl::" + Constant.BASE_URL_Images1 + moqPojoArrayList.get(0).getItemNumber() + ".jpg");
                System.out.println("MRP::" + moqPojoArrayList.get(0).getPrice());
                if (!TextUtils.isNullOrEmpty(moqPojoArrayList.get(0).getLogoUrl()))
                    Picasso.with(context).load(Constant.BASE_URL_Images1 + itemListArrayList.get(i).getItemNumber() + ".jpg").resize(ivWidth, ivHeight).into(viewHolder.ivItemImage);
                //  viewHolder.tvMoqMrp.setText("MOQ: " + itemListArrayList.get(i).getMinOrderQty() + " | MRP: " + new DecimalFormat("##.##").format((Double.parseDouble(itemListArrayList.get(i).getPrice()))));
                viewHolder.tvSelectUnitPrice.setText("| MRP: " + new DecimalFormat("##.##").format((Double.parseDouble(moqPojoArrayList.get(0).getPrice()))));

                //   viewHolder.tvMoqMrp.setText("MOQ: " + itemListArrayList.get(i).getMinOrderQty() + " | MRP: " + itemListArrayList.get(i).getPrice());
                String text = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format(Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice())) + "</font>" + " | Margins: " + (new DecimalFormat("##.##").format((((Double.parseDouble(moqPojoArrayList.get(0).getPrice()) - Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice())) / Double.parseDouble(moqPojoArrayList.get(0).getPrice())) * 100))) + "%";
                viewHolder.tvRateMargins.setText(Html.fromHtml(text));

                viewHolder.ivMinusImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int itemQuantity = Integer.parseInt(viewHolder.tvselectedItemQuantity.getText().toString());


                        if (itemQuantity == 0) {

                            //   ((HomeActivity) context).removeItemfromCart(itemListArrayList.get(i).getItemId());

                        }


                        if (itemQuantity > 0) {
                            itemQuantity -= Integer.parseInt(moqPojoArrayList.get(0).getMinOrderQty());


                            viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);


                            String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice())));
                            viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));

                            if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                                deliveryCharges = 10;
                            } else {
                                deliveryCharges = 0;
                            }

                            String status = ((HomeActivity) context).addItemInCartItemArrayList(moqPojoArrayList.get(0).getItemId(), itemQuantity, Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice()), moqPojoArrayList.get(0), deliveryCharges, Double.parseDouble(moqPojoArrayList.get(0).getDreamPoint()), moqPojoArrayList.get(0).getWarehouseId(), moqPojoArrayList.get(0).getCompanyId(), Double.parseDouble(moqPojoArrayList.get(0).getPrice()));
                            tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                            tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());

                            tvTotalDp.setText(("Dp : " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalDpPoints())));

                            ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());
                            Log.i(Constant.Tag, status);

                            String jsonString = Utility.getStringSharedPreferences(context, "ItemQJson");
                            try {
                                JSONObject jsonObject;
//                        JSONObject jsonObject = new JSONObject(jsonString.toString());

                                if (jsonString.isEmpty()) {
                                    jsonObject = new JSONObject();

                                } else {
                                    jsonObject = new JSONObject(jsonString.toString());

                                }

                                //jsonObject.put(""+getItemId(i), viewHolder.tvselectedItemQuantity.getText().toString());

                                jsonObject.put("" + moqPojoArrayList.get(0).getItemId(), viewHolder.tvselectedItemQuantity.getText().toString());


                                Utility.setStringSharedPreference(context, "ItemQJson", jsonObject.toString());

                            } catch (JSONException e) {
                                Toast.makeText(context, "Json error" + e.toString(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }


                            if (itemQuantity == 0) {

                                //   ((HomeActivity) context).removeItemfromCart(itemListArrayList.get(i).getItemId());

                            }


                            //    Toast.makeText(context, "Json Q "+Utility.getStringSharedPreferences(context, "ItemQJson"), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                viewHolder.ivPlusImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int itemQuantity = Integer.parseInt(viewHolder.tvselectedItemQuantity.getText().toString());
                        itemQuantity += Integer.parseInt(moqPojoArrayList.get(0).getMinOrderQty());
                        viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                        String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice())));
                        viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));

                        if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                            deliveryCharges = 10;
                        } else {
                            deliveryCharges = 0;
                        }

                        String status = ((HomeActivity) context).addItemInCartItemArrayList(moqPojoArrayList.get(0).getItemId(), itemQuantity, Double.parseDouble(moqPojoArrayList.get(0).getUnitPrice()), moqPojoArrayList.get(0), deliveryCharges, Double.parseDouble(moqPojoArrayList.get(0).getDreamPoint()), moqPojoArrayList.get(0).getWarehouseId(), moqPojoArrayList.get(0).getCompanyId(), Double.parseDouble(moqPojoArrayList.get(0).getPrice()));
                        ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());

                        tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                        tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());

                        tvTotalDp.setText(("Dp : " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalDpPoints())));

                        Log.i(Constant.Tag, status);


                        String jsonString = Utility.getStringSharedPreferences(context, "ItemQJson");
                        try {
                            JSONObject jsonObject;
//                        JSONObject jsonObject = new JSONObject(jsonString.toString());

                            if (jsonString.isEmpty()) {
                                jsonObject = new JSONObject();

                            } else {
                                jsonObject = new JSONObject(jsonString.toString());

                            }

                            //jsonObject.put(""+getItemId(i), viewHolder.tvselectedItemQuantity.getText().toString());

                            jsonObject.put("" + moqPojoArrayList.get(0).getItemId(), viewHolder.tvselectedItemQuantity.getText().toString());


                            Utility.setStringSharedPreference(context, "ItemQJson", jsonObject.toString());

                        } catch (JSONException e) {
                            Toast.makeText(context, "Json error" + e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        //    Toast.makeText(context, "Json Q "+Utility.getStringSharedPreferences(context, "ItemQJson"), Toast.LENGTH_SHORT).show();


                    }
                });


            }

        } catch (IndexOutOfBoundsException e) {
            //  e.printStackTrace();
            Log.e("Crash", "crash");

            context.startActivity(new Intent(context, HomeActivity.class));
            System.out.println("Run:::"+e.toString());
        }



        //TextView Click Action

        viewHolder.tvMoqPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogLayout = inflater.inflate(R.layout.moq_price_popup, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogLayout);
                customAlertDialog = builder.create();
                TextView cancelBtn = (TextView) dialogLayout.findViewById(R.id.cancel);
                TextView item_name = (TextView) dialogLayout.findViewById(R.id.item_name);
                item_name.setText(itemListArrayList.get(i).getItemname());
                ListView mMoqPriceList = (ListView) dialogLayout.findViewById(R.id.listview_moq_price);
                final MyAdapter adapter = new MyAdapter(context, moqPojoArrayList);
                mMoqPriceList.setAdapter(adapter);

                mMoqPriceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int ii, long id) {


                        try {
                            if(moqPojoArrayList.size()==0) {

                                context.startActivity(new Intent(context,HomeActivity.class));
                            }else{
                                //ii;
                               // String item = arg0.getItemAtPosition(ii).toString();


                                viewHolder.tvMoqPrice.setText(moqPojoArrayList.get(ii).getMinOrderQty());
                                viewHolder.tvItemName.setText(itemListArrayList.get(i).getItemname());

                                //  viewHolder.tvselectedItemQuantity.setText();


                                Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Kruti_Dev_010.ttf");

                                language = Utility.getStringSharedPreferences(context, "MultiLaguage");
                                if (language.equals("m")) {

                                    viewHolder.tvItemHindiName.setTypeface(tf);
                                    viewHolder.tvItemHindiName.setVisibility(View.VISIBLE);
                                }

                                if (language.equals("s")) {

                                    viewHolder.tvItemHindiName.setVisibility(View.GONE);
                                }


                                viewHolder.tvItemHindiName.setText(itemListArrayList.get(i).getItemHindiname());


                                if (moqPojoArrayList.get(ii).getDreamPoint().isEmpty()) {

                                    viewHolder.tvDpPoint.setVisibility(View.GONE);

                                }


                                viewHolder.tvDpPoint.setText("Dream Point " + moqPojoArrayList.get(ii).getDreamPoint());

                                ComplexPreferences mCartItemArraylistPref = ComplexPreferences.getComplexPreferences(context, Constant.CART_ITEM_ARRAYLIST_PREF, context.MODE_PRIVATE);
                                CartItemBean mCartItemBean = mCartItemArraylistPref.getObject(Constant.CART_ITEM_ARRAYLIST_PREF_OBJ, CartItemBean.class);
                                ArrayList<CartItemInfo> mCartItemInfos = mCartItemBean != null ? mCartItemBean.getmCartItemInfos() : new ArrayList<CartItemInfo>();
                                if (mCartItemInfos == null) {
                                    mCartItemInfos = new ArrayList<>();
                                }

                                boolean isItemFound = false;
                                if (!moqPojoArrayList.isEmpty()) {
                                    for (int j = 0; j < mCartItemInfos.size(); j++) {
                                        if (moqPojoArrayList.get(ii).getItemId().equalsIgnoreCase(mCartItemInfos.get(j).getItemId())) {
                                            isItemFound = true;
                                            int itemQuantity = mCartItemInfos.get(j).getQty();
                                            if (itemQuantity > 0) {
                                                if (itemQuantity > 0)
                                                    viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                                                else
                                                    viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                                                String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice())));
                                                viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));
                                                if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                                                    deliveryCharges = 10;
                                                } else {
                                                    deliveryCharges = 0;
                                                }
                                                ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());
                                                tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                                                tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());
                                            }else
                                            {
                                                //gole changed this
                                                isItemFound = false;
                                            }
                                            break;
                                        } else {
                                            isItemFound = false;
                                        }
                                    }
                                }


                                if (!isItemFound) {
                                    int itemQuantity = 0;
                                    viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                                    String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice())));
                                    viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));
                                    if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                                        deliveryCharges = 10;
                                    } else {
                                        deliveryCharges = 0;
                                    }
                                    ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());
                                    tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                                    tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());
                                }


                              //  System.out.println("ImageUrl::" + Constant.BASE_URL_Images1 + moqPojoArrayList.get(ii).getItemNumber() + ".jpg");
                                System.out.println("MRP::" + moqPojoArrayList.get(ii).getPrice());
                                if (!TextUtils.isNullOrEmpty(moqPojoArrayList.get(ii).getLogoUrl()))
                                    Picasso.with(context).load(Constant.BASE_URL_Images1 + itemListArrayList.get(i).getItemNumber() + ".jpg").resize(ivWidth, ivHeight).into(viewHolder.ivItemImage);
                                //  viewHolder.tvMoqMrp.setText("MOQ: " + itemListArrayList.get(i).getMinOrderQty() + " | MRP: " + new DecimalFormat("##.##").format((Double.parseDouble(itemListArrayList.get(i).getPrice()))));
                                viewHolder.tvSelectUnitPrice.setText("| MRP: " +new DecimalFormat("##.##").format((Double.parseDouble(moqPojoArrayList.get(ii).getPrice()))));

                                //   viewHolder.tvMoqMrp.setText("MOQ: " + itemListArrayList.get(i).getMinOrderQty() + " | MRP: " + itemListArrayList.get(i).getPrice());
                                String text = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format(Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice())) + "</font>" + " | Margins: " + (new DecimalFormat("##.##").format((((Double.parseDouble(moqPojoArrayList.get(ii).getPrice()) - Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice())) / Double.parseDouble(moqPojoArrayList.get(ii).getPrice())) * 100))) + "%";
                                viewHolder.tvRateMargins.setText(Html.fromHtml(text));

                                viewHolder.ivMinusImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        int itemQuantity = Integer.parseInt(viewHolder.tvselectedItemQuantity.getText().toString());


                                        if (itemQuantity == 0) {

                                            //   ((HomeActivity) context).removeItemfromCart(itemListArrayList.get(i).getItemId());

                                        }


                                        if (itemQuantity > 0) {
                                            itemQuantity -= Integer.parseInt(moqPojoArrayList.get(ii).getMinOrderQty());


                                            viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);


                                            String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice())));
                                            viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));

                                            if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                                                deliveryCharges = 10;
                                            } else {
                                                deliveryCharges = 0;
                                            }

                                            String status = ((HomeActivity) context).addItemInCartItemArrayList(moqPojoArrayList.get(ii).getItemId(), itemQuantity, Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice()), moqPojoArrayList.get(ii), deliveryCharges, Double.parseDouble(moqPojoArrayList.get(ii).getDreamPoint()), moqPojoArrayList.get(ii).getWarehouseId(), moqPojoArrayList.get(ii).getCompanyId(), Double.parseDouble(moqPojoArrayList.get(ii).getPrice()));
                                            tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                                            tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());
                                            tvTotalDp.setText(("Dp : " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalDpPoints())));
                                            ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());

                                            Log.i(Constant.Tag, status);

                                            String jsonString = Utility.getStringSharedPreferences(context, "ItemQJson");
                                            try {
                                                JSONObject jsonObject;
//                        JSONObject jsonObject = new JSONObject(jsonString.toString());

                                                if (jsonString.isEmpty()) {
                                                    jsonObject = new JSONObject();

                                                } else {
                                                    jsonObject = new JSONObject(jsonString.toString());

                                                }

                                                //jsonObject.put(""+getItemId(i), viewHolder.tvselectedItemQuantity.getText().toString());

                                                jsonObject.put("" + moqPojoArrayList.get(ii).getItemId(), viewHolder.tvselectedItemQuantity.getText().toString());


                                                Utility.setStringSharedPreference(context, "ItemQJson", jsonObject.toString());

                                            } catch (JSONException e) {
                                                Toast.makeText(context, "Json error" + e.toString(), Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }


                                            if (itemQuantity == 0) {

                                                //   ((HomeActivity) context).removeItemfromCart(itemListArrayList.get(i).getItemId());

                                            }


                                            //    Toast.makeText(context, "Json Q "+Utility.getStringSharedPreferences(context, "ItemQJson"), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                                viewHolder.ivPlusImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        int itemQuantity = Integer.parseInt(viewHolder.tvselectedItemQuantity.getText().toString());
                                        itemQuantity += Integer.parseInt(moqPojoArrayList.get(ii).getMinOrderQty());
                                        viewHolder.tvselectedItemQuantity.setText("" + itemQuantity);
                                        String price = "<font color=#FF4500>&#8377; " + new DecimalFormat("##.##").format((itemQuantity * Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice())));
                                        viewHolder.tvSelectedItemPrice.setText(Html.fromHtml(price));

                                        if (((HomeActivity) context).getCartItem().getTotalPrice() < 2000) {
                                            deliveryCharges = 10;
                                        } else {
                                            deliveryCharges = 0;
                                        }

                                        String status = ((HomeActivity) context).addItemInCartItemArrayList(moqPojoArrayList.get(ii).getItemId(), itemQuantity, Double.parseDouble(moqPojoArrayList.get(ii).getUnitPrice()), moqPojoArrayList.get(ii), deliveryCharges, Double.parseDouble(moqPojoArrayList.get(ii).getDreamPoint()), moqPojoArrayList.get(ii).getWarehouseId(), moqPojoArrayList.get(ii).getCompanyId(), Double.parseDouble(moqPojoArrayList.get(ii).getPrice()));

                                        tvTotalItemPrice.setText("Total: " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalPrice()));
                                        tvTotalItemQty.setText("" + (int) ((HomeActivity) context).getCartItem().getTotalQuantity());

                                        tvTotalDp.setText(("Dp : " + new DecimalFormat("##.##").format(((HomeActivity) context).getCartItem().getTotalDpPoints())));
                                        ShowPopup((int) ((HomeActivity) context).getCartItem().getTotalPrice());
                                        Log.i(Constant.Tag, status);


                                        String jsonString = Utility.getStringSharedPreferences(context, "ItemQJson");
                                        try {
                                            JSONObject jsonObject;
//                        JSONObject jsonObject = new JSONObject(jsonString.toString());

                                            if (jsonString.isEmpty()) {
                                                jsonObject = new JSONObject();

                                            } else {
                                                jsonObject = new JSONObject(jsonString.toString());

                                            }

                                            //jsonObject.put(""+getItemId(i), viewHolder.tvselectedItemQuantity.getText().toString());

                                            jsonObject.put("" + moqPojoArrayList.get(ii).getItemId(), viewHolder.tvselectedItemQuantity.getText().toString());


                                            Utility.setStringSharedPreference(context, "ItemQJson", jsonObject.toString());

                                        } catch (JSONException e) {
                                            Toast.makeText(context, "Json error" + e.toString(), Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }

                                        //    Toast.makeText(context, "Json Q "+Utility.getStringSharedPreferences(context, "ItemQJson"), Toast.LENGTH_SHORT).show();


                                    }
                                });


                            }

                        } catch (IndexOutOfBoundsException e) {
                            //  e.printStackTrace();
                            Log.e("Crash", "crash");

                            context.startActivity(new Intent(context, HomeActivity.class));
                            System.out.println("Run:::"+e.toString());
                        }



                        customAlertDialog.dismiss();
                    }
                });


                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        customAlertDialog.dismiss();


                    }
                });


                //  AlertDialog customAlertDialog = builder.create();
                customAlertDialog.show();


            }
        });



    }
    private void ShowPopup(int totalAmount) {
        boolean status1 = ((HomeActivity) context).showPopup(totalAmount);
        if(status1){
            show_popup.setVisibility(View.VISIBLE);
            show_popup.setText(R.string.left_free_dial);
        }else{
            show_popup.setVisibility(View.GONE);
        }

    }
    @Override
    public int getItemCount() {
        return itemListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivItemImage;
        private TextView tvItemName;
        private TextView tvMoqMrp;
        private TextView tvRateMargins;
        private TextView tvSelectedItemPrice;
        private TextView tvselectedItemQuantity;
        private ImageView ivMinusImage;
        private ImageView ivPlusImage;
        private TextView tvItemHindiName;
        private TextView tvDpPoint;
        private TextView tvSelectUnitPrice;
        private Spinner spSelectItemName;
        private ImageView ivOfferImage;
        private TextView tvSingleMoq;
        private TextView tvMoqPrice;
        private ImageView ivSpecialOfer;
        public ViewHolder(View view) {
            super(view);
            ivItemImage = (ImageView) view.findViewById(R.id.item_row_item_logo_iv);
            tvItemName = (TextView) view.findViewById(R.id.itemlist_item_name);
            tvMoqMrp = (TextView) view.findViewById(R.id.moq_mrp_tv);
            tvRateMargins = (TextView) view.findViewById(R.id.cost_margins_tv);
            tvSelectedItemPrice = (TextView) view.findViewById(R.id.item_list_row_total_cost_tv);
            tvselectedItemQuantity = (TextView) view.findViewById(R.id.item_row_quantity_tv);
            ivMinusImage = (ImageView) view.findViewById(R.id.item_row_minus_icon);
            ivPlusImage = (ImageView) view.findViewById(R.id.item_row_plus_icon);
            ivSpecialOfer = (ImageView) view.findViewById(R.id.special_offer);


            tvItemHindiName = (TextView) view.findViewById(R.id.itemlist_item_name_hindi);

            tvDpPoint = (TextView) view.findViewById(R.id.dp_point);

            spSelectItemName=(Spinner)view.findViewById(R.id.spinner_itemname);
            tvSelectUnitPrice = (TextView) view.findViewById(R.id.unit_price);
            ivOfferImage = (ImageView) view.findViewById(R.id.offer_image);
            tvSingleMoq = (TextView) view.findViewById(R.id.single_moq);
            tvMoqPrice = (TextView) view.findViewById(R.id.moq_price);
        }
    }

    public class GetCallItemOffer extends AsyncTask<String, Void, JSONObject> {
        Dialog mDialog;
        AnimationDrawable animation;

        @Override
        protected void onPreExecute() {
            mDialog = new Dialog(context);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.progress_dialog);
            ((TextView) mDialog.findViewById(R.id.progressText)).setText("Logging in please wait...");
            ImageView la = (ImageView) mDialog.findViewById(R.id.gridprogressBar);
            la.setBackgroundResource(R.drawable.custom_progress_dialog_animation);
            animation = (AnimationDrawable) la.getBackground();
            animation.start();
            mDialog.setCancelable(false);
            mDialog.show();
        }


        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonArrayFromUrl = null;
            String url=Constant.BASE_URL+"offer/GetOfferByItem?itemid="+params[0]+"&Companyid="+params[1];
            try {

                jsonArrayFromUrl = new HttpUrlConnectionJSONParser().getJsonObjectFromHttpUrlConnection(url, null, HttpUrlConnectionJSONParser.Http.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonArrayFromUrl;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (mDialog.isShowing()) {
                animation.stop();
                mDialog.dismiss();
            }
            if (jsonObject != null && jsonObject.length() > 0) {

                System.out.println("jsonArray::"+jsonObject);
                String offerMess;
                try {
                    String OfferType= jsonObject.getString("OfferType");
                    String NoOffreeQuantity= jsonObject.getString("NoOffreeQuantity");
                    String MinOrderQuantity= jsonObject.getString("MinOrderQuantity");
                    String itemname= jsonObject.getString("itemname");

                    if(OfferType.equalsIgnoreCase("WalletPoint"))
                    {
                        String FreeWalletPoint= jsonObject.getString("FreeWalletPoint");
                        offerMess= " <font color=#000000>You will get </font><font color=#FF6347>"+FreeWalletPoint+"</font><font color=#000000> wallet point free with </font><font color=#FF6347>"+MinOrderQuantity+"</font><font color=#000000> quantity of item </font><font color=#FF6347>"+itemname+"</font>";
                    }
                    else
                    {
                        String FreeItemName= jsonObject.getString("FreeItemName");
                        offerMess= " <font color=#000000>You will get </font><font color=#FF6347>"+NoOffreeQuantity+"</font> <font color=#000000> quantity free of item </font><font color=#FF6347>"+FreeItemName+"</font><font color=#000000> with </font></font><font color=#FF6347>"+MinOrderQuantity+"</font><font color=#000000> quantity of item </font><font color=#FF6347>"+itemname+"</font>";
                    }
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View dialogLayout = inflater.inflate(R.layout.offer_popup, null);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(dialogLayout);
                    customAlertDialog = builder.create();
                    TextView cancelBtn = (TextView) dialogLayout.findViewById(R.id.cancel);
                    TextView item_name = (TextView) dialogLayout.findViewById(R.id.item_name);
                    item_name.setText(Html.fromHtml(offerMess));
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customAlertDialog.dismiss();
                        }
                    });
                    customAlertDialog.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(context, "No offer", Toast.LENGTH_SHORT).show();
            }
            //  progressBar.setVisibility(View.GONE);
        }
    }

}
