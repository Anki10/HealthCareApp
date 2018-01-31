package com.winklix.indu.healthcareapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.winklix.indu.healthcareapp.R;
import com.winklix.indu.healthcareapp.activities.PaymentActivity;
import com.winklix.indu.healthcareapp.modals.Doctor_Modal;

import java.util.List;

/**
 * Created by harsh on 02/12/2017.
 */

public class Doctor_Adapter  extends RecyclerView.Adapter<Doctor_Adapter.MyHolder> {

    private List<Doctor_Modal> product_models;
    private Context mcontext;


    public Doctor_Adapter(Context montext, List<Doctor_Modal> product_models) {

        this.mcontext = montext;
        this.product_models = product_models;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.doctor_list_data, parent, false);

        return new MyHolder(myview, mcontext, product_models);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final Doctor_Modal pro_model = product_models.get(position);

        holder.dr_name_et.setText(pro_model.getDr_name());
        holder.dr_email_et.setText(pro_model.getDr_email());
        holder.dr_contact_et.setText(pro_model.getDr_phone());
        holder.dr_qualification_et.setText(pro_model.getDr_qualification());
        holder.dr_specilization_et.setText(pro_model.getDr_specialization());
        holder.dr_experience_et.setText(pro_model.getDr_experience());
        holder.dr_avilabel_et.setText(pro_model.getDr_available());

    }

    @Override
    public int getItemCount() {
        return product_models.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dr_name_et,dr_email_et,dr_contact_et,dr_qualification_et, dr_specilization_et,dr_experience_et,dr_avilabel_et;
        Context mcontext;
        List<Doctor_Modal> userList;
        Button book_btn;

        public MyHolder(View itemView, Context mcontext, List<Doctor_Modal> userList) {
            super(itemView);

            this.mcontext = mcontext;
            this.userList = userList;
            dr_name_et = (TextView) itemView.findViewById(R.id.dr_name_et);
            dr_email_et = (TextView) itemView.findViewById(R.id.dr_email_et);
            dr_contact_et = (TextView) itemView.findViewById(R.id.dr_contact_et);
            dr_qualification_et = (TextView) itemView.findViewById(R.id.dr_qualification_et);
            dr_specilization_et = (TextView) itemView.findViewById(R.id.dr_specilization_et);
            dr_experience_et = (TextView) itemView.findViewById(R.id.dr_experience_et);
            dr_avilabel_et = (TextView) itemView.findViewById(R.id.dr_avilabel_et);

            book_btn = (Button)itemView.findViewById(R.id.book_btn);
            book_btn.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.book_btn)
        {
            showPopup();
        }

    }

    private void showPopup() {

        final Dialog dialog = new Dialog(mcontext);
        dialog.setContentView(R.layout.payment_popup);
        dialog.setTitle("Our Services Availabel 24*7");

        Button online_pay_btn = (Button) dialog.findViewById(R.id.online_pay_btn);

        online_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcontext.startActivity(new Intent(mcontext, PaymentActivity.class));
                dialog.dismiss();
            }
        });



        Button cash_pay_btn = (Button) dialog.findViewById(R.id.cash_pay_btn);

        cash_pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "Thanks", Toast.LENGTH_SHORT).show();
               // mcontext.startActivity(new Intent(mcontext, ServiceLocation_Activity.class));
                dialog.dismiss();

            }
        });

        dialog.show();

        }

    }
}
