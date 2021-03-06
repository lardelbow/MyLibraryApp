package com.skootch.mobapps.utilities.mylibraryapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AccountDisplayActivity extends ListActivity {
	
	private AccountDisplayAdapter mAdapter;
	private final int ACCOUNT_REQUEST_CODE=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new AccountDisplayAdapter(getApplicationContext());
		getListView().setFooterDividersEnabled(true);
		TextView footerView = null;
		LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		footerView=(TextView) inflater.inflate(R.layout.footer_view, null);
		getListView().addFooterView(footerView);
		footerView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent addAccount=new Intent(getApplicationContext(), AddAccountActivity.class);
				startActivityForResult(addAccount, ACCOUNT_REQUEST_CODE);
			}
		});
		getListView().setAdapter(mAdapter);
	}
	
	public void onActivityResult(int intRequestCode, int intResultCode, Intent data) {
		if (intRequestCode==ACCOUNT_REQUEST_CODE) {
			if (intResultCode==RESULT_OK) {
				AccountRecord record=new AccountRecord(data);
				mAdapter.addRecord(record.getContentValues());
			}
		}
	}
}
