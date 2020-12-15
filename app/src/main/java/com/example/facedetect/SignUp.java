package com.example.facedetect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
   EditText  Fname,Lname,Phnno,Email,Password;
    Button btnReg;
    private ProgressDialog progressDialog;
    private TextView Login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if(StoreUserDetail.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        Fname = (EditText) findViewById(R.id.etFirstName);
        Lname = (EditText) findViewById(R.id.etLastName);
        Phnno = (EditText) findViewById(R.id.etphone);
        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPass);
        Login = (TextView) findViewById(R.id.btnLogin);


        btnReg = (Button) findViewById(R.id.btnReg);

       progressDialog =new ProgressDialog(this);

       btnReg.setOnClickListener(this);
        Login.setOnClickListener(this);

    }
    private void register(){
        final String fname=Fname.getText().toString().trim();
        final String lname=Lname.getText().toString().trim();
        final String phnno=Phnno.getText().toString().trim();
        final String email=Email.getText().toString().trim();
        final String password=Password.getText().toString().trim();

                progressDialog.setMessage("Registering User....");
                progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("fname",fname);//green wala php sanga same huna parxa
                params.put("lname",lname);
                params.put("phnno",phnno);
                params.put("email",email);
                params.put("password",password);
              return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if(v==btnReg)
        register();
        else if(v==Login)
            startActivity(new Intent(this,login.class));

    }
}
