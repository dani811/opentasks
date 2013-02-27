/*
 * TextFieldEditor.java
 *
 * Copyright (C) 2012 Marten Gajda <marten@dmfs.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.dmfs.tasks.widget;

import org.dmfs.tasks.R;
import org.dmfs.tasks.model.ContentSet;
import org.dmfs.tasks.model.FieldDescriptor;
import org.dmfs.tasks.model.adapters.FieldAdapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * A view that shows the string representation of an object.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class StringFieldView extends AbstractFieldView
{

	private FieldAdapter<?> mAdapter;
	private TextView mText;


	public StringFieldView(Context context)
	{
		super(context);
	}


	public StringFieldView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}


	public StringFieldView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}


	@Override
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		mText = (TextView) findViewById(R.id.text);
	}


	@Override
	public void setup(FieldDescriptor descriptor, Activity context)
	{
		super.setup(descriptor, context);
		mAdapter = (FieldAdapter<?>) descriptor.getFieldAdapter();
		mText.setHint(descriptor.getHint());
	}


	@Override
	public void onContentChanged(ContentSet contentSet, String key)
	{
		Object adapterValue = mAdapter.get(mValues);
		String adapterStringValue = adapterValue != null ? adapterValue.toString() : null;
		if (mValues != null && !TextUtils.isEmpty(adapterStringValue))
		{
			mText.setText(adapterStringValue);
		}
		else
		{
			setVisibility(View.GONE);
		}
		Integer customBackgroud = getCustomBackgroundColor();
		if (customBackgroud != null)
		{
			mText.setTextColor(AbstractFieldView.getTextColorFromBackground(customBackgroud));
		}
	}
}
