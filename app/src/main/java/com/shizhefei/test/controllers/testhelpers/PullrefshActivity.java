/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.shizhefei.test.controllers.testhelpers;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCPullrefshHelper;
import com.shizhefei.test.models.datasource.BooksDataSource;
import com.shizhefei.test.models.enties.Book;
import com.shizhefei.test.view.adapters.BooksAdapter;
import com.shizhefei.view.mvc.demo.R;

/**
 * 测试下拉刷新组件，MVCPullrefshHelper
 * 
 * @author LuckyJayce
 *
 */
public class PullrefshActivity extends Activity {

	private MVCHelper<List<Book>> listViewHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pullrefsh);

		// 设置LoadView的factory，用于创建使用者自定义的加载失败，加载中，加载更多等布局,写法参照DeFaultLoadViewFactory
		// ListViewHelper.setLoadViewFactory(new LoadViewFactory());

		PullToRefreshListView refreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
		listViewHelper = new MVCPullrefshHelper<List<Book>>(refreshListView);

		// 设置数据源
		listViewHelper.setDataSource(new BooksDataSource());
		// 设置适配器
		listViewHelper.setAdapter(new BooksAdapter(this));

		// 加载数据
		listViewHelper.refresh();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 释放资源
		listViewHelper.destory();
	}

	public void onClickBack(View view) {
		finish();
	}

}
