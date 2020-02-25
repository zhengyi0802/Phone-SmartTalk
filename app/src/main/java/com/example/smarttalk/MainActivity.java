package com.example.smarttalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarttalk.packages.PInfo;
import com.example.smarttalk.packages.Packages;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "SmartTalk";

    private SpeechRecognizer mSpeechRecognizer;
    private Button mButtonStart;
    private Button mButtonExecute;
    private TextView mOutEditText;
    private ListView mConversationView;
    private Packages mPackages;
    private ArrayList<PInfo> mApps;

    /**
     * Array adapter for the conversation thread
     */
    private ArrayAdapter<String> mConversationArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConversationView = (ListView) findViewById(R.id.speech_list);
        mButtonStart = (Button) findViewById(R.id.button_start);
        mButtonExecute = (Button) findViewById(R.id.button_execute);
        mOutEditText = (TextView) findViewById(R.id.edit_text_out);
        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        mConversationView.setAdapter(mConversationArrayAdapter);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConversationArrayAdapter.clear();
                //get the recognize intent
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //Specify the calling package to identify your application
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getClass().getPackage().getName());
                //Given an hint to the recognizer about what the user is going to say
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //specify the max number of results
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
                //User of SpeechRecognizer to "send" the intent.
                mSpeechRecognizer.startListening(intent);
                Log.i(TAG,"Intent sent");
                //Toast.makeText(R.string.start_speek,Toast.LENGTH_SHORT).show();
                Toast.makeText(v.getContext(), R.string.start_speek, Toast.LENGTH_SHORT).show();
            }
        });

        mButtonExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.edit_text_out);
                String message = textView.getText().toString();
                sendMessage(message);
                return;
            }
        });

        mConversationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListView mListView = (ListView) parent;
                    String message = mListView.getItemAtPosition(position).toString();
                    mOutEditText.setText(message);
                    sendMessage(message);
                    return;
            }
        });

        mPackages = new Packages(this);
        mApps = mPackages.getPackages();

        initScanner();

        return;
    }

    @Override
    protected void onStart() {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizer.setRecognitionListener(new listener());
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initScanner() {
        new IntentIntegrator(this)
                // 自定义Activity，重点是这行----------------------------
                .setCaptureActivity(CustomCaptureActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码
    }

    private void sendMessage(String msg) {
        Log.d(TAG, "sendMessage =" + msg);

        final int max = mApps.size();
        for (int i = 0; i < max; i++) {
            PInfo p = mApps.get(i);
            Log.d(TAG, "compare package name = " + p.getAppname() + " message = " + msg);
            if(msg.contains(p.getAppname()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              )) {
                execute(p);
            }
        }
        return;
    }

    private void execute(PInfo p) {
        Log.d(TAG, "execute =" + p.getAppname());

        String packagename = p.getPName();
        Intent intent = getPackageManager().getLaunchIntentForPackage(packagename);
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return;
    }

    /*
     * The Recognitionlistener for the SpeechRecognizer.
     */
    class listener implements RecognitionListener {

        public void onReadyForSpeech(Bundle params)	{
            Log.d(TAG, "onReadyForSpeech");
        }

        public void onBeginningOfSpeech(){
            Log.d(TAG, "onBeginningOfSpeech");
        }

        public void onRmsChanged(float rmsdB){
            Log.d(TAG, "onRmsChanged");
        }

        public void onBufferReceived(byte[] buffer)	{
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech()	{
            Log.d(TAG, "onEndofSpeech");
        }

        public void onError(int error)	{
            Log.d(TAG,  "error " +  error);
        }

        public void onResults(Bundle results) {

            Log.d(TAG, "onResults " + results);
            // Fill the list view with the strings the recognizer thought it could have heard, there should be 5, based on the call
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            mConversationArrayAdapter.addAll(matches);
            //display results.
            for (int i = 0; i < matches.size(); i++) {
                Log.d(TAG, "result " + matches.get(i));
            }
            String str = matches.get(0);
            mOutEditText.setText(str);
        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults");
        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }

}
