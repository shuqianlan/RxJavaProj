package com.ilifesmart.rxjavaproj;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.Action;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity";

	@BindView(R.id.button)
	Button mButton;
	@BindView(R.id.button2)
	Button mButton2;

	private Observer<String> mObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.button)
	public void onMButtonClicked() {
		// 创造事件序列
//		Observable observable = Observable.create(new ObservableOnSubscribe() {
//			@Override
//			public void subscribe(ObservableEmitter emitter) throws Exception {
//				emitter.onNext("Hello 1");
//				emitter.onNext("Hello 2");
//				emitter.onNext("Hello 3");
//				emitter.onComplete();
//			}
//		});

		// 快捷创建事件序列
//		Observable observable1 = Observable.just("1", "2", "3"); // onNext("1"),onNext("2"),onNext("3"),onComplete()
//		Observable observable2 = Observable.fromArray("1", "2", "3"); // onNext("1"),onNext("2"),onNext("3"),onComplete()
		String[] strings = {"Hello-0", "Hello-1", "Hello-2", "Hello-3"};
		Observable.fromArray(strings)
						// 后台线程取数据，UI线程刷新
						.subscribeOn(Schedulers.io())              // 指定subscribe是在io线程
						.observeOn(AndroidSchedulers.mainThread()) // 指定回调在主线程
						.map(new Function<String, String>() {
							@Override
							public String apply(String s) throws Exception {
								return "Item-"+s;
							}
						})
						.throttleFirst(50, TimeUnit.MILLISECONDS) // 事件触发后的50ms内丢弃到来的事件
						.subscribe(new Observer<String>() {
							@Override
							public void onSubscribe(Disposable d) {
								Log.d(TAG, "onSubscribe: ");
							}

							@Override
							public void onNext(String s) {
								Log.d(TAG, "onNext: s " + s);
							}

							@Override
							public void onError(Throwable e) {
								e.printStackTrace();
							}

							@Override
							public void onComplete() {
								Log.d(TAG, "onComplete: ");
							}
						});



	}

	@OnClick(R.id.button2)
	public void onMButton2Clicked() {
	}
}
