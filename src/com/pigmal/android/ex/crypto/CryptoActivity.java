package com.pigmal.android.ex.crypto;

import com.pigmal.android.ex.crypt.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CryptoActivity extends Activity implements OnClickListener {
	private Button encryptButton;
	private Button decryptButton;
	private EditText plainText;
	private EditText encryptedText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        plainText = (EditText)findViewById(R.id.plainText);
        encryptedText = (EditText)findViewById(R.id.encryptedText);
        encryptButton = (Button)findViewById(R.id.encryptButton);
        encryptButton.setOnClickListener(this);
        decryptButton = (Button)findViewById(R.id.decryptButton);
        decryptButton.setOnClickListener(this);
    }

    static String CRYPT_SEED = "hoge";
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.encryptButton:
			Editable plain = plainText.getText();
			if (plain != null) {
				try {
					encryptedText.setText(Crypto.encrypt(CRYPT_SEED, plain.toString()));
					plainText.setText("");
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(plainText.getWindowToken(), 0);
				} catch (Exception e) {
					Toast.makeText(CryptoActivity.this, "Error", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			} else {
				Toast.makeText(CryptoActivity.this, "Input plain data", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.decryptButton:
			Editable encrypted = encryptedText.getText();
			if (encrypted != null) {
				try {
					plainText.setText(Crypto.decrypt(CRYPT_SEED, encrypted.toString()));
					encryptedText.setText("");
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(encryptedText.getWindowToken(), 0);

				} catch (Exception e) {
					Toast.makeText(CryptoActivity.this, "Error", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			} else {
				Toast.makeText(CryptoActivity.this, "Input encrypted data", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		
	}
}