package com.finastra.accountsview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.finastra.accountsview.data.AccountTransaction;
import com.finastra.accountsview.data.AccountTransactionAdapter;
import com.finastra.accountsview.data.AccountTransactionsWrapper;
import com.finastra.accountsview.data.CustomerAccount;
import com.finastra.accountsview.data.CustomerAccountAdapter;
import com.finastra.accountsview.data.CustomerAccountWrapper;
import com.finastra.accountsview.data.Product;
import com.finastra.accountsview.data.ProductAdapter;
import com.finastra.accountsview.ui.main.MainFragment;
import com.finastra.accountsview.web.AccountTransactionsWebService;
import com.finastra.accountsview.web.CustomerAccountWebService;
import com.finastra.accountsview.web.Http;
import com.finastra.accountsview.web.ProductWebservice;
import com.finastra.accountsview.web.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private CustomerAccountAdapter nAdapter;
    private AccountTransactionAdapter oAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Recode incoming Network Hack here
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        initProductData();
//        initCustomerAccounts();
        initAccountTransactions();

    }

    private String sampleData = "[{\"productId\":\"300\",\"productName\":\"Business CDs < $100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"301\",\"productName\":\"Public Funds CD < $100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"302\",\"productName\":\"Business CDs > $100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"303\",\"productName\":\"Public Funds CD > $100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"314\",\"productName\":\"Brokered CD Bus<$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"315\",\"productName\":\"Brokered CD Bus>$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"318\",\"productName\":\"Bus CD>100M-Floating\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"319\",\"productName\":\"Bankruptcy CD <$100 M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"320\",\"productName\":\"Bankruptcy CD > $100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"325\",\"productName\":\"Personal Cert of Dep <$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"326\",\"productName\":\"IRA Cert of Dep <$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"327\",\"productName\":\"Personal Cert of Dep >$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"328\",\"productName\":\"IRA Cert of Dep >$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"336\",\"productName\":\"Brokered CD Per<$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"337\",\"productName\":\"Brokered CD Per>$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"340\",\"productName\":\"Per CD>100M-Floating\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"350\",\"productName\":\"Personal CD Flex <$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"351\",\"productName\":\"Personal CD Flex >$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"352\",\"productName\":\"Business CD Flex <$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"353\",\"productName\":\"Business CD Flex >$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"354\",\"productName\":\"Step-up CD Bus<$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"355\",\"productName\":\"Step-up CD Bus>$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"356\",\"productName\":\"Step-up CD Per<$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"357\",\"productName\":\"Step-up CD Per>$100M\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"399\",\"productName\":\"> 100,000 with new GL\",\"productAccountType\":\"CertficateOfDeposit\",\"productAccountSubType\":\"COD\"},{\"productId\":\"100\",\"productName\":\"Sacramento Account (Analyzed)\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"101\",\"productName\":\"Sacramento Copper Account\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"102\",\"productName\":\"DEMAND DEPOSIT PUBLIC ANALYSIS\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"103\",\"productName\":\"Demand Deposit Bankruptcy\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"104\",\"productName\":\"DEMAND DEPOSIT PUBLIC\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"105\",\"productName\":\"Direct Deposit Personal\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"124\",\"productName\":\"Stmts Mid Month 15th\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"125\",\"productName\":\"Sacramento Silver Acct-Analyze\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"126\",\"productName\":\"Sacramento Silver Account\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"127\",\"productName\":\"Sacramento Silver Acct-Public\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"128\",\"productName\":\"Sacramento Gold Acct-Waived SC\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"129\",\"productName\":\"Sacramento Gold Account\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"130\",\"productName\":\"Sacramento Gold Acct-Public\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"131\",\"productName\":\"Sacramento Gold Link-Non FDIC\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"132\",\"productName\":\"Attorney-Client Trust\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"133\",\"productName\":\"BanControl Accounts\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"134\",\"productName\":\"Related Account MMDA\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"135\",\"productName\":\"Now Accounts - Bankruptcy\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"136\",\"productName\":\"MMDA Account - Bankruptcy\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"137\",\"productName\":\"Sacramento Gold Acct-Analyzed\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"138\",\"productName\":\"Contractor Escrow MMA\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"150\",\"productName\":\"Golden State Checking-Analyzed\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"151\",\"productName\":\"Golden State Checking\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"152\",\"productName\":\"Golden Poppy Now-Ognizr/Foundr\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"153\",\"productName\":\"Golden State Checking/Employee\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"154\",\"productName\":\"Gldn Poppy Now-Ognizr/Fndr-Ana\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"155\",\"productName\":\"VIP BANKING\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"175\",\"productName\":\"Golden Poppy Now -Analysis\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"176\",\"productName\":\"Golden Poppy Now\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"177\",\"productName\":\"Golden Bear MMDA -Waived SC\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"178\",\"productName\":\"Golden Bear MMDA\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"179\",\"productName\":\"Golden Bridge Account-Non FDIC\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"180\",\"productName\":\"Prime Fund\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"181\",\"productName\":\"Federal Fund\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"182\",\"productName\":\"Tax Exempt CA Fund\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"183\",\"productName\":\"Golden Bear MMA-Analyzed\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"186\",\"productName\":\"Test for OD\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"DDA\"},{\"productId\":\"141\",\"productName\":\"Test for Karen\",\"productAccountType\":\"Checking\",\"productAccountSubType\":\"MM\"},{\"productId\":\"200\",\"productName\":\"Business Savings\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"201\",\"productName\":\"Public Savings\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"202\",\"productName\":\"Bankruptcy Savings\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"203\",\"productName\":\"Cycle Code Mid Month (15)\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"250\",\"productName\":\"Personal Savings\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"251\",\"productName\":\"IRA Savings\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"252\",\"productName\":\"Overdraft Savings Account\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"},{\"productId\":\"271\",\"productName\":\"Copy Class 251 UDF\",\"productAccountType\":\"Savings\",\"productAccountSubType\":\"SAV\"}]";

    private void initProductData() {

        //Create Header/Authorization
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        final String accessToken = Http.getAccessToken(Utils.clientId, Utils.clientSecret);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", accessToken).build();
                return chain.proceed(request);
            }
        });

        //Create Webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductWebservice productWebservice = retrofit.create(ProductWebservice.class);

        final Gson[] gson = {new Gson()};

        //account-products
        Call<List<Product>> call = productWebservice.getList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("Product", response.message()+":"+response.body());
                generateProductList(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Product", "Failed: "+t.getMessage());
            }
        });
    }

    private void initCustomerAccounts () {
        //Create Header/Authorization
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        final String accessToken = Http.getAccessToken(Utils.clientId, Utils.clientSecret);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", accessToken).build();
                return chain.proceed(request);
            }
        });

        //Create Webservice (Retrofit)

        // Plugin the headers created (httpClient) to Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.baseUrlEQ)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create actual Webservice

        CustomerAccountWebService customerAccountWebService = retrofit.create(CustomerAccountWebService.class);

        //customer-accounts
        Call<CustomerAccountWrapper> call = customerAccountWebService.getCustomerAccounts();
        call.enqueue(new Callback<CustomerAccountWrapper>() {
            @Override
            public void onResponse(Call<CustomerAccountWrapper> call, Response<CustomerAccountWrapper> response) {
                Log.d("Customer Accounts", response.message()+":"+response.body().getCustomerAccounts());
                generateCustomerAccountList(response.body().getCustomerAccounts());

            }

            @Override
            public void onFailure(Call<CustomerAccountWrapper> call, Throwable t) {
                Log.d("Customer Accounts", "Failed: "+t.getMessage());
            }
        });

    }

    private void initAccountTransactions () {
        //Create Header/Authorization
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        final String accessToken = Http.getAccessToken(Utils.clientId, Utils.clientSecret);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", accessToken).build();
                return chain.proceed(request);
            }
        });

        //Create Webservice (Retrofit)

        // Plugin the headers created (httpClient) to Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.baseUrlEQ)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create actual Webservice

        AccountTransactionsWebService accountTransactionsWebService = retrofit.create(AccountTransactionsWebService.class);

        //customer-accounts
        Call<AccountTransactionsWrapper> call = accountTransactionsWebService.getAccountTransactions();
        call.enqueue(new Callback<AccountTransactionsWrapper>() {
            @Override
            public void onResponse(Call<AccountTransactionsWrapper> call, Response<AccountTransactionsWrapper> response) {
                Log.d("Account Transactions", response.message()+":"+response.body().getAccountTransactions());
                generateAccountTransactionList(response.body().getAccountTransactions());

            }

            @Override
            public void onFailure(Call<AccountTransactionsWrapper> call, Throwable t) {
                Log.d("Customer Accounts", "Failed: "+t.getMessage());
            }
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateProductList(List<Product> updatedProductsList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ProductAdapter(updatedProductsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateCustomerAccountList(List<CustomerAccount> updatedCustomerAccount) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        nAdapter = new CustomerAccountAdapter(updatedCustomerAccount);
        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(nLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nAdapter);
        nAdapter.notifyDataSetChanged();
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateAccountTransactionList(List<AccountTransaction> updatedAccountTransaction) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        oAdapter = new AccountTransactionAdapter(updatedAccountTransaction);
        RecyclerView.LayoutManager oLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(oLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(oAdapter);
        oAdapter.notifyDataSetChanged();
    }

}
